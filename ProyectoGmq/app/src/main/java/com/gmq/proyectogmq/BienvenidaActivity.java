package com.gmq.proyectogmq;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.dbConnection;

import java.util.concurrent.Executor;

public class BienvenidaActivity extends AppCompatActivity{


    String token=null;
    dbConnection conection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        conection = new dbConnection(getApplicationContext(), Apis.TABLA_EMPLEADO, null, 1);
        SQLiteDatabase db = conection.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Apis.TABLA_EMPLEADO, null);
        while (cursor.moveToNext()) {
            token = cursor.getString(8);
        }
        if (token == null) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent dos = new Intent(BienvenidaActivity.this, LoginActivity.class);
                    startActivity(dos);
                }
            }, 2000);



        } else {

            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate())//Usamos un switch para poner diferentes posibilidades
            {
                case BiometricManager.BIOMETRIC_SUCCESS:
                    Toast.makeText(BienvenidaActivity.this, "Puedes usar el sensor de huella dactilar para iniciar sesión", Toast.LENGTH_LONG).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(BienvenidaActivity.this, "El dispositivo no tiene un sensor de huellas dactilares.", Toast.LENGTH_LONG).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Toast.makeText(BienvenidaActivity.this, "El sensor biométrico no está actualmente disponible.a", Toast.LENGTH_LONG).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(BienvenidaActivity.this, "Tu dispositivo no tiene ninguna huella dactilar guardada, por favor, comprueba tus ajustes de seguridad", Toast.LENGTH_LONG).show();
                    break;
            }

            //Ya creamos la comprobación de que el dispositivo tenga o no sensor de huellas ahora haremos
            //El cuadro de diálogo de la biometría.
            //Primero haremos un ejecutor.
            Executor executor = ContextCompat.getMainExecutor(this);
            //Ahora haremos un callback del prompt.
            //Eso nos dirá si podemos logearnos o no.
            BiometricPrompt biometricPrompt = new BiometricPrompt(BienvenidaActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override//Este método es llamado mientras ocurra un error en la autenticación.
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override//Este método es llamado cuando el inicio de sesión sea el correcto.
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent dos = new Intent(BienvenidaActivity.this, MainActivity.class);
                            startActivity(dos);
                        }
                    }, 2000);
                    Toast.makeText(getApplicationContext(), "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                }

                @Override//Este método es llamado cuando el inicio de sesión falla.
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                }
            });

            //Ahora creamos nuestro diálogo de la Biometría.
            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Inicio sesión")
                    .setDescription("Usa tu sensor de huella dactilar para iniciar sesión")
                    .setNegativeButtonText("Cancelar")
                    .build();



            biometricPrompt.authenticate(promptInfo);
        }


    }


}


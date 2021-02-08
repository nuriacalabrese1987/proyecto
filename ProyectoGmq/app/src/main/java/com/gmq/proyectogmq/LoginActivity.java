package com.gmq.proyectogmq;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.dbConnection;

import java.io.Serializable;
import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity implements Serializable {
     Button button;
     EditText pass=findViewById(R.id.password);;
     EditText telefono = findViewById(R.id.telefono);;
    dbConnection conection;
    String token=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.button);


        conection = new dbConnection(getApplicationContext(), Apis.TABLA_EMPLEADO, null, 1);
        SQLiteDatabase db = conection.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Apis.TABLA_EMPLEADO, null);
        while (cursor.moveToNext()) {
            token = cursor.getString(8);
        }
        if (token == null) {

           solicitarToken();

        } else {

            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate())//Usamos un switch para poner diferentes posibilidades
            {
                case BiometricManager.BIOMETRIC_SUCCESS:
                    Toast.makeText(LoginActivity.this, "Puedes usar el sensor de huella dactilar para iniciar sesión", Toast.LENGTH_LONG).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(LoginActivity.this, "El dispositivo no tiene un sensor de huellas dactilares.", Toast.LENGTH_LONG).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Toast.makeText(LoginActivity.this, "El sensor biométrico no está actualmente disponible.a", Toast.LENGTH_LONG).show();
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(LoginActivity.this, "Tu dispositivo no tiene ninguna huella dactilar guardada, por favor, comprueba tus ajustes de seguridad", Toast.LENGTH_LONG).show();
                    break;
            }

        }

        //Ya creamos la comprobación de que el dispositivo tenga o no sensor de huellas ahora haremos
        //El cuadro de diálogo de la biometría.
        //Primero haremos un ejecutor.
        Executor executor = ContextCompat.getMainExecutor(this);
        //Ahora haremos un callback del prompt.
        //Eso nos dirá si podemos logearnos o no.
        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
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
                        Intent dos = new Intent(LoginActivity.this, MainActivity.class);
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


    //-------------------------------- PASAMOS A SOLICITAR EL TOKEN Y LEERLO --------------------------------
        private void solicitarToken() {
            String mensaje="Introduzca su telefono";
            pass.setVisibility(View.INVISIBLE);
            telefono.setText(mensaje);

            comprobarPermisosSms();
            requestSMSPermission();
            new OTP_Receiver().setEditText(pass);

         }

         //Comprueba los permisos
    private void comprobarPermisosSms() {
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
    }
    //Muestra el cuadro de dialogo para que los aceptes en el movil
    private void requestSMSPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    /*public void token(View view){
        Intent token = new Intent(LoginActivity.this, TokenActivity.class );
        startActivity(token);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void token(View vista){
        pass=findViewById(R.id.password);
        pass.setVisibility(View.INVISIBLE);
    }*/
}
package com.gmq.proyectogmq;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;

import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.dbConnection;

import java.io.Serializable;

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
            token = cursor.getString(0);
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
    }

        private void solicitarToken() {
            String mensaje="Introduzca su telefono";
            pass.setVisibility(View.INVISIBLE);
            telefono.setText(mensaje);

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
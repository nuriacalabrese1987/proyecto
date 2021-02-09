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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gmq.proyectogmq.model.Empleados;
import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.EmpleadosService;
import com.gmq.proyectogmq.util.RetrofitService;
import com.gmq.proyectogmq.util.dbConnection;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.gmq.proyectogmq.util.Apis.URL_EMPLEADOS;
import static com.gmq.proyectogmq.util.Apis.llamada;
import static com.gmq.proyectogmq.util.RetrofitService.getCliente;

public class LoginActivity extends AppCompatActivity implements Serializable {
     Button button;
     EditText pass;
     EditText telefono;
     TextView textView2;

    String numTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.button);
        pass = findViewById(R.id.password);
        telefono = findViewById(R.id.telefono);
        textView2 = findViewById(R.id.textView2);



    }


    //-------------------------------- PASAMOS A SOLICITAR EL TOKEN Y LEERLO --------------------------------
        private void solicitarToken() {

            pass.setVisibility(View.INVISIBLE);
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

    public void llamarApi (View view){

        numTel=telefono.getText().toString();

        Call<List<Empleados>> call = llamada().getEmpleado(numTel);

        call.enqueue(new Callback<List<Empleados>>() {

            @Override
            public void onResponse(Call<List<Empleados>> call, Response<List<Empleados>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Error de llamada2", Toast.LENGTH_LONG).show();
                    return;
                }
                Empleados empl=new Empleados();
                List<Empleados> userList = response.body();
                for (Empleados users : userList) {


                    empl.setId_empleado(users.getId_empleado());
                    empl.setNombre(users.getNombre());
                    empl.setApellidos(users.getApellidos());
                    empl.setDireccion(users.getDireccion());
                    empl.setTelefono(users.getTelefono());
                    empl.setN_departamento(users.getN_departamento());
                    empl.setN_centro(users.getN_centro());
                    empl.setUrl_storage(users.getUrl_storage());
                    empl.setToken(users.getToken());

                }

            }



            @Override
            public void onFailure(Call<List<Empleados>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de llamada1", Toast.LENGTH_LONG).show();
            }
        });


    }


}

    /*public void token(View view){
        Intent token = new Intent(LoginActivity.this, TokenActivity.class );
        startActivity(token);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }*/


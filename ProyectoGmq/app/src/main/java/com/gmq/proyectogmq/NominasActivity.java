package com.gmq.proyectogmq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gmq.proyectogmq.model.Nominas;
import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.NominasService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NominasActivity extends AppCompatActivity {

    EditText idEmpleado;
    Button botonNomina;
    TextView jsnTxt;
    static int numBotones = 0;
    LinearLayout botonera;
    List<Nominas> listaNom = new ArrayList<>();
    List<String> Nomina;
    NominasService service;
    //Propiedades de los botones
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominas);

        idEmpleado = findViewById(R.id.idEmpleado);
        botonNomina = findViewById(R.id.verNomina);
        jsnTxt = findViewById(R.id.jsonText);
        botonera = findViewById(R.id.Botonera);




        botonNomina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;

                if (idEmpleado.getText().toString().isEmpty()) {
                    Toast.makeText(NominasActivity.this, "Espabila", Toast.LENGTH_SHORT).show();
                } else {
                    id = Integer.parseInt(idEmpleado.getText().toString());
                    getNominas(id);
                }

            }
        });
    }

    public void getNominas(int id) {
        System.out.println(id);
        service = Apis.getNominas();
        Call<List<Nominas>> call = service.getNominas(id);
        call.enqueue(new Callback<List<Nominas>>() {
            @Override
            public void onResponse(Call<List<Nominas>> call, Response<List<Nominas>> response) {
                listaNom = response.body();
                Nominas nomina = new Nominas();
                //1System.out.println(listaNom);
                for (Nominas nom : listaNom) {
                    /*
                    String resultado ="";
                    resultado += "Id: " + nom.getId()+ "\n";
                    resultado += "Id empleado: " + nom.getId_empleado()+ "\n";
                    resultado += "Fecha: " + (nom.getFecha().getMonth() + 1) + "\n";
                    resultado += "URL: " + nom.getUrl()+ "\n";*/
                    String url = nom.getUrl();
                    numBotones = 1; //Creamos 12 botones
                    //Bucle para crear botones
                    for (int i = 0; i < numBotones; i++) {
                        Button button = new Button(NominasActivity.this);
                        button.setLayoutParams(lp);
                        //Asignamos texto al boton
                        button.setText("VER NOMINA DE: " + obtenerMes(nom.getFecha().getMonth()));
                        botonera.addView(button);

                        //Asignamos metodo onClick
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                descargarNomina(url);
                            }
                        });
                    }
                    /*
                    jsnTxt.append(resultado);
                    nomina.setId(nom.getId());
                    nomina.setId_empleado(nom.getId());
                    nomina.setFecha(nom.getFecha());
                    nomina.setUrl(nom.getUrl());
*/
                    // Nomina.add(nom.getFecha().toString());
                    //Nomina.add(nom.getUrl());
                    //System.out.println(Nomina);

                    System.out.println(nom.getFecha().getMonth());
                }



            }

            @Override
            public void onFailure(Call<List<Nominas>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private String obtenerMes(int fecha) {
        String mes = null;
        switch (fecha) {
            case 0:
                mes = "Enero";
                break;
            case 1:
                mes = "Febrero";
                break;
            case 2:
                mes = "Marzo";
                break;
            case 3:
                mes = "Abril";
                break;
            case 4:
                mes = "Mayo";
                break;
            case 5:
                mes = "Junio";
                break;
            case 6:
                mes = "Julio";
                break;
            case 7:
                mes = "Agosto";
                break;
            case 8:
                mes = "Septiembre";
                break;
            case 9:
                mes = "Octubre";
                break;
            case 10:
                mes = "Noviembre";
                break;
            case 11:
                mes = "Diciembre";
                break;
        }
        return mes;
    }

    private void descargarNomina(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
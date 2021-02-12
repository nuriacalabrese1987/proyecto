package com.example.pruebasloggin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebasloggin.model.Usuario;
import com.example.pruebasloggin.utils.Apis;
import com.example.pruebasloggin.utils.UsuarioService;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.ByteArrayOutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Variables relacionadas al xml
    private TextView tv1, tv2, tv3, tvEstado;
    private EditText et1;
    private Button botonCamara, botonNomina;
    private ProgressBar progreso;

    //Variables para la imagen
    byte[] ImagenBytes;
    Bitmap ImagenBitmap;
    private final static int peticionCaptura = 1;
    String telefono = null;
    UsuarioService service;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textTitulo);
        tv2 = findViewById(R.id.textInfo);
        tv3 = findViewById(R.id.textInstrucciones);
        tvEstado = findViewById(R.id.textResult);
        et1 = findViewById(R.id.telefono);

        botonCamara = findViewById(R.id.botonCamara);
        botonNomina = findViewById(R.id.botonNomina);

        progreso = findViewById(R.id.progressBar);

        botonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, rellene el campo!", Toast.LENGTH_SHORT).show();
                } else {
                    telefono = et1.getText().toString();
                    System.out.println("Dentro " +et1.getText().toString());
                    abrirCamara();
                }

            }
        });

        botonNomina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarNomina();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == peticionCaptura && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            ImagenBitmap = (Bitmap)extras.get("data");
        }
        //Convertimos la imagen en Bytes para pasarsela a la storage
        ImagenBytes = ConvertirImagenByte(ImagenBitmap);
        //System.out.println(ImagenBytes);
        addImagen();
        progreso.setVisibility(View.VISIBLE);
    }



    private void addImagen() {
        System.out.println("-------------------Has entrado en el metodo----------------------");
        System.out.println(telefono);
        service = Apis.getUsuario();
        Call<Boolean> call = service.getEmpleado(telefono, Base64.encodeToString(ImagenBytes, Base64.NO_WRAP));
        System.out.println(Base64.encodeToString(ImagenBytes, Base64.NO_WRAP));
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean res = null;
                try {
                    System.out.println("*********************CAPTURANDO EL RESPONSE BODY************************");
                    res = Boolean.parseBoolean(response.body().toString());


                } catch (Exception e) {
                    System.out.println("Error: " +e.getMessage());
                }
                if (res == true) {
                    System.out.println("*********************CARAS IGUALES");
                    System.out.println(res);
                    progreso.setVisibility(View.INVISIBLE);
                    tvEstado.setBackgroundColor(Color.GREEN);
                    tvEstado.setTextColor(Color.WHITE);
                    tvEstado.setText("¡Fichaje correcto!");
                    tvEstado.setVisibility(View.VISIBLE);
                } else {
                    System.out.println("*********************NO SON IGUALES");
                    System.out.println(res);
                    progreso.setVisibility(View.INVISIBLE);
                    tvEstado.setBackgroundColor(Color.RED);
                    tvEstado.setTextColor(Color.WHITE);
                    tvEstado.setText("Fichaje incorrecto!");
                    tvEstado.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("\n\n\nNO HAY RESPUESTA DEL SERVIDOR\n\n\n\n\n"+ t.getMessage() + "\n\n\n\n\n");
            }
        });
    }

    private byte[] ConvertirImagenByte(Bitmap imagenBitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        imagenBitmap.compress(Bitmap.CompressFormat.JPEG, 100, array);
        byte[] imagenByte = array.toByteArray();
        System.out.println("-----------------------------------------------------");
        System.out.println(imagenByte.length);
        System.out.println("-------------------------------------------------------");
        //String ImagenString = Base64.encodeToString(imagenByte, Base64.DEFAULT);
        //UploadTask subirArchivo =
        //Toast.makeText(MainActivity.this, imagenByte.length, Toast.LENGTH_SHORT).show();
        return imagenByte;
    }

    private void descargarNomina() {
        System.out.println("-------------------Has entrado en el metodo----------------------");
        service = Apis.getNomina();
        Call<ResponseBody> call = service.descargarNomina();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String hola;
                try {
                    System.out.println("*********************CAPTURANDO EL RESPONSE BODY************************");
                    hola = response.body().string();
                    System.out.println(hola);
                    //Metodo para descargar pdf
                    descargarPdf(hola);
                } catch (Exception e) {
                    System.out.println("Error: " +e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("\n\n\nNO HAY RESPUESTA DEL SERVIDOR\n\n\n\n\n"+ t.getMessage() + "\n\n\n\n\n");
            }
        });
    }

    private void descargarPdf(String url) {
        String URL = url;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void abrirCamara() {
        //Realizamos un intento de abrir la camara
        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE //Capturamos una imagen de la camara para su posterior estudio de datos
        );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, peticionCaptura); //Hemos conseguido realizar con exito el abrir la camara y Guardar la imagen.
        } else {
            Toast.makeText(MainActivity.this, "Error en la captura de la imagen...", Toast.LENGTH_SHORT).show();
        }
    }

}
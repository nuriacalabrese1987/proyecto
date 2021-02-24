package com.gmq.proyectogmq;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.gmq.proyectogmq.model.Centros;
import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.FichajesService;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gmq.proyectogmq.util.Apis.llamada;
import static com.gmq.proyectogmq.util.Apis.llamadaFichaje;


public class FotoActivity extends AppCompatActivity implements Serializable {
    //Variables
    private TextView tv1, tv2, tv3, tvEstado;
    private EditText et1;
    private Button botonCamara, botonNomina;
    private ProgressBar progreso;
    private LocationManager ubicacion;
    float distancia;

    //Variables para la imagen
    byte[] ImagenBytes;
    Bitmap ImagenBitmap;
    private final static int peticionCaptura = 1;
    String telefono = null;
    FichajesService service;
    String url="";
    String centro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        Intent intent = getIntent();
        centro = intent.getStringExtra("centro");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv1 = findViewById(R.id.textTitulo);
        tv2 = findViewById(R.id.textInfo);
        tv3 = findViewById(R.id.textInstrucciones);
        tvEstado = findViewById(R.id.textResult);
        et1 = findViewById(R.id.telefono);

        localizacion();
        botonCamara = findViewById(R.id.botonCamara);

        botonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().isEmpty()) {
                    Toast.makeText(FotoActivity.this, "Por favor, rellene el campo!", Toast.LENGTH_SHORT).show();
                } else {
                    telefono = et1.getText().toString();
                    System.out.println("Dentro " +et1.getText().toString());
                    abrirCamara();
                }

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
        realizarFichaje();

    }

    //Metodo para localizar el telefono

    private void localizacion() {

        //Comprobamos que hay permiso de ubicacion y si no lo tiene se lo damos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);

        }
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location locEmpleado = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Call<Centros> call = llamadaFichaje().solicitarCentro(centro);

        call.enqueue(new Callback<Centros>() {
            @Override
            public void onResponse(Call<Centros> call, Response<Centros> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FotoActivity.this, "Error al geolocalizar", Toast.LENGTH_LONG).show();
                    return;
                }
                Centros centroObtenido = new Centros();
                centroObtenido = response.body();

                Location locCentro = new Location("providerNa");
                locCentro.setLatitude(centroObtenido.getLatitud());
                locCentro.setLongitude(centroObtenido.getLongitud());

                if(ubicacion!=null) {
                    Log.d("Latitud", String.valueOf(locEmpleado.getLatitude()));
                    Log.d("Longitud", String.valueOf(locEmpleado.getLongitude()));

                }
                distancia = locEmpleado.distanceTo(locCentro);
                Log.d("distancia",String.valueOf(distancia));
            }

            @Override
            public void onFailure(Call<Centros> call, Throwable t) {

            }
        });



    }

    //Metodo de llamada a la API
    private void realizarFichaje() {
        System.out.println("-------------------Has entrado en el metodo----------------------");
        System.out.println(telefono); //Comprobacion de que el tfno entra bien
        service = Apis.hacerFichaje();
        Call<Boolean> call = service.hacerFichaje(telefono, "40.425330991728416", "-3.6768286461484427", Base64.encodeToString(ImagenBytes, Base64.NO_WRAP));
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

                    tvEstado.setBackgroundColor(Color.GREEN);
                    tvEstado.setTextColor(Color.WHITE);
                    tvEstado.setText("¡Fichaje correcto!");
                    tvEstado.setVisibility(View.VISIBLE);
                } else {
                    System.out.println("*********************NO SON IGUALES");
                    System.out.println(res);
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

    //Metodo para transformar la imagen recogida en Bitmaps a array de bytes
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

    //Metodo para abrir la camara del telefono y capturar la foto
    private void abrirCamara() {
        //Realizamos un intento de abrir la camara
        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE //Capturamos una imagen de la camara para su posterior estudio de datos
        );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, peticionCaptura); //Hemos conseguido realizar con exito el abrir la camara y Guardar la imagen.
        } else {
            Toast.makeText(FotoActivity.this, "Error en la captura de la imagen...", Toast.LENGTH_SHORT).show();
        }
    }
}

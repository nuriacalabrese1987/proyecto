package com.gmq.proyectogmq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiciosActivity extends AppCompatActivity {

    TextView servicios, serviciosdos;
    ImageView hipercor, corte, supermercado, bricor, sfera, seguros, informatica, optica;
    private Typeface coolvetica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        //Creamos las fuentes
        String fuente1 = "fuentes/coolvetica_titulo.ttf";
        this.coolvetica=  Typeface.createFromAsset(getAssets(), fuente1);

        servicios = findViewById(R.id.textServicios);
        servicios.setTypeface(coolvetica);
        //serviciosdos = findViewById(R.id.textServiciosDos);
        //serviciosdos.setTypeface(coolvetica);

        hipercor = findViewById(R.id.imageHipercor);
        corte = findViewById(R.id.imageCorteIngles);
        supermercado = findViewById(R.id.imageSuper);
        bricor = findViewById(R.id.imageBricor);
        sfera = findViewById(R.id.imageSfera);
        seguros = findViewById(R.id.imageSeguros);
        informatica = findViewById(R.id.imageInformatica);
        optica = findViewById(R.id.imageOptica);


        hipercor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.hipercor.es/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        corte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.elcorteingles.es/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        supermercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.elcorteingles.es/supermercado/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        bricor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.elcorteingles.es/bricor/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        sfera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.sfera.com/es/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        seguros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://seguros.elcorteingles.es/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        informatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.elcorteingles.es/electronica/soluciones-y-servicios/informatica/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        optica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.optica2000.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
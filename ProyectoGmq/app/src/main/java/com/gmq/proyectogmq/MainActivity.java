package com.gmq.proyectogmq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmq.proyectogmq.model.Empleados;
import com.gmq.proyectogmq.model.Fichajes;
import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.dbConnection;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements Serializable, NavigationView.OnNavigationItemSelectedListener {

    Empleados empleado;
    private AppBarConfiguration mAppBarConfiguration;
    TextView textBienvenida, textNombre, textTfno, textoDepartamento, textoCentro, textoDireccion;
    ImageView imagenUser;
    dbConnection conection;
    DrawerLayout drawer;
    String urlImagen;
    String cent;

    private Typeface Mont;
    private Typeface NewYork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Creamos las fuentes
        String fuente1 = "fuentes/coolvetica_titulo.ttf";
        this.Mont=  Typeface.createFromAsset(getAssets(), fuente1);
        String fuente2 = "fuentes/coolvetica_fuente.ttf";
        this.NewYork = Typeface.createFromAsset(getAssets(), fuente2);

        textBienvenida = findViewById(R.id.textBienvenida);
        textBienvenida.setTypeface(Mont);

        textNombre = findViewById(R.id.textNombre);
        textNombre.setTypeface(NewYork);

        textoDireccion = findViewById(R.id.textDireccion);
        textoDireccion.setTypeface(NewYork);

        textTfno = findViewById(R.id.textTelefono);
        textTfno.setTypeface(NewYork);

        textoDepartamento = findViewById(R.id.textDepartamento);
        textoDepartamento.setTypeface(NewYork);

        textoCentro = findViewById(R.id.textCentro);
        textoCentro.setTypeface(NewYork);

        imagenUser = findViewById(R.id.imagenUser);

        leerBD();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.navFichaje, R.id.navNominas, R.id.navSalir)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        int id = item.getItemId();
        if (id == R.id.navHome) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.navFichaje) {
            Intent intent = new Intent(MainActivity.this, FotoActivity.class);
            startActivity(intent);
        } else if (id == R.id.navNominas) {

        } else if (id == R.id.navSalir) {

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navHome) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.navFichaje) {
            Intent intent = new Intent(MainActivity.this, FotoActivity.class);
            intent.putExtra("centro",cent);
            startActivity(intent);
        } else if(id == R.id.navNominas) {
            Intent intent = new Intent(MainActivity.this, NominasActivity.class);
            startActivity(intent);
        } else if (id == R.id.navSalir){
            Salir(this);
        }
        return false;
    }

    public static void Salir(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("¿Seguro que quieres salir?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                //Cerramos app
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void leerBD() {
    String mensaje=null;
    String bienvenida = null;
    String nombre= null;
    String direccion = null;
    String telefono = null;
    String departamento= null;
    String centro= null;
    //String url = null;
    Bitmap imagen = null;
        conection = new dbConnection(getApplicationContext(), Apis.TABLA_EMPLEADO,null,1);
        SQLiteDatabase db = conection.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Apis.TABLA_EMPLEADO, null);
        while (cursor.moveToNext()) {
            mensaje="id "+cursor.getString(0)+"\nnombre "+cursor.getString(1)+"\napellidos "+cursor.getString(2)+"\ndirecciom "+cursor.getString(3)+"\ntelefono "+cursor.getString(4)
                    +"\ndep "+cursor.getString(5)+"\ncentro "+cursor.getString(6)+"\nurl "+cursor.getString(7)+"\nel8 "+cursor.getString(8);
            bienvenida = cursor.getString(1);
            nombre = cursor.getString(1) +" " + cursor.getString(2);
            direccion = cursor.getString(3);
            telefono = cursor.getString(4);
            departamento = cursor.getString(5);
            centro = cursor.getString(6);
            cent = cursor.getString(6);
            //url = cursor.getString(7);
            urlImagen = cursor.getString(7);

        }


        LoadImage loadImage = new LoadImage(imagenUser);
        loadImage.execute(urlImagen);


        textBienvenida.setText("Bienvenido de nuevo " + bienvenida + ",\nA continuación tiene su infromación mas reciente!");


        textNombre.setText("Nombre del empleado:\n " + nombre);


        textoDireccion.setText("Direccion de residencia:\n " + direccion);


        textTfno.setText("Telefono de contacto:\n " + telefono );


        textoDepartamento.setText("Numero de departamento:\n " + departamento);


        textoCentro.setText("Numero de centro:\n " + centro);


    }


    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView img){
            this.imageView = img;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imagenUser.setImageBitmap(bitmap);
        }
    }
}
package com.gmq.proyectogmq;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.gmq.proyectogmq.model.Empleados;
import com.gmq.proyectogmq.util.Apis;
import com.gmq.proyectogmq.util.dbConnection;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    Empleados empleado;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        Bundle bundle = this.getIntent().getExtras();
        empleado=(Empleados) bundle.getSerializable("empleado");
        grabarEmpleado(empleado);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //GRABAMOS AL EMPLEADO EN LA BASE DE DATOS

    private void grabarEmpleado(Empleados empleado){

        dbConnection conection = new dbConnection(MainActivity.this, Apis.TABLA_EMPLEADO,null,1);
        SQLiteDatabase db = conection.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id_empleado ",empleado.getId_empleado());
        values.put("nombre",empleado.getNombre());
        values.put("apellidos",empleado.getApellidos());
        values.put("direccion",empleado.getDireccion());
        values.put("telefono",empleado.getTelefono());
        values.put("n_departamento",empleado.getN_departamento());
        values.put("n_centro",empleado.getN_centro());
        values.put("url_storage",empleado.getUrl_storage());
        Long resultado=db.insert(Apis.TABLA_EMPLEADO,null,values);
        db.close();
    }
}
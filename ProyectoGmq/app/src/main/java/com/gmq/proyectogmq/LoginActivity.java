package com.gmq.proyectogmq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
     Button button;
     EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.button);



    }

    /*public void token(View view){
        Intent token = new Intent(LoginActivity.this, TokenActivity.class );
        startActivity(token);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }*/
    public void token(View vista){
        pass=findViewById(R.id.password);
        pass.setVisibility(View.INVISIBLE);
    }
}
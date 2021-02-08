package com.gmq.proyectogmq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TokenActivity extends AppCompatActivity {

    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        phone = findViewById(R.id.editTextPhone);
    }

    public void volver(View vista){
        String telefono = phone.getText().toString();
        Intent token = new Intent(TokenActivity.this, LoginActivity.class );
        token.putExtra("telefono", telefono);
        startActivity(token);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}

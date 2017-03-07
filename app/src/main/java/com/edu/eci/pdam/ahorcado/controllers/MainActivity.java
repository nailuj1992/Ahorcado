package com.edu.eci.pdam.ahorcado.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.edu.eci.pdam.ahorcado.R;
import com.edu.eci.pdam.ahorcado.models.Usuario;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE_USER = "MESSAGE_USER";

    EditText txt_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnJugarClick(View view) {
        txt_nombre = (EditText) findViewById(R.id.nombreJugador);
        String nombre = txt_nombre.getText().toString();
        if(nombre.isEmpty()) {
            txt_nombre.setError("Debe ingresar un nombre");
        } else {
            Usuario usuario = new Usuario(nombre);
            Intent juego = new Intent(this, JuegoActivity.class);
            juego.putExtra(EXTRA_MESSAGE_USER, usuario);
            startActivity(juego);
            finish();
        }
    }
}

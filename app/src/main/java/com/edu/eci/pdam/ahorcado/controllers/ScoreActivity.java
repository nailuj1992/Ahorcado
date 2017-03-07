package com.edu.eci.pdam.ahorcado.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.edu.eci.pdam.ahorcado.R;

public class ScoreActivity extends AppCompatActivity {

    TextView txt_titulo, txt_subtitulo, txt_nombre, txt_puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // https://developer.android.com/training/basics/data-storage/shared-preferences.html#ReadSharedPreference
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int score = sharedPref.getInt(getString(R.string.saved_score), 0);
        String name = sharedPref.getString(getString(R.string.saved_name), "Nombre");
        boolean state = sharedPref.getBoolean(getString(R.string.saved_state), false);

        txt_titulo = (TextView) findViewById(R.id.titulo);
        txt_subtitulo = (TextView) findViewById(R.id.subtitulo);
        txt_nombre = (TextView) findViewById(R.id.nombre);
        txt_puntaje = (TextView) findViewById(R.id.puntaje);

        txt_nombre.setText(name);
        txt_puntaje.setText("" + score);
        if (state) {
            txt_titulo.setText("¡Victoria!".toUpperCase());
            txt_subtitulo.setText("Has ganado esta partida :D");
        } else {
            txt_titulo.setText("¡Derrota!".toUpperCase());
            txt_subtitulo.setText("Has perdido esta partida :(");
        }
    }
}

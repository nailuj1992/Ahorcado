package com.edu.eci.pdam.ahorcado.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.edu.eci.pdam.ahorcado.R;
import com.edu.eci.pdam.ahorcado.models.Ahorcado;
import com.edu.eci.pdam.ahorcado.models.Usuario;
import com.edu.eci.pdam.ahorcado.utils.AhorcadoException;

public class JuegoActivity extends AppCompatActivity {

    private Resources resources;
    private String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private Ahorcado ahorcado;
    private Usuario usuario;

    TextView txt_intentos, txt_puntaje, txt_palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        // Mostrar siempre el teclado.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE_USER);
        resources = getResources();
        ahorcado = new Ahorcado(resources);

        txt_intentos = (TextView) findViewById(R.id.txt_intentos);
        txt_puntaje = (TextView) findViewById(R.id.txt_puntos);
        txt_palabra = (TextView) findViewById(R.id.txt_palabra);
        repaint();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != event.KEYCODE_BACK) {
            String caracter = Character.toString(event.getDisplayLabel());
            if (letras.contains(caracter)) {
                try {
                    boolean play = ahorcado.evaluar(caracter);
                    repaint();
                    if (!play) {
                        goScore(true);
                    }
                } catch (AhorcadoException ex) {
                    ex.printStackTrace();
                    if (ex.getMessage().equals(AhorcadoException.FINISH_GAME_DEFEAT)) {
                        goScore(false);
                    }
                }
            }
        }
        return true;
    }

    /**
     * Puntua y muestra resultados.
     */
    private void goScore(boolean win) {
        usuario.setPuntaje(ahorcado.score);

        // https://developer.android.com/training/basics/data-storage/shared-preferences.html#WriteSharedPreference
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_score), usuario.getPuntaje());
        editor.putString(getString(R.string.saved_name), usuario.getNombre());
        editor.putBoolean(getString(R.string.saved_state), win);
        editor.commit();

        startActivity(new Intent(this, ScoreActivity.class));
    }

    /**
     * Se actualiza (repinta) el tablero de juego.
     */
    private void repaint() {
        txt_intentos.setText("Intento " + ahorcado.intentos + " de " + Ahorcado.MAX_TRYS);
        txt_puntaje.setText("" + ahorcado.score);
        txt_palabra.setText(ahorcado.word);
    }
}

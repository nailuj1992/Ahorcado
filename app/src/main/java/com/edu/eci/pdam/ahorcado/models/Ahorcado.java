package com.edu.eci.pdam.ahorcado.models;

import android.content.res.Resources;

import com.edu.eci.pdam.ahorcado.R;
import com.edu.eci.pdam.ahorcado.utils.AhorcadoException;

import java.io.Serializable;

/**
 * Source code: http://www.javamexico.org/foros/java_standard_edition/codigo_para_el_juego_el_ahorcado
 * Created by Julian Gonzalez Prieto (Avuuna la Luz del Alba) on 05/03/2017.
 */

public class Ahorcado implements Serializable {

    public static final int MAX_TRYS = 20;
    private static final int ADD_SCORE = 10;
    private static final String MASK_STR = "*";

    private String[] diccionario;
    private String secretWord;
    public String word;
    private boolean play;
    public int intentos;
    private boolean cambios;
    public int score;

    public Ahorcado(Resources resources) {
        this.diccionario = resources.getStringArray(R.array.palabras);
        this.intentos = 0;
        this.cambios = false;
        this.secretWord = randomWord();
        this.score = 0;

        String maskedWord = "";
        for (int i = 0; i <= this.secretWord.length() - 1; i++) {
            maskedWord += MASK_STR;
        }
        this.word = maskedWord;
        this.play = true;
    }

    /**
     * Se escoge una palabra aleatoria de la lista.
     *
     * @return
     */
    private String randomWord() {
        int num = (int) (Math.random() * (diccionario.length));
        return diccionario[num];
    }

    /**
     * Evalua el juego de acuerdo a los caracteres que se le pase.
     *
     * @param word Caracter a evaluar.
     * @return
     * @throws AhorcadoException Estado del juego (derrota o no jugando).
     */
    public boolean evaluar(String word) throws AhorcadoException {
        if (!play) {
            throw new AhorcadoException(AhorcadoException.NO_PLAYING);
        }
        String p = "";
        // controla que aun se pueda jugar
        if (this.intentos == MAX_TRYS - 1) {
            throw new AhorcadoException(AhorcadoException.FINISH_GAME_DEFEAT);
        }
        // evalua caracter por caracter
        for (int i = 0; i <= this.secretWord.length() - 1; i++) {
            // si el caracter se encuentra en la palabra secreta
            if (word.equalsIgnoreCase("" + secretWord.charAt(i)) && MASK_STR.equals("" + this.word.charAt(i))) {
                char[] wordArr = this.word.toCharArray();
                wordArr[i] = word.charAt(0);
                this.word = new String(wordArr);

                this.cambios = true;
                this.score += ADD_SCORE;
            }
            p += this.word.charAt(i);
        }
        // si no se produjo ningun cambio, quiere decir que el jugador se equivoco
        if (this.cambios == false) {
            this.intentos++;
        } else {
            this.cambios = false;
        }
        // comprobamos el estado del juego
        this.play = !this.word.equalsIgnoreCase(this.secretWord);
        return play;
    }

}

package com.edu.eci.pdam.ahorcado.utils;

/**
 * Created by Julian Gonzalez Prieto (Avuuna la Luz del Alba) on 05/03/2017.
 */

public class AhorcadoException extends Exception {

    public static final String FINISH_GAME_DEFEAT = "Juego terminado, usted ha perdido.";
    public static final String FINISH_GAME_WIN = "Juego terminado, usted ha ganado.";
    public static final String NO_PLAYING = "No hay una partida en curso.";

    public AhorcadoException(String message) {
        super(message);
    }
}

package utilidades;

import java.awt.event.KeyEvent;

public class Validaciones {

    // Solo letras y espacios
    public static void soloLetras(KeyEvent evt) {

        char c = evt.getKeyChar();

        if (!Character.isLetter(c)
                && c != KeyEvent.VK_SPACE
                && c != KeyEvent.VK_BACK_SPACE) {

            evt.consume();

        }

    }

    // Solo números
    public static void soloNumeros(KeyEvent evt) {

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)
                && c != KeyEvent.VK_BACK_SPACE) {

            evt.consume();

        }

    }

}
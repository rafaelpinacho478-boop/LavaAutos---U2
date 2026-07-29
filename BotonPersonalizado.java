package componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BorderFactory;

public class BotonPersonalizado extends JButton {

    public BotonPersonalizado() {

        configurar();

    }

    private void configurar() {

        setBackground(new Color(255, 102, 102));

        setForeground(Color.WHITE);

        setFont(new Font("Segoe UI", Font.BOLD, 14));

        setFocusPainted(false);

        setBorder(BorderFactory.createEmptyBorder(
                8,
                15,
                8,
                15));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}
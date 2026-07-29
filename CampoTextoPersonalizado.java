package componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CampoTextoPersonalizado extends JTextField {

    public CampoTextoPersonalizado() {

        configurar();

    }

    private void configurar() {

        setFont(new Font("Segoe UI", Font.PLAIN, 14));

        setBackground(Color.WHITE);

        setForeground(Color.BLACK);

        setCaretColor(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(
                8,
                10,
                8,
                10));

        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                20,
                20);

        g2.dispose();

        super.paintComponent(g);

    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(180,180,180));

        g2.drawRoundRect(
                0,
                0,
                getWidth()-1,
                getHeight()-1,
                20,
                20);

        g2.dispose();

    }

    @Override
    public Insets getInsets() {

        return new Insets(
                8,
                12,
                8,
                12);

    }

}
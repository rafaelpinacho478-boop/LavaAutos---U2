package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTitulo extends JPanel {

    private JLabel lblTitulo;

    public PanelTitulo() {

        configurar();

    }

    private void configurar() {

        setLayout(new BorderLayout());

        setBackground(new Color(255,105,105));

        setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10));

        lblTitulo = new JLabel();

        lblTitulo.setHorizontalAlignment(
                SwingConstants.CENTER);

        lblTitulo.setForeground(Color.WHITE);

        lblTitulo.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        20));

        add(lblTitulo, BorderLayout.CENTER);

    }

    public void setTitulo(String titulo) {

        lblTitulo.setText(titulo);

    }

    public String getTitulo() {

        return lblTitulo.getText();

    }

}
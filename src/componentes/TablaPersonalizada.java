package componentes;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class TablaPersonalizada extends JTable {

    public TablaPersonalizada() {

        configurar();

    }

    private void configurar() {

        setRowHeight(28);

        setFont(new Font("Segoe UI", Font.PLAIN, 13));

        setSelectionBackground(new Color(52, 152, 219));

        setSelectionForeground(Color.WHITE);

        setGridColor(new Color(220,220,220));

        setShowGrid(true);

        JTableHeader encabezado = getTableHeader();

        encabezado.setFont(
                new Font("Segoe UI", Font.BOLD, 14));

        encabezado.setBackground(
                new Color(51, 51, 255));

        encabezado.setForeground(Color.WHITE);

        encabezado.setReorderingAllowed(false);

        encabezado.setResizingAllowed(true);

    }

}
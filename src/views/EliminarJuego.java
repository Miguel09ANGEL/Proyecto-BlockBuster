package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;

public class EliminarJuego extends JFrame {

    public EliminarJuego() {
        // Configuración básica de la ventana
        setTitle("Eliminación de Videojuegos");
        setSize(1024, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usamos JLayeredPane para superponer componentes
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 650));
        setContentPane(layeredPane);

        // 1. PANEL BLANCO (fondo completo)
        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(null);
        panelIzq.setBackground(Color.decode("#FFFFFF"));
        panelIzq.setBounds(10, 62, 257, 475);
        layeredPane.add(panelIzq, JLayeredPane.PALETTE_LAYER);

        JButton btnClientes = new JButton("CLIENTES");
        btnClientes.setFont(new Font("Calibri", Font.BOLD, 16));
        btnClientes.setBackground(Color.decode("#263C54"));
        btnClientes.setForeground(Color.WHITE);
        btnClientes.setBounds(10, 11, 237, 100);
        panelIzq.add(btnClientes);
        
        JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
        btnVideojuegos.setForeground(Color.WHITE);
        btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
        btnVideojuegos.setBackground(new Color(38, 60, 84));
        btnVideojuegos.setBounds(10, 128, 237, 100);
        panelIzq.add(btnVideojuegos);
        btnVideojuegos.addActionListener(e -> {
            new AdministradorJuegos();         // Abre la segunda ventana
            dispose();            // Cierra la ventana actual
        });

        JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
        btnRentaYCompra.setForeground(Color.WHITE);
        btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
        btnRentaYCompra.setBackground(new Color(38, 60, 84));
        btnRentaYCompra.setBounds(10, 242, 237, 100);
        panelIzq.add(btnRentaYCompra);
        
        JButton btnClientes_3 = new JButton("NUEVA OPERACIÓN");
        btnClientes_3.setForeground(Color.WHITE);
        btnClientes_3.setFont(new Font("Calibri", Font.BOLD, 16));
        btnClientes_3.setBackground(new Color(38, 60, 84));
        btnClientes_3.setBounds(10, 364, 237, 100);
        panelIzq.add(btnClientes_3);

        // 2. PANEL GRIS CENTRAL
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(Color.decode("#F2F2F2"));
        panelCentral.setBounds(277, 62, 731, 475);
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

        // Datos de la tabla
        Object[][] data = {
            { null, null, null, null, "1", "2", "3" },
            { "4", "5", "6", "7", "8", "9", "10" },
            { "11", "12", "13", "14", "15", "16", "17" },
            { "18", "19", "20", "21", "22", "23", "24" },
            { "25", "26", "27", "28", "29", "30", "31" }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Tabla
        JTable table = new JTable(data, new String[] { "", "", "", "", "", "", "" });
        panelCentral.add(table);
        table.setFont(new Font("Anton", Font.PLAIN, 16));
        table.setBounds(74, 50, 591, 400);
        table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        table.setShowGrid(true);
        table.setGridColor(new Color(204, 204, 204));
        table.setTableHeader(null);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setRowHeight(80);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0, 1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

        setVisible(true);

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 9); // Esquinas redondeadas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EliminarJuego();
        });
    }
}

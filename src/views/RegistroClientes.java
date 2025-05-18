package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroClientes extends JFrame {

    public RegistroClientes() {
    	

    	try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        // Configuración básica de la ventana
        setTitle("Registro de Clientes");
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
        btnClientes.addActionListener(e -> {
            new AdministradorCliente();         // Abre la segunda ventana
            dispose();            // Cierra la ventana actual
        });
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
        btnRentaYCompra.addActionListener(e -> {
            new AdministradorRentaCompra();         // Abre la segunda ventana
            dispose();            // Cierra la ventana actual
        });
        panelIzq.add(btnRentaYCompra);
        
        JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
        btnNuevaOperacion.setForeground(Color.WHITE);
        btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
        btnNuevaOperacion.setBackground(new Color(38, 60, 84));
        btnNuevaOperacion.setBounds(10, 364, 237, 100);
        btnNuevaOperacion.addActionListener(e -> {
            new NuevaOperacion();         // Abre la segunda ventana
            dispose();            // Cierra la ventana actual
        });
        panelIzq.add(btnNuevaOperacion);

        // 2. PANEL GRIS CENTRAL
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(Color.decode("#F2F2F2"));
        panelCentral.setBounds(277, 62, 731, 475);
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        JLabel iniciar = new JLabel("REGISTRO DE CLIENTES");
		iniciar.setSize(236, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

        // Datos de la tabla
        Object[][] data = {
            { "Identificador", "Nombre", "Apellido Paterno", "Apellido  Materno", "Numero celular)", "Correo", },
            { "000001", "Manuel", "Orozco", "Vazquez", "612000000", "mov@gmail.com" },
            { "000002", "Gabriel", "Lauro", "Hernandez", "612000002", "glh@gmail.com"},
            { "000003", "Miguel", "Garcia", "Ahuanta", "61200124", "mga@gmail.com"},
            { "000004", "Fransisco", "Navarro", "Navarro", "612000034", "fnc@gmail.com" },
            { "000005", "Imies", "Kinn", "lokin", "$950", "612000253","aik@gmail.com"},
            { "000006", "ARK: Survival", "Mixto", "30", "612000247", "wik@gmail.com"},
            

        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Tabla
        JTable table = new JTable(data, new String[] { "", "", "", "", "", "",});
        panelCentral.add(table);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setBounds(26, 62, 695, 366);
        table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        table.setShowGrid(true);
        table.setGridColor(new Color(204, 204, 204));
        table.setTableHeader(null);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setRowHeight(40);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        
        JButton btnDetalles = new JButton("DETALLES");
        btnDetalles.setForeground(Color.WHITE);
        btnDetalles.setBackground(Color.decode("#6D91B9"));
        btnDetalles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new DetallesCliente();
        		 dispose();  
        		
        	}
        });
        btnDetalles.setBounds(406, 439, 172, 25);
        panelCentral.add(btnDetalles);
        
        
        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(Color.decode("#B82F2F"));
        btnEliminar.setBounds(187, 439, 172, 25);
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Confirma_1();
        		 
        		
        	}
        });
        panelCentral.add(btnEliminar);
        
       
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
        btnBuscar.setBounds(619, 25, 86, 25);
        panelCentral.add(btnBuscar);
        
       

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
            new RegistroClientes();
        });
    }
}

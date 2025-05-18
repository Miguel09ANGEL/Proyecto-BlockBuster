package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PromocionAutomatica extends JFrame {

    public PromocionAutomatica() {
        // Configuración básica de la ventana
        setTitle("Promocion Automatica");
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
        
        JLabel iniciar = new JLabel("PROMOCIÓN AUTOMATICA");
			iniciar.setSize(324, 60);
			iniciar.setLocation(143, 11);
			iniciar.setHorizontalAlignment(JLabel.CENTER);
			iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
			panelCentral.add(iniciar);
		
		 JButton btnBuscar = new JButton("Buscar");
	        btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
	        btnBuscar.setBounds(619, 25, 86, 25);
	        panelCentral.add(btnBuscar);
	        // Datos de la tabla
	        Object[][] data = {
	            { "Identificador", "Nombre", "Plataforma", },
	            { "","","", },
	            { "","","", },
	            { "","","", },
	            { "","","", },
	            { "","","", },
	            { "","","", },
	            { "","","", },
	           
	            

	        	};

	        	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        	centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	        	// Tabla
	        	JTable table = new JTable(data, new String[] { "", "", "",});
	        	panelCentral.add(table);
	        	table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	        	table.setBounds(10, 61, 368, 356);
	        	table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
	        	table.setShowGrid(true);
	        	table.setGridColor(new Color(204, 204, 204));
	        	table.setTableHeader(null);
	        	table.setDefaultRenderer(Object.class, centerRenderer);
	        	table.setRowHeight(35);
	        	table.setShowHorizontalLines(true);
	        	table.setShowVerticalLines(true);
	        
	        // Promociones
	        Object[][] promociones = {
	            { "En la Compra de:", "Descuentos por frecuencia", },
	            { "","", },
	            { "","", },
	            { "","", },
	            { "","", },
	            { "","", },
	            { "","", },
	            { "","", },
	           
	            

	        };

	        DefaultTableCellRenderer centerRendere = new DefaultTableCellRenderer();
	        centerRendere.setHorizontalAlignment(JLabel.CENTER);

	        // Tabla
	        JTable tabla = new JTable(promociones, new String[] { "", "",});
	        panelCentral.add(tabla);
	        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	        tabla.setBounds(406, 61, 315, 356);
	        tabla.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
	        tabla.setShowGrid(true);
	        tabla.setGridColor(new Color(204, 204, 204));
	        tabla.setTableHeader(null);
	        tabla.setDefaultRenderer(Object.class, centerRendere);
	        tabla.setRowHeight(35);
	        tabla.setShowHorizontalLines(true);
	        tabla.setShowVerticalLines(true);
	        
	        JButton btnEditarPromocion = new JButton("EDITAR PROMOCIÓN");
	        btnEditarPromocion.setForeground(Color.WHITE);
	        btnEditarPromocion.setBackground(Color.decode("#6D91B9"));
	        btnEditarPromocion.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new EditarPromociones();
	        		 dispose();  
	        		
	        	}
	        });
	        btnEditarPromocion.setBounds(279, 428, 195, 30);
	        panelCentral.add(btnEditarPromocion);

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
            new PromocionAutomatica();
        });
    }
}

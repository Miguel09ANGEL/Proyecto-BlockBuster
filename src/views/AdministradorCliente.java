package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministradorCliente extends JFrame {

    public AdministradorCliente() {
        // Configuración básica de la ventana
        setTitle("Panel Cliente");
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
        btnClientes.setBounds(10, 11, 237, 100); // x, y, ancho, alto
        
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
        btnRentaYCompra.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	new AdministradorRentaCompra();         // Abre la segunda ventana
                dispose();  
    	    }
    	});
        panelIzq.add(btnRentaYCompra);
        
        JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
        btnNuevaOperacion.setForeground(Color.WHITE);
        btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
        btnNuevaOperacion.setBackground(new Color(38, 60, 84));
        btnNuevaOperacion.setBounds(10, 364, 237, 100);
        btnNuevaOperacion.addActionListener(e -> {
            new AdministradorJuegos();         // Abre la segunda ventana
            dispose();            // Cierra la ventana actual
        });
        panelIzq.add(btnNuevaOperacion);

        // 2. PANEL GRIS CENTRAl
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(Color.decode("#F2F2F2"));
        panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        	ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        	Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        	JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
        	logo.setBounds(336, 22, 70, 70); //posicion
        	panelCentral.add(logo); 
        
        	JLabel iniciar = new JLabel("¡BIENVENIDO/A AL PANEL DE CLIENTE");
        	iniciar.setSize(548, 60);
        	iniciar.setLocation(100, 103);
        	iniciar.setHorizontalAlignment(JLabel.CENTER);
        	iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
        	panelCentral.add(iniciar);
        
	    	
	    	
	    	
        	JLabel iniciar_1 = new JLabel(
	    		    "<html><div style='text-align: center;'>Desde aquí podrás gestionar y controlar todas las<br>" +
	    		    "funcionalidades de los clientes. ¿Qué deseas hacer hoy?</div></html>"
	    		);
	    		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1.setBounds(168, 147, 391, 83); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JButton registros = new JButton("REGISTROS");
	   		 	registros.setBounds(100, 261, 206, 100);
	   		 	registros.setForeground(Color.WHITE);
	   		 	registros.setFont(new Font("Calibri", Font.BOLD, 16));
	   		 	registros.setBackground(new Color(38, 60, 84));
	   		 	registros.addActionListener(e -> {
	   		 		new RegistroClientes();         // Abre la segunda ventana
	   		 				dispose();  });  
	   		 	panelCentral.add(registros);
	   		 	
	   		 	
	   	        JButton btnAgregarCliente = new JButton("AGREGAR CLIENTE ");
	   	  		btnAgregarCliente.setForeground(Color.WHITE);
	   	  		btnAgregarCliente.setFont(new Font("Calibri", Font.BOLD, 16));
	   	  		btnAgregarCliente.setBackground(new Color(38, 60, 84));
	   	  		btnAgregarCliente.setBounds(442, 261, 206, 100);
	   	  		btnAgregarCliente.addActionListener(e -> {
		 			new AgregarCliente();         // Abre la segunda ventana
		 				dispose();            // Cierra la ventana actual
		 			});  
	   	        panelCentral.add(btnAgregarCliente);
	   	        

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdministradorCliente();
        });
    }
}
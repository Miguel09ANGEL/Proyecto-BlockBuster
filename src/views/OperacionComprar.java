package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperacionComprar extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

    public OperacionComprar() {
        // Configuración básica de la ventana
        setTitle("Detalles de operación Comprar");
        setSize(1024, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usamos JLayeredPane para superponer componentes
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 650));
        setContentPane(layeredPane);

        // 2. PANEL GRIS CENTRAl
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(Color.decode("#F2F2F2"));
        panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
        logo.setBounds(477, 11, 70, 70); //posicion
        panelCentral.add(logo); 
        
        JLabel iniciar = new JLabel("DETALLES DE OPERACIÓN");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
        
	    	
	    	
	    	
	    	JLabel iniciar_1 = new JLabel(
	    		    "Nombre del cliente"
	    		);
	    		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1.setBounds(85, 120, 160, 42); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JTextField nJuego = new JTextField();
	    		nJuego.setBackground(Color.decode("#D9D9D9"));
	    		nJuego.setBounds(85, 161, 255, 27);
	    		panelCentral.add(nJuego);
	    		nJuego.setColumns(10);
	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(175, 406, 183, 33);
	    		btnCancelar.addActionListener(e -> {
	    	            new Compra();         // Abre la segunda ventana
	    	            dispose();            // Cierra la ventana actual
	    	        });
	    		panelCentral.add(btnCancelar);
	    		
	    		
	    		JButton btnConfirmar = new JButton("Confirmar");
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(582, 406, 183, 33);
	    		btnConfirmar.addActionListener(e -> {
    	            new Confirma_13();         // Abre la segunda ventana
    	                       // Cierra la ventana actual
    	        	});
	    		panelCentral.add(btnCancelar);
	    		panelCentral.add(btnConfirmar);
	    		
	    		textField_1 = new JTextField();
	    		textField_1.setColumns(10);
	    		textField_1.setBackground(new Color(217, 217, 217));
	    		textField_1.setBounds(405, 161, 263, 27);
	    		panelCentral.add(textField_1);
	    		
	    		JLabel iniciar_1_1 = new JLabel("Nombre del Videojuego:");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1.setBounds(405, 120, 160, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		textField_2 = new JTextField();
	    		textField_2.setColumns(10);
	    		textField_2.setBackground(new Color(217, 217, 217));
	    		textField_2.setBounds(697, 161, 165, 27);
	    		panelCentral.add(textField_2);
	    		
	    		JLabel iniciar_1_1_1 = new JLabel("Cantidad:");
	    		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1.setBounds(697, 120, 160, 42);
	    		panelCentral.add(iniciar_1_1_1);
	    		
	    		JLabel iniciar_1_2 = new JLabel("Nombre de videojuego");
	    		iniciar_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2.setBounds(75, 215, 255, 42);
	    		panelCentral.add(iniciar_1_2);
	    		
	    		JLabel iniciar_1_2_1 = new JLabel("Precio (MXN):");
	    		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1.setBounds(403, 215, 255, 42);
	    		panelCentral.add(iniciar_1_2_1);
	    		
	    		JLabel iniciar_1_2_2 = new JLabel("Contra");
	    		iniciar_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_2.setBounds(75, 246, 255, 42);
	    		panelCentral.add(iniciar_1_2_2);
	    		
	    		JLabel iniciar_1_2_1_1 = new JLabel("$100");
	    		iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1_1.setBounds(403, 246, 255, 42);
	    		panelCentral.add(iniciar_1_2_1_1);
	    		
	    		JLabel iniciar_1_2_3 = new JLabel("Descuento:");
	    		iniciar_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_3.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_3.setBounds(75, 293, 255, 42);
	    		panelCentral.add(iniciar_1_2_3);
	    		
	    		JLabel iniciar_1_2_2_1 = new JLabel("07%");
	    		iniciar_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_2_1.setBounds(75, 320, 255, 42);
	    		panelCentral.add(iniciar_1_2_2_1);
	    		
	    		JLabel iniciar_1_2_1_2 = new JLabel("Fecha:");
	    		iniciar_1_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1_2.setBounds(403, 293, 255, 42);
	    		panelCentral.add(iniciar_1_2_1_2);
	    		
	    		JLabel iniciar_1_2_1_1_1 = new JLabel("15/05/2025");
	    		iniciar_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1_1_1.setBounds(403, 320, 255, 42);
	    		panelCentral.add(iniciar_1_2_1_1_1);
	    		
	    		JLabel iniciar_1_2_1_3 = new JLabel("Tipo:");
	    		iniciar_1_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1_3.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1_3.setBounds(687, 215, 101, 42);
	    		panelCentral.add(iniciar_1_2_1_3);
	    		
	    		JLabel iniciar_1_2_1_1_2 = new JLabel("Venta");
	    		iniciar_1_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1_1_2.setBounds(687, 246, 101, 42);
	    		panelCentral.add(iniciar_1_2_1_1_2);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OperacionComprar();
        });
    }
}
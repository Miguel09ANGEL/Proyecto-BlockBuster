package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperacionRentar extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

    public OperacionRentar() {
        // Configuración básica de la ventana
        setTitle("Operación Rentar");
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
	    		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1.setBounds(123, 108, 160, 42); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JTextField nJuego = new JTextField();
	    		nJuego.setBackground(Color.decode("#D9D9D9"));
	    		nJuego.setBounds(133, 141, 302, 27);
	    		panelCentral.add(nJuego);
	    		nJuego.setColumns(10);
	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(175, 406, 183, 33);
	    		btnCancelar.addActionListener(e -> {
	    	            new Renta();         // Abre la segunda ventana
	    	            dispose();            // Cierra la ventana actual
	    	        });
	    		panelCentral.add(btnCancelar);
	    		
	    		
	    		JButton btnConfirmar = new JButton("Confirmar");
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(533, 406, 183, 33);
	    		btnConfirmar.addActionListener(e -> {
    	            new ConfirmaOperacion();         // Abre la segunda ventana
    	            dispose();            // Cierra la ventana actual
    	        	});
	    		panelCentral.add(btnCancelar);
	    		panelCentral.add(btnConfirmar);
	    		
	    		textField_1 = new JTextField();
	    		textField_1.setColumns(10);
	    		textField_1.setBackground(new Color(217, 217, 217));
	    		textField_1.setBounds(550, 141, 302, 27);
	    		panelCentral.add(textField_1);
	    		
	    		JLabel iniciar_1_1 = new JLabel("Nombre del Videojuego:");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1.setBounds(550, 108, 160, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		JLabel iniciar_1_2 = new JLabel("Nombre del Videojuego:");
	    		iniciar_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2.setBounds(123, 179, 160, 42);
	    		panelCentral.add(iniciar_1_2);
	    		
	    		textField_2 = new JTextField();
	    		textField_2.setColumns(10);
	    		textField_2.setBackground(new Color(217, 217, 217));
	    		textField_2.setBounds(133, 211, 302, 27);
	    		panelCentral.add(textField_2);
	    		
	    		JLabel iniciar_1_2_1 = new JLabel("Nombre del Videojuego:");
	    		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1.setBounds(123, 270, 160, 42);
	    		panelCentral.add(iniciar_1_2_1);
	    		
	    		textField_3 = new JTextField();
	    		textField_3.setColumns(10);
	    		textField_3.setBackground(new Color(217, 217, 217));
	    		textField_3.setBounds(133, 306, 302, 27);
	    		panelCentral.add(textField_3);
	    		
	    		JLabel iniciar_1_2_2 = new JLabel("Nombre del Videojuego:");
	    		iniciar_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_2.setBounds(550, 179, 160, 42);
	    		panelCentral.add(iniciar_1_2_2);
	    		
	    		textField_4 = new JTextField();
	    		textField_4.setColumns(10);
	    		textField_4.setBackground(new Color(217, 217, 217));
	    		textField_4.setBounds(550, 214, 302, 27);
	    		panelCentral.add(textField_4);
	    		
	    		JLabel iniciar_1_2_2_1 = new JLabel("Nombre del Videojuego:");
	    		iniciar_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_2_1.setBounds(550, 270, 160, 42);
	    		panelCentral.add(iniciar_1_2_2_1);
	    		
	    		textField_5 = new JTextField();
	    		textField_5.setColumns(10);
	    		textField_5.setBackground(new Color(217, 217, 217));
	    		textField_5.setBounds(550, 306, 302, 27);
	    		panelCentral.add(textField_5);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OperacionRentar();
        });
    }
}
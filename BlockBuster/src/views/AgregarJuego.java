package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarJuego extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

    public AgregarJuego() {
        // Configuración básica de la ventana
        setTitle("Panel Administrador");
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
        
        JLabel iniciar = new JLabel("AGREGAR VIDEOJUEGOS");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
        
	    	
	    	
	    	
	    	JLabel iniciar_1 = new JLabel(
	    		    "Nombre del Videojuego:"
	    		);
	    		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1.setBounds(55, 108, 140, 42); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JLabel iniciar_1_1 = new JLabel("Año de lanzamiento :");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1.setBounds(55, 180, 132, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		JLabel iniciar_1_1_1 = new JLabel("Género:");
	    		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1.setBounds(55, 259, 140, 42);
	    		panelCentral.add(iniciar_1_1_1);
	    		
	    		JLabel iniciar_1_1_1_1 = new JLabel("Precio a renta (MXN)");
	    		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1.setBounds(55, 327, 140, 42);
	    		panelCentral.add(iniciar_1_1_1_1);
	    		
	    		JTextField nJuego = new JTextField();
	    		nJuego.setBackground(Color.decode("#D9D9D9"));
	    		nJuego.setBounds(53, 142, 230, 27);
	    		panelCentral.add(nJuego);
	    		nJuego.setColumns(10);
	    		
	    		textField_1 = new JTextField();
	    		textField_1.setBackground(Color.decode("#D9D9D9"));
	    		textField_1.setColumns(10);
	    		textField_1.setBounds(53, 214, 153, 27);
	    		panelCentral.add(textField_1);
	    		
	    		textField_2 = new JTextField();
	    		textField_2.setBackground(Color.decode("#D9D9D9"));
	    		textField_2.setColumns(10);
	    		textField_2.setBounds(55, 289, 188, 27);
	    		panelCentral.add(textField_2);
	    		
	    		textField_3 = new JTextField();
	    		textField_3.setBackground(Color.decode("#D9D9D9"));
	    		textField_3.setColumns(10);
	    		textField_3.setBounds(55, 368, 188, 27);
	    		panelCentral.add(textField_3);
	    		
	    		textField_4 = new JTextField();
	    		textField_4.setBackground(Color.decode("#D9D9D9"));
	    		textField_4.setColumns(10);
	    		textField_4.setBounds(227, 214, 120, 27);
	    		panelCentral.add(textField_4);
	    		
	    		JLabel iniciar_1_1_2 = new JLabel("Disponibilidad:");
	    		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2.setBounds(227, 180, 97, 42);
	    		panelCentral.add(iniciar_1_1_2);
	    		
	    		textField_5 = new JTextField();
	    		textField_5.setBackground(Color.decode("#D9D9D9"));
	    		textField_5.setColumns(10);
	    		textField_5.setBounds(301, 142, 202, 27);
	    		panelCentral.add(textField_5);
	    		
	    		textField_6 = new JTextField();
	    		textField_6.setBackground(Color.decode("#D9D9D9"));
	    		textField_6.setColumns(10);
	    		textField_6.setBounds(369, 214, 134, 27);
	    		panelCentral.add(textField_6);
	    		
	    		textField_7 = new JTextField();
	    		textField_7.setBackground(Color.decode("#D9D9D9"));
	    		textField_7.setColumns(10);
	    		textField_7.setBounds(288, 289, 215, 27);
	    		panelCentral.add(textField_7);
	    		
	    		textField_8 = new JTextField();
	    		textField_8.setBackground(Color.decode("#D9D9D9"));
	    		textField_8.setColumns(10);
	    		textField_8.setBounds(288, 368, 215, 27);
	    		panelCentral.add(textField_8);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Plataforma:");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_1.setBounds(301, 108, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_1);
	    		
	    		JLabel iniciar_1_1_2_2 = new JLabel("Clasificación:");
	    		iniciar_1_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_2.setBounds(369, 180, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_2);
	    		
	    		JLabel iniciar_1_1_3 = new JLabel("Existencias disponibles:");
	    		iniciar_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_3.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_3.setBounds(289, 252, 153, 42);
	    		panelCentral.add(iniciar_1_1_3);
	    		
	    		JLabel iniciar_1_1_1_1_1 = new JLabel("Precio a venta (MXN):");
	    		iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1_1.setBounds(288, 327, 140, 42);
	    		panelCentral.add(iniciar_1_1_1_1_1);
	    		
	    		textField_9 = new JTextField();
	    		textField_9.setBackground(Color.decode("#D9D9D9"));
	    		textField_9.setColumns(10);
	    		textField_9.setBounds(619, 259, 343, 136);
	    		panelCentral.add(textField_9);
	    		
	    		JLabel iniciar_1_1_2_2_1 = new JLabel("Acerca de:");
	    		iniciar_1_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_2_1.setBounds(619, 220, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_2_1);
	    		
	    		JButton btnNewButton = new JButton("New button");
	    		btnNewButton.setBounds(619, 142, 343, 86);
	    		panelCentral.add(btnNewButton);
	    		
	    		JLabel iniciar_1_1_2_2_1_1 = new JLabel("Acerca de:");
	    		iniciar_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_2_1_1.setBounds(619, 108, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_2_1_1);
	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(175, 406, 183, 33);
	    		panelCentral.add(btnCancelar);
	    		
	    		JButton btnConfirmar = new JButton("Confirmar");
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(533, 406, 183, 33);
	    		panelCentral.add(btnConfirmar);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AgregarJuego();
        });
    }
}
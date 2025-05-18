package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCliente extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

    public EditarCliente() {
        // Configuración básica de la ventana
        setTitle("Editar Cliente");
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
        
        JLabel iniciar = new JLabel("EDITAR CLIENTE");
		iniciar.setSize(263, 42);
		iniciar.setLocation(370, 94);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
        
	    	
	    	
	    	
	    	JLabel iniciar_1 = new JLabel(
	    		    "Nombre:"
	    		);
	    		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1.setBounds(84, 135, 87, 42); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JTextField nJuego = new JTextField();
	    		nJuego.setBackground(Color.decode("#D9D9D9"));
	    		nJuego.setBounds(84, 163, 330, 27);
	    		panelCentral.add(nJuego);
	    		nJuego.setColumns(10);
	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(175, 406, 183, 33);
	    		btnCancelar.addActionListener(e -> {
	    	            new DetallesCliente();         // Abre la segunda ventana
	    	            dispose();            // Cierra la ventana actual
	    	        });
	    		panelCentral.add(btnCancelar);
	    		
	    		
	    		JButton btnConfirmar = new JButton("Confirmar");
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(533, 406, 183, 33);
	    		btnConfirmar.addActionListener(e -> {
    	            new Confirma_2();         // Abre la segunda ventana
    	                        // Cierra la ventana actual
    	        });
	    		panelCentral.add(btnConfirmar);
	    		
	    		JLabel iniciar_1_1 = new JLabel("Apellido materno:");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1.setBounds(84, 209, 115, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		textField_1 = new JTextField();
	    		textField_1.setColumns(10);
	    		textField_1.setBackground(new Color(217, 217, 217));
	    		textField_1.setBounds(84, 236, 330, 27);
	    		panelCentral.add(textField_1);
	    		
	    		JLabel iniciar_1_1_1 = new JLabel("Telefono:");
	    		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1.setBounds(84, 282, 87, 42);
	    		panelCentral.add(iniciar_1_1_1);
	    		
	    		textField_2 = new JTextField();
	    		textField_2.setColumns(10);
	    		textField_2.setBackground(new Color(217, 217, 217));
	    		textField_2.setBounds(84, 311, 330, 27);
	    		panelCentral.add(textField_2);
	    		
	    		textField_3 = new JTextField();
	    		textField_3.setColumns(10);
	    		textField_3.setBackground(new Color(217, 217, 217));
	    		textField_3.setBounds(596, 163, 330, 27);
	    		panelCentral.add(textField_3);
	    		
	    		textField_4 = new JTextField();
	    		textField_4.setColumns(10);
	    		textField_4.setBackground(new Color(217, 217, 217));
	    		textField_4.setBounds(596, 236, 330, 27);
	    		panelCentral.add(textField_4);
	    		
	    		textField_5 = new JTextField();
	    		textField_5.setColumns(10);
	    		textField_5.setBackground(new Color(217, 217, 217));
	    		textField_5.setBounds(596, 311, 330, 27);
	    		panelCentral.add(textField_5);
	    		
	    		JLabel iniciar_1_2 = new JLabel("Apellido paterno:");
	    		iniciar_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2.setBounds(594, 135, 122, 42);
	    		panelCentral.add(iniciar_1_2);
	    		
	    		JLabel iniciar_1_1_2 = new JLabel("Fecha de nacimiento:");
	    		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2.setBounds(596, 209, 136, 42);
	    		panelCentral.add(iniciar_1_1_2);
	    		
	    		JLabel iniciar_1_1_1_1 = new JLabel("Correo:");
	    		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1.setBounds(596, 282, 87, 42);
	    		panelCentral.add(iniciar_1_1_1_1);

	    		

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditarCliente();
        });
    }
}
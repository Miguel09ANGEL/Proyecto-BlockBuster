package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarPromociones extends JFrame {
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
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;

    public EditarPromociones() {
        // Configuración básica de la ventana
        setTitle("Editar Promociones");
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
        
        JLabel iniciar = new JLabel("EDITAR PROMOCIONES");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
        
	    	
	    	
	    	
	    	JLabel iniciar_1 = new JLabel(
	    		    "Compra de:"
	    		);
	    		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1.setBounds(85, 120, 160, 42); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JTextField nJuego = new JTextField();
	    		nJuego.setBackground(Color.decode("#D9D9D9"));
	    		nJuego.setBounds(85, 147, 189, 27);
	    		panelCentral.add(nJuego);
	    		nJuego.setColumns(10);
	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(175, 406, 183, 33);
	    		btnCancelar.addActionListener(e -> {
	    	            new PromocionAutomatica();         // Abre la segunda ventana
	    	            dispose();            // Cierra la ventana actual
	    	        });
	    		panelCentral.add(btnCancelar);
	    		
	    		
	    		JButton btnConfirmar = new JButton("ACTUALIZAR");
	    		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(582, 406, 183, 33);
	    		btnConfirmar.addActionListener(e -> {
    	            new Confirma_15();         // Abre la segunda ventana
    	                        // Cierra la ventana actual
    	        	});
	    		panelCentral.add(btnCancelar);
	    		panelCentral.add(btnConfirmar);
	    		
	    		JLabel iniciar_1_1 = new JLabel("Compra de:");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1.setBounds(85, 181, 160, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		textField_1 = new JTextField();
	    		textField_1.setColumns(10);
	    		textField_1.setBackground(new Color(217, 217, 217));
	    		textField_1.setBounds(85, 207, 189, 27);
	    		panelCentral.add(textField_1);
	    		
	    		JLabel iniciar_1_1_1 = new JLabel("Compra de:");
	    		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1.setBounds(85, 246, 160, 42);
	    		panelCentral.add(iniciar_1_1_1);
	    		
	    		textField_2 = new JTextField();
	    		textField_2.setColumns(10);
	    		textField_2.setBackground(new Color(217, 217, 217));
	    		textField_2.setBounds(85, 273, 189, 27);
	    		panelCentral.add(textField_2);
	    		
	    		JLabel iniciar_1_1_1_1 = new JLabel("Compra de:");
	    		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1.setBounds(85, 313, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_1);
	    		
	    		textField_3 = new JTextField();
	    		textField_3.setColumns(10);
	    		textField_3.setBackground(new Color(217, 217, 217));
	    		textField_3.setBounds(85, 339, 189, 27);
	    		panelCentral.add(textField_3);
	    		
	    		textField_4 = new JTextField();
	    		textField_4.setColumns(10);
	    		textField_4.setBackground(new Color(217, 217, 217));
	    		textField_4.setBounds(306, 150, 189, 27);
	    		panelCentral.add(textField_4);
	    		
	    		textField_5 = new JTextField();
	    		textField_5.setColumns(10);
	    		textField_5.setBackground(new Color(217, 217, 217));
	    		textField_5.setBounds(306, 210, 189, 27);
	    		panelCentral.add(textField_5);
	    		
	    		textField_6 = new JTextField();
	    		textField_6.setColumns(10);
	    		textField_6.setBackground(new Color(217, 217, 217));
	    		textField_6.setBounds(306, 276, 189, 27);
	    		panelCentral.add(textField_6);
	    		
	    		textField_7 = new JTextField();
	    		textField_7.setColumns(10);
	    		textField_7.setBackground(new Color(217, 217, 217));
	    		textField_7.setBounds(306, 342, 189, 27);
	    		panelCentral.add(textField_7);
	    		
	    		textField_8 = new JTextField();
	    		textField_8.setColumns(10);
	    		textField_8.setBackground(new Color(217, 217, 217));
	    		textField_8.setBounds(530, 150, 189, 27);
	    		panelCentral.add(textField_8);
	    		
	    		textField_9 = new JTextField();
	    		textField_9.setColumns(10);
	    		textField_9.setBackground(new Color(217, 217, 217));
	    		textField_9.setBounds(530, 210, 189, 27);
	    		panelCentral.add(textField_9);
	    		
	    		textField_10 = new JTextField();
	    		textField_10.setColumns(10);
	    		textField_10.setBackground(new Color(217, 217, 217));
	    		textField_10.setBounds(530, 276, 189, 27);
	    		panelCentral.add(textField_10);
	    		
	    		textField_11 = new JTextField();
	    		textField_11.setColumns(10);
	    		textField_11.setBackground(new Color(217, 217, 217));
	    		textField_11.setBounds(530, 342, 189, 27);
	    		panelCentral.add(textField_11);
	    		
	    		textField_12 = new JTextField();
	    		textField_12.setColumns(10);
	    		textField_12.setBackground(new Color(217, 217, 217));
	    		textField_12.setBounds(749, 150, 189, 27);
	    		panelCentral.add(textField_12);
	    		
	    		textField_13 = new JTextField();
	    		textField_13.setColumns(10);
	    		textField_13.setBackground(new Color(217, 217, 217));
	    		textField_13.setBounds(749, 210, 189, 27);
	    		panelCentral.add(textField_13);
	    		
	    		textField_14 = new JTextField();
	    		textField_14.setColumns(10);
	    		textField_14.setBackground(new Color(217, 217, 217));
	    		textField_14.setBounds(749, 276, 189, 27);
	    		panelCentral.add(textField_14);
	    		
	    		textField_15 = new JTextField();
	    		textField_15.setColumns(10);
	    		textField_15.setBackground(new Color(217, 217, 217));
	    		textField_15.setBounds(749, 342, 189, 27);
	    		panelCentral.add(textField_15);
	    		
	    		JLabel iniciar_1_2 = new JLabel("Promocion de:");
	    		iniciar_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2.setBounds(306, 120, 160, 42);
	    		panelCentral.add(iniciar_1_2);
	    		
	    		JLabel iniciar_1_1_2 = new JLabel("Promocion de:");
	    		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2.setBounds(306, 181, 160, 42);
	    		panelCentral.add(iniciar_1_1_2);
	    		
	    		JLabel iniciar_1_1_1_2 = new JLabel("Promocion de:");
	    		iniciar_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_2.setBounds(306, 246, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_2);
	    		
	    		JLabel iniciar_1_1_1_1_1 = new JLabel("Promocion de:");
	    		iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1_1.setBounds(306, 313, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_1_1);
	    		
	    		JLabel iniciar_1_2_1 = new JLabel("Compra de:");
	    		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1.setBounds(530, 120, 160, 42);
	    		panelCentral.add(iniciar_1_2_1);
	    		
	    		JLabel iniciar_1_2_1_1 = new JLabel("Promocion de:");
	    		iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_2_1_1.setBounds(749, 120, 160, 42);
	    		panelCentral.add(iniciar_1_2_1_1);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Compra de:");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_1.setBounds(530, 181, 160, 42);
	    		panelCentral.add(iniciar_1_1_2_1);
	    		
	    		JLabel iniciar_1_1_2_2 = new JLabel("Promocion de:");
	    		iniciar_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_2.setBounds(749, 181, 160, 42);
	    		panelCentral.add(iniciar_1_1_2_2);
	    		
	    		JLabel iniciar_1_1_1_2_1 = new JLabel("Compra de:");
	    		iniciar_1_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_2_1.setBounds(530, 246, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_2_1);
	    		
	    		JLabel iniciar_1_1_1_2_1_1 = new JLabel("Promocion de:");
	    		iniciar_1_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_2_1_1.setBounds(749, 246, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_2_1_1);
	    		
	    		JLabel iniciar_1_1_1_1_1_1 = new JLabel("Compra de:");
	    		iniciar_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1_1_1.setBounds(530, 313, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_1_1_1);
	    		
	    		JLabel iniciar_1_1_1_1_1_2 = new JLabel("Promocion de:");
	    		iniciar_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_1_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1_1_2.setBounds(749, 313, 160, 42);
	    		panelCentral.add(iniciar_1_1_1_1_1_2);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditarPromociones();
        });
    }
}
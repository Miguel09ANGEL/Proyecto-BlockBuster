package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TarjetaCliente extends JFrame {
	private JTextField textField;

    public TarjetaCliente() {
        // Configuración básica de la ventana
        setTitle("Tarjeta Cliente");
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
        panelCentral.setBackground(Color.decode("#D9D9D9"));
        panelCentral.setBounds(270, 71, 486, 460); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
        logo.setBounds(213, 23, 70, 70); //posicion
        panelCentral.add(logo); 
        
        JLabel iniciar = new JLabel("INFORMACIÓN CLIENTE");
		iniciar.setSize(263, 42);
		iniciar.setLocation(121, 104);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
	    		
	    		JButton btnCancelar = new JButton("Regresar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(47, 406, 183, 33);
	    		btnCancelar.addActionListener(e -> {
	    	            new InformacionCliente();         // Abre la segunda ventana
	    	            dispose();            // Cierra la ventana actual
	    	        });
	    		panelCentral.add(btnCancelar);
	    		
	    		
	    		JButton btnConfirmar = new JButton("Descargar PDF");
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				 new Confirmar_4();
	    			}
	    		});
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(281, 406, 183, 33);
	    		panelCentral.add(btnConfirmar);
	    		
	    		JLabel iniciar_1_3 = new JLabel("Nombre:");
	    		iniciar_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_3.setBounds(96, 156, 107, 42);
	    		panelCentral.add(iniciar_1_3);
	    		
	    		JLabel iniciar_1_1_2 = new JLabel("Manuel orozco vazquez");
	    		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1_2.setBounds(192, 157, 192, 42);
	    		panelCentral.add(iniciar_1_1_2);
	    		
	    		JLabel iniciar_1_3_1 = new JLabel("Numero de control: ");
	    		iniciar_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_3_1.setBounds(121, 209, 161, 42);
	    		panelCentral.add(iniciar_1_3_1);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("00001");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1_2_1.setBounds(244, 209, 131, 42);
	    		panelCentral.add(iniciar_1_1_2_1);
	    		
	    		JLabel iniciar_1_3_2 = new JLabel("Correo:");
	    		iniciar_1_3_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_3_2.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_3_2.setBounds(123, 262, 107, 42);
	    		panelCentral.add(iniciar_1_3_2);
	    		
	    		JLabel iniciar_1_1_2_1_1 = new JLabel("mov@gmail.com");
	    		iniciar_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1_2_1_1.setBounds(213, 262, 162, 42);
	    		panelCentral.add(iniciar_1_1_2_1_1);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TarjetaCliente();
        });
    }
}
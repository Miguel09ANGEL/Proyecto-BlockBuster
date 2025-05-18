package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformacionCliente extends JFrame {
	private JTextField textField;

    public InformacionCliente() {
        // Configuración básica de la ventana
        setTitle("Informacion Cliente");
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
        panelCentral.setBounds(32, 62, 951, 475); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
        logo.setBounds(450, 11, 70, 70); //posicion
        panelCentral.add(logo); 
        
        JLabel iniciar = new JLabel("INFORMACIÓN CLIENTE");
		iniciar.setSize(263, 42);
		iniciar.setLocation(355, 93);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
        
	    	
	    	
	    	
	    	JLabel iniciar_1 = new JLabel( "Nombre:");
	    		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1.setBounds(84, 150, 87, 42); // Ajusta tamaño si es necesario
	    		panelCentral.add(iniciar_1);
	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(103, 406, 183, 33);
	    		btnCancelar.addActionListener(e -> {
	    	            new DetallesCliente();         // Abre la segunda ventana
	    	            dispose();            // Cierra la ventana actual
	    	        });
	    		panelCentral.add(btnCancelar);
	    		
	    		
	    		JButton btnConfirmar = new JButton("DESCARGAR PDF");
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(621, 406, 183, 33);
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new Confirma_3();         // Abre la segunda ventana
	    	              
	    			}
	    		});
	    		panelCentral.add(btnConfirmar);
	    		
	    		JButton btnCredencialpdf = new JButton("Credencial (PDF");
	    		btnCredencialpdf.setForeground(Color.WHITE);
	    		btnCredencialpdf.setBackground(Color.decode("#686868"));
	    		btnCredencialpdf.setBounds(370, 406, 183, 33);
	    		btnCredencialpdf.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new TarjetaCliente();         // Abre la segunda ventana
	    				dispose();
	    	              
	    			}
	    		});
	    		panelCentral.add(btnCredencialpdf);
	    		
	    		JLabel iniciar_1_1 = new JLabel("Manuel orozco vazquez");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1.setBounds(58, 177, 141, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		JLabel iniciar_1_2 = new JLabel("Correo:");
	    		iniciar_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_2.setBounds(84, 262, 87, 42);
	    		panelCentral.add(iniciar_1_2);
	    		
	    		JLabel iniciar_1_1_1 = new JLabel("mov@gmail.com");
	    		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1.setBounds(58, 287, 141, 42);
	    		panelCentral.add(iniciar_1_1_1);
	    		
	    		JLabel iniciar_1_3 = new JLabel("Fecha de nacimineto:");
	    		iniciar_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_3.setBounds(384, 150, 183, 42);
	    		panelCentral.add(iniciar_1_3);
	    		
	    		JLabel iniciar_1_1_2 = new JLabel("12/06/25");
	    		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2.setBounds(394, 177, 141, 42);
	    		panelCentral.add(iniciar_1_1_2);
	    		
	    		JLabel iniciar_1_2_1 = new JLabel("Numero de control:");
	    		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_2_1.setBounds(397, 262, 170, 42);
	    		panelCentral.add(iniciar_1_2_1);
	    		
	    		JLabel iniciar_1_1_1_1 = new JLabel("00001");
	    		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1.setBounds(394, 287, 141, 42);
	    		panelCentral.add(iniciar_1_1_1_1);
	    		
	    		JLabel iniciar_1_3_1 = new JLabel("Numero de telefono: ");
	    		iniciar_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_3_1.setBounds(695, 150, 177, 42);
	    		panelCentral.add(iniciar_1_3_1);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("6120000000");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_1.setBounds(709, 177, 141, 42);
	    		panelCentral.add(iniciar_1_1_2_1);
	    		
	    		JLabel iniciar_1_2_1_1 = new JLabel("Fecha de registro:");
	    		iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_2_1_1.setBounds(709, 262, 163, 42);
	    		panelCentral.add(iniciar_1_2_1_1);
	    		
	    		JLabel iniciar_1_1_1_1_1 = new JLabel(" 14-05-2025");
	    		iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_1_1_1.setBounds(709, 287, 141, 42);
	    		panelCentral.add(iniciar_1_1_1_1_1);
	    		
	    		

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InformacionCliente();
        });
    }
}
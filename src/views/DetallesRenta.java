package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesRenta extends JFrame {
	private JTextField textField;

    public DetallesRenta() {
        // Configuración básica de la ventana
        setTitle("Detalles VideoJuego Rentado");
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
        panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/contra.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        
        JLabel iniciar = new JLabel("DETALLES DE VIDEOJUEGO");
		iniciar.setSize(285, 42);
		iniciar.setLocation(442, 32);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Miguel Garcia");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1_2_1.setBounds(226, 102, 112, 42);
	    		panelCentral.add(iniciar_1_1_2_1);

	    		
	    		JButton btnCancelar = new JButton("REGRESAR");
	    		btnCancelar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new Renta();
	    				dispose();
	    			}
	    		});
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(185, 406, 183, 33);
	    		panelCentral.add(btnCancelar);
	    		
	    		JButton btnConfirmar = new JButton("Descargar (PDF)");
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				 new Confirma_12();
	    				 
	    			}
	    		});
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(614, 406, 183, 33);
	    		panelCentral.add(btnConfirmar);
	    		
	    		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Contra.png"));
	            Image imagen = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
	            JLabel logo = new JLabel(new ImageIcon(imagen));
	            logo.setBounds(32, 32, 184, 112); //posicion
	            panelCentral.add(logo);
	            
	            JLabel lblContra = new JLabel("CONTRA");
	            lblContra.setHorizontalAlignment(SwingConstants.LEFT);
	            lblContra.setFont(new Font("Calibri", Font.BOLD, 24));
	            lblContra.setBounds(32, 141, 135, 42);
	            panelCentral.add(lblContra);
	            
	            JLabel iniciar_1_1_1_1_2_2_1 = new JLabel("$108.00 MXN");
	            iniciar_1_1_1_1_2_2_1.setForeground(new Color(153, 0, 0));
	            iniciar_1_1_1_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_2_2_1.setBounds(719, 359, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_2_1);
	            
	            JTextArea txtrTotalivaIncluidoEn = new JTextArea("Total(IVA incluído, en caso de ser aplicable):");
	            txtrTotalivaIncluidoEn.setWrapStyleWord(true);
	            txtrTotalivaIncluidoEn.setOpaque(false);
	            txtrTotalivaIncluidoEn.setLineWrap(true);
	            txtrTotalivaIncluidoEn.setFont(new Font("Calibri", Font.BOLD, 20));
	            txtrTotalivaIncluidoEn.setEditable(false);
	            txtrTotalivaIncluidoEn.setBounds(628, 341, 298, 54);
	            panelCentral.add(txtrTotalivaIncluidoEn);
	            
	            JLabel iniciar_1_1_2 = new JLabel("Cliente:");
	            iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2.setBounds(256, 85, 70, 42);
	            panelCentral.add(iniciar_1_1_2);
	            
	            JLabel iniciar_1_1_2_2 = new JLabel("Correo:");
	            iniciar_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2.setBounds(503, 85, 70, 42);
	            panelCentral.add(iniciar_1_1_2_2);
	            
	            JLabel iniciar_1_1_2_1_1 = new JLabel("mga@gmail.com");
	            iniciar_1_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1.setBounds(466, 102, 149, 42);
	            panelCentral.add(iniciar_1_1_2_1_1);
	            
	            JLabel iniciar_1_1_2_2_1_1 = new JLabel("Fecha de renta:");
	            iniciar_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_1.setBounds(426, 204, 126, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_1);
	            
	            JLabel iniciar_1_1_2_2_1_1_1 = new JLabel("Límite de devolución");
	            iniciar_1_1_2_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_1_1.setBounds(407, 285, 157, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_1_1);
	            
	            JLabel iniciar_1_1_2_2_1_2 = new JLabel("Información de pago");
	            iniciar_1_1_2_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_2.setBounds(700, 141, 167, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_2);
	            
	            JLabel iniciar_1_1_2_2_2 = new JLabel("Tipo:");
	            iniciar_1_1_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_2_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_2.setBounds(772, 85, 70, 42);
	            panelCentral.add(iniciar_1_1_2_2_2);
	            
	            JLabel iniciar_1_1_2_1_1_3 = new JLabel("Renta");
	            iniciar_1_1_2_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1_3.setBounds(755, 102, 112, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_3);
	            
	            JLabel iniciar_1_1_2_2_1_3 = new JLabel("Clasificación:");
	            iniciar_1_1_2_2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_3.setBounds(188, 204, 112, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_3);
	            
	            JLabel iniciar_1_1_2_1_2 = new JLabel("E");
	            iniciar_1_1_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_2.setBounds(185, 232, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_2);
	            
	            JLabel iniciar_1_1_2_2_1_4 = new JLabel("Distribuidores:");
	            iniciar_1_1_2_2_1_4.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_4.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_4.setBounds(188, 285, 112, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_4);
	            
	            JLabel iniciar_1_1_2_1_3 = new JLabel("Konami");
	            iniciar_1_1_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_3.setBounds(198, 309, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_3);
	            
	            JLabel iniciar_1_1_2_1_2_1 = new JLabel("15/02/2025");
	            iniciar_1_1_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_2_1.setBounds(436, 232, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_2_1);
	            
	            JLabel iniciar_1_1_2_1_3_1 = new JLabel("15/03/2025");
	            iniciar_1_1_2_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_3_1.setBounds(436, 309, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_3_1);
	            
	            JLabel iniciar_1_1_2_1_4 = new JLabel("Cantidad:");
	            iniciar_1_1_2_1_4.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4.setBounds(700, 174, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4);
	            
	            JLabel iniciar_1_1_2_1_4_1 = new JLabel("Producto:");
	            iniciar_1_1_2_1_4_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_1.setBounds(700, 205, 70, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_1);
	            
	            JLabel iniciar_1_1_2_1_4_1_1 = new JLabel("IVA:");
	            iniciar_1_1_2_1_4_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_1_1.setBounds(700, 232, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_1_1);
	            
	            JLabel iniciar_1_1_2_1_4_1_2 = new JLabel("Subtotal:");
	            iniciar_1_1_2_1_4_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_1_2.setBounds(700, 258, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_1_2);
	            
	            JLabel iniciar_1_1_2_1_4_1_2_1 = new JLabel("Descuento:");
	            iniciar_1_1_2_1_4_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_1_2_1.setBounds(700, 285, 70, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_1_2_1);
	            
	            JLabel iniciar_1_1_2_1_4_2 = new JLabel("1");
	            iniciar_1_1_2_1_4_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_2.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_2.setBounds(760, 174, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_2);
	            
	            JLabel iniciar_1_1_2_1_4_2_1 = new JLabel("100.00 MXN");
	            iniciar_1_1_2_1_4_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_2_1.setBounds(760, 204, 85, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_2_1);
	            
	            JLabel iniciar_1_1_2_1_4_2_2 = new JLabel("$8");
	            iniciar_1_1_2_1_4_2_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_2_2.setBounds(728, 232, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_2_2);
	            
	            JLabel iniciar_1_1_2_1_4_2_3 = new JLabel("$108.00 MXN");
	            iniciar_1_1_2_1_4_2_3.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_2_3.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_2_3.setBounds(760, 258, 78, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_2_3);
	            
	            JLabel iniciar_1_1_2_1_4_2_4 = new JLabel("7%");
	            iniciar_1_1_2_1_4_2_4.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_2_4.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_4_2_4.setBounds(770, 285, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_2_4);
	            
	            JLabel iniciar_1_1_2_1_4_3 = new JLabel("1987");
	            iniciar_1_1_2_1_4_3.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_4_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_4_3.setBounds(42, 174, 57, 42);
	            panelCentral.add(iniciar_1_1_2_1_4_3);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DetallesRenta();
        });
    }
}
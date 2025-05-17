package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesRenta extends JFrame {
	private JTextField textField;

    public DetallesRenta() {
        // Configuración básica de la ventana
        setTitle("DetallesRenta");
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
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        
        JLabel iniciar = new JLabel("DETALLES DE VIDEOJUEGO");
		iniciar.setSize(285, 42);
		iniciar.setLocation(366, 32);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
	    		
	    		JLabel iniciar_1_1 = new JLabel("1988");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1.setBounds(55, 155, 70, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Miguel Garcia");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_1.setBounds(428, 85, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_1);

	    		
	    		JButton btnCancelar = new JButton("MENU DE RENTA");
	    		btnCancelar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new AdministradorRentaCompra();
	    				dispose();
	    			}
	    		});
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(450, 406, 183, 33);
	    		panelCentral.add(btnCancelar);
	    		
	    		JButton btnConfirmar = new JButton("Descargar (PDF)");
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				 new AvisoRenta();
	    				 dispose();
	    			}
	    		});
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(682, 406, 183, 33);
	    		panelCentral.add(btnConfirmar);
	    		
	    		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Block.png"));
	            Image imagen = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	            JLabel logo = new JLabel(new ImageIcon(imagen));
	            logo.setBounds(55, 32, 70, 70); //posicion
	            panelCentral.add(logo);
	            
	            JLabel lblContra = new JLabel("CONTRA");
	            lblContra.setHorizontalAlignment(SwingConstants.LEFT);
	            lblContra.setFont(new Font("Calibri", Font.BOLD, 24));
	            lblContra.setBounds(55, 127, 135, 42);
	            panelCentral.add(lblContra);
	            
	            JLabel iniciar_1_1_1 = new JLabel("Clasificación: Mayores 18");
	            iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1.setBounds(55, 191, 190, 42);
	            panelCentral.add(iniciar_1_1_1);
	            
	            JLabel iniciar_1_1_1_1 = new JLabel("Distribuidores: Konami");
	            iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1.setBounds(55, 219, 190, 42);
	            panelCentral.add(iniciar_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_1 = new JLabel("Precio: $100.00 MXN");
	            iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_1.setBounds(55, 258, 190, 42);
	            panelCentral.add(iniciar_1_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_2_2_1 = new JLabel("$800.00MX");
	            iniciar_1_1_1_1_2_2_1.setForeground(new Color(153, 0, 0));
	            iniciar_1_1_1_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_2_2_1.setBounds(743, 341, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_2_1);
	            
	            JTextArea txtrTotalivaIncluidoEn = new JTextArea("Total(IVA incluído, en caso de ser aplicable):");
	            txtrTotalivaIncluidoEn.setWrapStyleWord(true);
	            txtrTotalivaIncluidoEn.setOpaque(false);
	            txtrTotalivaIncluidoEn.setLineWrap(true);
	            txtrTotalivaIncluidoEn.setFont(new Font("Calibri", Font.BOLD, 20));
	            txtrTotalivaIncluidoEn.setEditable(false);
	            txtrTotalivaIncluidoEn.setBounds(648, 324, 298, 54);
	            panelCentral.add(txtrTotalivaIncluidoEn);
	            
	            JLabel iniciar_1_1_1_1_1_1 = new JLabel("Folio: 0002");
	            iniciar_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_1_1.setBounds(55, 305, 190, 42);
	            panelCentral.add(iniciar_1_1_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_1_1_1 = new JLabel("Tipo: Renta");
	            iniciar_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_1_1_1.setBounds(55, 354, 190, 42);
	            panelCentral.add(iniciar_1_1_1_1_1_1_1);
	            
	            JLabel iniciar_1_1_2 = new JLabel("Cliente:");
	            iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2.setBounds(376, 85, 70, 42);
	            panelCentral.add(iniciar_1_1_2);
	            
	            JLabel iniciar_1_1_2_2 = new JLabel("Correo:");
	            iniciar_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2.setBounds(376, 113, 70, 42);
	            panelCentral.add(iniciar_1_1_2_2);
	            
	            JLabel iniciar_1_1_2_1_1 = new JLabel("mga@gmail.com");
	            iniciar_1_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_1.setBounds(428, 113, 112, 42);
	            panelCentral.add(iniciar_1_1_2_1_1);
	            
	            JLabel iniciar_1_1_2_2_1 = new JLabel("Vigencia:");
	            iniciar_1_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1.setBounds(376, 155, 70, 42);
	            panelCentral.add(iniciar_1_1_2_2_1);
	            
	            JLabel iniciar_1_1_2_1_1_1 = new JLabel("15/02/2025");
	            iniciar_1_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1_1.setBounds(352, 185, 112, 33);
	            panelCentral.add(iniciar_1_1_2_1_1_1);
	            
	            JLabel iniciar_1_1_2_2_1_1 = new JLabel("Fecha de renta");
	            iniciar_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_1.setBounds(352, 219, 112, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_1);
	            
	            JLabel iniciar_1_1_2_1_1_1_1 = new JLabel("15/02/2025");
	            iniciar_1_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1_1_1.setBounds(352, 249, 112, 33);
	            panelCentral.add(iniciar_1_1_2_1_1_1_1);
	            
	            JLabel iniciar_1_1_2_2_1_1_1 = new JLabel("Límite de devolución");
	            iniciar_1_1_2_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_1_1.setBounds(331, 285, 157, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_1_1);
	            
	            JLabel iniciar_1_1_2_1_1_1_1_1 = new JLabel("15/02/2025");
	            iniciar_1_1_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_2_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1_1_1_1.setBounds(352, 318, 112, 33);
	            panelCentral.add(iniciar_1_1_2_1_1_1_1_1);
	            
	            JLabel iniciar_1_1_2_2_1_2 = new JLabel("Información de pago");
	            iniciar_1_1_2_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_2_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_2_1_2.setBounds(729, 141, 167, 42);
	            panelCentral.add(iniciar_1_1_2_2_1_2);
	            
	            JLabel iniciar_1_1_2_1_1_2 = new JLabel("Producto: $100.00 MXN");
	            iniciar_1_1_2_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_1_2.setBounds(729, 181, 167, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_2);
	            
	            JLabel iniciar_1_1_2_1_1_2_1 = new JLabel("IVA:$8");
	            iniciar_1_1_2_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_1_2_1.setBounds(729, 205, 167, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_2_1);
	            
	            JLabel iniciar_1_1_2_1_1_2_1_1 = new JLabel("Subtotal: $108.00 MXN");
	            iniciar_1_1_2_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_1_2_1_1.setBounds(729, 245, 167, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_2_1_1);
	            
	            JLabel iniciar_1_1_2_1_1_2_1_2 = new JLabel("Descuento: 7%");
	            iniciar_1_1_2_1_1_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1_2_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
	            iniciar_1_1_2_1_1_2_1_2.setBounds(729, 274, 167, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_2_1_2);

        

       
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
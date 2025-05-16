package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesJuego extends JFrame {
	private JTextField textField;

    public DetallesJuego() {
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
        panelCentral.setBackground(Color.decode("#D9D9D9"));
        panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        
        JLabel iniciar = new JLabel("DETALLES DE VIDEOJUEGO");
		iniciar.setSize(285, 42);
		iniciar.setLocation(431, 30);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
	    		
	    		JLabel iniciar_1_1 = new JLabel("1988");
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1.setBounds(55, 180, 70, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Plataforma:");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
	    		iniciar_1_1_2_1.setBounds(369, 61, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_1);
	    		
	    		JTextArea descripcion = new JTextArea("Contra es un videojuego de shoot 'em up desarrollado y publicado por Konami, lanzado originalmente como un juego de arcade el 20 de febrero de 1987. En 1988 se lanzó una versión doméstica para Nintendo Entertainment System, junto con puertos para varios formatos de computadora, incluido el MSX2.");
	    		descripcion.setFont(new Font("Calibri", Font.BOLD, 20));
	    		descripcion.setLineWrap(true); // Ajuste automático de línea
	    		descripcion.setWrapStyleWord(true); // Cortar por palabra
	    		descripcion.setEditable(false);
	    		descripcion.setOpaque(false); // Para que se vea como JLabel
	    		descripcion.setBounds(379, 108, 567, 145);
	    		panelCentral.add(descripcion);

	    		
	    		JButton btnCancelar = new JButton("Editar");
	    		btnCancelar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new EditarJuego();
	    				dispose();
	    			}
	    		});
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(511, 406, 183, 33);
	    		panelCentral.add(btnCancelar);
	    		
	    		JButton btnConfirmar = new JButton("Descargar (PDF)");
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				 new PDF();
	    				 dispose();
	    			}
	    		});
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(730, 406, 183, 33);
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
	            iniciar_1_1_1.setBounds(55, 232, 190, 42);
	            panelCentral.add(iniciar_1_1_1);
	            
	            JLabel iniciar_1_1_1_1 = new JLabel("Género: Disparos");
	            iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1.setBounds(55, 317, 190, 42);
	            panelCentral.add(iniciar_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_1 = new JLabel("Plataforma: Nintendo");
	            iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_1.setBounds(55, 374, 190, 42);
	            panelCentral.add(iniciar_1_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_2 = new JLabel("Precio por renta:");
	            iniciar_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_2.setBounds(688, 317, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2);
	            
	            JLabel iniciar_1_1_1_1_2_1 = new JLabel("Precio por venta:");
	            iniciar_1_1_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_2_1.setBounds(688, 353, 135, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_1);
	            
	            JLabel iniciar_1_1_1_1_2_2 = new JLabel("$100.00MXN");
	            iniciar_1_1_1_1_2_2.setForeground(new Color(153, 0, 0));
	            iniciar_1_1_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_2_2.setBounds(824, 317, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_2);
	            
	            JLabel iniciar_1_1_1_1_2_2_1 = new JLabel("$800.00MX");
	            iniciar_1_1_1_1_2_2_1.setForeground(new Color(153, 0, 0));
	            iniciar_1_1_1_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_2_2_1.setBounds(824, 353, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_2_1);

        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DetallesJuego();
        });
    }
}
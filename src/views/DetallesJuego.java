package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesJuego extends JFrame {
	private JTextField textField;

    public DetallesJuego() {
        // Configuración básica de la ventana
        setTitle("Detalles Videojuego");
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
        iniciar.setForeground(Color.decode("#263C54"));
		iniciar.setSize(331, 42);
		iniciar.setLocation(394, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Anton", Font.BOLD, 20));
		panelCentral.add(iniciar);
	    		
		 		
		 		
	    		JLabel iniciar_1_1 = new JLabel("1988");
	    		iniciar_1_1.setForeground(Color.decode("#3B3741"));
	    		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1.setBounds(55, 161, 70, 42);
	    		panelCentral.add(iniciar_1_1);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Acerca de:");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1_2_1.setBounds(379, 72, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_1);
	    		
	    		JTextArea descripcion = new JTextArea("Contra es un videojuego de shoot 'em up desarrollado y publicado por Konami, lanzado originalmente como un juego de arcade el 20 de febrero de 1987. En 1988 se lanzó una versión doméstica para Nintendo Entertainment System, junto con puertos para varios formatos de computadora, incluido el MSX2.");
	    		descripcion.setFont(new Font("Calibri Light", Font.BOLD, 20));
	    		descripcion.setForeground(Color.decode("#3B3741"));
	    		descripcion.setLineWrap(true); // Ajuste automático de línea
	    		descripcion.setWrapStyleWord(true); // Cortar por palabra
	    		descripcion.setEditable(false);
	    		descripcion.setOpaque(false); // Para que se vea como JLabel
	    		descripcion.setBounds(379, 108, 567, 145);
	    		panelCentral.add(descripcion);

	    		
	    		
	    		
	    	        
	    		
	    		
	            JLabel logo = new JLabel(new ImageIcon(DetallesJuego.class.getResource("/images/Contra.png")));
	            logo.setBounds(32, 22, 180, 120); //posicion
	            panelCentral.add(logo);
	            
	            JLabel lblContra = new JLabel("CONTRA");
	            lblContra.setHorizontalAlignment(SwingConstants.LEFT);
	            lblContra.setFont(new Font("Anton", Font.BOLD, 24));
	            lblContra.setBounds(55, 124, 135, 42);
	            panelCentral.add(lblContra);
	            
	            JLabel iniciar_1_1_1 = new JLabel("Clasificación:");
	            iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1.setBounds(55, 198, 103, 42);
	            panelCentral.add(iniciar_1_1_1);
	            
	            JLabel iniciar_1_1_1_1 = new JLabel("Género:");
	            iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1.setBounds(55, 333, 70, 42);
	            panelCentral.add(iniciar_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_1 = new JLabel("Plataforma:");
	            iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_1.setBounds(55, 372, 97, 42);
	            panelCentral.add(iniciar_1_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_1_2 = new JLabel("Precio por renta:");
	            iniciar_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
	            iniciar_1_1_1_1_2.setBounds(664, 317, 150, 42);
	            panelCentral.add(iniciar_1_1_1_1_2);
	            
	            JLabel iniciar_1_1_1_1_2_1 = new JLabel("Precio por venta:");
	            iniciar_1_1_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
	            iniciar_1_1_1_1_2_1.setBounds(664, 353, 159, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_1);
	            
	            JLabel iniciar_1_1_1_1_2_2 = new JLabel("$100.00MXN");
	            iniciar_1_1_1_1_2_2.setForeground(new Color(153, 0, 0));
	            iniciar_1_1_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 20));
	            iniciar_1_1_1_1_2_2.setBounds(824, 317, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_2);
	            
	            JLabel iniciar_1_1_1_1_2_2_1 = new JLabel("$800.00MX");
	            iniciar_1_1_1_1_2_2_1.setForeground(new Color(153, 0, 0));
	            iniciar_1_1_1_1_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
	            iniciar_1_1_1_1_2_2_1.setBounds(824, 353, 126, 42);
	            panelCentral.add(iniciar_1_1_1_1_2_2_1);
	            
	            JTextArea NombresDesarrolladores = new JTextArea("Konami,  Ocean Software, Hamster Corporation, Digital Eclipse, Backbone Entertainment, Paul Owens");
	            NombresDesarrolladores.setWrapStyleWord(true);
	            NombresDesarrolladores.setForeground(Color.decode("#3B3741"));
	            NombresDesarrolladores.setOpaque(false);
	            NombresDesarrolladores.setLineWrap(true);
	            NombresDesarrolladores.setFont(new Font("Calibri Light", Font.BOLD, 16));
	            NombresDesarrolladores.setEditable(false);
	            NombresDesarrolladores.setBounds(55, 265, 318, 70);
	            panelCentral.add(NombresDesarrolladores);
	            
	            JLabel iniciar_1_1_1_2 = new JLabel("Desarrolladores:");
	            
	            iniciar_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_2.setBounds(55, 233, 190, 42);
	            panelCentral.add(iniciar_1_1_1_2);
	            
	            JLabel iniciar_1_1_1_3 = new JLabel("Mayores 18");
	            iniciar_1_1_1_3.setForeground(Color.decode("#3B3741"));
	            iniciar_1_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_3.setBounds(158, 198, 103, 42);
	            panelCentral.add(iniciar_1_1_1_3);
	            
	            JLabel iniciar_1_1_1_1_3 = new JLabel("Disparos");
	            iniciar_1_1_1_1_3.setForeground(Color.decode("#3B3741"));
	            iniciar_1_1_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_3.setBounds(118, 333, 70, 42);
	            panelCentral.add(iniciar_1_1_1_1_3);
	            
	            JLabel iniciar_1_1_1_1_1_1 = new JLabel("Nintendo");
	            iniciar_1_1_1_1_1_1.setForeground(Color.decode("#3B3741"));
	            iniciar_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_1_1_1.setBounds(148, 372, 97, 42);
	            panelCentral.add(iniciar_1_1_1_1_1_1);
	            
	            
	            //botones
	            JButton btnEditar = new JButton("Editar");
	            btnEditar.setBackground(Color.decode("#6D91B9"));
	            btnEditar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new EditarJuego();
	    				dispose();
	    			}
	    		});
	          
	            btnEditar.setForeground(Color.WHITE);
	            btnEditar.setBounds(394, 417, 183, 33);
	    		panelCentral.add(btnEditar);
	            
	            JButton btnConfirmar = new JButton("Descargar (PDF)");
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(605, 417, 183, 33);
	    		panelCentral.add(btnConfirmar);
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				 new Confirma_7();
	    				 
	    			}
	    		});
	            
	            JButton btnRegresar = new JButton("Regresar");
	            btnRegresar.setForeground(Color.WHITE);
	            btnRegresar.setBackground(new Color(184, 47, 47));
	            btnRegresar.setBounds(175, 417, 183, 33);
	            panelCentral.add(btnRegresar);
	            btnRegresar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				 new RegistroJuegos();
	    				 dispose();
	    			}
	    		});
	            //

        

       
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
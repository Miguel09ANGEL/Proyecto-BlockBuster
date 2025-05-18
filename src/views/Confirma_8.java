package views;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Confirma_8 extends JFrame {
	private JTextField textField;

    public Confirma_8() {
        // Configuración básica de la ventana
        setTitle("Guardar cambios de videojuego");
        setUndecorated(true);


        setSize(654, 252);
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
        panelCentral.setBounds(0, 0, 654, 252); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        
        JLabel iniciar = new JLabel("Confirmar cambios");
		iniciar.setSize(285, 42);
		iniciar.setLocation(233, 51);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

	    		
	    		JButton btnCancelar = new JButton("Cancelar");
	    		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		    	btnCancelar.setForeground(Color.WHITE);
	    		btnCancelar.setBounds(31, 130, 183, 33);
	    		btnCancelar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				dispose();
	    			}
	    		});
	    		panelCentral.add(btnCancelar);
	    		
	    		JButton btnSi = new JButton("Si");
	    		btnSi.setBackground(Color.decode("#6D91B9")); // Color de fondo (azul oscuro)
		    	btnSi.setForeground(Color.WHITE);
	    		btnSi.setBounds(418, 130, 183, 33);
	    		btnSi.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				dispose();
	    				 
	    			}
	    		});
	    		panelCentral.add(btnSi);
	    		
	    		

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Confirma_8();
        });
    }
}
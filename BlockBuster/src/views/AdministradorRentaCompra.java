package views;



import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministradorRentaCompra extends JFrame {

    public AdministradorRentaCompra() {
        // Configuración básica de la ventana
        setTitle("Administrador Renta y Compra");
        setSize(1024, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usamos JLayeredPane para superponer componentes
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 650));
        setContentPane(layeredPane);
        

        // 1. PANEL BLANCO (fondo completo)
        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(null);
        panelIzq.setBackground(Color.decode("#FFFFFF"));
        panelIzq.setBounds(10, 62, 257, 475);
        layeredPane.add(panelIzq, JLayeredPane.PALETTE_LAYER);
        
        
        JButton btnClientes = new JButton("CLIENTES");
        btnClientes.setFont(new Font("Calibri", Font.BOLD, 16));
        btnClientes.setBackground(Color.decode("#263C54"));
        btnClientes.setForeground(Color.WHITE);
        btnClientes.setBounds(10, 11, 237, 100); // x, y, ancho, alto

        panelIzq.add(btnClientes);
        
        JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
        btnVideojuegos.setForeground(Color.WHITE);
        btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
        btnVideojuegos.setBackground(new Color(38, 60, 84));
        btnVideojuegos.setBounds(10, 128, 237, 100);
        btnVideojuegos.addActionListener(e -> {
            new AdministradorJuegos();         // Abre la segunda ventana
            dispose();            // Cierra la ventana actual
        }); 
        panelIzq.add(btnVideojuegos);
        
        JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
        btnRentaYCompra.setForeground(Color.WHITE);
        btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
        btnRentaYCompra.setBackground(new Color(38, 60, 84));
        btnRentaYCompra.setBounds(10, 242, 237, 100);
        panelIzq.add(btnRentaYCompra);
        
        JButton btnClientes_3 = new JButton("NUEVA OPERACIÓN");
        btnClientes_3.setForeground(Color.WHITE);
        btnClientes_3.setFont(new Font("Calibri", Font.BOLD, 16));
        btnClientes_3.setBackground(new Color(38, 60, 84));
        btnClientes_3.setBounds(10, 364, 237, 100);
        panelIzq.add(btnClientes_3);

        // 2. PANEL GRIS CENTRAl
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(Color.decode("#F2F2F2"));
        panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
        layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);
        
        //Logotipo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
        logo.setBounds(361, 31, 70, 70); //posicion
        panelCentral.add(logo); 
        
        JLabel iniciar = new JLabel("Renta y compra");
		iniciar.setSize(548, 60);
		iniciar.setLocation(119, 112);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
        
		 JButton registros = new JButton("Renta");
		 registros.setBounds(119, 242, 206, 100);
		 panelCentral.add(registros);
	        registros.setForeground(Color.WHITE);
	        registros.setFont(new Font("Calibri", Font.BOLD, 16));
	        registros.setBackground(new Color(38, 60, 84));
	        registros.addActionListener(e -> {
	            new Renta();         // Abre la segunda ventana
	            dispose();            // Cierra la ventana actual
	        });  
	        
	        JButton btnCompra = new JButton("Compra");
	        btnCompra.setForeground(Color.WHITE);
	        btnCompra.setFont(new Font("Calibri", Font.BOLD, 16));
	        btnCompra.setBackground(new Color(38, 60, 84));
	        btnCompra.setBounds(425, 242, 206, 100);
	        btnCompra.addActionListener(e -> {
	            new Compra();         // Abre la segunda ventana
	            dispose();            // Cierra la ventana actual
	        });
	        panelCentral.add(btnCompra);
	        
	        JLabel lblAquPuedesElegir = new JLabel("Aquí puedes elegir si la consulta es renta o compra");
	        lblAquPuedesElegir.setHorizontalAlignment(SwingConstants.CENTER);
	        lblAquPuedesElegir.setFont(new Font("Calibri", Font.BOLD, 24));
	        lblAquPuedesElegir.setBounds(119, 154, 548, 60);
	        panelCentral.add(lblAquPuedesElegir);
	       
	    	
	    	
	    	
	    	

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdministradorRentaCompra();
        });
    }
}
package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesCliente extends JFrame {
	private JTextField textField;

    public DetallesCliente() {
        // Configuración básica de la ventana
        setTitle("Detalles Cliente");
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
        
        JLabel iniciar = new JLabel("DETALLES DE CLIENTE");
        iniciar.setForeground(Color.decode("#263C54"));
		iniciar.setSize(331, 42);
		iniciar.setLocation(334, 32);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Anton", Font.BOLD, 20));
		panelCentral.add(iniciar);
	    		
	    		JLabel iniciar_1_1_2_1 = new JLabel("Identificador:");
	    		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
	    		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
	    		iniciar_1_1_2_1.setBounds(50, 84, 97, 42);
	    		panelCentral.add(iniciar_1_1_2_1);

	    		
	    		
	    		
	    		
	    		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Block.png"));
	            Image imagen = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	            
	            JLabel iniciar_1_1_1_3 = new JLabel("00001");
	            iniciar_1_1_1_3.setForeground(Color.decode("#3B3741"));
	            iniciar_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_3.setBounds(39, 109, 103, 42);
	            panelCentral.add(iniciar_1_1_1_3);
	            
	            
	            //botones
	            JButton btnEditar = new JButton("EDITAR");
	            btnEditar.setBackground(Color.decode("#6D91B9"));
	            btnEditar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				new EditarCliente();
	    				dispose();
	    			}
	    		});
	          
	            btnEditar.setForeground(Color.WHITE);
	            btnEditar.setBounds(398, 403, 183, 33);
	    		panelCentral.add(btnEditar);
	            
	            JButton btnConfirmar = new JButton("INFORMACION (PDF)");
	    		btnConfirmar.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    				 new InformacionCliente();
	    				 dispose();
	    			}
	    		});
	    		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		    	btnConfirmar.setForeground(Color.WHITE);
	    		btnConfirmar.setBounds(641, 403, 183, 33);
	    		panelCentral.add(btnConfirmar);
	            
	            JButton btnRegresar = new JButton("REGRESAR");
	            btnRegresar.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		new RegistroClientes();
	    				 dispose();
	            	}
	            });
	            btnRegresar.setForeground(Color.WHITE);
	            btnRegresar.setBackground(new Color(184, 47, 47));
	            btnRegresar.setBounds(157, 403, 183, 33);
	            panelCentral.add(btnRegresar);
	            //
	            
	            // Tabla de Rentas del Cliente
	            Object[][] data = {
	            	    { "Rentas", "Fecha de compra", "Fecha de devolución", "Descuentos" },
	            	    { "000006", "13/01/2025", "13/02/2025", "7%" },
	            	    { "Fornite", "13/01/2025", "13/02/2025", "7%" },
	            	    { "Halo 2", "10/01/2025", "13/02/2025", "7%" },
	            	    { "Halo 4", "17/01/2025", "13/02/2025", "7%" },
	            	    { "Super Mash Bros", "43/01/2025", "13/02/2025", "7%" },
	            	    { "The Last of Us Parte ll", "18/01/2025", "13/02/2025", "7%" }
	            	};

	            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	            // Tabla
	            JTable table = new JTable(data, new String[] { "", "", "", "",});
	            panelCentral.add(table);
	            table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	            table.setBounds(28, 170, 606, 210);
	            table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
	            table.setShowGrid(true);
	            table.setGridColor(new Color(204, 204, 204));
	            table.setTableHeader(null);
	            table.setDefaultRenderer(Object.class, centerRenderer);
	            table.setRowHeight(35);
	            table.setShowHorizontalLines(true);
	            table.setShowVerticalLines(true);
	            
	            // Tabla de compras del cliente
	            Object[][] compro = {
		                { "Compras",},
		                { "Fornite",},
		                { "Mortal Combat",},
		                { "Resident Evil",},
		                { ""},
		                { "",},
		            

		            };

		            DefaultTableCellRenderer ClienteCompro = new DefaultTableCellRenderer();
		            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	            JTable Compras = new JTable(compro, new String[] { "",});
	            panelCentral.add(Compras);
	            Compras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	            Compras.setBounds(712, 170, 183, 210);
	            Compras.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
	            Compras.setShowGrid(true);
	            Compras.setGridColor(new Color(204, 204, 204));
	            Compras.setTableHeader(null);
	            Compras.setDefaultRenderer(Object.class, centerRenderer);
	            Compras.setRowHeight(30);
	            Compras.setShowHorizontalLines(true);
	            Compras.setShowVerticalLines(true);
	            
	            JLabel iniciar_1_1_2_1_1 = new JLabel("Nombre:");
	            iniciar_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1.setBounds(230, 84, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_1);
	            
	            JLabel iniciar_1_1_1_3_1 = new JLabel("Manuel Orozco Vazquez");
	            iniciar_1_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_1_3_1.setForeground(new Color(59, 55, 65));
	            iniciar_1_1_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_3_1.setBounds(175, 109, 196, 42);
	            panelCentral.add(iniciar_1_1_1_3_1);
	            
	            JLabel iniciar_1_1_2_1_1_1 = new JLabel("Correo:");
	            iniciar_1_1_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1_1.setBounds(480, 85, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_1);
	            
	            JLabel iniciar_1_1_1_3_1_1 = new JLabel("mov@gmail.com");
	            iniciar_1_1_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_1_3_1_1.setForeground(new Color(59, 55, 65));
	            iniciar_1_1_1_3_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_3_1_1.setBounds(443, 109, 145, 42);
	            panelCentral.add(iniciar_1_1_1_3_1_1);
	            
	            JLabel iniciar_1_1_2_1_1_1_1 = new JLabel("Telefono:");
	            iniciar_1_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	            iniciar_1_1_2_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_2_1_1_1_1.setBounds(767, 84, 97, 42);
	            panelCentral.add(iniciar_1_1_2_1_1_1_1);
	            
	            JLabel iniciar_1_1_1_3_1_1_1 = new JLabel("6120000000");
	            iniciar_1_1_1_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	            iniciar_1_1_1_3_1_1_1.setForeground(new Color(59, 55, 65));
	            iniciar_1_1_1_3_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
	            iniciar_1_1_1_3_1_1_1.setBounds(733, 109, 145, 42);
	            panelCentral.add(iniciar_1_1_1_3_1_1_1);
        

       
        // 3. PANEL ROJO SUPERIOR (barra de título)
        JPanel barraRoja = new JPanel();
        barraRoja.setBackground(Color.decode("#B44635"));
        barraRoja.setBounds(0, 0,1024, 60);
        layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

       

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DetallesCliente();
        });
    }
}
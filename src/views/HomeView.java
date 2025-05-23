package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.json.ParseException;

import controller.UserController;
import controller.VideogamesController;
import models.Admins;
import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;

public class HomeView extends JFrame {
	
	
	public HomeView() {
		// TODO Auto-generated constructor stub
	}

	public void PanelAdministrador(List administrador) {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Configuración básica de la ventana
		setTitle("Panel Administrador");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// 1. PANEL BLANCO (fondo completo)
		JPanel fondoBlanco = new JPanel();
		fondoBlanco.setBackground(Color.WHITE);
		fondoBlanco.setBounds(0, 0, 1024, 576);
		layeredPane.add(fondoBlanco, JLayeredPane.DEFAULT_LAYER);

		// 2. PANEL GRIS CENTRAl
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(300, 70, 420, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(176, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("PANEL ADMINISTRADOR");
		iniciar.setSize(350, 60);
		iniciar.setLocation(35, 75);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel correo = new JLabel("Ingresar correo:");
		correo.setHorizontalAlignment(SwingConstants.LEFT);
		correo.setFont(new Font("SansSerif", Font.BOLD, 20));
		correo.setBounds(60, 146, 290, 26);
		panelCentral.add(correo);
		
		JTextField gmail = new JTextField();
		gmail.setBorder(BorderFactory.createLineBorder(Color.decode("#10A7DE")));
		gmail.setBackground(Color.decode("#D9D9D9"));
		gmail.setSize(290, 30);
		gmail.setLocation(60, 170);
		gmail.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(gmail);


		JLabel contraseña = new JLabel("Contraseña:");
		contraseña.setSize(116, 26);
		contraseña.setLocation(60, 263);
		contraseña.setHorizontalAlignment(JLabel.LEFT);
		contraseña.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelCentral.add(contraseña);

	    JPasswordField password = new JPasswordField();
		password.setBackground(Color.decode("#D9D9D9"));
		password.setSize(290, 30);
		password.setLocation(60, 288);
		password.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(password);
		
		JButton acceder = new JButton("Acceder");
		acceder.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		acceder.setForeground(Color.WHITE); // Color del texto (blanco)
		acceder.setFont(new Font("Calibri", Font.BOLD, 15));
		acceder.setBounds(50, 371, 289, 26);
		acceder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	 // para prebas comenta lo demas y decomentas esta parte de Inicio();
//		    	dispose();
//              Inicio();

		        // Resetear estilos
		        gmail.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		        
		        String passIngresada = new String(password.getPassword());
		        String emailIngresado = gmail.getText();
		        		        
		        // Validar si el campo email está vacío
		        if(emailIngresado.isEmpty()) {
		            gmail.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            JOptionPane.showMessageDialog(null, "Por favor ingrese su email", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // Validar si el campo contraseña está vacío
		        if(passIngresada.isEmpty()) {
		            password.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            JOptionPane.showMessageDialog(null, "Por favor ingrese su contraseña", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		    
		        
		        boolean credencialesCorrectas = false;
		        
		        // Aquí se valida la contraseña
		        for (Iterator iterator = administrador.iterator(); iterator.hasNext();) {
		            Admins ob = (Admins) iterator.next();
		            
		            // se valida el correo
		            if(emailIngresado.equals(ob.getEmail())) {
		                // se valida la contraseña
		                if(passIngresada.equals(ob.getContraseña())) {
		                    credencialesCorrectas = true;
		                 
		                    dispose();
		                    Inicio();
		                    break; 
		                }
		            }
		        }
		        
		        // Si no se encontraron credenciales correctas
		        if(!credencialesCorrectas) {
		            JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
		            gmail.setBorder(BorderFactory.createLineBorder(Color.RED));
		            password.setBorder(BorderFactory.createLineBorder(Color.RED));
		        }
		    }
		});
		panelCentral.add(acceder);

		//lo de adminitrador lo haremos despues
		
//		JLabel lblagregarNuevoAdministrador = new JLabel("¿Agregar nuevo administrador?");
//		lblagregarNuevoAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
//		lblagregarNuevoAdministrador.setFont(new Font("SansSerif", Font.BOLD, 14));
//		lblagregarNuevoAdministrador.setBounds(27, 410, 224, 26);
//		panelCentral.add(lblagregarNuevoAdministrador);
//
//		JButton btnAgregarAqui = new JButton("Agregar aqui");
//		btnAgregarAqui.setForeground(Color.BLACK);
//		btnAgregarAqui.setFont(new Font("Calibri", Font.BOLD, 15));
//		btnAgregarAqui.setBackground(Color.decode("#F2F2F2"));
//		btnAgregarAqui.setBounds(247, 414, 111, 26);
//		panelCentral.add(btnAgregarAqui);
//		btnAgregarAqui.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose(); // Cierra la ventana actual
//				AuthViews codigoregistro= new AuthViews();
//				codigoregistro.CodigoRegistro();
//			}
//		});
//		panelCentral.add(btnAgregarAqui);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Inicio() {
		// Configuración básica de la ventana
		setTitle("Panel Inicio");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);
		
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

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
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorRentaCompra(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAl
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(361, 31, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("¡BIENVENIDO/A AL PANEL DE ADMINISTRADOR");
		iniciar.setSize(548, 60);
		iniciar.setLocation(119, 112);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

//		JLabel iniciar_1 = new JLabel(
//				"<html><div style='text-align: center;'>Desde aquí podrás gestionar y controlar todas las<br>"
//						+ "funcionalidades de tu sitio. ¿Qué deseas hacer hoy?</div></html>");
		JLabel iniciar_1 = new JLabel(
				"<html><center>Desde aquí podrás gestionar y controlar todas las"
						+ "funcionalidades de tu sitio. ¿Qué deseas hacer hoy?<html>");
		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(170, 183, 391, 83); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/images/Salida.png"));
		JButton salida = new JButton(icono);
		salida.setBounds(650, 390,60, 60);
		salida.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			 // Abre la segunda ventana
		});
		panelCentral.add(salida);


				

		setVisible(true);
	}
	
	

	public void RegistrarAdministrador() {
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		JTextField textField_5;
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
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(115, 81, 767, 430); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(358, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("REGISTRAR ADMINISTRADOR");
		iniciar.setSize(301, 42);
		iniciar.setLocation(235, 89);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Nombre:");
		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(55, 142, 140, 42); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JTextField nJuego = new JTextField();
		nJuego.setBackground(Color.decode("#D9D9D9"));
		nJuego.setBounds(55, 174, 272, 27);
		panelCentral.add(nJuego);
		nJuego.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(164, 374, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AuthViews codigoregistro= new AuthViews();
			codigoregistro.CodigoRegistro();// Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(455, 374, 183, 33);
		btnConfirmar.addActionListener(e -> {
			Inicio(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnConfirmar);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(217, 217, 217));
		textField_1.setBounds(55, 238, 270, 27);
		panelCentral.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(217, 217, 217));
		textField_2.setBounds(55, 304, 270, 27);
		panelCentral.add(textField_2);

		JLabel iniciar_1_1 = new JLabel("Apellido materno:");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(55, 212, 140, 42);
		panelCentral.add(iniciar_1_1);

		JLabel iniciar_1_1_1 = new JLabel("Apellido Paterno:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(55, 276, 140, 42);
		panelCentral.add(iniciar_1_1_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(217, 217, 217));
		textField_3.setBounds(455, 304, 270, 27);
		panelCentral.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(217, 217, 217));
		textField_4.setBounds(455, 238, 270, 27);
		panelCentral.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(217, 217, 217));
		textField_5.setBounds(455, 174, 270, 27);
		panelCentral.add(textField_5);

		JLabel iniciar_1_2 = new JLabel("Ingresar correo:");
		iniciar_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2.setBounds(455, 142, 140, 42);
		panelCentral.add(iniciar_1_2);

		JLabel iniciar_1_1_2 = new JLabel("Contraseña:");
		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2.setBounds(455, 212, 140, 42);
		panelCentral.add(iniciar_1_1_2);

		JLabel iniciar_1_1_1_1 = new JLabel("Confirmar contraseña:");
		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1_1.setBounds(455, 276, 140, 42);
		panelCentral.add(iniciar_1_1_1_1);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	// PANELES PRINCIPALES
	// Cliente
	public void AdministradorCliente() {
		// Configuración básica de la ventana
		setTitle("Panel Cliente");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);
		// 3. PANEL ROJO SUPERIOR (barra de título)
				JPanel barraRoja = new JPanel();
				barraRoja.setBackground(Color.decode("#B44635"));
				barraRoja.setBounds(0, 0, 1024, 60);
				layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

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
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorRentaCompra(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAl
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(336, 22, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("¡BIENVENIDO/A AL PANEL DE CLIENTE");
		iniciar.setSize(548, 60);
		iniciar.setLocation(100, 103);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel(
				"<html><center>Desde aquí podrás gestionar y controlar todas las"
						+ "funcionalidades de los clientes. ¿Qué deseas hacer hoy?<html>");
		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(168, 147, 391, 83); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JButton registros = new JButton("REGISTROS");
		registros.setBounds(100, 261, 206, 100);
		registros.setForeground(Color.WHITE);
		registros.setFont(new Font("Calibri", Font.BOLD, 16));
		registros.setBackground(new Color(38, 60, 84));
		registros.addActionListener(e -> {
			dispose();
//			RegistroClientes(); // Abre la segunda ventana
			UserController uc = new UserController();
			uc.index();
		});
		panelCentral.add(registros);

		JButton btnAgregarCliente = new JButton("AGREGAR CLIENTE ");
		btnAgregarCliente.setForeground(Color.WHITE);
		btnAgregarCliente.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAgregarCliente.setBackground(new Color(38, 60, 84));
		btnAgregarCliente.setBounds(442, 261, 206, 100);
		btnAgregarCliente.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			
			AuthViews av = new AuthViews();
			av.AgregarCliente();
		});
		panelCentral.add(btnAgregarCliente);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/images/Salida.png"));
		JButton salida = new JButton(icono);
		salida.setBounds(650, 390,60, 60);
		salida.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(salida);

		

		setVisible(true);
	}
	
	//va en UserView//
	//frame funcional
	public void RegistroClientes(List usuarios) {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Configuración básica de la ventana
		setTitle("Registro de Clientes");
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
		btnClientes.setBounds(10, 11, 237, 100);
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorRentaCompra(); // Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
		});
		panelIzq.add(btnNuevaOperacion);

		
		//PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("REGISTRO DE CLIENTES");
		iniciar.setSize(236, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
		
//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		btnBuscar.setBounds(619, 25, 86, 25);
//		panelCentral.add(btnBuscar);
//		
//		JTextField Buscador = new JTextField();
//		Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		Buscador.setBounds(385, 25,220, 25);
//		panelCentral.add(Buscador);

		// Crear unan tabla
		String[] columnNames = { "ID", "Nombre", "A. pat:", "A. mat;", "Fecha de nacimierto", "telefono", "Correo" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer que la tabla no sea editable
			}
		};

		// Llenar la tabla con datos
		for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
			User usuario = (User) iterator.next();
			Object[] rowData = { usuario.getId(), usuario.getNombre(), usuario.getApellidoPaterno(),
					usuario.getApellidoMaterno(), usuario.getFechaNacimiento(), usuario.getTelefono(),
					usuario.getCorreo() };
			model.addRow(rowData);
		}

		// se crea la tabla
		JTable table = new JTable(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // ordenar por columnas

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 62, 695, 366);
		panelCentral.add(scrollPane);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.decode("#B82F2F"));
		btnEliminar.setBounds(187, 439, 172, 25);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un usuario", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Confirmamos eliminacion
				int confirm = JOptionPane.showConfirmDialog(layeredPane,
						"¿Está seguro que desea eliminar este usuario?", "Confirmar eliminación",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					// Obtener ID del usuario seleccionado
					int userId = (int) model.getValueAt(selectedRow, 0);

					// Eliminamos de la base de datos
					UsersModel um = new UsersModel();
					boolean eliminado = um.remove(userId);

					if (eliminado) {
						// Eliminar de la tabla
						model.removeRow(selectedRow);
						JOptionPane.showMessageDialog(layeredPane, "Usuario eliminado correctamente");
					} else {
						JOptionPane.showMessageDialog(layeredPane, "Error al eliminar el usuario", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		panelCentral.add(btnEliminar);

		

		// BOTON EDITAR PROVICIONAL, MEJORAR
		JButton btnEditar = new JButton("DETALLES");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.decode("#4fadbd"));
		btnEditar.setBounds(379, 439, 172, 25);
		btnEditar.addActionListener(e -> {

			int selectedRow = table.getSelectedRow();

			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un usuario", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Obtener datos del usuario seleccionado
			int userId = (int) model.getValueAt(selectedRow, 0);

			dispose();
			UserController uc = new UserController();
			uc.update2(userId);

		});
		panelCentral.add(btnEditar);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		
	}

	//frame funcional
	public void DetallesCliente(User user) {
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		JLabel iniciar = new JLabel("DETALLES DE CLIENTE");
		iniciar.setForeground(Color.decode("#263C54"));
		iniciar.setSize(331, 42);
		iniciar.setLocation(334, 32);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Anton", Font.BOLD, 20));
		panelCentral.add(iniciar);

		JLabel Identificador = new JLabel("Identificador:");
		Identificador.setHorizontalAlignment(SwingConstants.LEFT);
		Identificador.setFont(new Font("Calibri", Font.BOLD, 18));
		Identificador.setBounds(50, 84, 110, 42);
		panelCentral.add(Identificador);

		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagen = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		JLabel iniciar_1_1_1_3 = new JLabel(""+user.getId());
		iniciar_1_1_1_3.setForeground(Color.decode("#3B3741"));
		iniciar_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
		iniciar_1_1_1_3.setBounds(39, 109, 103, 42);
		panelCentral.add(iniciar_1_1_1_3);

		// botones
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBackground(Color.decode("#6D91B9"));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserController uc = new UserController();
				uc.update(user.getId());
//				EditarCliente();
				dispose();
			}
		});

		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBounds(398, 403, 183, 33);
		panelCentral.add(btnEditar);

		JButton btnConfirmar = new JButton("INFORMACION (PDF)");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				dispose();
				//Falta mandar a llamar datos del Cliente
//				VideogamesController vm = new VideogamesController();
//				vm.updateVideogames2(WIDTH);
//				InformacionCliente();;
			}
		});
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(641, 403, 183, 33);
		panelCentral.add(btnConfirmar);

		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserController uc = new UserController();
				uc.index();
				
				dispose();
			}
		});
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(new Color(184, 47, 47));
		btnRegresar.setBounds(157, 403, 183, 33);
		panelCentral.add(btnRegresar);
		//

		// Tabla de Rentas del Cliente
		Object[][] data = { { "Rentas", "Fecha de compra", "Fecha de devolución", "Descuentos" },
				{ "000006", "13/01/2025", "13/02/2025", "7%" }, { "Fornite", "13/01/2025", "13/02/2025", "7%" },
				{ "Halo 2", "10/01/2025", "13/02/2025", "7%" }, { "Halo 4", "17/01/2025", "13/02/2025", "7%" },
				{ "Super Mash Bros", "43/01/2025", "13/02/2025", "7%" },
				{ "The Last of Us Parte ll", "18/01/2025", "13/02/2025", "7%" } };

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
		JTable table = new JTable(data, new String[] { "", "", "", "", });
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
		Object[][] compro = { { "Compras", }, { "Fornite", }, { "Mortal Combat", }, { "Resident Evil", }, { "" },
				{ "", },

		};

		DefaultTableCellRenderer ClienteCompro = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		JTable Compras = new JTable(compro, new String[] { "", });
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

		JLabel iniciar_1_1_1_3_1 = new JLabel(user.getNombre() + " " + user.getApellidoPaterno());
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

		JLabel iniciar_1_1_1_3_1_1 = new JLabel(user.getCorreo());
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

		JLabel iniciar_1_1_1_3_1_1_1 = new JLabel(user.getTelefono());
		iniciar_1_1_1_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_1_3_1_1_1.setForeground(new Color(59, 55, 65));
		iniciar_1_1_1_3_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
		iniciar_1_1_1_3_1_1_1.setBounds(733, 109, 145, 42);
		panelCentral.add(iniciar_1_1_1_3_1_1_1);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	//frame funcional
	public void InformacionCliente(User user) {
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(450, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("INFORMACIÓN CLIENTE");
		iniciar.setSize(263, 42);
		iniciar.setLocation(355, 93);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Nombre:");
		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 18));
		iniciar_1.setBounds(84, 150, 87, 42); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(103, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
//			DetallesCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("DESCARGAR PDF");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(621, 406, 183, 33);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panelCentral.add(btnConfirmar);

		JButton btnCredencialpdf = new JButton("Credencial (PDF");
		btnCredencialpdf.setForeground(Color.WHITE);
		btnCredencialpdf.setBackground(Color.decode("#686868"));
		btnCredencialpdf.setBounds(370, 406, 183, 33);
		btnCredencialpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TarjetaCliente(); // Abre la segunda ventana
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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void TarjetaCliente() {
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(213, 23, 70, 70); // posicion
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
//			InformacionCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Descargar PDF");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		JLabel NombreCliente = new JLabel("Manuel orozco vazquez");
		NombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		NombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		NombreCliente.setBounds(192, 157, 192, 42);
		panelCentral.add(NombreCliente);

		JLabel iniciar_1_3_1 = new JLabel("Numero de control: ");
		iniciar_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
		iniciar_1_3_1.setBounds(121, 209, 161, 42);
		panelCentral.add(iniciar_1_3_1);

		JLabel Ncontrol = new JLabel("00001");
		Ncontrol.setHorizontalAlignment(SwingConstants.CENTER);
		Ncontrol.setFont(new Font("Calibri", Font.BOLD, 18));
		Ncontrol.setBounds(244, 209, 131, 42);
		panelCentral.add(Ncontrol);

		JLabel iniciar_1_3_2 = new JLabel("Correo:");
		iniciar_1_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_3_2.setFont(new Font("Calibri", Font.BOLD, 18));
		iniciar_1_3_2.setBounds(123, 262, 107, 42);
		panelCentral.add(iniciar_1_3_2);

		JLabel gmail = new JLabel("mov@gmail.com");
		gmail.setHorizontalAlignment(SwingConstants.LEFT);
		gmail.setFont(new Font("Calibri", Font.BOLD, 18));
		gmail.setBounds(213, 262, 162, 42);
		panelCentral.add(gmail);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	
	// VideoJuegos
	public void AdministradorJuegos() {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Configuración básica de la ventana
		setTitle("Administrador Juegos");
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
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorCliente(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorJuegos(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnVideojuegos);

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorRentaCompra(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				NuevaOperacion(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAl
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(344, 31, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("¡BIENVENIDO/A AL PANEL DE VIDEOJUEGOS");
		iniciar.setSize(548, 60);
		iniciar.setLocation(100, 112);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JButton registros = new JButton("<html><center>REGISTROS DE VIDEOJUEGOS<html>");
		registros.setBounds(100, 261, 206, 100);
		panelCentral.add(registros);
		registros.setForeground(Color.WHITE);
		registros.setFont(new Font("Calibri", Font.BOLD, 16));
		registros.setBackground(new Color(38, 60, 84));
		registros.addActionListener(e -> {
//			RegistroJuegos(); // Abre la segunda ventana
			
			dispose(); // Cierra la ventana actual
			VideogamesController lt = new VideogamesController();
			lt.indexVideoGames();
		});

		JButton btnAgregarVideojuegos = new JButton("<html><center>AGREGAR VIDEOJUEGOS<html>");
		btnAgregarVideojuegos.setForeground(Color.WHITE);
		btnAgregarVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAgregarVideojuegos.setBackground(new Color(38, 60, 84));
		btnAgregarVideojuegos.setBounds(442, 261, 206, 100);
		btnAgregarVideojuegos.addActionListener(e -> {
			dispose();
			AuthViews agregarjuego= new AuthViews(); 
			agregarjuego.AgregarJuego();
			
		});
		panelCentral.add(btnAgregarVideojuegos);

		JLabel lblDesdeAquPodrs = new JLabel(
				"<html><center>Desde aquí podrás gestionar y controlar todas las funcionalidades de videojuegos. \r\n"
				+ "¿Qué deseas hacer hoy?\r\n"
				+ "<html>");
		lblDesdeAquPodrs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeAquPodrs.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDesdeAquPodrs.setBounds(100, 168, 464, 60);
		panelCentral.add(lblDesdeAquPodrs);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/images/Salida.png"));
		JButton salida = new JButton(icono);
		salida.setBounds(650, 390,60, 60);
		salida.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			 // Abre la segunda ventana
		});
		panelCentral.add(salida);
		

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	// Frame Funcional
	//Necesito mover este frame a UserViews pero afecta a Videogames Controller
	public void RegistroJuegos(List juegos) {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Configuración básica de la ventana
		setTitle("Registro de Videojuegos");
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
		btnClientes.setBounds(10, 11, 237, 100);
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorcliente= new HomeView(); 
			administradorcliente.AdministradorCliente();
			; // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorjuego= new HomeView(); 
			administradorjuego.AdministradorJuegos();
			 // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		panelIzq.add(btnRentaYCompra);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorRentaCompra= new HomeView(); 
			administradorRentaCompra.AdministradorRentaCompra();
			 // Cierra la ventana actual
		});

		JButton btnClientes_3 = new JButton("NUEVA OPERACIÓN");
		btnClientes_3.setForeground(Color.WHITE);
		btnClientes_3.setFont(new Font("Calibri", Font.BOLD, 16));
		btnClientes_3.setBackground(new Color(38, 60, 84));
		btnClientes_3.setBounds(10, 364, 237, 100);
		panelIzq.add(btnClientes_3);
		btnClientes_3.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView nuevaoperacion= new HomeView(); 
			nuevaoperacion.NuevaOperacion();
			// Cierra la ventana actual
		});

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("REGISTRO DE JUEGOS");
		iniciar.setSize(236, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);
		
//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		btnBuscar.setBounds(619, 25, 86, 25);
//		panelCentral.add(btnBuscar);
//		
//		JTextField Buscador = new JTextField();
//		Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		Buscador.setBounds(385, 25,220, 25);
//		panelCentral.add(Buscador);

		// Crear unan tabla
		String[] columnNames = { "ID", "Nombre", "Plataforma", "Existencia", "Precio venta", "Precio renta" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer que la tabla no sea editable
			}
		};

		
		// Llenar la tabla con datos
		for (Iterator iterator = juegos.iterator(); iterator.hasNext();) {
			VideoGames juego = (VideoGames) iterator.next();
			 Object[] rowData = {
				        juego.getId(),
				        juego.getNombre(),
				        juego.getPlataforma(),
				        juego.getExistenciasDisponibles(),
				        "$" + juego.getPrecioVenta(),
				        "$" + juego.getPrecioRenta(),
				        juego.getClasificacion()
				    };
			model.addRow(rowData);
		}

		// se crea la tabla
		JTable table = new JTable(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // ordenar por columnas

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 62, 695, 366);
		panelCentral.add(scrollPane);

		
		// BOTÓN EDITAR
		JButton btnEditar = new JButton("DETALLES	");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.decode("#4fadbd"));
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(400, 439, 172, 25);
		btnEditar.addActionListener(e -> {
		    int selectedRow = table.getSelectedRow();
		    
		    if (selectedRow == -1) {
		        JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un juego", "Advertencia",
		                JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		  
		    // Obtener datos del juego seleccionado
		    int juegoId = (int) model.getValueAt(selectedRow, 0);

		    // Cerrar esta ventana y abrir ventana de edición con los datos cargados
		    dispose();
		    
		    int videogameId = (int) model.getValueAt(selectedRow, 0);

			VideogamesController vc = new VideogamesController();
		    vc.updateVideogames(videogameId);
		    
		});
		panelCentral.add(btnEditar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.decode("#B82F2F"));
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBounds(187, 439, 172, 25);
		btnEliminar.setToolTipText("Eliminar el videojuego seleccionado");

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un juego", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(layeredPane,
						"¿Está seguro que desea eliminar este videojuego?", "Confirmar eliminación",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						int videogameId = (int) model.getValueAt(selectedRow, 0);
						
						VideoGamesModel vm = new VideoGamesModel();
						boolean eliminado = vm.removeVideogame(videogameId);

						if (eliminado) {
							// Eliminar de la tabla
							model.removeRow(selectedRow);
							JOptionPane.showMessageDialog(layeredPane, "Usuario eliminado correctamente");
						} else {
							JOptionPane.showMessageDialog(layeredPane, "Error al eliminar el usuario", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(layeredPane, "Error al eliminar el videojuego:\n" + ex.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		panelCentral.add(btnEliminar);


		

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 9); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public void DetallesJuego(VideoGames videogames) {
	    // Configuración de la ventana
	    setTitle("Detalles Videojuego");
	    setSize(1024, 576);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);

	    // Pane para capas
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setPreferredSize(new Dimension(900, 650));
	    setContentPane(layeredPane);

	    // Panel central gris
	    JPanel panelCentral = new JPanel();
	    panelCentral.setLayout(null);
	    panelCentral.setBackground(Color.decode("#D9D9D9"));
	    panelCentral.setBounds(5, 62, 998, 475);
	    layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

	    // Título
	    JLabel lblTitulo = new JLabel("DETALLES DE VIDEOJUEGO");
	    lblTitulo.setForeground(Color.decode("#263C54"));
	    lblTitulo.setBounds(394, 11, 331, 42);
	    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lblTitulo.setFont(new Font("Anton", Font.BOLD, 20));
	    panelCentral.add(lblTitulo);

	    // Nombre del juego
	    JLabel lblNombre = new JLabel(videogames.getNombre());
	    lblNombre.setFont(new Font("Anton", Font.BOLD, 20));
	    lblNombre.setBounds(55, 124, 250, 42);
	    panelCentral.add(lblNombre);

	    // Año
	    JLabel lblAnio = new JLabel(""+videogames.getAñoLanzamiento());
	    lblAnio.setForeground(Color.decode("#3B3741"));
	    lblAnio.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblAnio.setBounds(100, 161, 70, 42);
	    panelCentral.add(lblAnio);

	    // Clasificación
	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setHorizontalAlignment(JLabel.LEFT);
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblClasificacion.setBounds(55, 198, 120, 42);
	    panelCentral.add(lblClasificacion);

	    JLabel lblClasificacionValor = new JLabel(videogames.getClasificacion());
	    lblClasificacionValor.setForeground(Color.decode("#3B3741"));
	    lblClasificacionValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblClasificacionValor.setBounds(169, 198, 103, 42);
	    panelCentral.add(lblClasificacionValor);

	    // Desarrolladores
	    JLabel lblDesarrolladores = new JLabel("Desarrolladores:");
	    lblDesarrolladores.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblDesarrolladores.setBounds(55, 233, 190, 42);
	    panelCentral.add(lblDesarrolladores);

	    JTextArea txtDesarrolladores = new JTextArea(videogames.getDesarrolladoPor());
	    txtDesarrolladores.setWrapStyleWord(true);
	    txtDesarrolladores.setLineWrap(true);
	    txtDesarrolladores.setOpaque(false);
	    txtDesarrolladores.setEditable(false);
	    txtDesarrolladores.setFont(new Font("Calibri Light", Font.BOLD, 16));
	    txtDesarrolladores.setForeground(Color.decode("#3B3741"));
	    txtDesarrolladores.setBounds(55, 265, 318, 70);
	    panelCentral.add(txtDesarrolladores);

	    // Género
	    JLabel lblGenero = new JLabel("Género:");
	    lblGenero.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblGenero.setBounds(55, 333, 70, 42);
	    panelCentral.add(lblGenero);

	    JLabel lblGeneroValor = new JLabel(videogames.getGenero());
	    lblGeneroValor.setForeground(Color.decode("#3B3741"));
	    lblGeneroValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblGeneroValor.setBounds(130, 333, 70, 42);
	    panelCentral.add(lblGeneroValor);

	    // Plataforma
	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblPlataforma.setBounds(55, 372, 110, 42);
	    panelCentral.add(lblPlataforma);

	    JLabel lblPlataformaValor = new JLabel(videogames.getPlataforma());
	    lblPlataformaValor.setForeground(Color.decode("#3B3741"));
	    lblPlataformaValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblPlataformaValor.setBounds(170, 372, 97, 42);
	    panelCentral.add(lblPlataformaValor);

	    // Descripción
	    JLabel lblAcercaDe = new JLabel("Acerca de:");
	    lblAcercaDe.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblAcercaDe.setBounds(379, 72, 97, 42);
	    panelCentral.add(lblAcercaDe);

	    JTextArea txtDescripcion = new JTextArea(videogames.getDescripcion());
	    txtDescripcion.setFont(new Font("Calibri Light", Font.BOLD, 20));
	    txtDescripcion.setForeground(Color.decode("#3B3741"));
	    txtDescripcion.setLineWrap(true);
	    txtDescripcion.setWrapStyleWord(true);
	    txtDescripcion.setEditable(false);
	    txtDescripcion.setOpaque(false);
	    txtDescripcion.setBounds(379, 108, 567, 145);
	    panelCentral.add(txtDescripcion);

	    // Precios
	    JLabel lblPrecioRenta = new JLabel("Precio por renta:");
	    lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblPrecioRenta.setBounds(640, 317, 180, 42);
	    panelCentral.add(lblPrecioRenta);

	    JLabel lblPrecioVenta = new JLabel("Precio por venta:");
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblPrecioVenta.setBounds(640, 353, 180, 42);
	    panelCentral.add(lblPrecioVenta);

	    JLabel lblValorRenta = new JLabel(""+videogames.getPrecioRenta());
	    lblValorRenta.setForeground(new Color(153, 0, 0));
	    lblValorRenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblValorRenta.setBounds(820, 317, 126, 42);
	    panelCentral.add(lblValorRenta);

	    JLabel lblValorVenta = new JLabel(""+videogames.getPrecioVenta());
	    lblValorVenta.setForeground(new Color(153, 0, 0));
	    lblValorVenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblValorVenta.setBounds(820, 353, 126, 42);
	    panelCentral.add(lblValorVenta);

	    // Botón Editar
	    JButton btnEditar = new JButton("Editar");
	    btnEditar.setBackground(Color.decode("#6D91B9"));
	    btnEditar.setForeground(Color.WHITE);
	    btnEditar.setBounds(394, 417, 183, 33); // Ajusta posición si es necesario
	    btnEditar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
//	            EditarJuego();
	        	VideogamesController vc = new VideogamesController();

			    vc.updateVideogames2(videogames.getId());
	            dispose();
	        }
	        
	    });
	    panelCentral.add(btnEditar);
	    
	    JButton btnDescargarPDF = new JButton("Descargar (PDF)");
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBounds(605, 417, 183, 33);
		panelCentral.add(btnDescargarPDF);
		btnDescargarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(new Color(184, 47, 47));
		btnRegresar.setBounds(175, 417, 183, 33);
		panelCentral.add(btnRegresar);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VideogamesController vc = new VideogamesController();

				vc.indexVideoGames();

			}
		});

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);


	    setVisible(true);
	}


	public void EditarJuego(VideoGames videogames) {
	    
	    // Declaración de campos de texto con nombres descriptivos
	    JTextField txtNombreJuego;
	    JTextField txtAnioLanzamiento;
	    JTextField txtGenero;
	    JTextField txtPrecioRenta;
	    JTextField txtPlataforma;
	    JTextField txtClasificacion;
	    JTextField txtExistencias;  
	    JTextField txtPrecioVenta;
	    JTextField txtAcercaDe;
	    JTextField txtDescripcion;
	    
	    // Configuración básica de la ventana
	    setTitle("Editar Juego");
	    setSize(1024, 576);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);

	    // Usamos JLayeredPane para superponer componentes
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setPreferredSize(new Dimension(900, 650));
	    setContentPane(layeredPane);

	    // Panel central gris
	    JPanel panelCentral = new JPanel();
	    panelCentral.setLayout(null);
	    panelCentral.setBackground(Color.decode("#F2F2F2"));
	    panelCentral.setBounds(5, 62, 998, 475);
	    layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

	    // Logotipo
	    ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
	    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	    JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
	    logo.setBounds(477, 11, 70, 70);
	    panelCentral.add(logo);

	    // Etiquetas y campos del formulario
	    JLabel lblTitulo = new JLabel("EDITAR VIDEOJUEGOS");
	    lblTitulo.setSize(273, 42);
	    lblTitulo.setLocation(369, 78);
	    lblTitulo.setHorizontalAlignment(JLabel.LEFT);
	    lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
	    panelCentral.add(lblTitulo);

	    JLabel lblNombre = new JLabel("Nombre del Videojuego:");
	    lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblNombre.setBounds(55, 108, 140, 42);
	    panelCentral.add(lblNombre);

	    txtNombreJuego = new JTextField(videogames.getNombre());
	    txtNombreJuego.setBackground(Color.decode("#D9D9D9"));
	    txtNombreJuego.setBounds(53, 142, 230, 27);
	    panelCentral.add(txtNombreJuego);

	    JLabel lblAnioLanzamiento = new JLabel("Año de lanzamiento:");
	    lblAnioLanzamiento.setHorizontalAlignment(SwingConstants.LEFT);
	    lblAnioLanzamiento.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAnioLanzamiento.setBounds(53, 180, 153, 42);
	    panelCentral.add(lblAnioLanzamiento);

	    txtAnioLanzamiento = new JTextField(String.valueOf(videogames.getAñoLanzamiento()));
	    txtAnioLanzamiento.setBackground(Color.decode("#D9D9D9"));
	    txtAnioLanzamiento.setBounds(53, 214, 153, 27);
	    panelCentral.add(txtAnioLanzamiento);

	    JLabel lblGenero = new JLabel("Género:");
	    lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
	    lblGenero.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblGenero.setBounds(55, 259, 140, 42);
	    panelCentral.add(lblGenero);

	    txtGenero = new JTextField(videogames.getGenero());
	    txtGenero.setBackground(Color.decode("#D9D9D9"));
	    txtGenero.setBounds(55, 289, 188, 27);
	    panelCentral.add(txtGenero);

	    JLabel lblPrecioRenta = new JLabel("Precio a renta (MXN)");
	    lblPrecioRenta.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioRenta.setBounds(55, 327, 188, 42);
	    panelCentral.add(lblPrecioRenta);

	    txtPrecioRenta = new JTextField(String.valueOf(videogames.getPrecioRenta())); // Campo prellenado
	    txtPrecioRenta.setBackground(Color.decode("#D9D9D9"));
	    txtPrecioRenta.setBounds(55, 368, 188, 27);
	    panelCentral.add(txtPrecioRenta);

	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPlataforma.setBounds(301, 108, 97, 42);
	    panelCentral.add(lblPlataforma);

	    txtPlataforma = new JTextField(videogames.getPlataforma()); // Campo prellenado
	    txtPlataforma.setBackground(Color.decode("#D9D9D9"));
	    txtPlataforma.setBounds(301, 142, 202, 27);
	    panelCentral.add(txtPlataforma);

	    JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
	    lblDisponibilidad.setHorizontalAlignment(SwingConstants.LEFT);
	    lblDisponibilidad.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDisponibilidad.setBounds(227, 180, 120, 42);
	    panelCentral.add(lblDisponibilidad);

	    // CheckBox para disponibilidad
	    JTextField chkDisponible = new JTextField(""+videogames.isDisponibilidad());
	    chkDisponible.setBackground(Color.decode("#F2F2F2"));
	    chkDisponible.setBounds(227, 214, 120, 27);
	    panelCentral.add(chkDisponible);

	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblClasificacion.setBounds(369, 180, 97, 42);
	    panelCentral.add(lblClasificacion);

	    txtClasificacion = new JTextField(videogames.getClasificacion());
	    txtClasificacion.setBackground(Color.decode("#D9D9D9"));
	    txtClasificacion.setBounds(369, 214, 134, 27);
	    panelCentral.add(txtClasificacion);

	    JLabel lblExistencias = new JLabel("Existencias disponibles:");
	    lblExistencias.setHorizontalAlignment(SwingConstants.LEFT);
	    lblExistencias.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblExistencias.setBounds(288, 252, 215, 42);
	    panelCentral.add(lblExistencias);

	    txtExistencias = new JTextField(""+videogames.getExistenciasDisponibles());
	    txtExistencias.setBackground(Color.decode("#D9D9D9"));
	    txtExistencias.setBounds(288, 289, 215, 27);
	    panelCentral.add(txtExistencias);

	    JLabel lblPrecioVenta = new JLabel("Precio a venta (MXN):");
	    lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioVenta.setBounds(288, 327, 215, 42);
	    panelCentral.add(lblPrecioVenta);

	    txtPrecioVenta = new JTextField(String.valueOf(videogames.getPrecioVenta())); // Campo prellenado
	    txtPrecioVenta.setBackground(Color.decode("#D9D9D9"));
	    txtPrecioVenta.setBounds(288, 368, 215, 27);
	    panelCentral.add(txtPrecioVenta);

	    JLabel lblDescripcion = new JLabel("Desarrollado por:");
	    lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
	    lblDescripcion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDescripcion.setBounds(619, 108, 343, 42);
	    panelCentral.add(lblDescripcion);

	    txtDescripcion = new JTextField(videogames.getDesarrolladoPor());
	    txtDescripcion.setBackground(Color.decode("#D9D9D9"));
	    txtDescripcion.setBounds(619, 142, 343, 77);
	    panelCentral.add(txtDescripcion);

	    JLabel lblAcercaDe = new JLabel("Decripcion: ");
	    lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAcercaDe.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAcercaDe.setBounds(619, 220, 97, 42);
	    panelCentral.add(lblAcercaDe);

	    txtAcercaDe = new JTextField(videogames.getDescripcion());
	    txtAcercaDe.setBackground(Color.decode("#D9D9D9"));
	    txtAcercaDe.setBounds(619, 259, 343, 136);
	    panelCentral.add(txtAcercaDe);

	    // Botones
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBackground(Color.decode("#B82F2F"));
	    btnCancelar.setForeground(Color.WHITE);
	    btnCancelar.setBounds(175, 406, 183, 33);
	    btnCancelar.addActionListener(e -> {
	    	dispose();
	    	VideogamesController lt = new VideogamesController();
			lt.updateVideogames(videogames.getId());
	    	
	    });
	    panelCentral.add(btnCancelar);
	    
//	    boolean Disponibilidad = (chkDisponible.getText()) != null;

	    JButton btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.setBackground(Color.decode("#263C54"));
	    btnConfirmar.setForeground(Color.WHITE);
	    btnConfirmar.setBounds(533, 406, 183, 33);
	    btnConfirmar.addActionListener(e -> {
	        // Resetear todos los bordes a su estado normal
	        txtNombreJuego.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtPlataforma.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtClasificacion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtGenero.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtExistencias.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtAcercaDe.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	        // Validar campos vacíos
	        boolean camposValidos = true;
	        StringBuilder mensajeError = new StringBuilder();

	        if (txtNombreJuego.getText().trim().isEmpty()) {
	            txtNombreJuego.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Nombre del juego es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtPlataforma.getText().trim().isEmpty()) {
	            txtPlataforma.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Plataforma es obligatoria\n");
	            camposValidos = false;
	        }
	        
	        if (txtAnioLanzamiento.getText().trim().isEmpty()) {
	            txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Año de lanzamiento es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtClasificacion.getText().trim().isEmpty()) {
	            txtClasificacion.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Clasificación es obligatoria\n");
	            camposValidos = false;
	        }
	        
	        if (txtGenero.getText().trim().isEmpty()) {
	            txtGenero.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Género es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtExistencias.getText().trim().isEmpty()) {
	            txtExistencias.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Existencias disponibles es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtPrecioRenta.getText().trim().isEmpty()) {
	            txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Precio de renta es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtPrecioVenta.getText().trim().isEmpty()) {
	            txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Precio de venta es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtDescripcion.getText().trim().isEmpty()) {
	            txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Desarrollado por es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtAcercaDe.getText().trim().isEmpty()) {
	            txtAcercaDe.setBorder(BorderFactory.createLineBorder(Color.RED));
	            mensajeError.append("• Descripción es obligatoria\n");
	            camposValidos = false;
	        }

	        // Mostrar todos los errores de campos vacíos juntos
	        if (!camposValidos) {
	            JOptionPane.showMessageDialog(this, "Por favor complete los siguientes campos:\n\n" + mensajeError.toString(), 
	                                        "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        try {
	            // Validaciones numéricas
	            Integer AñoLanzamiento = Integer.parseInt(txtAnioLanzamiento.getText().trim());
	            Integer ExistenciasDisponibles = Integer.parseInt(txtExistencias.getText().trim());
	            BigDecimal PrecioRenta = new BigDecimal(txtPrecioRenta.getText().trim());
	            BigDecimal PrecioVenta = new BigDecimal(txtPrecioVenta.getText().trim());
	            
	            // Validaciones adicionales
	            if (AñoLanzamiento <= 0) {
	                txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.RED));
	                throw new NumberFormatException("El año de lanzamiento debe ser un número positivo");
	            }
	            
	            if (ExistenciasDisponibles < 0) {
	                txtExistencias.setBorder(BorderFactory.createLineBorder(Color.RED));
	                throw new NumberFormatException("Las existencias no pueden ser negativas");
	            }
	            
	            if (PrecioRenta.compareTo(BigDecimal.ZERO) <= 0) {
	                txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.RED));
	                throw new NumberFormatException("El precio de renta debe ser mayor a cero");
	            }
	            
	            if (PrecioVenta.compareTo(BigDecimal.ZERO) <= 0) {
	                txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.RED));
	                throw new NumberFormatException("El precio de venta debe ser mayor a cero");
	            }

	            // Obtener valores validados
	            String Nombre = txtNombreJuego.getText().trim();
	            String Plataforma = txtPlataforma.getText().trim();
	    	    boolean Disponibilidad = (chkDisponible.getText()) != null;
	            String Clasificacion = txtClasificacion.getText().trim();
	            String Genero = txtGenero.getText().trim();
	            String DesarrolladoPor = txtDescripcion.getText().trim();
	            String Descripcion = txtAcercaDe.getText().trim();

	            // Actualizar el videojuego
	            VideoGamesModel vm = new VideoGamesModel();
	            boolean actualizado = vm.updateVideogame(videogames.getId(), Nombre, Plataforma, AñoLanzamiento, 
	                    Disponibilidad, Clasificacion, Genero, ExistenciasDisponibles, 
	                    PrecioRenta, PrecioVenta, DesarrolladoPor, Descripcion);
	            
	            if (actualizado) {
	                JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente");
	                dispose();
	                
	                VideogamesController vc = new VideogamesController();
	                vc.updateVideogames(videogames.getId());
	            } else {
	                JOptionPane.showMessageDialog(this, "Error al guardar los cambios", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Error en formato numérico: " + ex.getMessage(), "Error",
	                    JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentral.add(btnConfirmar);

	    // Barra roja superior
	    JPanel barraRoja = new JPanel();
	    barraRoja.setBackground(Color.decode("#B44635"));
	    barraRoja.setBounds(0, 0, 1024, 60);
	    layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

	    setVisible(true);
	}




	// Renta y Compra
	public void AdministradorRentaCompra() {
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
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});
		panelIzq.add(btnVideojuegos);

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAl
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(361, 31, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("BIENVENIDO/A AL PANEL DE RENTA Y COMPRA");
		iniciar.setSize(548, 60);
		iniciar.setLocation(119, 112);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JButton renta = new JButton("RENTA");
		renta.setBounds(119, 242, 206, 100);
		panelCentral.add(renta);
		renta.setForeground(Color.WHITE);
		renta.setFont(new Font("Calibri", Font.BOLD, 16));
		renta.setBackground(new Color(38, 60, 84));
		renta.addActionListener(e -> {
			dispose(); // Abre la segunda ventana
			UserViews rentaa= new UserViews(); 
			rentaa.Renta();
			 // Cierra la ventana actual
		});

		JButton btnCompra = new JButton("VENTA");
		btnCompra.setForeground(Color.WHITE);
		btnCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnCompra.setBackground(new Color(38, 60, 84));
		btnCompra.setBounds(425, 242, 206, 100);
		btnCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			 // Abre la segunda ventana
			UserViews compra= new UserViews(); 
			compra.Compra();// Abre la segunda ventana
		});
		panelCentral.add(btnCompra);

		JLabel lblAquPuedesElegir = new JLabel("<html><center>Desde aquí podrás gestionar y controlar todas las funcionalides de renta y venta. \r\n"
				+ "¿Qué deseas hacer hoy?\r\n"
				+ "<html>");
		lblAquPuedesElegir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAquPuedesElegir.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAquPuedesElegir.setBounds(119, 154, 548, 60);
		panelCentral.add(lblAquPuedesElegir);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/images/Salida.png"));
		JButton salida = new JButton(icono);
		salida.setBounds(650, 390,60, 60);
		salida.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			 // Abre la segunda ventana
		});
		panelCentral.add(salida);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	

	

	


	public void Confirma_12() {
		// Configuración básica de la ventana
		setTitle("Confirma Eliminar Juego");
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		JLabel iniciar = new JLabel("Descargar Detalles de Videojuego");
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

	




	// Nueva Operacion
	public void NuevaOperacion() {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Configuración básica de la ventana
		setTitle("Nueva Operación");
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
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorCliente(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorJuegos(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnVideojuegos);

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorRentaCompra(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				NuevaOperacion(); // Abre la segunda ventana
			}
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAl
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(344, 31, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("¡ENTRASTE AL PANEL DE NUEVA OPERACIÓN!");
		iniciar.setSize(548, 60);
		iniciar.setLocation(100, 112);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JButton devoluciones = new JButton("<html><center>DEVOLUCIONES PENDIENTES<html>");
		devoluciones.setBounds(34, 261, 206, 100);
		panelCentral.add(devoluciones);
		devoluciones.setForeground(Color.WHITE);
		devoluciones.setFont(new Font("Calibri", Font.BOLD, 16));
		devoluciones.setBackground(new Color(38, 60, 84));
		devoluciones.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			DevolucionesPendientes(); // Abre la segunda ventana
		});

		JButton btnPromocionAutomatica = new JButton("<html><center>PROMOCIÓN AUTOMATICA<html>");
		btnPromocionAutomatica.setForeground(Color.WHITE);
		btnPromocionAutomatica.setFont(new Font("Calibri", Font.BOLD, 16));
		btnPromocionAutomatica.setBackground(new Color(38, 60, 84));
		btnPromocionAutomatica.setBounds(266, 261, 206, 100);
		btnPromocionAutomatica.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			PromocionAutomatica(); // Abre la segunda ventana
		});
		panelCentral.add(btnPromocionAutomatica);

		JLabel lblDesdeAquPodrs = new JLabel(
				"<html><center>Desde aqui podras gestionar y controlar todas las funcionalidades de los descuentos por clientes frecuentes, calculo de devolucion de cada cliente al igual que las promociones automaticas.\r\n"
				+ " ¿Qué deseas hacer hoy?\r\n<html>"
				+ "");
		lblDesdeAquPodrs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeAquPodrs.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDesdeAquPodrs.setBounds(90, 168, 494, 60);
		panelCentral.add(lblDesdeAquPodrs);

		JButton btnDescuentoPorCliente = new JButton("<html><center>DESCUENTO POR CLIENTE FRECUENTE<html>");
		btnDescuentoPorCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DescuentoFrecuencia(); // Abre la segunda ventana
			}
		});
		btnDescuentoPorCliente.setForeground(Color.WHITE);
		btnDescuentoPorCliente.setFont(new Font("Calibri", Font.BOLD, 16));
		btnDescuentoPorCliente.setBackground(new Color(38, 60, 84));
		btnDescuentoPorCliente.setBounds(497, 261, 206, 100);
		panelCentral.add(btnDescuentoPorCliente);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/images/Salida.png"));
		JButton salida = new JButton(icono);
		salida.setBounds(650, 390,60, 60);
		salida.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			 // Abre la segunda ventana
		});
		panelCentral.add(salida);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void DevolucionesPendientes() {
		// Configuración básica de la ventana
		setTitle("Devoluciones pendientes");
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
		btnClientes.setBounds(10, 11, 237, 100);
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorRentaCompra(); // Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("DEVOLUCIONES PENDIENTES");
		iniciar.setSize(324, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		btnBuscar.setBounds(619, 25, 86, 25);
//		panelCentral.add(btnBuscar);
		// Datos de la tabla
		Object[][] data = {
				{ "Identificador", "Nombre", "Plataforma", "Disponibilidad", "Precio(venta)", "Precio(renta)", },
				{ "000001", "Contra", "Nintendo", "5", "$800", "$100" },
				{ "000002", "God of War", "Play Station", "25", "$850", "$250" },
				{ "000003", "Halo", "Xbox", "32", "$890", "$640" }, { "000004", "Fornite", "Pc", "40", "$900", "$700" },
				{ "000005", "Pokemon", "Mixto", "41", "$950", "$720" },
				{ "000006", "ARK: Survival", "Mixto", "30", "$990", "$720" },

		};

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
		JTable table = new JTable(data, new String[] { "", "", "", "", "", "", });
		panelCentral.add(table);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setBounds(26, 62, 695, 402);
		table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		table.setShowGrid(true);
		table.setGridColor(new Color(204, 204, 204));
		table.setTableHeader(null);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setRowHeight(40);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 9); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void PromocionAutomatica() {
		// Configuración básica de la ventana
		setTitle("Promocion Automatica");
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
		btnClientes.setBounds(10, 11, 237, 100);
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorRentaCompra(); // Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("PROMOCIÓN AUTOMATICA");
		iniciar.setSize(324, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		btnBuscar.setBounds(619, 25, 86, 25);
//		panelCentral.add(btnBuscar);
		// Datos de la tabla
		Object[][] data = { { "Identificador", "Nombre", "Plataforma", }, { "", "", "", }, { "", "", "", },
				{ "", "", "", }, { "", "", "", }, { "", "", "", }, { "", "", "", }, { "", "", "", },

		};

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
		JTable table = new JTable(data, new String[] { "", "", "", });
		panelCentral.add(table);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setBounds(10, 61, 368, 356);
		table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		table.setShowGrid(true);
		table.setGridColor(new Color(204, 204, 204));
		table.setTableHeader(null);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setRowHeight(35);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		// Promociones
		Object[][] promociones = { { "En la Compra de:", "Descuentos por frecuencia", }, { "", "", }, { "", "", },
				{ "", "", }, { "", "", }, { "", "", }, { "", "", }, { "", "", },

		};

		DefaultTableCellRenderer centerRendere = new DefaultTableCellRenderer();
		centerRendere.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
		JTable tabla = new JTable(promociones, new String[] { "", "", });
		panelCentral.add(tabla);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tabla.setBounds(406, 61, 315, 356);
		tabla.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		tabla.setShowGrid(true);
		tabla.setGridColor(new Color(204, 204, 204));
		tabla.setTableHeader(null);
		tabla.setDefaultRenderer(Object.class, centerRendere);
		tabla.setRowHeight(35);
		tabla.setShowHorizontalLines(true);
		tabla.setShowVerticalLines(true);

		JButton btnEditarPromocion = new JButton("EDITAR PROMOCIÓN");
		btnEditarPromocion.setForeground(Color.WHITE);
		btnEditarPromocion.setBackground(Color.decode("#6D91B9"));
		btnEditarPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditarPromociones();

			}
		});
		btnEditarPromocion.setBounds(279, 428, 195, 30);
		panelCentral.add(btnEditarPromocion);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 9); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void EditarPromociones() {
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		JTextField textField_5;
		JTextField textField_6;
		JTextField textField_7;
		JTextField textField_8;
		JTextField textField_9;
		JTextField textField_10;
		JTextField textField_11;
		JTextField textField_12;
		JTextField textField_13;
		JTextField textField_14;
		JTextField textField_15;
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(477, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("EDITAR PROMOCIONES");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Compra de:");
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
			dispose(); // Cierra la ventana actual
			PromocionAutomatica(); // Abre la segunda ventana
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
//			Confirma_15(); // Abre la segunda ventana
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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_15() {
		// Configuración básica de la ventana
		setTitle("Confirma Eliminar Juego");
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		JLabel iniciar = new JLabel("Actualizar promociones");
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

	public void DescuentoFrecuencia() {
		// Configuración básica de la ventana
		setTitle(" Editar Descuento");
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
		btnClientes.setBounds(10, 11, 237, 100);
		btnClientes.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorRentaCompra(); // Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton nuevaoperacion = new JButton("NUEVA OPERACIÓN");
		nuevaoperacion.setForeground(Color.WHITE);
		nuevaoperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		nuevaoperacion.setBackground(new Color(38, 60, 84));
		nuevaoperacion.setBounds(10, 364, 237, 100);
		nuevaoperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorRentaCompra(); // Abre la segunda ventana
		});
		panelIzq.add(nuevaoperacion);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("DESCUENTO POR FRECUENCIA");
		iniciar.setSize(324, 60);
		iniciar.setLocation(26, 10);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		btnBuscar.setBounds(619, 25, 86, 25);
//		panelCentral.add(btnBuscar);

		// Datos de la tabla
		Object[][] data = { { "Identificador", "Nombre", "Plataforma", }, { "", "", "", }, { "", "", "", },
				{ "", "", "", }, { "", "", "", }, { "", "", "", }, { "", "", "", }, { "", "", "", },

		};

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
		JTable table = new JTable(data, new String[] { "", "", "", });
		panelCentral.add(table);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setBounds(10, 61, 368, 356);
		table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		table.setShowGrid(true);
		table.setGridColor(new Color(204, 204, 204));
		table.setTableHeader(null);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setRowHeight(35);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		// Promociones
		Object[][] promociones = { { "En la Compra de:", "Descuentos por frecuencia", }, { "", "", }, { "", "", },
				{ "", "", }, { "", "", }, { "", "", }, { "", "", }, { "", "", },

		};

		DefaultTableCellRenderer centerRendere = new DefaultTableCellRenderer();
		centerRendere.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
		JTable tabla = new JTable(promociones, new String[] { "", "", });
		panelCentral.add(tabla);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tabla.setBounds(406, 61, 315, 356);
		tabla.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		tabla.setShowGrid(true);
		tabla.setGridColor(new Color(204, 204, 204));
		tabla.setTableHeader(null);
		tabla.setDefaultRenderer(Object.class, centerRendere);
		tabla.setRowHeight(35);
		tabla.setShowHorizontalLines(true);
		tabla.setShowVerticalLines(true);

		JButton btnEditarPromocion = new JButton("EDITAR DESCUENTO");
		btnEditarPromocion.setBackground(Color.decode("#6D91B9"));
		btnEditarPromocion.setForeground(Color.WHITE);
		btnEditarPromocion.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnEditarPromocion.setBounds(294, 428, 184, 25);
		btnEditarPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditarDescuento(); // Abre la segunda ventana
			}
		});
		panelCentral.add(btnEditarPromocion);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 9); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void EditarDescuento() {
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		JTextField textField_5;
		JTextField textField_6;
		JTextField textField_7;
		JTextField textField_8;
		JTextField textField_9;
		JTextField textField_10;
		JTextField textField_11;
		JTextField textField_12;
		JTextField textField_13;
		JTextField textField_14;
		JTextField textField_15;
		// Configuración básica de la ventana
		setTitle("Editar Descuento");
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

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(477, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("EDITAR DESCUENTO");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Mes de:");
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
			dispose(); // Cierra la ventana actual
			DescuentoFrecuencia(); // Abre la segunda ventana
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			// Abre la segunda ventana
//			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnConfirmar);

		JLabel iniciar_1_1 = new JLabel("Mes de:");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(85, 181, 160, 42);
		panelCentral.add(iniciar_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(217, 217, 217));
		textField_1.setBounds(85, 207, 189, 27);
		panelCentral.add(textField_1);

		JLabel iniciar_1_1_1 = new JLabel("Mes de:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(85, 246, 160, 42);
		panelCentral.add(iniciar_1_1_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(217, 217, 217));
		textField_2.setBounds(85, 273, 189, 27);
		panelCentral.add(textField_2);

		JLabel iniciar_1_1_1_1 = new JLabel("Mes de:");
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

		JLabel iniciar_1_2_1 = new JLabel("Mes de:");
		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1.setBounds(530, 120, 160, 42);
		panelCentral.add(iniciar_1_2_1);

		JLabel iniciar_1_2_1_1 = new JLabel("Promocion de:");
		iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1.setBounds(749, 120, 160, 42);
		panelCentral.add(iniciar_1_2_1_1);

		JLabel iniciar_1_1_2_1 = new JLabel("Mes de:");
		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2_1.setBounds(530, 181, 160, 42);
		panelCentral.add(iniciar_1_1_2_1);

		JLabel iniciar_1_1_2_2 = new JLabel("Promocion de:");
		iniciar_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2_2.setBounds(749, 181, 160, 42);
		panelCentral.add(iniciar_1_1_2_2);

		JLabel iniciar_1_1_1_2_1 = new JLabel("Mes de:");
		iniciar_1_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1_2_1.setBounds(530, 246, 160, 42);
		panelCentral.add(iniciar_1_1_1_2_1);

		JLabel iniciar_1_1_1_2_1_1 = new JLabel("Promocion de:");
		iniciar_1_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1_2_1_1.setBounds(749, 246, 160, 42);
		panelCentral.add(iniciar_1_1_1_2_1_1);

		JLabel iniciar_1_1_1_1_1_1 = new JLabel("Mes de:");
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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

}

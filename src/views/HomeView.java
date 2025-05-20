package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import models.User;
import models.UsersModel;
import models.VideoGames;

public class HomeView extends JFrame {

	public HomeView() {
		// TODO Auto-generated constructor stub
	}

	public void PanelAdministrador() {

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
		panelCentral.setBounds(312, 70, 380, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(156, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("PANEL ADMINISTRADOR");
		iniciar.setSize(250, 60);
		iniciar.setLocation(66, 75);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel correo = new JLabel("Ingresar correo:");
		correo.setHorizontalAlignment(SwingConstants.CENTER);
		correo.setFont(new Font("SansSerif", Font.BOLD, 20));
		correo.setBounds(39, 146, 158, 26);
		panelCentral.add(correo);

		JTextField gmail = new JTextField();
		gmail.setBackground(Color.decode("#D9D9D9"));
		gmail.setSize(290, 30);
		gmail.setLocation(39, 288);
		gmail.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(gmail);

		JLabel contraseña = new JLabel("Contraseña:");
		contraseña.setSize(116, 26);
		contraseña.setLocation(39, 263);
		contraseña.setHorizontalAlignment(JLabel.CENTER);
		contraseña.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelCentral.add(contraseña);

		JTextField password = new JTextField();
		password.setBorder(BorderFactory.createLineBorder(Color.decode("#10A7DE")));
		password.setBackground(Color.decode("#D9D9D9"));
		password.setSize(290, 30);
		password.setLocation(39, 170);
		password.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(password);

		JButton acceder = new JButton("Acceder");
		acceder.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		acceder.setForeground(Color.WHITE); // Color del texto (blanco)
		acceder.setFont(new Font("Calibri", Font.BOLD, 15));
		acceder.setBounds(40, 371, 289, 26); // También puedes usar setSize + setLocation si prefieres
		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio(); // Abre la segunda ventana
			}
		});
		panelCentral.add(acceder);

		JLabel lblagregarNuevoAdministrador = new JLabel("¿Agregar nuevo administrador?");
		lblagregarNuevoAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblagregarNuevoAdministrador.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblagregarNuevoAdministrador.setBounds(27, 410, 224, 26);
		panelCentral.add(lblagregarNuevoAdministrador);

		JButton btnAgregarAqui = new JButton("Agregar aqui");
		btnAgregarAqui.setForeground(Color.BLACK);
		btnAgregarAqui.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAgregarAqui.setBackground(Color.decode("#F2F2F2"));
		btnAgregarAqui.setBounds(247, 414, 111, 26);
		panelCentral.add(btnAgregarAqui);
		btnAgregarAqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CodigoRegistro(); // Abre la segunda ventana
				dispose();
			}
		});
		panelCentral.add(btnAgregarAqui);

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

		JLabel iniciar_1 = new JLabel(
				"<html><div style='text-align: center;'>Desde aquí podrás gestionar y controlar todas las<br>"
						+ "funcionalidades de tu sitio. ¿Qué deseas hacer hoy?</div></html>");
		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(170, 183, 391, 83); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void CodigoRegistro() {

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
		panelCentral.setBounds(312, 91, 380, 423); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(156, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("PANEL ADMINISTRADOR");
		iniciar.setSize(250, 60);
		iniciar.setLocation(66, 75);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel correo = new JLabel("Ingresar contraseña de registro:");
		correo.setHorizontalAlignment(SwingConstants.CENTER);
		correo.setFont(new Font("SansSerif", Font.BOLD, 16));
		correo.setBounds(49, 133, 250, 26);
		panelCentral.add(correo);

		JTextField password = new JTextField();
		password.setBorder(BorderFactory.createLineBorder(Color.decode("#10A7DE")));
		password.setBackground(Color.decode("#D9D9D9"));
		password.setSize(290, 46);
		password.setLocation(49, 170);
		password.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(password);

		JButton acceder = new JButton("Acceder");
		acceder.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		acceder.setForeground(Color.WHITE); // Color del texto (blanco)
		acceder.setFont(new Font("Calibri", Font.BOLD, 15));
		acceder.setBounds(50, 317, 289, 26); // También puedes usar setSize + setLocation si prefieres
		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarAdministrador(); // Abre la segunda ventana
				dispose();
			}
		});
		panelCentral.add(acceder);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

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
			CodigoRegistro(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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
				"<html><div style='text-align: center;'>Desde aquí podrás gestionar y controlar todas las<br>"
						+ "funcionalidades de los clientes. ¿Qué deseas hacer hoy?</div></html>");
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
			AgregarCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnAgregarCliente);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			AdministradorRentaCompra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAL
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

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);

		// BOTON EDITAR PROVICIONAL, MEJORAR
		JButton btnEditar = new JButton("Editar");
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
			String nombre = (String) model.getValueAt(selectedRow, 1);
			String apellidoPaterno = (String) model.getValueAt(selectedRow, 2);
			String apellidoMaterno = (String) model.getValueAt(selectedRow, 3);
			String telefono = (String) model.getValueAt(selectedRow, 5);
			String correo = (String) model.getValueAt(selectedRow, 6);

			// Cerrar esta ventana y abrir ventana de edición con los datos cargados
			dispose();
			Object rawFecha = model.getValueAt(selectedRow, 4);

//			String fechaFormateada;
//			if (rawFecha instanceof java.sql.Date) {
//				java.sql.Date fecha = (java.sql.Date) rawFecha;
//				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//				fechaFormateada = sdf.format(fecha);
//			} else {
//				// Por si ya viene como String o en otro formato
//				fechaFormateada = rawFecha.toString();
//			}

//			EditarCliente(userId, nombre, apellidoPaterno, apellidoMaterno, fechaFormateada, telefono, correo);
			
			UserController uc = new UserController();
			uc.update(userId);

		});
		panelCentral.add(btnEditar);

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

	public void Confirma_1() {
		// Configuración básica de la ventana
		setTitle("Confirmar Eliminación");
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

		JLabel iniciar = new JLabel("Confirmar operación");
		iniciar.setSize(285, 42);
		iniciar.setLocation(233, 51);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));

		panelCentral.add(iniciar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(31, 130, 183, 33);
		panelCentral.add(btnCancelar);

		JButton btnSi = new JButton("Si");
		btnSi.setBackground(Color.decode("#6D91B9")); // Color de fondo (azul oscuro)
		btnSi.setForeground(Color.WHITE);
		btnSi.setBounds(418, 130, 183, 33);
		///////////////////////////////////////
		panelCentral.add(btnSi);

		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagen = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		setVisible(true);
	}

	public void DetallesCliente() {
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

		// botones
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBackground(Color.decode("#6D91B9"));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

				InformacionCliente();
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
//				RegistroClientes();
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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void EditarCliente(User user) {
		// Campos de texto renombrados con sentido
		JTextField txtNombre;
		JTextField txtApellidoMaterno;
		JTextField txtTelefono;
		JTextField txtApellidoPaterno;
		JTextField txtFechaNac = null;
		JTextField txtCorreo;

		// Configuración de la ventana
		setTitle("Editar Cliente");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Panel principal
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// Panel central
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

		// Título
		JLabel lblTitulo = new JLabel("EDITAR CLIENTE");
		lblTitulo.setSize(263, 42);
		lblTitulo.setLocation(370, 94);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// -------- LADO IZQUIERDO --------

		// Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(84, 135, 87, 42);
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
		panelCentral.add(lblNombre);

		txtNombre = new JTextField(user.getNombre());
		txtNombre.setBounds(84, 163, 330, 27);
		txtNombre.setBackground(Color.decode("#D9D9D9"));
		txtNombre.setColumns(10);
		panelCentral.add(txtNombre);

		// Apellido Materno
		JLabel lblApellidoMaterno = new JLabel("Apellido materno:");
		lblApellidoMaterno.setBounds(84, 209, 115, 42);
		lblApellidoMaterno.setFont(new Font("Calibri", Font.BOLD, 14));
		panelCentral.add(lblApellidoMaterno);

		txtApellidoMaterno = new JTextField(user.getApellidoMaterno());
		txtApellidoMaterno.setBounds(84, 236, 330, 27);
		txtApellidoMaterno.setBackground(Color.decode("#D9D9D9"));
		txtApellidoMaterno.setColumns(10);
		panelCentral.add(txtApellidoMaterno);

		// Teléfono
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(84, 282, 87, 42);
		lblTelefono.setFont(new Font("Calibri", Font.BOLD, 14));
		panelCentral.add(lblTelefono);

		txtTelefono = new JTextField(user.getTelefono());
		txtTelefono.setBounds(84, 311, 330, 27);
		txtTelefono.setBackground(Color.decode("#D9D9D9"));
		txtTelefono.setColumns(10);
		panelCentral.add(txtTelefono);

		// -------- LADO DERECHO --------

		// Apellido Paterno
		JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
		lblApellidoPaterno.setBounds(594, 135, 122, 42);
		lblApellidoPaterno.setFont(new Font("Calibri", Font.BOLD, 14));
		panelCentral.add(lblApellidoPaterno);

		txtApellidoPaterno = new JTextField(user.getApellidoPaterno());
		txtApellidoPaterno.setBounds(596, 163, 330, 27);
		txtApellidoPaterno.setBackground(Color.decode("#D9D9D9"));
		txtApellidoPaterno.setColumns(10);
		panelCentral.add(txtApellidoPaterno);

		// Fecha de nacimiento
		JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
		lblFechaNac.setBounds(596, 209, 136, 42);
		lblFechaNac.setFont(new Font("Calibri", Font.BOLD, 14));
		panelCentral.add(lblFechaNac);
//
//		txtFechaNac = new JTextField(user.getFechaNacimiento());
//		txtFechaNac.setBounds(596, 236, 330, 27);
//		txtFechaNac.setBackground(Color.decode("#D9D9D9"));
//		txtFechaNac.setColumns(10);
//		panelCentral.add(txtFechaNac);

		// Correo
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(596, 282, 87, 42);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 14));
		panelCentral.add(lblCorreo);

		txtCorreo = new JTextField(user.getCorreo());
		txtCorreo.setBounds(596, 311, 330, 27);
		txtCorreo.setBackground(Color.decode("#D9D9D9"));
		txtCorreo.setColumns(10);
		panelCentral.add(txtCorreo);

		// -------- BOTONES --------

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(e -> {
			dispose();
			DetallesCliente();
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(533, 406, 183, 33);
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.addActionListener(e -> {
			// Obtener valores desde los campos de texto
			String nuevoNombre = txtNombre.getText().trim();
			String nuevoApellidoPaterno = txtApellidoPaterno.getText().trim();
			String nuevoApellidoMaterno = txtApellidoMaterno.getText().trim();
			String nuevaFechaNacTexto = txtFechaNac.getText().trim();
			String nuevoTelefono = txtTelefono.getText().trim();
			String nuevoCorreo = txtCorreo.getText().trim();

			java.sql.Date nuevaFechaNac = null;

			try {
				// Validar y convertir la fecha
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				sdf.setLenient(false); // No permite fechas inválidas como 31/02/2023
				Date fechaUtil = sdf.parse(nuevaFechaNacTexto);
				nuevaFechaNac = new java.sql.Date(fechaUtil.getTime());
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(panelCentral, "Fecha inválida. Usa el formato dd/MM/yyyy",
						"Error de fecha", JOptionPane.ERROR_MESSAGE);
				return; // No continúa con el proceso si la fecha es inválida
			} catch (java.text.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Llamar al método del modelo para actualizar
			UsersModel model = new UsersModel();
			boolean actualizado = model.update(user.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno,
					nuevaFechaNac, nuevoTelefono, nuevoCorreo);

			if (actualizado) {
				JOptionPane.showMessageDialog(panelCentral, "Cliente actualizado correctamente", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
				DetallesCliente(); // Vuelve a la vista anterior
			} else {
				JOptionPane.showMessageDialog(panelCentral, "Error al actualizar el cliente", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		panelCentral.add(btnConfirmar);

		// -------- BARRA ROJA SUPERIOR --------

		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_2() {
		// Configuración básica de la ventana
		setTitle("Confirmar Eliminación");
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

		JLabel iniciar = new JLabel("¿Esta seguro de hacer cambios?");
		iniciar.setSize(285, 42);
		iniciar.setLocation(233, 51);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));

		panelCentral.add(iniciar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(31, 130, 183, 33);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		panelCentral.add(btnCancelar);

		JButton btnSi = new JButton("SI");
		btnSi.setBackground(Color.decode("#6D91B9")); // Color de fondo (azul oscuro)
		btnSi.setForeground(Color.WHITE);
		btnSi.setBounds(418, 130, 183, 33);
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformacionCliente();
				dispose();

			}
		});
		panelCentral.add(btnSi);

		setVisible(true);
	}

	public void InformacionCliente() {
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
			DetallesCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("DESCARGAR PDF");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(621, 406, 183, 33);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirma_3(); // Abre la segunda ventana

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

	public void Confirma_3() {
		// Configuración básica de la ventana
		setTitle("Confirmar Eliminación");
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

		JLabel iniciar = new JLabel("Descargar información del Cliente");
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
			InformacionCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Descargar PDF");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirmar_4();
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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirmar_4() {
		// Configuración básica de la ventana
		setTitle("Descargar Tarjeta de Cliente");
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

		JLabel iniciar = new JLabel("¿Esta seguro de hacer cambios?");
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

		JButton btnSi = new JButton("SI");
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

	public void AgregarCliente() {

		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		JTextField textField_5;
		// Configuración básica de la ventana
		setTitle("Agregar Cliente");
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
		logo.setBounds(465, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("AGREGAR CLIENTE");
		iniciar.setSize(263, 42);
		iniciar.setLocation(370, 94);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Nombre:");
		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(84, 135, 87, 42); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JTextField nJuego = new JTextField();
		nJuego.setBackground(Color.decode("#D9D9D9"));
		nJuego.setBounds(84, 163, 330, 27);
		panelCentral.add(nJuego);
		nJuego.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Guardar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(533, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			Confirma_5(); // Abre la segunda ventana
			// Cierra la ventana actual
		});
		panelCentral.add(btnConfirmar);

		JLabel iniciar_1_1 = new JLabel("Apellido materno:");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(84, 209, 115, 42);
		panelCentral.add(iniciar_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(217, 217, 217));
		textField_1.setBounds(84, 236, 330, 27);
		panelCentral.add(textField_1);

		JLabel iniciar_1_1_1 = new JLabel("Telefono:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(84, 282, 87, 42);
		panelCentral.add(iniciar_1_1_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(217, 217, 217));
		textField_2.setBounds(84, 311, 330, 27);
		panelCentral.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(217, 217, 217));
		textField_3.setBounds(596, 163, 330, 27);
		panelCentral.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(217, 217, 217));
		textField_4.setBounds(596, 236, 330, 27);
		panelCentral.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(217, 217, 217));
		textField_5.setBounds(596, 311, 330, 27);
		panelCentral.add(textField_5);

		JLabel iniciar_1_2 = new JLabel("Apellido paterno:");
		iniciar_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2.setBounds(594, 135, 122, 42);
		panelCentral.add(iniciar_1_2);

		JLabel iniciar_1_1_2 = new JLabel("Fecha de nacimiento:");
		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2.setBounds(596, 209, 136, 42);
		panelCentral.add(iniciar_1_1_2);

		JLabel iniciar_1_1_1_1 = new JLabel("Correo:");
		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1_1.setBounds(596, 282, 87, 42);
		panelCentral.add(iniciar_1_1_1_1);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_5() {
		// Configuración básica de la ventana
		setTitle("Guardar Cliente");
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

		JLabel iniciar = new JLabel("Guardar cliente");
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
				AdministradorCliente(); // Abre la segunda ventana
				dispose();
			}
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
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
				NuevaOperacion(); // Abre la segunda ventana
				dispose();
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

		JButton registros = new JButton("REGISTROS DE VIDEOJUEGOS");
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

		JButton btnAgregarVideojuegos = new JButton("AGREGAR VIDEOJUEGOS");
		btnAgregarVideojuegos.setForeground(Color.WHITE);
		btnAgregarVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAgregarVideojuegos.setBackground(new Color(38, 60, 84));
		btnAgregarVideojuegos.setBounds(442, 261, 206, 100);
		panelCentral.add(btnAgregarVideojuegos);

		JLabel lblDesdeAquPodrs = new JLabel(
				"Desde aquí podrás gestionar y controlar todas las \n funcionalidades de videojuegos. \n¿Qué deseas hacer hoy?\n");
		lblDesdeAquPodrs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeAquPodrs.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDesdeAquPodrs.setBounds(100, 168, 464, 60);
		panelCentral.add(lblDesdeAquPodrs);
		btnAgregarVideojuegos.addActionListener(e -> {
			AgregarJuego(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		panelIzq.add(btnRentaYCompra);
		btnRentaYCompra.addActionListener(e -> {
			AdministradorRentaCompra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnClientes_3 = new JButton("NUEVA OPERACIÓN");
		btnClientes_3.setForeground(Color.WHITE);
		btnClientes_3.setFont(new Font("Calibri", Font.BOLD, 16));
		btnClientes_3.setBackground(new Color(38, 60, 84));
		btnClientes_3.setBounds(10, 364, 237, 100);
		panelIzq.add(btnClientes_3);
		btnClientes_3.addActionListener(e -> {
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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

		// Crear unan tabla
		String[] columnNames = { "ID", "Nombre", "Plataforma", "Disponibilida", "Precio venta", "Precio renta" };
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
				        juego.isDisponibilidad() ? "Disponible" : "No disponible",
				        "$" + juego.getPrecioCompra(),
				        "$" + juego.getPrecioranta(),
				        juego.getClasificacion()
				    };
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

//		JButton btnNewButton = new JButton("DETALLES");
//		btnNewButton.setForeground(Color.WHITE);
//		btnNewButton.setBackground(Color.decode("#6D91B9"));
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				DetallesJuego();
//				dispose();
//
//			}
//		});
//		btnNewButton.setBounds(406, 439, 172, 25);
//		panelCentral.add(btnNewButton);
		
		// BOTÓN EDITAR
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.decode("#4fadbd"));
		btnEditar.setBounds(26, 439, 172, 25);
		btnEditar.addActionListener(e -> {
		    int selectedRow = table.getSelectedRow();
		    
		    if (selectedRow == -1) {
		        JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un juego", "Advertencia",
		                JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    // Obtener datos del juego seleccionado
		    int juegoId = (int) model.getValueAt(selectedRow, 0);
		    String nombre = (String) model.getValueAt(selectedRow, 1);
		    String plataforma = (String) model.getValueAt(selectedRow, 2);
		    String disponibilidad = (String) model.getValueAt(selectedRow, 3);
		    String precioVenta = ((String) model.getValueAt(selectedRow, 4)).substring(1); // Eliminar el $
		    String precioRenta = ((String) model.getValueAt(selectedRow, 5)).substring(1); // Eliminar el $

		    // Cerrar esta ventana y abrir ventana de edición con los datos cargados
		    dispose();
		    EditarJuego(juegoId, nombre, plataforma, disponibilidad.equals("Disponible"), 
		            Double.parseDouble(precioVenta), Double.parseDouble(precioRenta));
		});
		panelCentral.add(btnEditar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.decode("#B82F2F"));
		btnEliminar.setBounds(187, 439, 172, 25);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panelCentral.add(btnEliminar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);

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


	public void DetallesJuego() {
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
	    JLabel lblNombre = new JLabel("CONTRA");
	    lblNombre.setFont(new Font("Anton", Font.BOLD, 24));
	    lblNombre.setBounds(55, 124, 135, 42);
	    panelCentral.add(lblNombre);

	    // Año
	    JLabel lblAnio = new JLabel("1988");
	    lblAnio.setForeground(Color.decode("#3B3741"));
	    lblAnio.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblAnio.setBounds(55, 161, 70, 42);
	    panelCentral.add(lblAnio);

	    // Clasificación
	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblClasificacion.setBounds(55, 198, 103, 42);
	    panelCentral.add(lblClasificacion);

	    JLabel lblClasificacionValor = new JLabel("Mayores 18");
	    lblClasificacionValor.setForeground(Color.decode("#3B3741"));
	    lblClasificacionValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblClasificacionValor.setBounds(158, 198, 103, 42);
	    panelCentral.add(lblClasificacionValor);

	    // Desarrolladores
	    JLabel lblDesarrolladores = new JLabel("Desarrolladores:");
	    lblDesarrolladores.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblDesarrolladores.setBounds(55, 233, 190, 42);
	    panelCentral.add(lblDesarrolladores);

	    JTextArea txtDesarrolladores = new JTextArea("Konami, Ocean Software, Hamster Corporation, Digital Eclipse, Backbone Entertainment, Paul Owens");
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

	    JLabel lblGeneroValor = new JLabel("Disparos");
	    lblGeneroValor.setForeground(Color.decode("#3B3741"));
	    lblGeneroValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblGeneroValor.setBounds(118, 333, 70, 42);
	    panelCentral.add(lblGeneroValor);

	    // Plataforma
	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblPlataforma.setBounds(55, 372, 97, 42);
	    panelCentral.add(lblPlataforma);

	    JLabel lblPlataformaValor = new JLabel("Nintendo");
	    lblPlataformaValor.setForeground(Color.decode("#3B3741"));
	    lblPlataformaValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblPlataformaValor.setBounds(148, 372, 97, 42);
	    panelCentral.add(lblPlataformaValor);

	    // Descripción
	    JLabel lblAcercaDe = new JLabel("Acerca de:");
	    lblAcercaDe.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblAcercaDe.setBounds(379, 72, 97, 42);
	    panelCentral.add(lblAcercaDe);

	    JTextArea txtDescripcion = new JTextArea("Contra es un videojuego de shoot 'em up desarrollado y publicado por Konami, lanzado originalmente como un juego de arcade el 20 de febrero de 1987. En 1988 se lanzó una versión doméstica para Nintendo Entertainment System, junto con puertos para varios formatos de computadora, incluido el MSX2.");
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
	    lblPrecioRenta.setBounds(664, 317, 150, 42);
	    panelCentral.add(lblPrecioRenta);

	    JLabel lblPrecioVenta = new JLabel("Precio por venta:");
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblPrecioVenta.setBounds(664, 353, 159, 42);
	    panelCentral.add(lblPrecioVenta);

	    JLabel lblValorRenta = new JLabel("$100.00MXN");
	    lblValorRenta.setForeground(new Color(153, 0, 0));
	    lblValorRenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblValorRenta.setBounds(824, 317, 126, 42);
	    panelCentral.add(lblValorRenta);

	    JLabel lblValorVenta = new JLabel("$800.00MX");
	    lblValorVenta.setForeground(new Color(153, 0, 0));
	    lblValorVenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblValorVenta.setBounds(824, 353, 126, 42);
	    panelCentral.add(lblValorVenta);

	    // Botón Editar
	    JButton btnEditar = new JButton("Editar");
	    btnEditar.setBackground(Color.decode("#6D91B9"));
	    btnEditar.setForeground(Color.WHITE);
	    btnEditar.setBounds(850, 420, 100, 35); // Ajusta posición si es necesario
	    btnEditar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
//	            EditarJuego();
	            dispose();
	        }
	    });
	    panelCentral.add(btnEditar);

	    setVisible(true);
	}


	public void Confirma_7() {
		// Configuración básica de la ventana
		setTitle("Guardar Cliente");
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

		JLabel iniciar = new JLabel("Descargar Detalles del Juego");
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

	public void EditarJuego(Integer juegoId, String nombre, String plataforma, boolean disponibilidad, 
	        double precioVenta, double precioRenta) {
	    
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
	    lblTitulo.setSize(263, 42);
	    lblTitulo.setLocation(369, 78);
	    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
	    panelCentral.add(lblTitulo);

	    JLabel lblNombre = new JLabel("Nombre del Videojuego:");
	    lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblNombre.setBounds(55, 108, 140, 42);
	    panelCentral.add(lblNombre);

	    txtNombreJuego = new JTextField(nombre); // Campo prellenado con el nombre actual
	    txtNombreJuego.setBackground(Color.decode("#D9D9D9"));
	    txtNombreJuego.setBounds(53, 142, 230, 27);
	    panelCentral.add(txtNombreJuego);

	    JLabel lblAnioLanzamiento = new JLabel("Año de lanzamiento:");
	    lblAnioLanzamiento.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAnioLanzamiento.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAnioLanzamiento.setBounds(55, 180, 132, 42);
	    panelCentral.add(lblAnioLanzamiento);

	    txtAnioLanzamiento = new JTextField();
	    txtAnioLanzamiento.setBackground(Color.decode("#D9D9D9"));
	    txtAnioLanzamiento.setBounds(53, 214, 153, 27);
	    panelCentral.add(txtAnioLanzamiento);

	    JLabel lblGenero = new JLabel("Género:");
	    lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
	    lblGenero.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblGenero.setBounds(55, 259, 140, 42);
	    panelCentral.add(lblGenero);

	    txtGenero = new JTextField();
	    txtGenero.setBackground(Color.decode("#D9D9D9"));
	    txtGenero.setBounds(55, 289, 188, 27);
	    panelCentral.add(txtGenero);

	    JLabel lblPrecioRenta = new JLabel("Precio a renta (MXN)");
	    lblPrecioRenta.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioRenta.setBounds(55, 327, 140, 42);
	    panelCentral.add(lblPrecioRenta);

	    txtPrecioRenta = new JTextField(String.valueOf(precioRenta)); // Campo prellenado
	    txtPrecioRenta.setBackground(Color.decode("#D9D9D9"));
	    txtPrecioRenta.setBounds(55, 368, 188, 27);
	    panelCentral.add(txtPrecioRenta);

	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPlataforma.setBounds(301, 108, 97, 42);
	    panelCentral.add(lblPlataforma);

	    txtPlataforma = new JTextField(plataforma); // Campo prellenado
	    txtPlataforma.setBackground(Color.decode("#D9D9D9"));
	    txtPlataforma.setBounds(301, 142, 202, 27);
	    panelCentral.add(txtPlataforma);

	    JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
	    lblDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDisponibilidad.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDisponibilidad.setBounds(227, 180, 97, 42);
	    panelCentral.add(lblDisponibilidad);

	    // CheckBox para disponibilidad
	    JCheckBox chkDisponible = new JCheckBox("Disponible");
	    chkDisponible.setSelected(disponibilidad); // Prellenado
	    chkDisponible.setBackground(Color.decode("#F2F2F2"));
	    chkDisponible.setBounds(227, 214, 120, 27);
	    panelCentral.add(chkDisponible);

	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblClasificacion.setBounds(369, 180, 97, 42);
	    panelCentral.add(lblClasificacion);

	    txtClasificacion = new JTextField();
	    txtClasificacion.setBackground(Color.decode("#D9D9D9"));
	    txtClasificacion.setBounds(369, 214, 134, 27);
	    panelCentral.add(txtClasificacion);

	    JLabel lblExistencias = new JLabel("Existencias disponibles:");
	    lblExistencias.setHorizontalAlignment(SwingConstants.CENTER);
	    lblExistencias.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblExistencias.setBounds(289, 252, 153, 42);
	    panelCentral.add(lblExistencias);

	    txtExistencias = new JTextField();
	    txtExistencias.setBackground(Color.decode("#D9D9D9"));
	    txtExistencias.setBounds(288, 289, 215, 27);
	    panelCentral.add(txtExistencias);

	    JLabel lblPrecioVenta = new JLabel("Precio a venta (MXN):");
	    lblPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioVenta.setBounds(288, 327, 140, 42);
	    panelCentral.add(lblPrecioVenta);

	    txtPrecioVenta = new JTextField(String.valueOf(precioVenta)); // Campo prellenado
	    txtPrecioVenta.setBackground(Color.decode("#D9D9D9"));
	    txtPrecioVenta.setBounds(288, 368, 215, 27);
	    panelCentral.add(txtPrecioVenta);

	    JLabel lblDescripcion = new JLabel("Descripción:");
	    lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
	    lblDescripcion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDescripcion.setBounds(619, 108, 97, 42);
	    panelCentral.add(lblDescripcion);

	    txtDescripcion = new JTextField();
	    txtDescripcion.setBackground(Color.decode("#D9D9D9"));
	    txtDescripcion.setBounds(619, 142, 343, 77);
	    panelCentral.add(txtDescripcion);

	    JLabel lblAcercaDe = new JLabel("Acerca de:");
	    lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAcercaDe.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAcercaDe.setBounds(619, 220, 97, 42);
	    panelCentral.add(lblAcercaDe);

	    txtAcercaDe = new JTextField();
	    txtAcercaDe.setBackground(Color.decode("#D9D9D9"));
	    txtAcercaDe.setBounds(619, 259, 343, 136);
	    panelCentral.add(txtAcercaDe);

	    // Botones
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBackground(Color.decode("#B82F2F"));
	    btnCancelar.setForeground(Color.WHITE);
	    btnCancelar.setBounds(175, 406, 183, 33);
	    btnCancelar.addActionListener(e -> {
	        DetallesJuego();
	        dispose();
	    });
	    panelCentral.add(btnCancelar);

	    JButton btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.setBackground(Color.decode("#263C54"));
	    btnConfirmar.setForeground(Color.WHITE);
	    btnConfirmar.setBounds(533, 406, 183, 33);
	    btnConfirmar.addActionListener(e -> {
	        // Aquí iría la lógica para guardar los cambios
	        Confirma_8();
	    });
	    panelCentral.add(btnConfirmar);

	    // Barra roja superior
	    JPanel barraRoja = new JPanel();
	    barraRoja.setBackground(Color.decode("#B44635"));
	    barraRoja.setBounds(0, 0, 1024, 60);
	    layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

	    setVisible(true);
	}

	public void Confirma_8() {
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

		// Logotipo
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

	public void AgregarJuego() {
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
		panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(477, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("AGREGAR VIDEOJUEGOS");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Nombre del Videojuego:");
		iniciar_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(55, 108, 164, 42); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JLabel iniciar_1_1 = new JLabel("Año de lanzamiento :");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(55, 180, 132, 42);
		panelCentral.add(iniciar_1_1);

		JLabel iniciar_1_1_1 = new JLabel("Género:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(55, 259, 140, 42);
		panelCentral.add(iniciar_1_1_1);

		JLabel iniciar_1_1_1_1 = new JLabel("Precio a renta (MXN)");
		iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1_1.setBounds(55, 327, 140, 42);
		panelCentral.add(iniciar_1_1_1_1);

		JTextField nJuego = new JTextField();
		nJuego.setBackground(Color.decode("#D9D9D9"));
		nJuego.setBounds(53, 142, 230, 27);
		panelCentral.add(nJuego);
		nJuego.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.decode("#D9D9D9"));
		textField_1.setColumns(10);
		textField_1.setBounds(53, 214, 153, 27);
		panelCentral.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBackground(Color.decode("#D9D9D9"));
		textField_2.setColumns(10);
		textField_2.setBounds(55, 289, 188, 27);
		panelCentral.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setBackground(Color.decode("#D9D9D9"));
		textField_3.setColumns(10);
		textField_3.setBounds(55, 368, 188, 27);
		panelCentral.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setBackground(Color.decode("#D9D9D9"));
		textField_4.setColumns(10);
		textField_4.setBounds(227, 214, 120, 27);
		panelCentral.add(textField_4);

		JLabel iniciar_1_1_2 = new JLabel("Disponibilidad:");
		iniciar_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2.setBounds(227, 180, 97, 42);
		panelCentral.add(iniciar_1_1_2);

		textField_5 = new JTextField();
		textField_5.setBackground(Color.decode("#D9D9D9"));
		textField_5.setColumns(10);
		textField_5.setBounds(301, 142, 202, 27);
		panelCentral.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setBackground(Color.decode("#D9D9D9"));
		textField_6.setColumns(10);
		textField_6.setBounds(369, 214, 134, 27);
		panelCentral.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setBackground(Color.decode("#D9D9D9"));
		textField_7.setColumns(10);
		textField_7.setBounds(288, 289, 215, 27);
		panelCentral.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setBackground(Color.decode("#D9D9D9"));
		textField_8.setColumns(10);
		textField_8.setBounds(288, 368, 215, 27);
		panelCentral.add(textField_8);

		JLabel iniciar_1_1_2_1 = new JLabel("Plataforma:");
		iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2_1.setBounds(301, 108, 97, 42);
		panelCentral.add(iniciar_1_1_2_1);

		JLabel iniciar_1_1_2_2 = new JLabel("Clasificación:");
		iniciar_1_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2_2.setBounds(369, 180, 97, 42);
		panelCentral.add(iniciar_1_1_2_2);

		JLabel iniciar_1_1_3 = new JLabel("Existencias disponibles:");
		iniciar_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_3.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_3.setBounds(289, 252, 153, 42);
		panelCentral.add(iniciar_1_1_3);

		JLabel iniciar_1_1_1_1_1 = new JLabel("Precio a venta (MXN):");
		iniciar_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1_1_1.setBounds(288, 327, 140, 42);
		panelCentral.add(iniciar_1_1_1_1_1);

		textField_9 = new JTextField();
		textField_9.setBackground(Color.decode("#D9D9D9"));
		textField_9.setColumns(10);
		textField_9.setBounds(619, 259, 343, 136);
		panelCentral.add(textField_9);

		JLabel iniciar_1_1_2_2_1 = new JLabel("Acerca de:");
		iniciar_1_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2_2_1.setBounds(619, 220, 97, 42);
		panelCentral.add(iniciar_1_1_2_2_1);

		JLabel iniciar_1_1_2_2_1_1 = new JLabel("Acerca de:");
		iniciar_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_2_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_2_2_1_1.setBounds(619, 108, 97, 42);
		panelCentral.add(iniciar_1_1_2_2_1_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(259, 417, 183, 33);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorJuegos();
				dispose();

			}
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(561, 417, 183, 33);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirma_9();

			}
		});
		panelCentral.add(btnConfirmar);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBackground(new Color(217, 217, 217));
		textField_10.setBounds(619, 145, 343, 82);
		panelCentral.add(textField_10);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_9() {
		// Configuración básica de la ventana
		setTitle("Guardar videojuego");
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

		JLabel iniciar = new JLabel("Guardar videojuego");
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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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

		JButton registros = new JButton("Renta");
		registros.setBounds(119, 242, 206, 100);
		panelCentral.add(registros);
		registros.setForeground(Color.WHITE);
		registros.setFont(new Font("Calibri", Font.BOLD, 16));
		registros.setBackground(new Color(38, 60, 84));
		registros.addActionListener(e -> {
			Renta(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnCompra = new JButton("Venta");
		btnCompra.setForeground(Color.WHITE);
		btnCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnCompra.setBackground(new Color(38, 60, 84));
		btnCompra.setBounds(425, 242, 206, 100);
		btnCompra.addActionListener(e -> {
			Compra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Renta() {
		// Configuración básica de la ventana
		setTitle("Renta");
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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			AdministradorRentaCompra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("RENTA");
		iniciar.setSize(236, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

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
		table.setBounds(26, 62, 695, 346);
		table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		table.setShowGrid(true);
		table.setGridColor(new Color(204, 204, 204));
		table.setTableHeader(null);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setRowHeight(40);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		JButton btnNewButton = new JButton("RENTAR");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.decode("#6D91B9"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacionRentar();
				dispose();

			}
		});
		btnNewButton.setBounds(536, 419, 151, 30);
		panelCentral.add(btnNewButton);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);

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

	public void OperacionRentar() {
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		// Configuración básica de la ventana
		setTitle("Operación Rentar");
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

		JLabel iniciar = new JLabel("DETALLES DE OPERACIÓN");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Nombre del cliente");
		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(85, 120, 160, 42); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JTextField nJuego = new JTextField();
		nJuego.setBackground(Color.decode("#D9D9D9"));
		nJuego.setBounds(85, 161, 255, 27);
		panelCentral.add(nJuego);
		nJuego.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			Renta(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			Confirma_11(); // Abre la segunda ventana
			// Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);
		panelCentral.add(btnConfirmar);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(217, 217, 217));
		textField_1.setBounds(405, 161, 263, 27);
		panelCentral.add(textField_1);

		JLabel iniciar_1_1 = new JLabel("Nombre del Videojuego:");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(405, 120, 160, 42);
		panelCentral.add(iniciar_1_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(217, 217, 217));
		textField_2.setBounds(697, 161, 165, 27);
		panelCentral.add(textField_2);

		JLabel iniciar_1_1_1 = new JLabel("Cantidad:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(697, 120, 160, 42);
		panelCentral.add(iniciar_1_1_1);

		JLabel iniciar_1_2 = new JLabel("Nombre de videojuego");
		iniciar_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2.setBounds(75, 215, 255, 42);
		panelCentral.add(iniciar_1_2);

		JLabel iniciar_1_2_1 = new JLabel("Precio (MXN):");
		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1.setBounds(403, 215, 255, 42);
		panelCentral.add(iniciar_1_2_1);

		JLabel iniciar_1_2_2 = new JLabel("Contra");
		iniciar_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_2.setBounds(75, 246, 255, 42);
		panelCentral.add(iniciar_1_2_2);

		JLabel iniciar_1_2_1_1 = new JLabel("$100");
		iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1.setBounds(403, 246, 255, 42);
		panelCentral.add(iniciar_1_2_1_1);

		JLabel iniciar_1_2_3 = new JLabel("Descuento:");
		iniciar_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_3.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_3.setBounds(75, 293, 255, 42);
		panelCentral.add(iniciar_1_2_3);

		JLabel iniciar_1_2_2_1 = new JLabel("07%");
		iniciar_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_2_1.setBounds(75, 320, 255, 42);
		panelCentral.add(iniciar_1_2_2_1);

		JLabel iniciar_1_2_1_2 = new JLabel("Fecha:");
		iniciar_1_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_2.setBounds(403, 293, 255, 42);
		panelCentral.add(iniciar_1_2_1_2);

		JLabel iniciar_1_2_1_1_1 = new JLabel("15/05/2025");
		iniciar_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1_1.setBounds(403, 320, 255, 42);
		panelCentral.add(iniciar_1_2_1_1_1);

		JLabel iniciar_1_2_1_3 = new JLabel("Tipo:");
		iniciar_1_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_3.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_3.setBounds(687, 215, 101, 42);
		panelCentral.add(iniciar_1_2_1_3);

		JLabel iniciar_1_2_1_1_2 = new JLabel("Renta");
		iniciar_1_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1_2.setBounds(687, 246, 101, 42);
		panelCentral.add(iniciar_1_2_1_1_2);

		JLabel iniciar_1_2_1_2_1 = new JLabel("Fecha de devolución:");
		iniciar_1_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_2_1.setBounds(643, 293, 201, 42);
		panelCentral.add(iniciar_1_2_1_2_1);

		JLabel iniciar_1_2_1_1_1_1 = new JLabel("15/06/2025");
		iniciar_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1_1_1.setBounds(672, 320, 143, 42);
		panelCentral.add(iniciar_1_2_1_1_1_1);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_11() {
		// Configuración básica de la ventana
		setTitle("Confirma Renta");
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

		JLabel iniciar = new JLabel("Confirmar Operacion");
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
				DetallesRenta();
				dispose();

			}
		});
		panelCentral.add(btnSi);

		setVisible(true);
	}

	public void DetallesRenta() {
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

		// Logotipo
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
				Renta();
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

				Confirma_12();

			}
		});
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(614, 406, 183, 33);
		panelCentral.add(btnConfirmar);

		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagen = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagen));
		logo.setBounds(32, 32, 184, 112); // posicion
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

	public void Compra() {
		// Configuración básica de la ventana
		setTitle("Compra");
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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			AdministradorRentaCompra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("VENTA");
		iniciar.setSize(236, 60);
		iniciar.setLocation(143, 11);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);
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
		table.setBounds(26, 62, 695, 346);
		table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		table.setShowGrid(true);
		table.setGridColor(new Color(204, 204, 204));
		table.setTableHeader(null);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setRowHeight(40);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacionComprar();
				dispose();

			}
		});
		btnNewButton.setBounds(528, 439, 143, 25);
		panelCentral.add(btnNewButton);

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

	public void OperacionComprar() {
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		// Configuración básica de la ventana
		setTitle("Detalles de operación Comprar");
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

		JLabel iniciar = new JLabel("DETALLES DE OPERACIÓN");
		iniciar.setSize(263, 42);
		iniciar.setLocation(369, 78);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel iniciar_1 = new JLabel("Nombre del cliente");
		iniciar_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1.setBounds(85, 120, 160, 42); // Ajusta tamaño si es necesario
		panelCentral.add(iniciar_1);

		JTextField nJuego = new JTextField();
		nJuego.setBackground(Color.decode("#D9D9D9"));
		nJuego.setBounds(85, 161, 255, 27);
		panelCentral.add(nJuego);
		nJuego.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			Compra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			Confirma_13(); // Abre la segunda ventana
			// Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);
		panelCentral.add(btnConfirmar);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(217, 217, 217));
		textField_1.setBounds(405, 161, 263, 27);
		panelCentral.add(textField_1);

		JLabel iniciar_1_1 = new JLabel("Nombre del Videojuego:");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(405, 120, 160, 42);
		panelCentral.add(iniciar_1_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(217, 217, 217));
		textField_2.setBounds(697, 161, 165, 27);
		panelCentral.add(textField_2);

		JLabel iniciar_1_1_1 = new JLabel("Cantidad:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(697, 120, 160, 42);
		panelCentral.add(iniciar_1_1_1);

		JLabel iniciar_1_2 = new JLabel("Nombre de videojuego");
		iniciar_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2.setBounds(75, 215, 255, 42);
		panelCentral.add(iniciar_1_2);

		JLabel iniciar_1_2_1 = new JLabel("Precio (MXN):");
		iniciar_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1.setBounds(403, 215, 255, 42);
		panelCentral.add(iniciar_1_2_1);

		JLabel iniciar_1_2_2 = new JLabel("Contra");
		iniciar_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_2.setBounds(75, 246, 255, 42);
		panelCentral.add(iniciar_1_2_2);

		JLabel iniciar_1_2_1_1 = new JLabel("$100");
		iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1.setBounds(403, 246, 255, 42);
		panelCentral.add(iniciar_1_2_1_1);

		JLabel iniciar_1_2_3 = new JLabel("Descuento:");
		iniciar_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_3.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_3.setBounds(75, 293, 255, 42);
		panelCentral.add(iniciar_1_2_3);

		JLabel iniciar_1_2_2_1 = new JLabel("07%");
		iniciar_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_2_1.setBounds(75, 320, 255, 42);
		panelCentral.add(iniciar_1_2_2_1);

		JLabel iniciar_1_2_1_2 = new JLabel("Fecha:");
		iniciar_1_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_2.setBounds(403, 293, 255, 42);
		panelCentral.add(iniciar_1_2_1_2);

		JLabel iniciar_1_2_1_1_1 = new JLabel("15/05/2025");
		iniciar_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1_1.setBounds(403, 320, 255, 42);
		panelCentral.add(iniciar_1_2_1_1_1);

		JLabel iniciar_1_2_1_3 = new JLabel("Tipo:");
		iniciar_1_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_3.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_3.setBounds(687, 215, 101, 42);
		panelCentral.add(iniciar_1_2_1_3);

		JLabel iniciar_1_2_1_1_2 = new JLabel("Venta");
		iniciar_1_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		iniciar_1_2_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_2_1_1_2.setBounds(687, 246, 101, 42);
		panelCentral.add(iniciar_1_2_1_1_2);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_13() {
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

		JLabel iniciar = new JLabel("Confirmar operación");
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
				DetallesCompra();
				dispose();

			}
		});
		panelCentral.add(btnSi);

		setVisible(true);
	}

	public void DetallesCompra() {
		// Configuración básica de la ventana
		setTitle("Detalles de Compra");
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
				Compra();
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

				Confirma_14();

			}
		});
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(614, 406, 183, 33);
		panelCentral.add(btnConfirmar);

		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagen = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagen));
		logo.setBounds(32, 32, 184, 112); // posicion
		panelCentral.add(logo);

		JLabel lblContra = new JLabel("CONTRA");
		lblContra.setHorizontalAlignment(SwingConstants.LEFT);
		lblContra.setFont(new Font("Calibri", Font.BOLD, 24));
		lblContra.setBounds(32, 141, 135, 42);
		panelCentral.add(lblContra);

		JLabel iniciar_1_1_1_1_2_2_1 = new JLabel("$800.00 MXN");
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

		JLabel iniciar_1_1_2_2_1_1 = new JLabel("Fecha de compra:");
		iniciar_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_2_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
		iniciar_1_1_2_2_1_1.setBounds(426, 204, 147, 42);
		panelCentral.add(iniciar_1_1_2_2_1_1);

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
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Confirma_14() {
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

		JLabel iniciar = new JLabel("Confirmar operación");
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
				AdministradorCliente(); // Abre la segunda ventana
				dispose();
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
				AdministradorJuegos(); // Abre la segunda ventana
				dispose();
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
				AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
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
				NuevaOperacion(); // Abre la segunda ventana
				dispose();
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

		JButton registros = new JButton("DEVOLUCIONES PENDIENTES");
		registros.setBounds(34, 261, 206, 100);
		panelCentral.add(registros);
		registros.setForeground(Color.WHITE);
		registros.setFont(new Font("Calibri", Font.BOLD, 16));
		registros.setBackground(new Color(38, 60, 84));
		registros.addActionListener(e -> {
			DevolucionesPendientes(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnPromocionAutomatica = new JButton("PROMOCIÓN AUTOMATICA");
		btnPromocionAutomatica.setForeground(Color.WHITE);
		btnPromocionAutomatica.setFont(new Font("Calibri", Font.BOLD, 16));
		btnPromocionAutomatica.setBackground(new Color(38, 60, 84));
		btnPromocionAutomatica.setBounds(266, 261, 206, 100);
		btnPromocionAutomatica.addActionListener(e -> {
			PromocionAutomatica(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnPromocionAutomatica);

		JLabel lblDesdeAquPodrs = new JLabel(
				"Desde aquí podrás gestionar y controlar todas las \n funcionalidades de videojuegos. \n¿Qué deseas hacer hoy?\n");
		lblDesdeAquPodrs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeAquPodrs.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDesdeAquPodrs.setBounds(100, 168, 464, 60);
		panelCentral.add(lblDesdeAquPodrs);

		JButton btnDescuentoPorCliente = new JButton("DESCUENTO POR CLIENTE FRECUENTE");
		btnDescuentoPorCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DescuentoFrecuencia(); // Abre la segunda ventana
				dispose();
			}
		});
		btnDescuentoPorCliente.setForeground(Color.WHITE);
		btnDescuentoPorCliente.setFont(new Font("Calibri", Font.BOLD, 16));
		btnDescuentoPorCliente.setBackground(new Color(38, 60, 84));
		btnDescuentoPorCliente.setBounds(497, 261, 206, 100);
		panelCentral.add(btnDescuentoPorCliente);

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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			AdministradorRentaCompra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);
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
			AdministradorCliente(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			AdministradorRentaCompra(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			NuevaOperacion(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);
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
				EditarPromociones();
				dispose();

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
			PromocionAutomatica(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			Confirma_15(); // Abre la segunda ventana
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
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

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

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		btnBuscar.setBounds(619, 25, 86, 25);
		panelCentral.add(btnBuscar);

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
				EditarDescuento(); // Abre la segunda ventana
				dispose();
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
			DescuentoFrecuencia(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			// Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);
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

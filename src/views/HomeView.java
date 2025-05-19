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
import models.User;
import models.UsersModel;

public class HomeView extends JFrame {

	public HomeView() {
		// TODO Auto-generated constructor stub
	}

	//Panel Principal
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
			RegistroJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
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

	
	public void Confirma_6() {
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

		JLabel iniciar = new JLabel("Confirmar eliminicación");
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

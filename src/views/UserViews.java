package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import controller.UserController;
import controller.VideogamesController;
import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;

public class UserViews extends JFrame {

	public UserViews() {
		// TODO Auto-generated constructor stub
	}


	//	Avanzar desde Aqui y luego a Home View
	// Videojuegos


	
	//Renta y Compra
	
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
			dispose(); // Cierra la ventana actual
			HomeView administradorcliente= new HomeView(); 
			administradorcliente.AdministradorCliente();
			 // Abre la segunda ventana
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
			HomeView administradorjuegos= new HomeView(); 
			administradorjuegos.AdministradorJuegos();
			 // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorrentacompra= new HomeView(); 
			administradorrentacompra.AdministradorRentaCompra();
			// Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView nuevaoperacion= new HomeView(); 
			nuevaoperacion.NuevaOperacion();
			 // Abre la segunda ventana
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
				dispose();
				AuthViews operacionrentar= new AuthViews(); 
				operacionrentar.OperacionRentar();
				

			}
		});
		btnNewButton.setBounds(536, 419, 151, 30);
		panelCentral.add(btnNewButton);

//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//		btnBuscar.setBounds(619, 25, 86, 25);
//		panelCentral.add(btnBuscar);

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
			dispose(); // Cierra la ventana actual
			HomeView administradorcliente= new HomeView(); 
			administradorcliente.AdministradorCliente(); // Cierra la ventana actual
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
			HomeView administradorjuegos= new HomeView(); 
			administradorjuegos.AdministradorJuegos();// Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorrentacompra= new HomeView(); 
			administradorrentacompra.AdministradorRentaCompra(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView nuevaoperacion= new HomeView(); 
			nuevaoperacion.NuevaOperacion(); // Cierra la ventana actual
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

		JButton comprar = new JButton("Comprar");
		comprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AuthViews operacioncomprar= new AuthViews(); 
				operacioncomprar.OperacionComprar();
				

			}
		});
		comprar.setBounds(528, 439, 143, 25);
		panelCentral.add(comprar);

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
	
	//Nueva Operacion
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
			HomeView administradorcliente= new HomeView(); 
			administradorcliente.AdministradorCliente();
			 // Abre la segunda ventana
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
			HomeView administradorjuegos= new HomeView(); 
			administradorjuegos.AdministradorJuegos();
			 // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorrentacompra= new HomeView(); 
			administradorrentacompra.AdministradorRentaCompra();
			// Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView nuevacompra= new HomeView(); 
			nuevacompra.NuevaOperacion();
			 // Abre la segunda ventana
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
			dispose(); // Cierra la ventana actual
			HomeView administradorcliente = new HomeView(); 
			administradorcliente.AdministradorCliente();
			 // Abre la segunda ventana
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
			HomeView administradorjuegos = new HomeView(); 
			administradorjuegos.AdministradorJuegos();
			// Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView administradorrentacompra = new HomeView(); 
			administradorrentacompra.AdministradorRentaCompra();
			// Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			HomeView nuevaoperacion = new HomeView(); 
			nuevaoperacion.NuevaOperacion();
			 // Abre la segunda ventana
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
				dispose();
				HomeView editarpromocion = new HomeView(); 
				editarpromocion.EditarPromociones();
				

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
			dispose(); // Cierra la ventana actual
			HomeView administradorjuegos = new HomeView(); 
			administradorjuegos.AdministradorJuegos();
			 // Abre la segunda ventana
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
		iniciar.setSize(340, 60);
		iniciar.setLocation(26, 10);
		iniciar.setHorizontalAlignment(JLabel.LEFT);
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
				dispose();
				AuthViews editarpromociones = new AuthViews(); 
				editarpromociones.EditarPromociones();
				 // Abre la segunda ventana
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

	
}

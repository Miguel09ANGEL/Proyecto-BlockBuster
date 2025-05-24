package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import models.User;

public class PromotionsView extends JFrame{

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
				UserViews uv = new UserViews();
				uv.AdministradorCliente(); // Abre la segunda ventana
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
				VideogamesView vv = new VideogamesView();
				vv.AdministradorJuegos(); // Abre la segunda ventana
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
				TransactionView tv = new TransactionView();
				tv.AdministradorRentaCompra(); // Abre la segunda ventana
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
			UserViews uv = new UserViews();
			uv.AdministradorCliente(); // Abre la segunda ventana
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
			VideogamesView vv = new VideogamesView();
			vv.AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			TransactionView tv = new TransactionView();
			tv.AdministradorRentaCompra(); // Abre la segunda ventana
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
			UserViews uv = new UserViews();
			uv.AdministradorCliente(); // Abre la segunda ventana
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
			VideogamesView vv = new VideogamesView();
			vv.AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			TransactionView tv = new TransactionView();
			tv.AdministradorRentaCompra(); // Abre la segunda ventana
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
			UserViews uv = new UserViews();
			uv.AdministradorCliente(); // Abre la segunda ventana
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
			VideogamesView vv = new VideogamesView();
			vv.AdministradorJuegos(); // Abre la segunda ventana
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			TransactionView tv = new TransactionView();
			tv.AdministradorRentaCompra(); // Abre la segunda ventana
		});
		panelIzq.add(btnRentaYCompra);

		JButton nuevaoperacion = new JButton("NUEVA OPERACIÓN");
		nuevaoperacion.setForeground(Color.WHITE);
		nuevaoperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		nuevaoperacion.setBackground(new Color(38, 60, 84));
		nuevaoperacion.setBounds(10, 364, 237, 100);
		nuevaoperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			NuevaOperacion(); // Abre la segunda ventana
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

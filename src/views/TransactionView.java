package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
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
import javax.swing.UIManager;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.json.ParseException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controller.AuthController;
import controller.TransactionController;
import controller.VideogamesController;
import models.TransactionModel;
import models.User;
import models.VideoGames;
import views.UserViews.DateLabelFormatter;

public class TransactionView extends JFrame {

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
			UserViews uv = new UserViews();
			uv.AdministradorCliente(); // Abre la segunda ventana
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			VideogamesView vv = new VideogamesView();
			vv.AdministradorJuegos(); // Abre la segunda ventana
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
			PromotionsView pv = new PromotionsView();
			pv.NuevaOperacion(); // Abre la segunda ventana
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
			dispose(); // Cierra la ventana actual
			TransactionController tc = new TransactionController();
			tc.indexRenta(); // Abre la segunda ventana

		});

		JButton btnCompra = new JButton("VENTA");
		btnCompra.setForeground(Color.WHITE);
		btnCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnCompra.setBackground(new Color(38, 60, 84));
		btnCompra.setBounds(425, 242, 206, 100);
		btnCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			TransactionController tc = new TransactionController();
			tc.indexCompra();// Abre la segunda ventana
		});
		panelCentral.add(btnCompra);

		JLabel lblAquPuedesElegir = new JLabel(
				"<html><center>Desde aquí podrás gestionar y controlar todas las funcionalides de renta y venta. \r\n"
						+ "¿Qué deseas hacer hoy?\r\n" + "<html>");
		lblAquPuedesElegir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAquPuedesElegir.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAquPuedesElegir.setBounds(119, 154, 548, 60);
		panelCentral.add(lblAquPuedesElegir);

		ImageIcon icono = new ImageIcon(getClass().getResource("/images/Salida.png"));
		JButton salida = new JButton(icono);
		salida.setBounds(650, 390, 60, 60);
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

	public void Renta(List<VideoGames> videoGamesList) {
		// Configuración básica de la ventana
		setTitle("Renta de Videojuegos");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(1024, 576));
		setContentPane(layeredPane);

		// Panel izquierdo (menú)
		JPanel panelIzq = new JPanel();
		panelIzq.setLayout(null);
		panelIzq.setBackground(Color.decode("#FFFFFF"));
		panelIzq.setBounds(10, 62, 257, 475);
		layeredPane.add(panelIzq, JLayeredPane.PALETTE_LAYER);

		// Botones del menú lateral
		JButton btnClientes = new JButton("CLIENTES");
		btnClientes.setFont(new Font("Calibri", Font.BOLD, 16));
		btnClientes.setBackground(Color.decode("#263C54"));
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setBounds(10, 11, 237, 100);
		btnClientes.addActionListener(e -> {
			dispose();
			UserViews uv = new UserViews();
			uv.AdministradorCliente();
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(e -> {
			dispose();
			VideogamesView vv = new VideogamesView();
			vv.AdministradorJuegos();
		});
		panelIzq.add(btnVideojuegos);

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose();
			AdministradorRentaCompra();
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose();
			PromotionsView pv = new PromotionsView();
			pv.NuevaOperacion();
		});
		panelIzq.add(btnNuevaOperacion);

		// Panel central (contenido principal)
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Título
		JLabel lblTitulo = new JLabel("SELECCIONAR VIDEOJUEGO PARA RENTA");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setBounds(150, 11, 450, 42);
		panelCentral.add(lblTitulo);

		// Modelo de tabla con columnas
		String[] columnNames = { "ID", "Nombre", "Plataforma", "Existencias", "Precio Renta", "Clasificación" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer que la tabla no sea editable
			}
		};

		// Llenar la tabla con datos de videojuegos
		for (VideoGames juego : videoGamesList) {
			Object[] rowData = { juego.getId(), juego.getNombre(), juego.getPlataforma(),
					juego.getExistenciasDisponibles(), "$" + juego.getPrecioRenta(), juego.getClasificacion() };
			model.addRow(rowData);
		}

		// Crear la tabla con el modelo
		JTable table = new JTable(model);

		// Configurar apariencia de la tabla
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // Permitir ordenar por columnas

		// Ajustar anchos de columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(30); // ID
		table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
		table.getColumnModel().getColumn(2).setPreferredWidth(100); // Plataforma
		table.getColumnModel().getColumn(3).setPreferredWidth(80); // Existencias
		table.getColumnModel().getColumn(4).setPreferredWidth(80); // Precio Renta

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 62, 680, 350);
		panelCentral.add(scrollPane);

		// Botón para rentar
		JButton btnRentar = new JButton("RENTAR");
		btnRentar.setForeground(Color.WHITE);
		btnRentar.setBackground(Color.decode("#6D91B9"));
		btnRentar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRentar.setBounds(534, 420, 172, 30);
		btnRentar.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();

			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un videojuego", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			int juegoId = (int) model.getValueAt(selectedRow, 0);
			String nombreJuego = (String) model.getValueAt(selectedRow, 1);

			// Verificar existencias
			int existencias = (int) model.getValueAt(selectedRow, 3);
			if (existencias <= 0) {
				JOptionPane.showMessageDialog(layeredPane, "No hay existencias disponibles de " + nombreJuego, "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Obtener el videojuego seleccionado
			VideoGames juegoSeleccionado = null;
			for (VideoGames juego : videoGamesList) {
				if (juego.getId() == juegoId) {
					juegoSeleccionado = juego;
					break;
				}
			}

			if (juegoSeleccionado != null) {
				dispose();
				TransactionController tc = new TransactionController();
				tc.operacionRenta(juegoId);
			}
		});
		panelCentral.add(btnRentar);

		// Botón para cancelar/regresar
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(26, 420, 172, 30);
		btnCancelar.addActionListener(e -> {
			dispose();
			AdministradorRentaCompra();
		});
		panelCentral.add(btnCancelar);

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void Compra(List<VideoGames> videoGamesList) {
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
			UserViews uv = new UserViews();
			uv.AdministradorCliente(); // Cierra la ventana actual
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
			vv.AdministradorJuegos();// Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			AdministradorRentaCompra(); // Cierra la ventana actual
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			PromotionsView pv = new PromotionsView();
			pv.NuevaOperacion(); // Cierra la ventana actual
		});
		panelIzq.add(btnNuevaOperacion);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Título
		JLabel lblTitulo = new JLabel("SELECCIONAR VIDEOJUEGO PARA VENDER");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setBounds(150, 11, 450, 42);
		panelCentral.add(lblTitulo);

		// Modelo de tabla con columnas
		String[] columnNames = { "ID", "Nombre", "Plataforma", "Existencias", "Precio Venta", "Clasificación" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer que la tabla no sea editable
			}
		};

		// Llenar la tabla con datos de videojuegos
		for (VideoGames juego : videoGamesList) {
			Object[] rowData = { juego.getId(), juego.getNombre(), juego.getPlataforma(),
					juego.getExistenciasDisponibles(), "$" + juego.getPrecioVenta(), juego.getClasificacion() };
			model.addRow(rowData);
		}

		// Crear la tabla con el modelo
		JTable table = new JTable(model);

		// Configurar apariencia de la tabla
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // Permitir ordenar por columnas

		// Ajustar anchos de columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(30); // ID
		table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
		table.getColumnModel().getColumn(2).setPreferredWidth(100); // Plataforma
		table.getColumnModel().getColumn(3).setPreferredWidth(80); // Existencias
		table.getColumnModel().getColumn(4).setPreferredWidth(80); // Precio Venta

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 62, 680, 350);
		panelCentral.add(scrollPane);

		JButton btnVender = new JButton("VENDER");
		btnVender.setForeground(Color.WHITE);
		btnVender.setBackground(Color.decode("#6D91B9"));
		btnVender.setFont(new Font("Arial", Font.BOLD, 14));
		btnVender.setBounds(534, 420, 172, 30);
		btnVender.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();

			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un videojuego", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			int juegoId = (int) model.getValueAt(selectedRow, 0);
			String nombreJuego = (String) model.getValueAt(selectedRow, 1);

			// Verificar existencias
			int existencias = (int) model.getValueAt(selectedRow, 3);
			if (existencias <= 0) {
				JOptionPane.showMessageDialog(layeredPane, "No hay existencias disponibles de " + nombreJuego, "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Obtener el videojuego seleccionado
			VideoGames juegoSeleccionado = null;
			for (VideoGames juego : videoGamesList) {
				if (juego.getId() == juegoId) {
					juegoSeleccionado = juego;
					break;
				}
			}

			if (juegoSeleccionado != null) {
				dispose();
				TransactionController tc = new TransactionController();
				tc.operacionVender(juegoId);
			}
		});
		panelCentral.add(btnVender);

		// Botón para cancelar/regresar
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(26, 420, 172, 30);
		btnCancelar.addActionListener(e -> {
			dispose();
			AdministradorRentaCompra();
		});
		panelCentral.add(btnCancelar);

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

	public void OperacionRentar(VideoGames videogames, List<User> users) {
		// Define estos JTextFields como atributos de clase (arriba, fuera de métodos)
		JTextField txtNombreCliente;
		JTextField txtNombreVideojuego;
		JTextField txtCantidad;

		// Configuración básica de la ventana
		setTitle("Detalles de operación Rentar");
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

		// Campos de entrada
		JLabel lblNombreCliente = new JLabel("Nombre del cliente");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreCliente.setBounds(450, 130, 160, 42);
		panelCentral.add(lblNombreCliente);

		// esta parte se encarga de mostrar los usuarios y los correos
		// Crear un arreglo con los nombres y correos de los usuarios
		String[] listaUsuarios = new String[users.size()];

		for (int i = 0; i < users.size(); i++) {
			User usuario = users.get(i);
			listaUsuarios[i] = usuario.getId() + " - " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " "
					+ usuario.getApellidoMaterno() + " (" + usuario.getCorreo() + ")";
		}

		// Crear y configurar el ComboBox
		JComboBox<String> comboUsuarios = new JComboBox<>(listaUsuarios);
		comboUsuarios.setBounds(290, 161, 510, 27);
		comboUsuarios.setBackground(Color.decode("#D9D9D9"));
		panelCentral.add(comboUsuarios);

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose();

			TransactionController tc = new TransactionController();
			tc.updateVideogames(videogames.getId());

		});
		panelCentral.add(btnCancelar);

		// Detalles de la compra
		JLabel lblTituloJuego = new JLabel("Nombre de videojuego");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTituloJuego.setBounds(75, 215, 255, 42);
		panelCentral.add(lblTituloJuego);

		JLabel lblNombre = new JLabel(videogames.getNombre());
		lblNombre.setFont(new Font("Anton", Font.BOLD, 14));
		lblNombre.setBounds(130, 246, 355, 42);
		panelCentral.add(lblNombre);

		JLabel lblPrecio = new JLabel("Precio (MXN):");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecio.setBounds(403, 215, 255, 42);
		panelCentral.add(lblPrecio);

		JLabel lblValorRenta = new JLabel("" + videogames.getPrecioRenta());
		lblValorRenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorRenta.setBounds(503, 246, 255, 42);
		panelCentral.add(lblValorRenta);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblFecha.setBounds(403, 293, 255, 42);
		panelCentral.add(lblFecha);

		// en esta tambien pero de diferente forma
		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorFecha.setBounds(403, 320, 255, 42);
		panelCentral.add(lblValorFecha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTipo.setBounds(687, 215, 101, 42);
		panelCentral.add(lblTipo);

		JLabel lblValorTipo = new JLabel("Renta");
		lblValorTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorTipo.setBounds(687, 246, 101, 42);
		panelCentral.add(lblValorTipo);

		JLabel devolucion = new JLabel("Fecha de devolución:");
		devolucion.setHorizontalAlignment(SwingConstants.CENTER);
		devolucion.setFont(new Font("Calibri", Font.BOLD, 14));
		devolucion.setBounds(637, 293, 255, 42);
		panelCentral.add(devolucion);

		UtilDateModel model = new UtilDateModel();

		// en esta parte se hace la fecha actual en le calendario desplegable
		model.setDate(fechaActual.getYear(), fechaActual.getMonthValue() - 1, fechaActual.getDayOfMonth());
		model.setSelected(true);

		// Crear el date picker con el formateador
		JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(model, new Properties()),
				new DateLabelFormatter());

		// Configurar el campo de texto
		JFormattedTextField textField = datePicker.getJFormattedTextField();
		textField.setBackground(Color.decode("#D9D9D9"));
		textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textField.setEditable(false);

		datePicker.setBounds(697, 325, 165, 27);
		panelCentral.add(datePicker);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			try {
				// 1. Obtener usuario seleccionado
				String seleccion = (String) comboUsuarios.getSelectedItem();
				int customerId = Integer.parseInt(seleccion.split(" - ")[0]);

				// 2. Obtener fecha de devolución
				Date fechaDevolucion = new Date(((java.util.Date) datePicker.getModel().getValue()).getTime());

				// 3. Crear la transacción en la base de datos
				TransactionModel transModel = new TransactionModel();
				boolean exito = transModel.createTransaction(customerId, videogames.getId(), "rental", fechaDevolucion,
						videogames.getPrecioRenta(), "rented");

				if (exito) {
					JOptionPane.showMessageDialog(this, "¡Renta registrada con éxito!");
					dispose();
					new TransactionController().detallesRenta(customerId, videogames.getId(), fechaDevolucion);
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar la renta", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
		panelCentral.add(btnConfirmar);

		setVisible(true);
	}

	public void DetallesRenta(User user, VideoGames videogames, Date fechaDevolucion) {
		// Configuración básica de la ventana
		setTitle("Detalles VideoJuego Rentado");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#D9D9D9"));
		panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);

		// Título principal
		JLabel lblTitulo = new JLabel("DETALLES DE VIDEOJUEGO");
		lblTitulo.setSize(285, 42);
		lblTitulo.setLocation(442, 32);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// Información del cliente
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCliente.setBounds(256, 85, 70, 42);
		panelCentral.add(lblCliente);

		JLabel lblNombreCliente = new JLabel(user.getNombre()+" "+user.getApellidoPaterno());
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreCliente.setBounds(226, 102, 112, 42);
		panelCentral.add(lblNombreCliente);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCorreo.setBounds(503, 85, 70, 42);
		panelCentral.add(lblCorreo);

		JLabel lblEmailCliente = new JLabel(user.getCorreo());
		lblEmailCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEmailCliente.setBounds(436, 102, 200, 42);
		panelCentral.add(lblEmailCliente);

		// Botones
		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TransactionController tc = new TransactionController();
				tc.indexRenta();

			}
		});
		btnRegresar.setBackground(Color.decode("#B82F2F"));
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnRegresar);

		// Logo del juego
		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagen = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel lblLogoJuego = new JLabel(new ImageIcon(imagen));
		lblLogoJuego.setBounds(32, 32, 184, 112);
		panelCentral.add(lblLogoJuego);

		JLabel lblTituloJuego = new JLabel(videogames.getNombre());
		lblTituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTituloJuego.setBounds(32, 141, 400, 42);
		panelCentral.add(lblTituloJuego);

		// Información del juego
		JLabel lblAnioJuego = new JLabel("" + videogames.getAñoLanzamiento());
		lblAnioJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnioJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblAnioJuego.setBounds(42, 174, 57, 42);
		panelCentral.add(lblAnioJuego);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacion.setBounds(188, 204, 112, 42);
		panelCentral.add(lblClasificacion);

		JLabel lblValorClasificacion = new JLabel(videogames.getClasificacion());
		lblValorClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorClasificacion.setBounds(185, 232, 97, 42);
		panelCentral.add(lblValorClasificacion);

		JLabel lblDistribuidor = new JLabel("Distribuidores:");
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDistribuidor.setBounds(188, 285, 112, 42);
		panelCentral.add(lblDistribuidor);

		JLabel lblNombreDistribuidor = new JLabel(videogames.getDesarrolladoPor());
		lblNombreDistribuidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreDistribuidor.setBounds(138, 309, 200, 42);
		panelCentral.add(lblNombreDistribuidor);

		// Fechas de renta
		JLabel lblFechaRenta = new JLabel("Fecha de renta:");
		lblFechaRenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaRenta.setBounds(426, 204, 126, 42);
		panelCentral.add(lblFechaRenta);

		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFecha.setBounds(436, 232, 97, 42);
		panelCentral.add(lblValorFecha);

		JLabel lblFechaLimite = new JLabel("Límite de devolución");
		lblFechaLimite.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaLimite.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaLimite.setBounds(407, 285, 157, 42);
		panelCentral.add(lblFechaLimite);
		
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		JLabel lblValorFechaLimite = new JLabel();
	    lblValorFechaLimite.setText(sdf.format(fechaDevolucion));
		lblValorFechaLimite.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFechaLimite.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFechaLimite.setBounds(436, 309, 97, 42);
		panelCentral.add(lblValorFechaLimite);

		// Información de pago
		JLabel lblInfoPago = new JLabel("Información de pago");
		lblInfoPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInfoPago.setBounds(700, 141, 167, 42);
		panelCentral.add(lblInfoPago);

		JLabel lblTipoPago = new JLabel("Tipo:");
		lblTipoPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTipoPago.setBounds(772, 85, 70, 42);
		panelCentral.add(lblTipoPago);

		JLabel lblValorTipoPago = new JLabel("Renta");
		lblValorTipoPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTipoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorTipoPago.setBounds(755, 102, 112, 42);
		panelCentral.add(lblValorTipoPago);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCantidad.setBounds(700, 174, 100, 42);
		panelCentral.add(lblCantidad);

		JLabel lblValorCantidad = new JLabel("1");
		lblValorCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorCantidad.setBounds(775, 174, 57, 42);
		panelCentral.add(lblValorCantidad);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setFont(new Font("Calibri", Font.BOLD, 14));
		lblProducto.setBounds(700, 215, 70, 42);
		panelCentral.add(lblProducto);

		JLabel lblPrecioProducto = new JLabel("" + videogames.getPrecioRenta());
		lblPrecioProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioProducto.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecioProducto.setBounds(760, 215, 85, 42);
		panelCentral.add(lblPrecioProducto);

		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubtotal.setFont(new Font("Calibri", Font.BOLD, 14));
		lblSubtotal.setBounds(700, 248, 57, 42);
		panelCentral.add(lblSubtotal);

		JLabel lblValorSubtotal = new JLabel(" "+videogames.getPrecioRenta());
		lblValorSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorSubtotal.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorSubtotal.setBounds(760, 248, 78, 42);
		panelCentral.add(lblValorSubtotal);

		// Total
		JTextArea txtTotal = new JTextArea("Total(IVA incluído, en caso de ser aplicable):");
		txtTotal.setWrapStyleWord(true);
		txtTotal.setOpaque(false);
		txtTotal.setLineWrap(true);
		txtTotal.setFont(new Font("Calibri", Font.BOLD, 20));
		txtTotal.setEditable(false);
		txtTotal.setBounds(628, 341, 298, 54);
		panelCentral.add(txtTotal);

		JLabel lblTotal = new JLabel(" "+videogames.getPrecioRenta());
		lblTotal.setForeground(new Color(153, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTotal.setBounds(719, 359, 126, 42);
		panelCentral.add(lblTotal);
		
		JButton btnDescargarPDF = new JButton("Descargar (PDF)");
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBounds(614, 406, 183, 33);
		btnDescargarPDF.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Guardar archivo PDF");
		        fileChooser.setSelectedFile(new File("Detalles_Renta_" + videogames.getNombre() + ".pdf"));

		        int userSelection = fileChooser.showSaveDialog(null);

		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToSave = fileChooser.getSelectedFile();
		            Document document = new Document();

		            try {
		                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
		                document.open();

		                // Encabezado
		                Paragraph titulo = new Paragraph("COMPROBANTE DE RENTA\n\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
		                titulo.setAlignment(Element.ALIGN_CENTER);
		                document.add(titulo);

		                // Información del cliente
		                Paragraph clienteHeader = new Paragraph("INFORMACIÓN DEL CLIENTE\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(clienteHeader);
		                
		                document.add(new Paragraph("Nombre: " + user.getNombre() + " " + 
		                    user.getApellidoPaterno() + " " + user.getApellidoMaterno()));
		                document.add(new Paragraph("Correo: " + user.getCorreo()));
		                document.add(Chunk.NEWLINE);

		                // Información del videojuego
		                Paragraph juegoHeader = new Paragraph("INFORMACIÓN DEL VIDEOJUEGO\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(juegoHeader);
		                
		                document.add(new Paragraph("Título: " + videogames.getNombre()));
		                document.add(new Paragraph("Plataforma: " + videogames.getPlataforma()));
		                document.add(new Paragraph("Año: " + videogames.getAñoLanzamiento()));
		                document.add(new Paragraph("Clasificación: " + videogames.getClasificacion()));
		                document.add(new Paragraph("Desarrollador: " + videogames.getDesarrolladoPor()));
		                document.add(Chunk.NEWLINE);

		                // Detalles de la renta
		                Paragraph rentaHeader = new Paragraph("DETALLES DE LA RENTA\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(rentaHeader);
		                
//		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		                document.add(new Paragraph("Fecha de renta: " + fechaFormateada));
		                document.add(new Paragraph("Fecha de devolución: " + fechaDevolucion));
		                
		                // Conversión segura y cálculo de días
		                LocalDate fechaDevolucionLocal = new java.sql.Date(fechaDevolucion.getTime()).toLocalDate();
		                LocalDate fechaActualLocal = LocalDate.now();
		                long diasRenta = ChronoUnit.DAYS.between(fechaActualLocal, fechaDevolucionLocal);
		                
		                document.add(new Paragraph("Días de renta: " + diasRenta));
		                document.add(Chunk.NEWLINE);

		                // Información de pago
		                Paragraph pagoHeader = new Paragraph("INFORMACIÓN DE PAGO\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(pagoHeader);
		                
		                document.add(new Paragraph("Precio por día: $" + videogames.getPrecioRenta()));
		                document.add(new Paragraph("Días rentados: " + diasRenta));
		                
		                BigDecimal subtotal = videogames.getPrecioRenta();
		                BigDecimal iva = subtotal ;
		                BigDecimal total = subtotal;
		                
		                document.add(new Paragraph("Subtotal: $" + String.format("%.2f", subtotal)));
		                document.add(new Paragraph("IVA (16%): $" + String.format("%.2f", iva)));
		                
		                Paragraph totalParagraph = new Paragraph("TOTAL: $" + String.format("%.2f", total), 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
		                document.add(totalParagraph);
		                
		                // es el pie de pagina
		                Paragraph footer = new Paragraph(
		                        "Gracias por su preferencia\n" +
		                        "Videojuegos Rentables S.A. de C.V.\n" +
		                        "Tel: 555-123-4567 | contacto@rentables.com",
		                        FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10));
		                    footer.setAlignment(Element.ALIGN_CENTER);
		                    document.add(footer);

		                JOptionPane.showMessageDialog(null, 
		                    "PDF generado exitosamente", 
		                    "Éxito", 
		                    JOptionPane.INFORMATION_MESSAGE);
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, 
		                    "Error al generar PDF: " + ex.getMessage(), 
		                    "Error", 
		                    JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            } finally {
		                document.close();
		            }
		        }
		    }
		});
		panelCentral.add(btnDescargarPDF);


		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}
	
	public void OperacionComprar(VideoGames videogames, List<User> users) {
		// Configuración básica de la ventana
		setTitle("Detalles de operación Comprar");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// Panel principal
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(5, 62, 998, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagenEscalada));
		lblLogo.setBounds(477, 11, 70, 70);
		panelCentral.add(lblLogo);

		// Título principal
		JLabel lblTitulo = new JLabel("DETALLES DE OPERACIÓN");
		lblTitulo.setSize(273, 42);
		lblTitulo.setLocation(369, 78);
		lblTitulo.setHorizontalAlignment(JLabel.LEFT);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// Campos de entrada
		JLabel lblNombreCliente = new JLabel("Nombre del cliente");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreCliente.setBounds(450, 130, 160, 42);
		panelCentral.add(lblNombreCliente);

		// esta parte se encarga de mostrar los usuarios y los correos
		// Crear un arreglo con los nombres y correos de los usuarios
		String[] listaUsuarios = new String[users.size()];

		for (int i = 0; i < users.size(); i++) {
			User usuario = users.get(i);
			listaUsuarios[i] = usuario.getId() + " - " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " "
					+ usuario.getApellidoMaterno() + " (" + usuario.getCorreo() + ")";
		}

		// Crear y configurar el ComboBox
		JComboBox<String> comboUsuarios = new JComboBox<>(listaUsuarios);
		comboUsuarios.setBounds(290, 161, 510, 27);
		comboUsuarios.setBackground(Color.decode("#D9D9D9"));
		panelCentral.add(comboUsuarios);

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose();
			TransactionController tc = new TransactionController();
			tc.indexCompra();

		});
		panelCentral.add(btnCancelar);


		// Detalles de la compra
		JLabel lblTituloJuego = new JLabel("Nombre de videojuego");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTituloJuego.setBounds(75, 215, 255, 42);
		panelCentral.add(lblTituloJuego);

		JLabel lblNombre = new JLabel(videogames.getNombre());
		lblNombre.setFont(new Font("Anton", Font.BOLD, 14));
		lblNombre.setBounds(150, 246, 255, 42);
		panelCentral.add(lblNombre);

		JLabel lblPrecio = new JLabel("Precio (MXN):");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecio.setBounds(403, 215, 255, 42);
		panelCentral.add(lblPrecio);

		JLabel lblValorVenta = new JLabel("" + videogames.getPrecioVenta());
		lblValorVenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorVenta.setBounds(503, 246, 255, 42);
		panelCentral.add(lblValorVenta);

		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescuento.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDescuento.setBounds(75, 293, 255, 42);
		panelCentral.add(lblDescuento);

		JLabel lblValorDescuento = new JLabel("07%");
		lblValorDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorDescuento.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorDescuento.setBounds(75, 320, 255, 42);
		panelCentral.add(lblValorDescuento);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblFecha.setBounds(403, 293, 255, 42);
		panelCentral.add(lblFecha);

		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorFecha.setBounds(403, 320, 255, 42);
		panelCentral.add(lblValorFecha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTipo.setBounds(687, 215, 101, 42);
		panelCentral.add(lblTipo);

		JLabel lblVenta = new JLabel("Venta");
		lblVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblVenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblVenta.setBounds(687, 246, 101, 42);
		panelCentral.add(lblVenta);

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		UtilDateModel model = new UtilDateModel();
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			try {
				// 1. Obtener usuario seleccionado
				String seleccion = (String) comboUsuarios.getSelectedItem();
				int customerId = Integer.parseInt(seleccion.split(" - ")[0]);

				// 3. Crear la transacción en la base de datos
				TransactionModel transModel = new TransactionModel();
				boolean exito = transModel.createTransaction(customerId, videogames.getId(), "sale",
						null, videogames.getPrecioVenta(), "completed");

				if (exito) {
					JOptionPane.showMessageDialog(this, "¡Venta registrada con éxito!");
					dispose();
					new TransactionController().indexDetallesCompra(customerId, videogames.getId());
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar la renta", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
		panelCentral.add(btnConfirmar);

		setVisible(true);
	}

	public void DetallesCompra(User user, VideoGames videogames) {

		// Configuración básica de la ventana
		setTitle("Detalles de Compra");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// Panel principal
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#D9D9D9"));
		panelCentral.setBounds(5, 62, 998, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logo del juego
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);

		// Título principal
		JLabel lblTitulo = new JLabel("DETALLES DE VIDEOJUEGO");
		lblTitulo.setSize(285, 42);
		lblTitulo.setLocation(442, 32);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// Información del cliente
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCliente.setBounds(256, 85, 70, 42);
		panelCentral.add(lblCliente);

		JLabel lblNombreCliente = new JLabel(user.getNombre()+" "+user.getApellidoPaterno());
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreCliente.setBounds(226, 102, 112, 42);
		panelCentral.add(lblNombreCliente);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCorreo.setBounds(503, 85, 70, 42);
		panelCentral.add(lblCorreo);

		JLabel lblEmailCliente = new JLabel(user.getCorreo());
		lblEmailCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEmailCliente.setBounds(436, 102, 200, 42);
		panelCentral.add(lblEmailCliente);

		// Botones
		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(e -> {
			dispose();
			TransactionController tc = new TransactionController();
			tc.indexCompra();

		});
		btnRegresar.setBackground(Color.decode("#B82F2F"));
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnRegresar);

		// Imagen del juego
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagenLogo = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel lblLogoJuego = new JLabel(new ImageIcon(imagenLogo));
		lblLogoJuego.setBounds(32, 32, 184, 112);
		panelCentral.add(lblLogoJuego);

		JLabel lblTituloJuego = new JLabel(videogames.getNombre());
		lblTituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTituloJuego.setBounds(32, 141, 400, 42);
		panelCentral.add(lblTituloJuego);

		// Detalles del juego
		JLabel lblAnioJuego = new JLabel("" + videogames.getAñoLanzamiento());
		lblAnioJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnioJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblAnioJuego.setBounds(42, 174, 57, 42);
		panelCentral.add(lblAnioJuego);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacion.setBounds(188, 204, 112, 42);
		panelCentral.add(lblClasificacion);

		JLabel lblValorClasificacion = new JLabel(videogames.getClasificacion());
		lblValorClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorClasificacion.setBounds(185, 232, 97, 42);
		panelCentral.add(lblValorClasificacion);

		JLabel lblDistribuidor = new JLabel("Distribuidores:");
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDistribuidor.setBounds(188, 285, 112, 42);
		panelCentral.add(lblDistribuidor);

		JLabel lblNombreDistribuidor = new JLabel(videogames.getDesarrolladoPor());
		lblNombreDistribuidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreDistribuidor.setBounds(138, 309, 200, 42);
		panelCentral.add(lblNombreDistribuidor);

		// Fechas
		JLabel lblFechaCompra = new JLabel("Fecha de compra:");
		lblFechaCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaCompra.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaCompra.setBounds(426, 204, 147, 42);
		panelCentral.add(lblFechaCompra);

		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFecha.setBounds(436, 232, 97, 42);
		panelCentral.add(lblValorFecha);

		// Información de pago
		JLabel lblInfoPago = new JLabel("Información de pago");
		lblInfoPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInfoPago.setBounds(700, 141, 167, 42);
		panelCentral.add(lblInfoPago);

		JLabel lblTipoPago = new JLabel("Tipo:");
		lblTipoPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTipoPago.setBounds(772, 85, 70, 42);
		panelCentral.add(lblTipoPago);

		JLabel lblValorTipoPago = new JLabel("Venta");
		lblValorTipoPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTipoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorTipoPago.setBounds(755, 102, 112, 42);
		panelCentral.add(lblValorTipoPago);

		// Detalles de pago
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCantidad.setBounds(700, 174, 57, 42);
		panelCentral.add(lblCantidad);

		JLabel lblValorCantidad = new JLabel("1");
		lblValorCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorCantidad.setBounds(765, 174, 57, 42);
		panelCentral.add(lblValorCantidad);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setFont(new Font("Calibri", Font.BOLD, 14));
		lblProducto.setBounds(700, 205, 70, 42);
		panelCentral.add(lblProducto);

		JLabel lblPrecioProducto = new JLabel("" + videogames.getPrecioVenta());
		lblPrecioProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioProducto.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecioProducto.setBounds(760, 204, 85, 42);
		panelCentral.add(lblPrecioProducto);

		JLabel lblIVA = new JLabel("IVA:");
		lblIVA.setHorizontalAlignment(SwingConstants.LEFT);
		lblIVA.setFont(new Font("Calibri", Font.BOLD, 14));
		lblIVA.setBounds(700, 232, 57, 42);
		panelCentral.add(lblIVA);

		JLabel lblValorIVA = new JLabel("$8");
		lblValorIVA.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorIVA.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorIVA.setBounds(728, 232, 57, 42);
		panelCentral.add(lblValorIVA);

		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubtotal.setFont(new Font("Calibri", Font.BOLD, 14));
		lblSubtotal.setBounds(700, 258, 57, 42);
		panelCentral.add(lblSubtotal);

		JLabel lblValorSubtotal = new JLabel("$108.00 MXN");
		lblValorSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorSubtotal.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorSubtotal.setBounds(760, 258, 78, 42);
		panelCentral.add(lblValorSubtotal);

		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescuento.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDescuento.setBounds(700, 285, 70, 42);
		panelCentral.add(lblDescuento);

		JLabel lblValorDescuento = new JLabel("7%");
		lblValorDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorDescuento.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorDescuento.setBounds(770, 285, 57, 42);
		panelCentral.add(lblValorDescuento);

		// Total
		JTextArea txtTotal = new JTextArea("Total(IVA incluído, en caso de ser aplicable):");
		txtTotal.setWrapStyleWord(true);
		txtTotal.setOpaque(false);
		txtTotal.setLineWrap(true);
		txtTotal.setFont(new Font("Calibri", Font.BOLD, 20));
		txtTotal.setEditable(false);
		txtTotal.setBounds(628, 341, 298, 54);
		panelCentral.add(txtTotal);

		JLabel lblTotal = new JLabel("$800.00 MXN");
		lblTotal.setForeground(new Color(153, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTotal.setBounds(719, 359, 126, 42);
		panelCentral.add(lblTotal);
		
		JButton btnDescargarPDF = new JButton("Descargar (PDF)");
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBounds(614, 406, 183, 33);
		btnDescargarPDF.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar archivo PDF");
	        fileChooser.setSelectedFile(new File("Detalles_Venta_" + videogames.getNombre() + ".pdf"));

	        int userSelection = fileChooser.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            Document document = new Document();

	            try {
	                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
	                document.open();

	                // Encabezado
	                Paragraph titulo = new Paragraph("COMPROBANTE DE VENTA\n\n", 
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
	                titulo.setAlignment(Element.ALIGN_CENTER);
	                document.add(titulo);

	                // Información del cliente
	                Paragraph clienteHeader = new Paragraph("INFORMACIÓN DEL CLIENTE\n", 
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
	                document.add(clienteHeader);
	                
	                document.add(new Paragraph("Nombre: " + user.getNombre() + " " + 
	                    user.getApellidoPaterno() + " " + user.getApellidoMaterno()));
	                document.add(new Paragraph("Correo: " + user.getCorreo()));
	                document.add(Chunk.NEWLINE);

	                // Información del videojuego
	                Paragraph juegoHeader = new Paragraph("INFORMACIÓN DEL VIDEOJUEGO\n", 
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
	                document.add(juegoHeader);
	                
	                document.add(new Paragraph("Título: " + videogames.getNombre()));
	                document.add(new Paragraph("Plataforma: " + videogames.getPlataforma()));
	                document.add(new Paragraph("Año: " + videogames.getAñoLanzamiento()));
	                document.add(new Paragraph("Clasificación: " + videogames.getClasificacion()));
	                document.add(new Paragraph("Desarrollador: " + videogames.getDesarrolladoPor()));
	                document.add(Chunk.NEWLINE);

	                // Detalles de la renta
	                Paragraph rentaHeader = new Paragraph("DETALLES DE LA VENTA\n", 
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
	                document.add(rentaHeader);
	                
	                document.add(new Paragraph("Fecha de venta: " + fechaFormateada));
	                
	                document.add(Chunk.NEWLINE);

	                // Información de pago
	                Paragraph pagoHeader = new Paragraph("INFORMACIÓN DE PAGO\n", 
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
	                document.add(pagoHeader);
	                
	                document.add(new Paragraph("Precio por día: $" + videogames.getPrecioVenta()));
	                
	                BigDecimal subtotal = videogames.getPrecioVenta();
	                BigDecimal iva = subtotal ;
	                BigDecimal total = subtotal;
	                
	                document.add(new Paragraph("Subtotal: $" + String.format("%.2f", subtotal)));
	                document.add(new Paragraph("IVA (16%): $" + String.format("%.2f", iva)));
	                
	                Paragraph totalParagraph = new Paragraph("TOTAL: $" + String.format("%.2f", total), 
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
	                document.add(totalParagraph);
	                document.add(Chunk.NEWLINE);
	                
	                // es el pie de pagina
	                Paragraph footer = new Paragraph(
	                        "Gracias por su preferencia\n" +
	                        "Videojuegos Rentables S.A. de C.V.\n" +
	                        "Tel: 555-123-4567 | contacto@rentables.com",
	                        FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10));
	                    footer.setAlignment(Element.ALIGN_CENTER);
	                    document.add(footer);

	                JOptionPane.showMessageDialog(null, 
	                    "PDF generado exitosamente", 
	                    "Éxito", 
	                    JOptionPane.INFORMATION_MESSAGE);
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, 
	                    "Error al generar PDF: " + ex.getMessage(), 
	                    "Error", 
	                    JOptionPane.ERROR_MESSAGE);
	                ex.printStackTrace();
	            } finally {
	                document.close();
	            }
	        }
	    
		});
		panelCentral.add(btnDescargarPDF);

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

	}

	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException, java.text.ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}
}

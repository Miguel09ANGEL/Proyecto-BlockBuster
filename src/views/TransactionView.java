package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.json.ParseException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import controller.AuthController;
import controller.TransactionController;
import controller.UserController;
import controller.VideogamesController;
import models.TransactionModel;
import models.User;
import models.UsersModel;
import models.VideoGames;
import utils.GifLoading;
import utils.LoadingFrame;

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
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

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
			Runnable tarea = () -> {
				UserViews uv = new UserViews();
				uv.AdministradorCliente(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(e -> {
			// se crea el objeto runnable que es la tarea que tiene que recibir
			Runnable tarea = () -> {
				VideogamesView vv = new VideogamesView();
				vv.AdministradorJuegos(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
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
			Runnable tarea = () -> {
				PromotionsView pv = new PromotionsView();
				pv.NuevaOperacion(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();

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
			Runnable tarea = () -> {
				TransactionController tc = new TransactionController();
				tc.rentalIndex(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});

		JButton btnCompra = new JButton("VENTA");
		btnCompra.setForeground(Color.WHITE);
		btnCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnCompra.setBackground(new Color(38, 60, 84));
		btnCompra.setBounds(425, 242, 206, 100);
		btnCompra.addActionListener(e -> {
			Runnable tarea = () -> {
				TransactionController tc = new TransactionController();
				tc.salesIndex();// Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();

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
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

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
			Runnable tarea = () -> {
				UserViews uv = new UserViews();
				uv.AdministradorCliente(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		btnVideojuegos.addActionListener(e -> {
			Runnable tarea = () -> {
				VideogamesView vv = new VideogamesView();
				vv.AdministradorJuegos(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelIzq.add(btnVideojuegos);

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			Runnable tarea = () -> {
				TransactionView tv = new TransactionView();
				tv.AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			Runnable tarea = () -> {
				PromotionsView pv = new PromotionsView();
				pv.NuevaOperacion(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();

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
		lblTitulo.setBounds(150, 11, 500, 42);
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
			Object[] rowData = { juego.getId(), juego.getname(), juego.getplatform(),
					juego.getavailableStock(), "$" + juego.getrentPrice(), juego.getclassification() };
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
		scrollPane.setBounds(26, 80, 680, 330);
		panelCentral.add(scrollPane);

		// Buscador icon
		ImageIcon Buscadorpng = new ImageIcon(getClass().getResource("/images/Buscador.png"));
		Image imagenEscalada = Buscadorpng.getImage().getScaledInstance(30, 29, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(455, 49, 30, 29); // posicion
		panelCentral.add(logo);

		// Cuadro de buscador
		JTextField Buscador = new JTextField("");
		Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		Buscador.setBounds(485, 50, 220, 25);
		Buscador.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased (KeyEvent e) {
				String textoBusqueda = Buscador.getText().trim().toLowerCase();
				model.setRowCount(0);

				for (VideoGames juegos : videoGamesList) {
					String nombreVideojuego = juegos.getname().toLowerCase();
					String plataforma = juegos.getplatform().toLowerCase();
		            String id = String.valueOf(juegos.getId()); 

					if (nombreVideojuego.contains(textoBusqueda) || 
							plataforma.contains(textoBusqueda) ||
							id.contains(textoBusqueda)) {
						
						Object[] rowData = { juegos.getId(), 
								juegos.getname(), 
								juegos.getplatform(),
								juegos.getavailableStock(), 
								"$" + juegos.getsalePrice(),
								juegos.getclassification() };
						model.addRow(rowData);
					}
				}

			}

		});
		panelCentral.add(Buscador);

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
				Runnable tarea = () -> {
					TransactionController tc = new TransactionController();
					tc.selectCustomerRent(juegoId);
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
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
			Runnable tarea = () -> {
				TransactionView tv = new TransactionView();
				tv.AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();

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
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

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
			Runnable tarea = () -> {
				UserViews uv = new UserViews();
				uv.AdministradorCliente(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			Runnable tarea = () -> {
				VideogamesView vv = new VideogamesView();
				vv.AdministradorJuegos(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			Runnable tarea = () -> {
				TransactionView tv = new TransactionView();
				tv.AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			Runnable tarea = () -> {
				PromotionsView pv = new PromotionsView();
				pv.NuevaOperacion(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
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
			Object[] rowData = { juego.getId(), juego.getname(), juego.getplatform(),
					juego.getavailableStock(), "$" + juego.getsalePrice(), juego.getclassification() };
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
		scrollPane.setBounds(26, 80, 680, 330);
		panelCentral.add(scrollPane);

		// Buscador icon
		ImageIcon Buscadorpng = new ImageIcon(getClass().getResource("/images/Buscador.png"));
		Image imagenEscalada = Buscadorpng.getImage().getScaledInstance(30, 29, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(455, 49, 30, 29); // posicion
		panelCentral.add(logo);

		// Cuadro de buscador
		JTextField Buscador = new JTextField("");
		Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		Buscador.setBounds(485, 50, 220, 25);
		Buscador.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String textoBusqueda = Buscador.getText().trim().toLowerCase();
				model.setRowCount(0);

				for (VideoGames juegos : videoGamesList) {
					String nombreVideojuego = juegos.getname().toLowerCase();
					String plataforma = juegos.getplatform().toLowerCase();
					String id = String.valueOf(juegos.getId());

					if (nombreVideojuego.contains(textoBusqueda) || plataforma.contains(textoBusqueda)
							|| id.contains(textoBusqueda)) {

						Object[] rowData = { juegos.getId(), juegos.getname(), juegos.getplatform(),
								juegos.getavailableStock(), "$" + juegos.getsalePrice(),
								juegos.getclassification() };
						model.addRow(rowData);
					}
				}

			}

		});
		panelCentral.add(Buscador);

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
				Runnable tarea = () -> {
					TransactionController tc = new TransactionController();
					tc.selectCustomerSale(juegoId);
					dispose();
				};
				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
				
			}
		});
		panelCentral.add(btnVender);

		// Botón para cancelar/regresar
		JButton btnCancelar = new JButton("REGRESAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(26, 420, 172, 30);
		btnCancelar.addActionListener(e -> {
			Runnable tarea = () -> {
				TransactionView tv = new TransactionView();
				tv.AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
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

	public void SeleccionClienteRenta(VideoGames videojuegos ,List<User> usuarios) {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Configuración básica de la ventana
		setTitle("Seleccione usuario para renta");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

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
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				
				Runnable tarea = () -> {
					UserViews uv = new UserViews();
					uv.AdministradorCliente(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}

		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					VideogamesView vv = new VideogamesView();
					vv.AdministradorJuegos(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}

		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					TransactionView tv = new TransactionView();
					tv.AdministradorRentaCompra(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}

		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					PromotionsView pv = new PromotionsView();
					pv.NuevaOperacion(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}
		});
		panelIzq.add(btnNuevaOperacion);

		
		//PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("SELECCIONE UN USUARIO PARA RENTA");
		iniciar.setBounds(150, 11, 500, 42);
		iniciar.setHorizontalAlignment(JLabel.LEFT);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 20));
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
		for (User usuario : usuarios) {
		    Object[] rowData = {
		        usuario.getId(),
		        usuario.getfirstName(),
		        usuario.getlastName(),
		        usuario.getApellidoMaterno(),
		        usuario.getFechaNacimiento(),
		        usuario.getTelefono(),
		        usuario.getCorreo()
		    };
		    model.addRow(rowData);
		}

		// se crea la tabla
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10); // aqui redusco id
		table.getColumnModel().getColumn(2).setPreferredWidth(50); // aqui se reduce paellido paterno
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // here i reduce midle name

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // ordenar por columnas

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 80, 680, 330);
		panelCentral.add(scrollPane);

		// Buscador icon
		ImageIcon Buscadorpng = new ImageIcon(getClass().getResource("/images/Buscador.png"));
		Image imagenEscalada = Buscadorpng.getImage().getScaledInstance(30, 29, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(455, 49, 30, 29); // posicion
		panelCentral.add(logo);

		JTextField Buscador = new JTextField();
		Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		Buscador.setBounds(485, 50, 220, 25);
		Buscador.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        String textoBusqueda = Buscador.getText().trim().toLowerCase();
		        model.setRowCount(0); // Limpiar tabla

		        for (User usuario : usuarios) {
		            String nombre = usuario.getfirstName().toLowerCase() ;
		            String apellidoP = usuario.getlastName().toLowerCase();
		            String apellidoM = usuario.getApellidoMaterno() != null ? usuario.getApellidoMaterno().toLowerCase() : "";
		            String correo = usuario.getCorreo().toLowerCase();
		            String id = String.valueOf(usuario.getId()); 

		            if (nombre.contains(textoBusqueda) ||
		                apellidoP.contains(textoBusqueda) ||
		                apellidoM.contains(textoBusqueda)||
		                correo.contains(textoBusqueda)||
		                id.contains(textoBusqueda)) {

		                Object[] rowData = {
		                    usuario.getId(),
		                    usuario.getfirstName(),
		                    usuario.getlastName(),
		                    usuario.getApellidoMaterno(),
		                    usuario.getFechaNacimiento(),
		                    usuario.getTelefono(),
		                    usuario.getCorreo()
		                };
		                model.addRow(rowData);
		            }
		        }
		    }
		});
		panelCentral.add(Buscador);

		// BOTON EDITAR PROVICIONAL, MEJORAR
		JButton btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(Color.decode("#6D91B9"));
		btnSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
		btnSiguiente.setBounds(534, 420, 172, 30);
		btnSiguiente.addActionListener(e -> {

			int selectedRow = table.getSelectedRow();

			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un usuario", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Obtener datos del usuario seleccionado
			int userId = (int) model.getValueAt(selectedRow, 0);
			Runnable tarea = () -> {
				TransactionController tc = new TransactionController();
				tc.rentalOperation(videojuegos.getId(), userId);
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelCentral.add(btnSiguiente);
		
		JButton btnEliminar = new JButton("REGRESAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.decode("#B82F2F"));
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBounds(26, 420, 172, 30);
		btnEliminar.addActionListener(e -> {
			int cancelar;
			
			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres regresar?","Confirmar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					TransactionController tc = new TransactionController();
					tc.rentalIndex();
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}
			
		});
		panelCentral.add(btnEliminar);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		
	}

	public void OperacionRentar(User user, VideoGames videogames) {
		// Define estos JTextFields como atributos de clase (arriba, fuera de métodos)
		JTextField txtNombreCliente;
		JTextField txtNombreVideojuego;
		JTextField txtCantidad;
		JLabel precioRentafinal;
		JLabel lblValorDiasRenta;

		// selecciona la fecha actual y le da formato
		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// Configuración básica de la ventana
		setTitle("Detalles de operación Rentar");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

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

		// Nombre del cliente
		JLabel lblNombreCliente = new JLabel("Nombre del cliente");
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreCliente.setBounds(450, 130, 160, 42);
		panelCentral.add(lblNombreCliente);

		// Nombre completo del cliente
		JLabel lblValorNombreCliente = new JLabel(
				user.getfirstName() + " " + user.getApellidoMaterno() + " " + user.getApellidoMaterno());
		lblValorNombreCliente.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorNombreCliente.setBounds(450, 160, 300, 20);
		panelCentral.add(lblValorNombreCliente);

		// ID del Cliente
		JLabel lblIDCliente = new JLabel("ID del cliente:");
		lblIDCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblIDCliente.setBounds(155, 140, 150, 20);
		panelCentral.add(lblIDCliente);

		JLabel lblValorIDCliente = new JLabel(String.valueOf(user.getId()));
		lblValorIDCliente.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorIDCliente.setBounds(185, 160, 200, 20);
		panelCentral.add(lblValorIDCliente);

		// Correo electrónico
		JLabel lblCorreoCliente = new JLabel("Correo electrónico:");
		lblCorreoCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCorreoCliente.setBounds(700, 140, 160, 20);
		panelCentral.add(lblCorreoCliente);

		JLabel lblValorCorreoCliente = new JLabel(user.getCorreo());
		lblValorCorreoCliente.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorCorreoCliente.setBounds(700, 160, 300, 20);
		panelCentral.add(lblValorCorreoCliente);

		// Detalles de la compra
		JLabel lblTituloJuego = new JLabel("Nombre de videojuego");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTituloJuego.setBounds(75, 215, 255, 42);
		panelCentral.add(lblTituloJuego);

		JLabel lblNombre = new JLabel(videogames.getname());
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNombre.setBounds(135, 246, 355, 42);
		panelCentral.add(lblNombre);

		JLabel lblPrecio = new JLabel("Precio (MXN) por dia:");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecio.setBounds(450, 215, 255, 42);
		panelCentral.add(lblPrecio);

		JLabel lblValorRenta = new JLabel("" + videogames.getrentPrice());
		lblValorRenta.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorRenta.setBounds(465, 246, 255, 42);
		panelCentral.add(lblValorRenta);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblFecha.setBounds(70, 293, 255, 42);
		panelCentral.add(lblFecha);

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorFecha.setBounds(75, 320, 255, 42);
		panelCentral.add(lblValorFecha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTipo.setBounds(695, 215, 101, 42);
		panelCentral.add(lblTipo);

		JLabel lblValorTipo = new JLabel("Renta");
		lblValorTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTipo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorTipo.setBounds(700, 246, 101, 42);
		panelCentral.add(lblValorTipo);
		
		JLabel lblDiasRenta = new JLabel("Días de renta:");
		lblDiasRenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasRenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDiasRenta.setBounds(620, 293, 255, 20);  // Posición debajo del precio final
		panelCentral.add(lblDiasRenta);

		lblValorDiasRenta = new JLabel("1");
		lblValorDiasRenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorDiasRenta.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorDiasRenta.setBounds(625, 320, 255, 20);
		panelCentral.add(lblValorDiasRenta);
		
		JLabel lblFechaDevolucion = new JLabel("Fecha de devolución:");
		lblFechaDevolucion.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDevolucion.setFont(new Font("Calibri", Font.BOLD, 14));
		lblFechaDevolucion.setBounds(450, 293, 300, 27);
		panelCentral.add(lblFechaDevolucion);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");

		// Establecer la fecha mínima como HOY (no permite fechas anteriores)
		Calendar min = Calendar.getInstance();
		min.add(Calendar.DAY_OF_MONTH, 1); 
		dateChooser.setMinSelectableDate(min.getTime());
		
		//Establecer fecha inicial (mañana)
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1); // Sumar 1 día
		dateChooser.setDate(calendar.getTime());
		
		Calendar max = Calendar.getInstance();
		max.add(Calendar.MONTH, 1);// maximo un mes
		dateChooser.setMaxSelectableDate(max.getTime());


		// Configurar el campo de texto
		JFormattedTextField textField = ((JTextFieldDateEditor) dateChooser.getDateEditor());
		textField.setBackground(Color.decode("#D9D9D9"));
		textField.setBorder(BorderFactory.createCompoundBorder(
		    BorderFactory.createLineBorder(Color.GRAY),
		    BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));
		textField.setEditable(false);
		
		dateChooser.addPropertyChangeListener("date", evt -> {
			java.util.Date selectedDate = dateChooser.getDate();
		    if (selectedDate != null) {
		        LocalDate selectedLocalDate = selectedDate.toInstant()
		            .atZone(ZoneId.systemDefault())
		            .toLocalDate();
		       
		            // --- AQUÍ VA EL CÁLCULO DE DÍAS ---
		            long diasRenta = ChronoUnit.DAYS.between(
		                LocalDate.now(),
		                selectedLocalDate
		            );
		            lblValorDiasRenta.setText(String.valueOf(diasRenta));		        
		    }
		});

		dateChooser.setBounds(450, 325, 165, 27);
		panelCentral.add(dateChooser);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		// Botones
		JButton btnCancelar = new JButton("REGRESAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(175, 406, 172, 30);
		btnCancelar.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres regresar?", "Confirmar",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					TransactionController tc = new TransactionController();
					tc.selectCustomerRent(videogames.getId());
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();

			}
		});
		panelCentral.add(btnCancelar);

		JButton btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(Color.decode("#6D91B9"));
		btnSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
		btnSiguiente.setBounds(620, 406, 172, 30);
		btnSiguiente.addActionListener(e -> {
			try {
				
				if (lblValorDiasRenta.getText().equals("1")) {
				    int respuesta = JOptionPane.showConfirmDialog(
				        null,
				        "¿Estás seguro de que solo quieres rentar por 1 día?",
				        "Confirmar renta de 1 día",
				        JOptionPane.YES_NO_OPTION
				    );

				    if (respuesta != JOptionPane.YES_OPTION) {
				        return;
				    }
				}
				
				lblValorDiasRenta.getText().equals("1");
				
		        java.sql.Date fechaDevolucion = new java.sql.Date(dateChooser.getDate().getTime());
		        
		        Runnable tarea = () -> {
		        	TransactionController tc = new TransactionController();
					tc.rentalDetails(user.getId(), videogames.getId(), fechaDevolucion);
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
		panelCentral.add(btnSiguiente);

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
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#D9D9D9"));
		panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel lblFoto = new JLabel();
		lblFoto.setBounds(30, 20, 150, 100);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		ImageIcon icon = new ImageIcon(getClass().getResource("/ImagesCustomer/GameVacio.png"));
		Image img = icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
		lblFoto.setIcon(new ImageIcon(img));
		panelCentral.add(lblFoto);

		// Título principal
		JLabel lblTitulo = new JLabel("RENTA DE VIDEOJUEGO");
		lblTitulo.setSize(500, 42);
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

		JLabel lblNombreCliente = new JLabel(user.getfirstName()+" "+user.getlastName()+" "+user.getApellidoMaterno());
		lblNombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreCliente.setBounds(256, 102, 250, 42);
		panelCentral.add(lblNombreCliente);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCorreo.setBounds(553, 85, 70, 42);
		panelCentral.add(lblCorreo);

		JLabel lblEmailCliente = new JLabel(user.getCorreo());
		lblEmailCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEmailCliente.setBounds(469, 102, 250, 42);
		panelCentral.add(lblEmailCliente);

		JLabel lblTituloJuego = new JLabel(videogames.getname());
		lblTituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTituloJuego.setBounds(32, 141, 600, 42);
		panelCentral.add(lblTituloJuego);

		// Información del juego
		JLabel lblAnioJuego = new JLabel("" + videogames.getreleaseYear());
		lblAnioJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnioJuego.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAnioJuego.setBounds(42, 174, 200, 42);
		panelCentral.add(lblAnioJuego);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacion.setBounds(188, 204, 300, 42);
		panelCentral.add(lblClasificacion);

		JLabel lblValorClasificacion = new JLabel(videogames.getclassification());
		lblValorClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorClasificacion.setBounds(185, 232, 97, 42);
		panelCentral.add(lblValorClasificacion);

		JLabel lblDistribuidor = new JLabel("Distribuidores:");
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDistribuidor.setBounds(188, 285, 300, 42);
		panelCentral.add(lblDistribuidor);

		JLabel lblNombreDistribuidor = new JLabel(videogames.getdevelopedBy());
		lblNombreDistribuidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreDistribuidor.setBounds(138, 309, 200, 42);
		panelCentral.add(lblNombreDistribuidor);

		// Fechas de renta
		JLabel lblFechaRenta = new JLabel("Fecha de renta:");
		lblFechaRenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaRenta.setBounds(428, 204, 300, 42);
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
		lblFechaLimite.setBounds(407, 285, 300, 42);
		panelCentral.add(lblFechaLimite);
		
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		JLabel lblValorFechaLimite = new JLabel();
	    lblValorFechaLimite.setText(sdf.format(fechaDevolucion));
		lblValorFechaLimite.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFechaLimite.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFechaLimite.setBounds(436, 309, 97, 42);
		panelCentral.add(lblValorFechaLimite);
		
		JLabel lblDiasRenta = new JLabel("Días de renta:");
		lblDiasRenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiasRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDiasRenta.setBounds(626, 204, 200, 42);
		panelCentral.add(lblDiasRenta);
	
		// esta la logica para sacar los dias de renta
		// se hace la conversion a java sql
        LocalDate fechaDevolucionLocal = new java.sql.Date(fechaDevolucion.getTime()).toLocalDate();
        // se compra la fecha actual con la fecha de devolucion
        // aqui aqui hay error de logica pero lo arrgelo despues
        long diasRenta = ChronoUnit.DAYS.between(fechaActual, fechaDevolucionLocal);

		JLabel lblValorDiasRenta = new JLabel(String.valueOf(diasRenta));
		lblValorDiasRenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorDiasRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorDiasRenta.setBounds(636, 232, 97, 42);
		panelCentral.add(lblValorDiasRenta);

		// Información de pago
		JLabel lblInfoPago = new JLabel("Información de pago");
		lblInfoPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInfoPago.setBounds(780, 141, 300, 42);
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
		lblCantidad.setBounds(780, 174, 100, 42);
		panelCentral.add(lblCantidad);

		JLabel lblValorCantidad = new JLabel("1");
		lblValorCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCantidad.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorCantidad.setBounds(880, 174, 57, 42);
		panelCentral.add(lblValorCantidad);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setFont(new Font("Calibri", Font.BOLD, 18));
		lblProducto.setBounds(780, 215, 100, 42);
		panelCentral.add(lblProducto);

		JLabel lblPrecioProducto = new JLabel("" + videogames.getrentPrice());
		lblPrecioProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioProducto.setFont(new Font("Calibri", Font.BOLD, 18));
		lblPrecioProducto.setBounds(860, 215, 85, 42);
		panelCentral.add(lblPrecioProducto);

		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubtotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSubtotal.setBounds(780, 248, 100, 42);
		panelCentral.add(lblSubtotal);

		// logica para sacar total de Producto
		// Primero pasamos los dias de renta que estaban en entero a bigdecimal
		BigDecimal diasDerenta = BigDecimal.valueOf(diasRenta);
		// despues selccionamos el precio de renta y lo multilicamos
		// haci sacando el total del producto
		BigDecimal valorTotalProducto = videogames.getrentPrice().multiply(diasDerenta);
		// despues lo convertimos a un string para poder mostrarlo
		JLabel lblValorSubtotal = new JLabel(String.valueOf(valorTotalProducto));
		lblValorSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorSubtotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorSubtotal.setBounds(860, 248, 78, 42);
		panelCentral.add(lblValorSubtotal);

		// Operaciones
		// todo lo que ocupe la logica de los numero debe estar abajo de esto
		BigDecimal valorIVA = BigDecimal.valueOf(0.16);
		BigDecimal subtotal = valorTotalProducto;
		BigDecimal iva = valorTotalProducto.multiply(valorIVA);
		BigDecimal total = subtotal.add(iva);

//		// IVA logica
//		JLabel lblIVA = new JLabel("IVA");
//		lblIVA.setHorizontalAlignment(SwingConstants.LEFT);
//		lblIVA.setFont(new Font("Calibri", Font.BOLD, 18));
//		lblIVA.setBounds(780, 304, 126, 42);
//		panelCentral.add(lblIVA);

		// este lo ocupe para darle formato de que solo me muestr 2 digitos
		DecimalFormat formato = new DecimalFormat("#0.00");
		JLabel lblValorIVA = new JLabel(formato.format(iva));
		lblValorIVA.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorIVA.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorIVA.setBounds(880, 285, 97, 42);
		panelCentral.add(lblValorIVA);

		// Total
		JTextArea txtTotal = new JTextArea("Total(IVA 16%):");
		txtTotal.setWrapStyleWord(true);
		txtTotal.setOpaque(false);
		txtTotal.setLineWrap(true);
		txtTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		txtTotal.setEditable(false);
		txtTotal.setBounds(778, 294, 298, 54);
		panelCentral.add(txtTotal);
		
		JLabel precioFinal = new JLabel("Precio Final:");
		precioFinal.setHorizontalAlignment(SwingConstants.LEFT);
		precioFinal.setFont(new Font("Calibri", Font.BOLD, 18));
		precioFinal.setBounds(780, 320, 100, 42);
		panelCentral.add(precioFinal);

		JLabel lblTotal = new JLabel(String.valueOf(total));
		lblTotal.setForeground(new Color(153, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTotal.setBounds(875, 320, 126, 42);
		panelCentral.add(lblTotal);
		
		// Botones
		JButton btnCancelar = new JButton("REGRESAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int cancelar;

				cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres regresar?", "Confirmar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (cancelar == JOptionPane.YES_OPTION) {
					Runnable tarea = () -> {
						TransactionController tc = new TransactionController();
						tc.salesOperation(videogames.getId(), user.getId());
						dispose();
					};

					// recibe el label donde esta el gif y la tarea a ejecutar
					new LoadingFrame(labelGif, tarea).show();

				}
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnCancelar);
		
		
		JButton btnRegresar = new JButton("INICIO");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable tarea = () -> {
					TransactionView tv = new TransactionView();
					tv.AdministradorRentaCompra();
					dispose();
				};
				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}
		});
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(Color.decode("#B82F2F"));
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setVisible(false);
		btnRegresar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnRegresar);
		
		
		JButton btnDescargarPDF = new JButton("DESCARGAR (PDF)");
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setFont(new Font("Arial", Font.BOLD, 14));
		btnDescargarPDF.setBounds(690, 406, 183, 33);
		btnDescargarPDF.setEnabled(false); // Con esto se desabilita el boton al inicio
		btnDescargarPDF.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Guardar archivo PDF");
		        fileChooser.setSelectedFile(new File("Detalles_Renta_" + videogames.getname() + ".pdf"));

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
		                
		                document.add(new Paragraph("Nombre: " + user.getfirstName() + " " + 
		                    user.getlastName() + " " + user.getApellidoMaterno()));
		                document.add(new Paragraph("Correo: " + user.getCorreo()));
		                document.add(Chunk.NEWLINE);

		                // Información del videojuego
		                Paragraph juegoHeader = new Paragraph("INFORMACIÓN DEL VIDEOJUEGO\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(juegoHeader);
		                
		                document.add(new Paragraph("Título: " + videogames.getname()));
		                document.add(new Paragraph("Plataforma: " + videogames.getplatform()));
		                document.add(new Paragraph("Año: " + videogames.getreleaseYear()));
		                document.add(new Paragraph("Clasificación: " + videogames.getclassification()));
		                document.add(new Paragraph("Desarrollador: " + videogames.getdevelopedBy()));
		                document.add(Chunk.NEWLINE);

		                // Detalles de la renta
		                Paragraph rentaHeader = new Paragraph("DETALLES DE LA RENTA\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(rentaHeader);
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		                document.add(new Paragraph("Fecha de renta: " + fechaFormateada));
		                document.add(new Paragraph("Fecha de devolución: " + sdf.format(fechaDevolucion)));
		                
		                // Conversión segura y cálculo de días
//		                LocalDate fechaDevolucionLocal = new java.sql.Date(fechaDevolucion.getTime()).toLocalDate();
		                LocalDate fechaActualLocal = LocalDate.now();
		                long diasRenta = ChronoUnit.DAYS.between(fechaActualLocal, fechaDevolucionLocal);
		                
		                document.add(new Paragraph("Días de renta: " + diasRenta));
		                document.add(Chunk.NEWLINE);

		                // Información de pago
		                Paragraph pagoHeader = new Paragraph("INFORMACIÓN DE PAGO\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(pagoHeader);
		                
		                
		                document.add(new Paragraph("Precio por día: $" + videogames.getrentPrice()));
		                document.add(new Paragraph("Días rentados: " + diasRenta));
		                
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
		

		JButton btnConfirmacionCompra = new JButton("CONFIRMACION DE RENTA");
		btnConfirmacionCompra.setForeground(Color.WHITE);
		btnConfirmacionCompra.setBackground(Color.decode("#6D91B9"));
		btnConfirmacionCompra.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirmacionCompra.setBounds(420, 406, 220, 33);
		btnConfirmacionCompra.addActionListener(e ->{
			try {

				TransactionModel transModel = new TransactionModel();
				boolean exito = transModel.createTransaction(user.getId(), videogames.getId(), "rental", fechaDevolucion,
						total, "rented");

				if (exito) {
					JOptionPane.showMessageDialog(this, "¡Renta registrada con éxito!");
					btnDescargarPDF.setEnabled(true); // Aqui se habilita el boton de descargar
					
					
		            btnCancelar.setVisible(false);// Aqui Oculta Cancelar y aparece Regresar
		            btnRegresar.setVisible(true);
		            
		            panelCentral.revalidate();
		            panelCentral.repaint();///////
				
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar la renta", "Error",
							JOptionPane.ERROR_MESSAGE);
					btnDescargarPDF.setEnabled(false); //Sigue desabilitado el boton descargar
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
				btnDescargarPDF.setEnabled(false);//Sigue desabilitado el boton descargar
			}
			
		});
		panelCentral.add(btnConfirmacionCompra);


		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
		
	}
	
	public void SeleccionClienteVenta(VideoGames videojuegos ,List<User> usuarios) {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Configuración básica de la ventana
		setTitle("Seleccione usuario para venta");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

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
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					UserViews uv = new UserViews();
					uv.AdministradorCliente(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}

		});
		panelIzq.add(btnClientes);

		JButton btnVideojuegos = new JButton("VIDEOJUEGOS");
		btnVideojuegos.setForeground(Color.WHITE);
		btnVideojuegos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVideojuegos.setBackground(new Color(38, 60, 84));
		btnVideojuegos.setBounds(10, 128, 237, 100);
		panelIzq.add(btnVideojuegos);
		btnVideojuegos.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					VideogamesView vv = new VideogamesView();
					vv.AdministradorJuegos(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}

		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					TransactionView tv = new TransactionView();
					tv.AdministradorRentaCompra(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}

		});
		panelIzq.add(btnRentaYCompra);

		JButton btnNuevaOperacion = new JButton("NUEVA OPERACIÓN");
		btnNuevaOperacion.setForeground(Color.WHITE);
		btnNuevaOperacion.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNuevaOperacion.setBackground(new Color(38, 60, 84));
		btnNuevaOperacion.setBounds(10, 364, 237, 100);
		btnNuevaOperacion.addActionListener(e -> {
			int cancelar;

			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?", "Confirmar cancelacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					PromotionsView pv = new PromotionsView();
					pv.NuevaOperacion(); // Abre la segunda ventana
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}
		});
		panelIzq.add(btnNuevaOperacion);

		
		//PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(277, 62, 731, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel iniciar = new JLabel("SELECCIONE UN USUARIO PARA VENTA");
		iniciar.setBounds(150, 11, 500, 42);
		iniciar.setHorizontalAlignment(JLabel.LEFT);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 20));
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
		for (User usuario : usuarios) {
		    Object[] rowData = {
		        usuario.getId(),
		        usuario.getfirstName(),
		        usuario.getlastName(),
		        usuario.getApellidoMaterno(),
		        usuario.getFechaNacimiento(),
		        usuario.getTelefono(),
		        usuario.getCorreo()
		    };
		    model.addRow(rowData);
		}

		// se crea la tabla
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10); // aqui redusco id
		table.getColumnModel().getColumn(2).setPreferredWidth(50); // aqui se reduce paellido paterno
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // here i reduce midle name

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // ordenar por columnas

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 80, 680, 330);
		panelCentral.add(scrollPane);

		// Buscador icon
		ImageIcon Buscadorpng = new ImageIcon(getClass().getResource("/images/Buscador.png"));
		Image imagenEscalada = Buscadorpng.getImage().getScaledInstance(30, 29, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(455, 49, 30, 29); // posicion
		panelCentral.add(logo);

		JTextField Buscador = new JTextField();
		Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
		Buscador.setBounds(485, 50, 220, 25);
		Buscador.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        String textoBusqueda = Buscador.getText().trim().toLowerCase();
		        model.setRowCount(0); // Limpiar tabla

		        for (User usuario : usuarios) {
		            String nombre = usuario.getfirstName().toLowerCase() ;
		            String apellidoP = usuario.getlastName().toLowerCase();
		            String apellidoM = usuario.getApellidoMaterno() != null ? usuario.getApellidoMaterno().toLowerCase() : "";
		            String correo = usuario.getCorreo().toLowerCase();
		            String id = String.valueOf(usuario.getId()); 

		            if (nombre.contains(textoBusqueda) ||
		                apellidoP.contains(textoBusqueda) ||
		                apellidoM.contains(textoBusqueda)||
		                correo.contains(textoBusqueda)||
		                id.contains(textoBusqueda)) {

		                Object[] rowData = {
		                    usuario.getId(),
		                    usuario.getfirstName(),
		                    usuario.getlastName(),
		                    usuario.getApellidoMaterno(),
		                    usuario.getFechaNacimiento(),
		                    usuario.getTelefono(),
		                    usuario.getCorreo()
		                };
		                model.addRow(rowData);
		            }
		        }
		    }
		});
		panelCentral.add(Buscador);

		// BOTON EDITAR PROVICIONAL, MEJORAR
		JButton btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(Color.decode("#6D91B9"));
		btnSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
		btnSiguiente.setBounds(534, 420, 172, 30);
		btnSiguiente.addActionListener(e -> {

			int selectedRow = table.getSelectedRow();

			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(layeredPane, "Por favor seleccione un usuario", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Obtener datos del usuario seleccionado
			int userId = (int) model.getValueAt(selectedRow, 0);
			Runnable tarea = () -> {
				TransactionController tc = new TransactionController();
				tc.salesOperation(videojuegos.getId(), userId);
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();
		});
		panelCentral.add(btnSiguiente);
		
		JButton btnEliminar = new JButton("CANCELAR");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.decode("#B82F2F"));
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBounds(26, 420, 172, 30);
		btnEliminar.addActionListener(e -> {
			int cancelar;
			
			cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar?","Confirmar cancelacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			
			if(cancelar == JOptionPane.YES_OPTION) {
				Runnable tarea = () -> {
					TransactionController tc = new TransactionController();
					tc.salesIndex();
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();
			}
			
		});
		panelCentral.add(btnEliminar);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

		
	}
	
	public void OperacionComprar(VideoGames videogames, User user) {

		// selecciona la fecha actual y le da formato
		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// Configuración básica de la ventana
		setTitle("Detalles de operación Comprar");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

		// Panel principal
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(5, 62, 998, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(477, 11, 70, 70); // posicion
		panelCentral.add(logo);

		// Nombre del cliente
		JLabel lblNombreCliente = new JLabel("Nombre del cliente");
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreCliente.setBounds(450, 130, 160, 42);
		panelCentral.add(lblNombreCliente);

		// Nombre completo del cliente
		JLabel lblValorNombreCliente = new JLabel(
				user.getfirstName() + " " + user.getApellidoMaterno() + " " + user.getlastName());
		lblValorNombreCliente.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorNombreCliente.setBounds(450, 160, 300, 20);
		panelCentral.add(lblValorNombreCliente);

		// ID del Cliente
		JLabel lblIDCliente = new JLabel("ID del cliente:");
		lblIDCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblIDCliente.setBounds(155, 140, 150, 20);
		panelCentral.add(lblIDCliente);

		JLabel lblValorIDCliente = new JLabel(String.valueOf(user.getId()));
		lblValorIDCliente.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorIDCliente.setBounds(190, 160, 200, 20);
		panelCentral.add(lblValorIDCliente);

		// Correo electrónico
		JLabel lblCorreoCliente = new JLabel("Correo electrónico:");
		lblCorreoCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCorreoCliente.setBounds(700, 140, 160, 20);
		panelCentral.add(lblCorreoCliente);

		JLabel lblValorCorreoCliente = new JLabel(user.getCorreo());
		lblValorCorreoCliente.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorCorreoCliente.setBounds(700, 160, 300, 20);
		panelCentral.add(lblValorCorreoCliente);

		// Detalles de la compra
		JLabel lblTituloJuego = new JLabel("Nombre de videojuego");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTituloJuego.setBounds(75, 215, 255, 42);
		panelCentral.add(lblTituloJuego);

		JLabel lblNombre = new JLabel(videogames.getname());
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNombre.setBounds(135, 246, 355, 42);
		panelCentral.add(lblNombre);

		JLabel lblPrecio = new JLabel("Precio (MXN):");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecio.setBounds(460, 215, 255, 42);
		panelCentral.add(lblPrecio);

		JLabel lblValorRenta = new JLabel("$ " + videogames.getsalePrice());
		lblValorRenta.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorRenta.setBounds(470, 246, 255, 42);
		panelCentral.add(lblValorRenta);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblFecha.setBounds(460, 293, 255, 42);
		panelCentral.add(lblFecha);

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorFecha.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorFecha.setBounds(460, 320, 255, 42);
		panelCentral.add(lblValorFecha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTipo.setBounds(695, 215, 101, 42);
		panelCentral.add(lblTipo);

		JLabel lblValorTipo = new JLabel("Venta");
		lblValorTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTipo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblValorTipo.setBounds(700, 246, 101, 42);
		panelCentral.add(lblValorTipo);

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		// Botones
		JButton btnCancelar = new JButton("REGRESAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			Runnable tarea = () -> {
				TransactionController tc = new TransactionController();
				tc.selectCustomerSale(videogames.getId());
				dispose();
			};

			// recibe el label donde esta el gif y la tarea a ejecutar
			new LoadingFrame(labelGif, tarea).show();

		});
		panelCentral.add(btnCancelar);

		JButton btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(Color.decode("#6D91B9"));
		btnSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
		btnSiguiente.setBounds(620, 406, 183, 33);
		btnSiguiente.addActionListener(e -> {
			try {

				Runnable tarea = () -> {
					TransactionController tc = new TransactionController();
					tc.indexDetallesCompra(user.getId(), videogames.getId());
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
		panelCentral.add(btnSiguiente);

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
		
		JLabel labelGif = GifLoading.crearLabelGif(layeredPane);

		// Panel principal
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#D9D9D9"));
		panelCentral.setBounds(5, 62, 998, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		JLabel lblFoto = new JLabel();
		lblFoto.setBounds(30, 20, 150, 100);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		ImageIcon icon = new ImageIcon(getClass().getResource("/ImagesCustomer/GameVacio.png"));
		Image img = icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
		lblFoto.setIcon(new ImageIcon(img));
		panelCentral.add(lblFoto);

		// Título principal
		JLabel lblTitulo = new JLabel("VENTA DE VIDEOJUEGO");
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

		JLabel lblNombreCliente = new JLabel(
				user.getfirstName() + " " + user.getlastName() + " " + user.getApellidoMaterno());
		lblNombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreCliente.setBounds(256, 102, 250, 42);
		panelCentral.add(lblNombreCliente);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCorreo.setBounds(553, 85, 70, 42);
		panelCentral.add(lblCorreo);

		JLabel lblEmailCliente = new JLabel(user.getCorreo());
		lblEmailCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEmailCliente.setBounds(469, 102, 250, 42);
		panelCentral.add(lblEmailCliente);

		JLabel lblTituloJuego = new JLabel(videogames.getname());
		lblTituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTituloJuego.setBounds(32, 141, 400, 42);
		panelCentral.add(lblTituloJuego);

		// Información del juego
		JLabel lblAnioJuego = new JLabel("" + videogames.getreleaseYear());
		lblAnioJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnioJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblAnioJuego.setBounds(42, 174, 200, 42);
		panelCentral.add(lblAnioJuego);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacion.setBounds(270, 204, 300, 42);
		panelCentral.add(lblClasificacion);

		JLabel lblValorClasificacion = new JLabel(videogames.getclassification());
		lblValorClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorClasificacion.setBounds(280, 232, 97, 42);
		panelCentral.add(lblValorClasificacion);

		JLabel lblDistribuidor = new JLabel("Distribuidores:");
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDistribuidor.setBounds(270, 285, 200, 42);
		panelCentral.add(lblDistribuidor);

		JLabel lblNombreDistribuidor = new JLabel(videogames.getdevelopedBy());
		lblNombreDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreDistribuidor.setBounds(270, 309, 200, 42);
		panelCentral.add(lblNombreDistribuidor);

		// Fechas de renta
		JLabel lblFechaRenta = new JLabel("Fecha de renta:");
		lblFechaRenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaRenta.setBounds(520, 204, 250, 42);
		panelCentral.add(lblFechaRenta);

		LocalDate fechaActual = LocalDate.now();
		String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		JLabel lblValorFecha = new JLabel(fechaFormateada);
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFecha.setBounds(530, 232, 97, 42);
		panelCentral.add(lblValorFecha);

		// Información de pago
		JLabel lblInfoPago = new JLabel("Información de pago");
		lblInfoPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoPago.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInfoPago.setBounds(780, 141, 300, 42);
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

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCantidad.setBounds(780, 174, 100, 42);
		panelCentral.add(lblCantidad);

		JLabel lblValorCantidad = new JLabel("1");
		lblValorCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCantidad.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorCantidad.setBounds(880, 174, 57, 42);
		panelCentral.add(lblValorCantidad);

		// Producto y precio (sin cambios)
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setFont(new Font("Calibri", Font.BOLD, 18));
		lblProducto.setBounds(780, 215, 100, 42);
		panelCentral.add(lblProducto);

		JLabel lblPrecioProducto = new JLabel("" + videogames.getsalePrice());
		lblPrecioProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioProducto.setFont(new Font("Calibri", Font.BOLD, 18));
		lblPrecioProducto.setBounds(880, 215, 85, 42);
		panelCentral.add(lblPrecioProducto);

		// Subtotal (directamente el precio de venta)
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubtotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSubtotal.setBounds(780, 248, 100, 42);
		panelCentral.add(lblSubtotal);

		// Valor subtotal (precio directo sin multiplicar por días)
		BigDecimal subtotal = videogames.getsalePrice();
		JLabel lblValorSubtotal = new JLabel(String.valueOf(subtotal));
		lblValorSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorSubtotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorSubtotal.setBounds(880, 248, 78, 42);
		panelCentral.add(lblValorSubtotal);

		// Operaciones (IVA y Total)
		BigDecimal valorIVA = BigDecimal.valueOf(0.16);
		BigDecimal iva = subtotal.multiply(valorIVA);
		BigDecimal total = subtotal.add(iva);

		// Formato para mostrar solo 2 decimales
		DecimalFormat formato = new DecimalFormat("#0.");

		// Mostrar IVA
		JLabel lblValorIVA = new JLabel(formato.format(iva));
		lblValorIVA.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorIVA.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorIVA.setBounds(880, 285, 97, 42);
		panelCentral.add(lblValorIVA);

		// Total
		JTextArea txtTotal = new JTextArea("Total(IVA 16%):");
		txtTotal.setWrapStyleWord(true);
		txtTotal.setOpaque(false);
		txtTotal.setLineWrap(true);
		txtTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		txtTotal.setEditable(false);
		txtTotal.setBounds(778, 294, 298, 54);
		panelCentral.add(txtTotal);
		
		JLabel precioFinal = new JLabel("Precio Final:");
		precioFinal.setHorizontalAlignment(SwingConstants.LEFT);
		precioFinal.setFont(new Font("Calibri", Font.BOLD, 18));
		precioFinal.setBounds(780, 320, 100, 42);
		panelCentral.add(precioFinal);

		JLabel lbltotal = new JLabel(String.valueOf(total));
		lbltotal.setForeground(new Color(153, 0, 0));
		lbltotal.setHorizontalAlignment(SwingConstants.LEFT);
		lbltotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lbltotal.setBounds(875, 320, 126, 42);
		panelCentral.add(lbltotal);

		// Botones
		JButton btnCancelar = new JButton("REGRESAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int cancelar;

				cancelar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres regresar?", "Confirmar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (cancelar == JOptionPane.YES_OPTION) {

					Runnable tarea = () -> {
						TransactionController tc = new TransactionController();
						tc.salesOperation(videogames.getId(), user.getId());
						dispose();
					};

					// recibe el label donde esta el gif y la tarea a ejecutar
					new LoadingFrame(labelGif, tarea).show();

				}
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnCancelar);
		
		
		JButton btnRegresar = new JButton("INICIO");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable tarea = () -> {
					TransactionView tv = new TransactionView();
					tv.AdministradorRentaCompra();
					dispose();
				};

				// recibe el label donde esta el gif y la tarea a ejecutar
				new LoadingFrame(labelGif, tarea).show();

			}
		});
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(Color.decode("#B82F2F"));
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegresar.setVisible(false);
		btnRegresar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnRegresar);

		JButton btnDescargarPDF = new JButton("DESCARGAR (PDF)");
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setFont(new Font("Arial", Font.BOLD, 14));
		btnDescargarPDF.setBounds(690, 406, 183, 33);
		btnDescargarPDF.setEnabled(false); // Con esto se desabilita el boton al inicio
		btnDescargarPDF.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Guardar archivo PDF");
		        fileChooser.setSelectedFile(new File("Detalles_Venta_" + videogames.getname() + ".pdf"));

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
		                
		                document.add(new Paragraph("Nombre: " + user.getfirstName() + " " + 
		                    user.getlastName() + " " + user.getApellidoMaterno()));
		                document.add(new Paragraph("Correo: " + user.getCorreo()));
		                document.add(Chunk.NEWLINE);

		                // Información del videojuego
		                Paragraph juegoHeader = new Paragraph("INFORMACIÓN DEL VIDEOJUEGO\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(juegoHeader);
		                
		                document.add(new Paragraph("Título: " + videogames.getname()));
		                document.add(new Paragraph("Plataforma: " + videogames.getplatform()));
		                document.add(new Paragraph("Año: " + videogames.getreleaseYear()));
		                document.add(new Paragraph("Clasificación: " + videogames.getclassification()));
		                document.add(new Paragraph("Desarrollador: " + videogames.getdevelopedBy()));
		                document.add(Chunk.NEWLINE);

		                // Detalles de la renta
		                Paragraph rentaHeader = new Paragraph("DETALLES DE LA VENTA\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(rentaHeader);
		                
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		                document.add(new Paragraph("Fecha de renta: " + fechaFormateada));

		                // Información de pago
		                Paragraph pagoHeader = new Paragraph("INFORMACIÓN DE PAGO\n", 
		                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
		                document.add(pagoHeader);
		                
		                
		                document.add(new Paragraph("Precio fijo de venta: $" + videogames.getsalePrice()));
		                
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
		
		JButton btnConfirmacionCompra = new JButton("CONFIRMACION DE VENTA");
		btnConfirmacionCompra.setForeground(Color.WHITE);
		btnConfirmacionCompra.setBackground(Color.decode("#6D91B9"));
		btnConfirmacionCompra.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirmacionCompra.setBounds(420, 406, 220, 33);
		btnConfirmacionCompra.addActionListener(e ->{
			try {

				TransactionModel transModel = new TransactionModel();
				boolean exito = transModel.createTransaction(user.getId(), videogames.getId(), "sale",
						null, total, "completed");

				if (exito) {
					JOptionPane.showMessageDialog(this, "¡Renta registrada con éxito!");
					btnDescargarPDF.setEnabled(true); // Aqui se habilita el boton de descargar
					
					btnCancelar.setVisible(false);// Aqui Oculta Cancelar y aparece Regresar
		            btnRegresar.setVisible(true);
		            
		            panelCentral.revalidate();
		            panelCentral.repaint();///////
				
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar la renta", "Error",
							JOptionPane.ERROR_MESSAGE);
					btnDescargarPDF.setEnabled(false); //Sigue desabilitado el boton descargar
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
				btnDescargarPDF.setEnabled(false);//Sigue desabilitado el boton descargar
			}
			
		});
		panelCentral.add(btnConfirmacionCompra);

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);

	}

}

package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Iterator;

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

import controller.PromotionsController;
import models.Promotions;
import models.PromotionsModel;
import models.Transaction;
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
			PromotionsController pc = new PromotionsController();
			pc.pending_returns();
		});

		JButton btnPromocionAutomatica = new JButton("<html><center>PROMOCIÓN AUTOMATICA<html>");
		btnPromocionAutomatica.setForeground(Color.WHITE);
		btnPromocionAutomatica.setFont(new Font("Calibri", Font.BOLD, 16));
		btnPromocionAutomatica.setBackground(new Color(38, 60, 84));
		btnPromocionAutomatica.setBounds(266, 261, 206, 100);
		btnPromocionAutomatica.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			PromotionsController pc = new PromotionsController();
			pc.indexPromocion();// Abre la segunda ventana
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

	
	public void DevolucionesPendientes(java.util.List<Transaction> transaciones) {
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
		// Crear unan tabla
		String[] columnNames = { "Núm", "Nombre cliente","Juego", "Fecha de Renta", "Fecha de Devolución", "Precio" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Transaction t : transaciones) {

			Object[] rowData = { t.getId(), t.getCustomerName(),t.getVideoGameName(), t.getTransactionDate(),t.getReturnDate(), "$" + t.getPrice() };
			model.addRow(rowData);
		}

		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(10); // aqui redusco id
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // ordenar por columnas

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(28, 60, 650, 400);
		panelCentral.add(scrollPane);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Tabla
//		JTable table = new JTable(data, new String[] { "", "", "", "", "", "", });
//		panelCentral.add(table);
//		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		table.setBounds(26, 62, 695, 402);
//		table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
//		table.setShowGrid(true);
//		table.setGridColor(new Color(204, 204, 204));
//		table.setTableHeader(null);
//		table.setDefaultRenderer(Object.class, centerRenderer);
//		table.setRowHeight(40);
//		table.setShowHorizontalLines(true);
//		table.setShowVerticalLines(true);

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

	public void PromocionAutomatica(java.util.List<Promotions> promocion) {
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


		DefaultTableCellRenderer centerRendere = new DefaultTableCellRenderer();
		centerRendere.setHorizontalAlignment(JLabel.CENTER);
		
		// Crear unan tabla
		String[] columnNames = { "En la compra de:", "Promociones por compra" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer que la tabla no sea editable
			}
		};

		// Llenar la tabla con datos
		for (Iterator iterator = promocion.iterator(); iterator.hasNext();) {
			Promotions usuario = (Promotions) iterator.next();
			Object[] rowData = {usuario.getCompraCantida()+" $", usuario.getPromocionCompra()+" %" };
			model.addRow(rowData);
		}

		// se crea la tabla
		JTable table2 = new JTable(model);
		table2.setFont(new Font("Arial", Font.PLAIN, 14));
		table2.setRowHeight(25);
		table2.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table2.setAutoCreateRowSorter(true); // ordenar por columnas
		
		for (int i = 0; i < table2.getColumnCount(); i++) {
		    table2.getColumnModel().getColumn(i).setCellRenderer(centerRendere);
		}

		// Agregar la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table2);
		scrollPane.setBounds(406, 61, 315, 356);
		panelCentral.add(scrollPane);

		JButton btnEditarPromocion = new JButton("EDITAR PROMOCIÓN");
		btnEditarPromocion.setForeground(Color.WHITE);
		btnEditarPromocion.setBackground(Color.decode("#6D91B9"));
		btnEditarPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				PromotionsController pc = new PromotionsController();
				pc.indexPromocion2(); //llama a editar promociones
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

	public void EditarPromociones(java.util.List<Promotions> promocion) {
		
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

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (azul oscuro)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			PromotionsController pc = new PromotionsController();
			pc.indexPromocion();// Abre la segunda ventana(); 
			
		});
		panelCentral.add(btnCancelar);
		
		// se declara el arreglo de textFields (16 campos en total)
		JTextField[] campos = new JTextField[16];

		// Coordenadas X para cada columna
		int[] xPos = {85, 306, 530, 749}; // columnas
		int[] yPos = {147, 207, 273, 339}; // filas

		// Crear los campos dinámicamente
		int index = 0;
		for (int fila = 0; fila < 4; fila++) {
		    for (int col = 0; col < 4; col++) {
		    	
		        campos[index] = new JTextField();
		        campos[index].setColumns(10);
		        campos[index].setBackground(new Color(217, 217, 217));
		        campos[index].setBounds(xPos[col], yPos[fila], 189, 27);
		        panelCentral.add(campos[index]);
		        index++;
		    }
		}
		
		
		for (int i = 0; i < promocion.size() && i < 8; i++) {
			Promotions p = promocion.get(i);
			campos[i * 2].setText(String.valueOf(p.getCompraCantida()+"$"));
			campos[i * 2 + 1].setText(String.valueOf(p.getPromocionCompra()+"%"));
			}

		// Nombres de las etiquetas
		String[] nombresEtiquetas = {
		    "Compra de:", "Promoción de:", "Compra de:", "Promoción de:",
		    "Compra de:", "Promoción de:", "Compra de:", "Promoción de:",
		    "Compra de:", "Promoción de:", "Compra de:", "Promoción de:",
		    "Compra de:", "Promoción de:", "Compra de:", "Promoción de:",
		    };

		// Arreglo para guardar las etiquetas por si quieres usarlas después
		JLabel[] etiquetas = new JLabel[16];

		// Coordenadas X para cada columna
		int[] xPos2 = {85, 306, 530, 749}; // columnas
		int[] yPos2 = {123, 183, 249, 315}; // filas

		int index2 = 0;
		for (int fila = 0; fila < 4; fila++) {
		    for (int col = 0; col < 4; col++) {
		        etiquetas[index2] = new JLabel(nombresEtiquetas[index2]);
		        etiquetas[index2].setFont(new Font("Tahoma", Font.PLAIN, 14));
		        etiquetas[index2].setBounds(xPos2[col], yPos2[fila], 150, 14);
		        panelCentral.add(etiquetas[index2]);
		        index2++;
		    }
		}
		
		
		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			
			int confirmar = JOptionPane.showConfirmDialog(layeredPane,"¿Está seguro que desea actualizar?", "Confirmar cambios",JOptionPane.YES_NO_OPTION);

	        if (confirmar == JOptionPane.YES_OPTION) {
			
			 if (promocion != null && campos != null) {
			        for (int i = 0; i < Math.min(promocion.size(), 8); i++) {
			            try {
			                BigDecimal compraCantida = new BigDecimal(campos[i*2].getText());
			                BigDecimal promocionCompra = new BigDecimal(campos[i*2+1].getText());

			                Promotions promo = promocion.get(i);
			          
			                PromotionsModel pm = new PromotionsModel();
			                boolean resultado = pm.updatePromotion(promo.getId(), compraCantida, promocionCompra);
			                
			                
			            } catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(layeredPane, "Por favor ingrese valores numéricos válidos en todos los campos", "Error de formato", JOptionPane.ERROR_MESSAGE);
			                
			                return;
			            } catch (Exception ex) {
			                JOptionPane.showMessageDialog(layeredPane, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
			        }
			        JOptionPane.showMessageDialog(layeredPane, "Actualización completada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			        PromotionsController pc = new PromotionsController();
			        pc.indexPromocion();
			    }
	        }
		});
		panelCentral.add(btnConfirmar);


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

		// se declara el arreglo de textFields (16 campos en total)
		JTextField[] campos = new JTextField[16];

		// Coordenadas X para cada columna
		int[] xPos = { 85, 306, 530, 749 }; // columnas
		int[] yPos = { 147, 207, 273, 339 }; // filas

		// Crear los campos dinámicamente
		int index = 0;
		for (int fila = 0; fila < 4; fila++) {
			for (int col = 0; col < 4; col++) {

				campos[index] = new JTextField();
				campos[index].setColumns(10);
				campos[index].setBackground(new Color(217, 217, 217));
				campos[index].setBounds(xPos[col], yPos[fila], 189, 27);
				panelCentral.add(campos[index]);
				index++;
			}
		}

		// Nombres de las etiquetas
		String[] nombresEtiquetas = { "Mes de:", "Promoción de:", "Mes de:", "Promoción de:", "Mes de:",
				"Promoción de:", "Mes de:", "Promoción de:", "Mes de:", "Promoción de:", "Mes de:",
				"Promoción de:", "Mes de:", "Promoción de:", "Mes de:", "Promoción de:", };

		// Arreglo para guardar las etiquetas por si quieres usarlas después
		JLabel[] etiquetas = new JLabel[16];

		// Coordenadas X para cada columna
		int[] xPos2 = { 85, 306, 530, 749 }; // columnas
		int[] yPos2 = { 123, 183, 249, 315 }; // filas

		int index2 = 0;
		for (int fila = 0; fila < 4; fila++) {
			for (int col = 0; col < 4; col++) {
				etiquetas[index2] = new JLabel(nombresEtiquetas[index2]);
				etiquetas[index2].setFont(new Font("Tahoma", Font.PLAIN, 14));
				etiquetas[index2].setBounds(xPos2[col], yPos2[fila], 150, 14);
				panelCentral.add(etiquetas[index2]);
				index2++;
			}
		}

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}
	
}

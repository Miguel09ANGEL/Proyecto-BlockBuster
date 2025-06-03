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
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controller.VideogamesController;
import models.VideoGames;
import models.VideoGamesModel;

public class VideogamesView extends JFrame {

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
				PromotionsView pv = new PromotionsView();
				pv.NuevaOperacion(); // Abre la segunda ventana
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
			AgregarJuego();
			
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
			UserViews uv = new UserViews();
			uv.AdministradorCliente();
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
			AdministradorJuegos();
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		panelIzq.add(btnRentaYCompra);
		btnRentaYCompra.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			TransactionView tv = new TransactionView();
			tv.AdministradorRentaCompra();
		});

		JButton btnClientes_3 = new JButton("NUEVA OPERACIÓN");
		btnClientes_3.setForeground(Color.WHITE);
		btnClientes_3.setFont(new Font("Calibri", Font.BOLD, 16));
		btnClientes_3.setBackground(new Color(38, 60, 84));
		btnClientes_3.setBounds(10, 364, 237, 100);
		panelIzq.add(btnClientes_3);
		btnClientes_3.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			PromotionsView pv = new PromotionsView();
			pv.NuevaOperacion();
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
	    
	    JLabel lblanio = new JLabel("Año de lanzamiento:");
	    lblanio.setHorizontalAlignment(JLabel.LEFT);
	    lblanio.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblanio.setBounds(55, 164, 180, 42);
	    panelCentral.add(lblanio);

	    // Año
	    JLabel lblAnio = new JLabel(""+videogames.getAñoLanzamiento());
	    lblAnio.setForeground(Color.decode("#3B3741"));
	    lblAnio.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblAnio.setBounds(220, 165, 70, 42);
	    panelCentral.add(lblAnio);

	    // Clasificación
	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setHorizontalAlignment(JLabel.LEFT);
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblClasificacion.setBounds(55, 204, 120, 42);
	    panelCentral.add(lblClasificacion);

	    JLabel lblClasificacionValor = new JLabel(videogames.getClasificacion());
	    lblClasificacionValor.setForeground(Color.decode("#3B3741"));
	    lblClasificacionValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblClasificacionValor.setBounds(160, 204, 103, 42);
	    panelCentral.add(lblClasificacionValor);

	    // Desarrolladores
	    JLabel lblDesarrolladores = new JLabel("Desarrolladores:");
	    lblDesarrolladores.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblDesarrolladores.setBounds(55, 244, 190, 42);
	    panelCentral.add(lblDesarrolladores);

	    JTextArea txtDesarrolladores = new JTextArea(videogames.getDesarrolladoPor());
	    txtDesarrolladores.setWrapStyleWord(true);
	    txtDesarrolladores.setLineWrap(true);
	    txtDesarrolladores.setOpaque(false);
	    txtDesarrolladores.setEditable(false);
	    txtDesarrolladores.setFont(new Font("Calibri Light", Font.BOLD, 18));
	    txtDesarrolladores.setForeground(Color.decode("#3B3741"));
	    txtDesarrolladores.setBounds(180, 255, 318, 70);
	    panelCentral.add(txtDesarrolladores);

	    // Género
	    JLabel lblGenero = new JLabel("Género:");
	    lblGenero.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblGenero.setBounds(55, 284, 70, 42);
	    panelCentral.add(lblGenero);

	    JLabel lblGeneroValor = new JLabel(videogames.getGenero());
	    lblGeneroValor.setForeground(Color.decode("#3B3741"));
	    lblGeneroValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblGeneroValor.setBounds(130, 284, 200, 42);
	    panelCentral.add(lblGeneroValor);

	    // Plataforma
	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblPlataforma.setBounds(55, 324, 110, 42);
	    panelCentral.add(lblPlataforma);

	    JLabel lblPlataformaValor = new JLabel(videogames.getPlataforma());
	    lblPlataformaValor.setForeground(Color.decode("#3B3741"));
	    lblPlataformaValor.setFont(new Font("Calibri", Font.BOLD, 18));
	    lblPlataformaValor.setBounds(150, 324, 200, 42);
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
	    JLabel lblPrecioRenta = new JLabel("Precio por renta (mxn):");
	    lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblPrecioRenta.setBounds(610, 317, 200, 42);
	    panelCentral.add(lblPrecioRenta);

	    JLabel lblValorRenta = new JLabel(""+videogames.getPrecioRenta());
	    lblValorRenta.setForeground(new Color(153, 0, 0));
	    lblValorRenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblValorRenta.setBounds(820, 317, 126, 42);
	    panelCentral.add(lblValorRenta);
	    
	    JLabel lblPrecioVenta = new JLabel("Precio por venta (mxn):");
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 20));
	    lblPrecioVenta.setBounds(610, 353, 200, 42);
	    panelCentral.add(lblPrecioVenta);

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
	        	dispose();
	        	VideogamesController vc = new VideogamesController();
			    vc.updateVideogames2(videogames.getId());
	        }
	        
	    });
	    panelCentral.add(btnEditar);
	    
	    JButton btnDescargarPDF = new JButton("Descargar (PDF)");
	    btnDescargarPDF.setBackground(Color.decode("#263C54"));
	    btnDescargarPDF.setForeground(Color.WHITE);
	    btnDescargarPDF.setBounds(605, 417, 183, 33);
	    btnDescargarPDF.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setDialogTitle("Guardar archivo PDF");

	            fileChooser.setSelectedFile(new File("Detalles_Videojuego_" + videogames.getNombre() + ".pdf"));

	            int userSelection = fileChooser.showSaveDialog(null);

	            if (userSelection == JFileChooser.APPROVE_OPTION) {
	                File fileToSave = fileChooser.getSelectedFile();
	                Document document = new Document();

	                try {
	                    PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
	                    document.open();

	                    Paragraph titulo = new Paragraph("DETALLES DEL VIDEOJUEGO\n\n");
	                    document.add(titulo);

	                    document.add(new Paragraph("Nombre: " + videogames.getNombre()));
	                    document.add(new Paragraph("Año de lanzamiento: " + videogames.getAñoLanzamiento()));
	                    document.add(new Paragraph("Clasificación: " + videogames.getClasificacion()));
	                    document.add(new Paragraph("Desarrolladores: " + videogames.getDesarrolladoPor()));
	                    document.add(new Paragraph("Género: " + videogames.getGenero()));
	                    document.add(new Paragraph("Plataforma: " + videogames.getPlataforma()));

	                    document.add(new Paragraph("\nAcerca del juego:"));
	                    document.add(new Paragraph(videogames.getDescripcion()));

	                    document.add(new Paragraph("\nPrecio por renta: $" + videogames.getPrecioRenta()));
	                    document.add(new Paragraph("Precio por venta: $" + videogames.getPrecioVenta()));

	                    JOptionPane.showMessageDialog(null, "PDF guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                } catch (FileNotFoundException | DocumentException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Error al guardar el PDF:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                } finally {
	                    document.close();
	                }
	            }
	        }
	    });
		panelCentral.add(btnDescargarPDF);


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
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);

	    // Usamos JLayeredPane para superponer componentes
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setPreferredSize(new Dimension(1024, 576));
	    setContentPane(layeredPane);

	    // Panel central gris
	    JPanel panelCentral = new JPanel();
	    panelCentral.setLayout(null);
	    panelCentral.setBackground(Color.decode("#F2F2F2"));
	    panelCentral.setBounds(5, 62, 1014, 509);
	    layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

	    // Logotipo
	    ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
	    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	    JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
	    logo.setBounds(477, 11, 70, 70);
	    panelCentral.add(logo);

	    // Título de la ventana
	    JLabel lblTitulo = new JLabel("EDITAR VIDEOJUEGO");
	    lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
	    lblTitulo.setBounds(350, 78, 350, 42);
	    panelCentral.add(lblTitulo);

	    // Sección izquierda (Datos básicos)
	    JLabel lblNombre = new JLabel("Nombre del Videojuego:");
	    lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblNombre.setBounds(55, 130, 180, 20);
	    panelCentral.add(lblNombre);

	    txtNombreJuego = new JTextField(videogames.getNombre());
	    txtNombreJuego.setBounds(55, 155, 230, 25);
	    panelCentral.add(txtNombreJuego);
	    
	    JLabel lblAnio = new JLabel("Año de lanzamiento:");
	    lblAnio.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAnio.setBounds(55, 190, 180, 20);
	    panelCentral.add(lblAnio);

	    txtAnioLanzamiento = new JTextField(String.valueOf(videogames.getAñoLanzamiento()));
	    txtAnioLanzamiento.setBounds(55, 215, 150, 25);
	    panelCentral.add(txtAnioLanzamiento);

	    JLabel lblGenero = new JLabel("Género:");
	    lblGenero.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblGenero.setBounds(55, 250, 180, 20);
	    panelCentral.add(lblGenero);

	    // ComboBox para Género
	    String[] genero = { "Acción", "Aventura", "Estrategia", "RPG", "Deportes", 
	                        "Carreras", "Shooter", "Lucha", "Plataformas", "Simulación", "Otro" };
	    
	    JComboBox<String> comboGenero = new JComboBox<String>(genero);
	    
	    comboGenero.setSelectedItem(videogames.getGenero());
	    comboGenero.setBounds(55, 275, 180, 25);
	    panelCentral.add(comboGenero);

	    JLabel lblPrecioRenta = new JLabel("Precio renta (MXN):");
	    lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioRenta.setBounds(55, 310, 180, 20);
	    panelCentral.add(lblPrecioRenta);

	    txtPrecioRenta = new JTextField(String.valueOf(videogames.getPrecioRenta()));
	    txtPrecioRenta.setBounds(55, 335, 180, 25);
	    panelCentral.add(txtPrecioRenta);

	    // Sección central (Detalles técnicos)
	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPlataforma.setBounds(300, 130, 180, 20);
	    panelCentral.add(lblPlataforma);

	    txtPlataforma = new JTextField(videogames.getPlataforma());
	    txtPlataforma.setBounds(300, 155, 200, 25);
	    panelCentral.add(txtPlataforma);

	    JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
	    lblDisponibilidad.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDisponibilidad.setBounds(250, 190, 180, 20);
	    panelCentral.add(lblDisponibilidad);

	    // CheckBox para disponibilidad
	    JCheckBox chkDisponible = new JCheckBox("Disponible");
	    chkDisponible.setSelected(videogames.isDisponibilidad());
	    chkDisponible.setBounds(250, 215, 100, 25);
	    panelCentral.add(chkDisponible);

	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblClasificacion.setBounds(390, 190, 180, 20);
	    panelCentral.add(lblClasificacion);

	    txtClasificacion = new JTextField(videogames.getClasificacion());
	    txtClasificacion.setBounds(390, 215, 130, 25);
	    panelCentral.add(txtClasificacion);

	    JLabel lblExistencias = new JLabel("Existencias disponibles:");
	    lblExistencias.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblExistencias.setBounds(300, 250, 180, 20);
	    panelCentral.add(lblExistencias);

	    txtExistencias = new JTextField(String.valueOf(videogames.getExistenciasDisponibles()));
	    txtExistencias.setBounds(300, 275, 200, 25);
	    panelCentral.add(txtExistencias);

	    JLabel lblPrecioVenta = new JLabel("Precio venta (MXN):");
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioVenta.setBounds(300, 310, 180, 20);
	    panelCentral.add(lblPrecioVenta);

	    txtPrecioVenta = new JTextField(String.valueOf(videogames.getPrecioVenta()));
	    txtPrecioVenta.setBounds(300, 335, 200, 25);
	    panelCentral.add(txtPrecioVenta);

	    // Sección derecha (Información adicional)
	    JLabel lblDesarrollador = new JLabel("Desarrollado por:");
	    lblDesarrollador.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDesarrollador.setBounds(550, 130, 180, 20);
	    panelCentral.add(lblDesarrollador);

	    txtDescripcion = new JTextField(videogames.getDesarrolladoPor());
	    txtDescripcion.setBounds(550, 155, 340, 25);
	    panelCentral.add(txtDescripcion);

	    JLabel lblAcercaDe = new JLabel("Descripción:");
	    lblAcercaDe.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAcercaDe.setBounds(550, 190, 180, 20);
	    panelCentral.add(lblAcercaDe);

	    // Usando JTextArea con JScrollPane para la descripción
	    JTextArea txtAreaDescripcion = new JTextArea(videogames.getDescripcion());
	    txtAreaDescripcion.setLineWrap(true);
	    txtAreaDescripcion.setWrapStyleWord(true);
	    JScrollPane scrollDescripcion = new JScrollPane(txtAreaDescripcion);
	    scrollDescripcion.setBounds(550, 215, 340, 150);
	    panelCentral.add(scrollDescripcion);

	    // Botones
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBackground(Color.decode("#B82F2F"));
	    btnCancelar.setForeground(Color.WHITE);
	    btnCancelar.setBounds(300, 420, 150, 30);
	    btnCancelar.addActionListener(e -> {
	        dispose();
	        VideogamesController vc = new VideogamesController();
	        vc.updateVideogames(videogames.getId());
	    });
	    panelCentral.add(btnCancelar);

	    JButton btnConfirmar = new JButton("Confirmar Cambios");
	    btnConfirmar.setBackground(Color.decode("#263C54"));
	    btnConfirmar.setForeground(Color.WHITE);
	    btnConfirmar.setBounds(550, 420, 150, 30);
	    btnConfirmar.addActionListener(e -> {
	        // Resetear todos los bordes a su estado normal
	        txtNombreJuego.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtPlataforma.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtClasificacion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtExistencias.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        scrollDescripcion.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	        // Validar campos vacíos
	        boolean camposValidos = true;
	        StringBuilder mensajeError = new StringBuilder();

	        if (txtNombreJuego.getText().trim().isEmpty()) {
	            txtNombreJuego.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Nombre del juego es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtPlataforma.getText().trim().isEmpty()) {
	            txtPlataforma.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Plataforma es obligatoria\n");
	            camposValidos = false;
	        }
	        
	        if (txtAnioLanzamiento.getText().trim().isEmpty()) {
	            txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Año de lanzamiento es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtClasificacion.getText().trim().isEmpty()) {
	            txtClasificacion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Clasificación es obligatoria\n");
	            camposValidos = false;
	        }
	        
	        if (txtExistencias.getText().trim().isEmpty()) {
	            txtExistencias.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Existencias disponibles es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtPrecioRenta.getText().trim().isEmpty()) {
	            txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Precio de renta es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtPrecioVenta.getText().trim().isEmpty()) {
	            txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Precio de venta es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtDescripcion.getText().trim().isEmpty()) {
	            txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	            mensajeError.append("• Desarrollado por es obligatorio\n");
	            camposValidos = false;
	        }
	        
	        if (txtAreaDescripcion.getText().trim().isEmpty()) {
	            scrollDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
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
	            int AñoLanzamiento = Integer.parseInt(txtAnioLanzamiento.getText().trim());
	            int ExistenciasDisponibles = Integer.parseInt(txtExistencias.getText().trim());
	            BigDecimal PrecioRenta = new BigDecimal(txtPrecioRenta.getText().trim());
	            BigDecimal PrecioVenta = new BigDecimal(txtPrecioVenta.getText().trim());
	            
	            // Validaciones adicionales
	            if (AñoLanzamiento <= 0) {
	                txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	                throw new NumberFormatException("El año de lanzamiento debe ser un número positivo");
	            }
	            
	            if (ExistenciasDisponibles < 0) {
	                txtExistencias.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	                throw new NumberFormatException("Las existencias no pueden ser negativas");
	            }
	            
	            if (PrecioRenta.compareTo(BigDecimal.ZERO) <= 0) {
	                txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	                throw new NumberFormatException("El precio de renta debe ser mayor a cero");
	            }
	            
	            if (PrecioVenta.compareTo(BigDecimal.ZERO) <= 0) {
	                txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	                throw new NumberFormatException("El precio de venta debe ser mayor a cero");
	            }

	            // Obtener valores validados
	            String Nombre = txtNombreJuego.getText().trim();
	            String Plataforma = txtPlataforma.getText().trim();
	            boolean Disponibilidad = chkDisponible.isSelected();
	            String Clasificacion = txtClasificacion.getText().trim();
	            String Genero = (String) comboGenero.getSelectedItem(); // Obteniendo el género del ComboBox
	            String DesarrolladoPor = txtDescripcion.getText().trim();
	            String Descripcion = txtAreaDescripcion.getText().trim();

	            // Actualizar el videojuego
	            VideoGamesModel vm = new VideoGamesModel();
	            boolean actualizado = vm.updateVideogame(videogames.getId(), Nombre, Plataforma, AñoLanzamiento, 
	                    Disponibilidad, Clasificacion, ExistenciasDisponibles, 
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
	
	public void AgregarJuego() {
	    // Componentes de entrada
	    JTextField txtNombre = new JTextField();
	    JTextField txtPlataforma = new JTextField();
	    JTextField txtAnioLanzamiento = new JTextField();
	    JCheckBox chkDisponible = new JCheckBox("Disponible");
	    JTextField txtClasificacion = new JTextField();
	    JTextField txtGenero = new JTextField();
	    JTextField txtExistencias = new JTextField();
	    JTextField txtPrecioRenta = new JTextField();
	    JTextField txtPrecioVenta = new JTextField();
	    JTextField txtDesarrollador = new JTextField();
	    JTextArea txtDescripcion = new JTextArea();
	    
	    // Configuración básica de la ventana
	    setTitle("Agregar Videojuego");
	    setSize(1024, 576);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);

	    // Panel principal con capas
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setPreferredSize(new Dimension(1024, 576));
	    setContentPane(layeredPane);

	    // Panel central gris
	    JPanel panelCentral = new JPanel();
	    panelCentral.setLayout(null);
	    panelCentral.setBackground(Color.decode("#F2F2F2"));
	    panelCentral.setBounds(5, 62, 1014, 509);
	    layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

	    // Logotipo
	    ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
	    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	    JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
	    logo.setBounds(477, 11, 70, 70);
	    panelCentral.add(logo);

	    // Título
	    JLabel lblTitulo = new JLabel("AGREGAR NUEVO VIDEOJUEGO");
	    lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
	    lblTitulo.setBounds(350, 78, 350, 42);
	    panelCentral.add(lblTitulo);

	    // Sección izquierda (Datos básicos)
	    JLabel lblNombre = new JLabel("Nombre del Videojuego:");
	    lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblNombre.setBounds(55, 130, 180, 20);
	    panelCentral.add(lblNombre);

	    txtNombre.setBounds(55, 155, 230, 25);
	    panelCentral.add(txtNombre);
	    
	    JLabel lblPrecioRenta = new JLabel("Precio renta (MXN):");
	    lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioRenta.setBounds(55, 310, 180, 20);
	    panelCentral.add(lblPrecioRenta);

	    txtPrecioRenta.setBounds(55, 335, 180, 25);
	    panelCentral.add(txtPrecioRenta);


	    // Sección central (Detalles técnicos)
	    JLabel lblPlataforma = new JLabel("Plataforma:");
	    lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPlataforma.setBounds(300, 130, 180, 20);
	    panelCentral.add(lblPlataforma);

	    txtPlataforma.setBounds(300, 155, 200, 25);
	    panelCentral.add(txtPlataforma);
	    
	    JLabel lblAnio = new JLabel("Año de lanzamiento:");
	    lblAnio.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblAnio.setBounds(55, 190, 180, 20);
	    panelCentral.add(lblAnio);

	    txtAnioLanzamiento.setBounds(55, 215, 150, 25);
	    panelCentral.add(txtAnioLanzamiento);

	    JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
	    lblDisponibilidad.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDisponibilidad.setBounds(250, 190, 180, 20);
	    panelCentral.add(lblDisponibilidad);

	    chkDisponible.setBounds(250, 215, 100, 25);
	    chkDisponible.setSelected(true);
	    panelCentral.add(chkDisponible);

	    JLabel lblClasificacion = new JLabel("Clasificación:");
	    lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblClasificacion.setBounds(390, 190, 180, 20);
	    panelCentral.add(lblClasificacion);

	    txtClasificacion.setBounds(390, 215, 130, 25);
	    panelCentral.add(txtClasificacion);
	    
	    JLabel lblGenero = new JLabel("Género:");
	    lblGenero.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblGenero.setBounds(55, 250, 180, 20);
	    panelCentral.add(lblGenero);

	    txtGenero.setBounds(55, 275, 180, 25);
	    panelCentral.add(txtGenero);


	    JLabel lblExistencias = new JLabel("Existencias disponibles:");
	    lblExistencias.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblExistencias.setBounds(300, 250, 180, 20);
	    panelCentral.add(lblExistencias);

	    txtExistencias.setBounds(300, 275, 200, 25);
	    panelCentral.add(txtExistencias);
	    
	    JLabel lblPrecioVenta = new JLabel("Precio venta (MXN):");
	    lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblPrecioVenta.setBounds(300, 310, 180, 20);
	    panelCentral.add(lblPrecioVenta);

	    txtPrecioVenta.setBounds(300, 335, 200, 25);
	    panelCentral.add(txtPrecioVenta);

	    // Sección derecha (Información adicional)
	    JLabel lblDesarrollador = new JLabel("Desarrollado por:");
	    lblDesarrollador.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDesarrollador.setBounds(550, 130, 180, 20);
	    panelCentral.add(lblDesarrollador);

	    txtDesarrollador.setBounds(550, 155, 340, 25);
	    panelCentral.add(txtDesarrollador);

	    JLabel lblDescripcion = new JLabel("Descripción:");
	    lblDescripcion.setFont(new Font("Calibri", Font.BOLD, 14));
	    lblDescripcion.setBounds(550, 190, 180, 20);
	    panelCentral.add(lblDescripcion);

	    txtDescripcion.setLineWrap(true);
	    txtDescripcion.setWrapStyleWord(true);
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    scrollDescripcion.setBounds(550, 215, 340, 150);
	    panelCentral.add(scrollDescripcion);

	    // Botones
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(300, 420, 150, 30);
		btnCancelar.addActionListener(e -> {
			dispose();
			AdministradorJuegos();
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Agregar Juego");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(550, 420, 150, 30);
		btnConfirmar.addActionListener(e -> {
		    // Resetear todos los bordes a su estado normal
		    txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtPlataforma.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtClasificacion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtGenero.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtExistencias.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtDesarrollador.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		    StringBuilder mensajeError = new StringBuilder();

		 // Validaciones, cada mensaje dentro del if correspondiente
		 if (txtNombre.getText().trim().isEmpty()) {
		     txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Nombre es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtPlataforma.getText().trim().isEmpty()) {
		     txtPlataforma.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Plataforma es obligatoria.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtAnioLanzamiento.getText().trim().isEmpty()) {
		     txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Año de lanzamiento es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtClasificacion.getText().trim().isEmpty()) {
		     txtClasificacion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Clasificación es obligatoria.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtGenero.getText().trim().isEmpty()) {
		     txtGenero.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Género es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtExistencias.getText().trim().isEmpty()) {
		     txtExistencias.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Existencias es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtPrecioRenta.getText().trim().isEmpty()) {
		     txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Precio de renta es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtPrecioVenta.getText().trim().isEmpty()) {
		     txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Precio de venta es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtDesarrollador.getText().trim().isEmpty()) {
		     txtDesarrollador.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Desarrollador es obligatorio.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }

		 if (txtDescripcion.getText().trim().isEmpty()) {
		     txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		     mensajeError.append("• Descripción es obligatoria.\n");
		     JOptionPane.showMessageDialog(null, mensajeError.toString(), "Campos obligatorios", JOptionPane.ERROR_MESSAGE);
		     return;
		 }


		    try {
		        // Validaciones numéricas
		        int año = Integer.parseInt(txtAnioLanzamiento.getText().trim());
		        int existencias = Integer.parseInt(txtExistencias.getText().trim());
		        BigDecimal precioRenta = new BigDecimal(txtPrecioRenta.getText().trim());
		        BigDecimal precioVenta = new BigDecimal(txtPrecioVenta.getText().trim());
		        
		        // Validaciones adicionales
		        if (año <= 0) {
		            txtAnioLanzamiento.setBorder(BorderFactory.createLineBorder(Color.RED));
		            throw new NumberFormatException("El año de lanzamiento debe ser un número positivo");
		        }
		        
		        if (existencias < 0) {
		            txtExistencias.setBorder(BorderFactory.createLineBorder(Color.RED));
		            throw new NumberFormatException("Las existencias no pueden ser negativas");
		        }
		        
		        if (precioRenta.compareTo(BigDecimal.ZERO) <= 0) {
		            txtPrecioRenta.setBorder(BorderFactory.createLineBorder(Color.RED));
		            throw new NumberFormatException("El precio de renta debe ser mayor a cero");
		        }
		        
		        if (precioVenta.compareTo(BigDecimal.ZERO) <= 0) {
		            txtPrecioVenta.setBorder(BorderFactory.createLineBorder(Color.RED));
		            throw new NumberFormatException("El precio de venta debe ser mayor a cero");
		        }

		        boolean disponibilidad = chkDisponible.isSelected();
		        String nombre = txtNombre.getText().trim();
		        String plataforma = txtPlataforma.getText().trim();
		        String clasificacion = txtClasificacion.getText().trim();
		        String genero = txtGenero.getText().trim();
		        String desarrollador = txtDesarrollador.getText().trim();
		        String descripcion = txtDescripcion.getText().trim();

		        VideoGamesModel modelo = new VideoGamesModel();
		        boolean exito = modelo.addVideogame(nombre, plataforma, año, disponibilidad, clasificacion, genero,
		                existencias, precioRenta, precioVenta, desarrollador, descripcion);

		        if (exito) {
		            JOptionPane.showMessageDialog(this, "Juego agregado exitosamente");
		            dispose();
		            VideogamesController vc = new VideogamesController();
		            vc.indexVideoGames();
		        } else {
		            JOptionPane.showMessageDialog(this, "Error al agregar el juego", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Error en formato numérico: " + ex.getMessage(), "Error",
		                JOptionPane.ERROR_MESSAGE);
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		});
		panelCentral.add(btnConfirmar);
		// Barra superior roja
		JPanel barraSuperior = new JPanel();
		barraSuperior.setBackground(Color.decode("#B44635"));
		barraSuperior.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraSuperior, JLayeredPane.PALETTE_LAYER);

	    setVisible(true);
	}
	
}

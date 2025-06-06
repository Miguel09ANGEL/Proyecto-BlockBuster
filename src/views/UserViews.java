package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;

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
import com.itextpdf.text.pdf.PdfAcroForm;
import com.itextpdf.text.pdf.PdfWriter;

import controller.UserController;
import controller.VideogamesController;
import models.Transaction;
import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;

public class UserViews extends JFrame {

	public UserViews() {
		// TODO Auto-generated constructor stub
	}

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
				VideogamesView vv = new VideogamesView();
				vv.AdministradorJuegos(); // Abre la segunda ventana
			});

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
//				RegistroClientes(); // Abre la segunda ventana
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
				
				UserViews uv = new UserViews();
				uv.AgregarCliente();
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
		
		public void RegistroClientes(List<User> usuarios) {

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
				PromotionsView pv = new PromotionsView();
				pv.NuevaOperacion(); // Abre la segunda ventana
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
			
//			JButton btnBuscar = new JButton("Buscar");
//			btnBuscar.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//			btnBuscar.setBounds(619, 25, 86, 25);
//			panelCentral.add(btnBuscar);
//			
//			JTextField Buscador = new JTextField();
//			Buscador.setFont(new Font("League Spartan Light", Font.PLAIN, 14));
//			Buscador.setBounds(385, 25,220, 25);
//			panelCentral.add(Buscador);

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
			        usuario.getNombre(),
			        usuario.getApellidoPaterno(),
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

		public void DetallesCliente(User user, List<Transaction> transacciones, List<Transaction> misVentas) {
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
			iniciar.setLocation(334, 12);
			iniciar.setHorizontalAlignment(JLabel.CENTER);
			iniciar.setFont(new Font("Anton", Font.BOLD, 20));
			panelCentral.add(iniciar);

			ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Block.png"));
			Image imagen = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			
			JLabel Identificador = new JLabel("Identificador:");
			Identificador.setHorizontalAlignment(SwingConstants.LEFT);
			Identificador.setFont(new Font("Calibri", Font.BOLD, 18));
			Identificador.setBounds(50, 54, 110, 42);
			panelCentral.add(Identificador);

			JLabel iniciar_1_1_1_3 = new JLabel(""+user.getId());
			iniciar_1_1_1_3.setForeground(Color.decode("#3B3741"));
			iniciar_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_1_3.setBounds(39, 79, 103, 42);
			panelCentral.add(iniciar_1_1_1_3);

			// botones
			JButton btnEditar = new JButton("EDITAR");
			btnEditar.setBackground(Color.decode("#6D91B9"));
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					UserController uc = new UserController();
					uc.update(user.getId());
//					EditarCliente();
					dispose();
				}
			});

			btnEditar.setForeground(Color.WHITE);
			btnEditar.setBounds(398, 403, 183, 33);
			panelCentral.add(btnEditar);

			JButton btnConfirmar = new JButton("INFORMACION (PDF)");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					UserController uc = new UserController();
					uc.update3(user.getId());
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
			
			JLabel HistorialRentas = new JLabel("Historial de rentas:");
			HistorialRentas.setHorizontalAlignment(SwingConstants.LEFT);
			HistorialRentas.setFont(new Font("Calibri", Font.BOLD, 18));
			HistorialRentas.setBounds(250, 140, 150, 42);
			panelCentral.add(HistorialRentas);
			
			// Crear unan tabla
			String[] columnNames = { "ID", "Juego", "Fecha de Renta", "Fecha de Devolución", "Precio" };
			DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};

			for (Transaction t : transacciones) {

			    Object[] rowData = {
			        t.getId(),
			        t.getVideoGameName(),
			        t.getTransactionDate(),
			        t.getReturnDate() != null ? t.getReturnDate() : "No devuelto",
			        "$" + t.getPrice()
			    };
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
			scrollPane.setBounds(28, 170, 606, 210);
			panelCentral.add(scrollPane);
			
			JLabel HistorialVentas = new JLabel("Historial de ventas:");
			HistorialVentas.setHorizontalAlignment(SwingConstants.LEFT);
			HistorialVentas.setFont(new Font("Calibri", Font.BOLD, 18));
			HistorialVentas.setBounds(750, 140, 150, 42);
			panelCentral.add(HistorialVentas);
			
			String[] purchaseColumnNames = {"Juego", "Fecha de Compra", "Precio" };
			DefaultTableModel purchaseModel = new DefaultTableModel(purchaseColumnNames, 0) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};

			for (Transaction sale : misVentas) {
			    Object[] rowData = {
			        sale.getVideoGameName(),
			        sale.getTransactionDate(),
			        "$" + sale.getPrice()
			    };
			    purchaseModel.addRow(rowData);
			}

			JTable purchasesTable = new JTable(purchaseModel);
			purchasesTable.setFont(new Font("Arial", Font.PLAIN, 14));
			purchasesTable.setRowHeight(25);
			purchasesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
			purchasesTable.setAutoCreateRowSorter(true);

			// Agregar la tabla de compras a un JScrollPane
			JScrollPane purchasesScrollPane = new JScrollPane(purchasesTable);
			purchasesScrollPane.setBounds(680, 170, 280, 210); 
			panelCentral.add(purchasesScrollPane); 
			
			

			JLabel iniciar_1_1_2_1_1 = new JLabel("Nombre:");
			iniciar_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			iniciar_1_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_2_1_1.setBounds(240, 54, 97, 42);
			panelCentral.add(iniciar_1_1_2_1_1);

			JLabel iniciar_1_1_1_3_1 = new JLabel(user.getNombre() + " " + user.getApellidoPaterno() + " " + user.getApellidoMaterno());
			iniciar_1_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_1_3_1.setForeground(new Color(59, 55, 65));
			iniciar_1_1_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_1_3_1.setBounds(175, 79, 196, 42);
			panelCentral.add(iniciar_1_1_1_3_1);

			JLabel iniciar_1_1_2_1_1_1 = new JLabel("Correo:");
			iniciar_1_1_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			iniciar_1_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_2_1_1_1.setBounds(490, 54, 97, 42);
			panelCentral.add(iniciar_1_1_2_1_1_1);

			JLabel iniciar_1_1_1_3_1_1 = new JLabel(user.getCorreo());
			iniciar_1_1_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_1_3_1_1.setForeground(new Color(59, 55, 65));
			iniciar_1_1_1_3_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_1_3_1_1.setBounds(410, 79, 250, 42);
			panelCentral.add(iniciar_1_1_1_3_1_1);

			JLabel iniciar_1_1_2_1_1_1_1 = new JLabel("Telefono:");
			iniciar_1_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			iniciar_1_1_2_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_2_1_1_1_1.setBounds(767, 54, 97, 42);
			panelCentral.add(iniciar_1_1_2_1_1_1_1);

			JLabel iniciar_1_1_1_3_1_1_1 = new JLabel(user.getTelefono());
			iniciar_1_1_1_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_1_3_1_1_1.setForeground(new Color(59, 55, 65));
			iniciar_1_1_1_3_1_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_1_1_3_1_1_1.setBounds(733, 79, 145, 42);
			panelCentral.add(iniciar_1_1_1_3_1_1_1);

			// 3. PANEL ROJO SUPERIOR (barra de título)
			JPanel barraRoja = new JPanel();
			barraRoja.setBackground(Color.decode("#B44635"));
			barraRoja.setBounds(0, 0, 1024, 60);
			layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

			setVisible(true);
		}

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
			panelCentral.setBounds(32, 62, 951, 475); 
			layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

			// Logotipo
			ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
			Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
			logo.setBounds(450, 11, 70, 70); 
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

			JButton btnCancelar = new JButton("CANCELAR");
			btnCancelar.setBackground(Color.decode("#B82F2F")); 
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBounds(103, 406, 183, 33);
			btnCancelar.addActionListener(e -> {
				dispose(); // Cierra la ventana actual
				UserController uc = new UserController();
				uc.update2(user.getId());
			});
			panelCentral.add(btnCancelar);

			JButton btnConfirmar = new JButton("DESCARGAR PDF");
			btnConfirmar.setBackground(Color.decode("#263C54")); 
			btnConfirmar.setForeground(Color.WHITE);
			btnConfirmar.setBounds(621, 406, 183, 33);
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Guardar archivo PDF");

					
					fileChooser.setSelectedFile(new File("Informacion_Cliente_" + user.getNombre() + ".pdf"));

					int userSelection = fileChooser.showSaveDialog(null);

					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						Document document = new Document();

						try {
							PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
							document.open();
							Paragraph clienteHeader = new Paragraph("DETALLES DEL CLIENTE\n", 
				                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
				            document.add(clienteHeader);
							document.add(new Paragraph("ID: " + user.getId()));
							document.add(new Paragraph("Nombre: " + user.getNombre() + " " + user.getApellidoPaterno()+" "+ user.getApellidoMaterno()));
							document.add(new Paragraph("Correo: " + user.getCorreo()));
							document.add(new Paragraph("Teléfono: " + user.getTelefono()));
			                document.add(Chunk.NEWLINE);

							Paragraph footer = new Paragraph(
			                        "Videojuegos Rentables S.A. de C.V.\n" +
			                        "Tel: 555-123-4567 | contacto@rentables.com",
			                        FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10));
			                    footer.setAlignment(Element.ALIGN_CENTER);
			                    document.add(footer);

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
			panelCentral.add(btnConfirmar);

			JButton btnCredencialpdf = new JButton("CREDENCIAL (PDF)");
			btnCredencialpdf.setForeground(Color.WHITE);
			btnCredencialpdf.setBackground(Color.decode("#686868"));
			btnCredencialpdf.setBounds(370, 406, 183, 33);
			btnCredencialpdf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Guardar archivo PDF");

					
					fileChooser.setSelectedFile(new File("Credencial_Cliente_" + user.getNombre() + ".pdf"));

					int userSelection = fileChooser.showSaveDialog(null);

					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						Document document = new Document();

						try {
							PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
							document.open();
							Paragraph clienteHeader = new Paragraph("TARJETA DE CLIENTE\n", 
				                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
				            document.add(clienteHeader);
							document.add(new Paragraph("ID: " + user.getId()));
							document.add(new Paragraph("Nombre: " + user.getNombre() + " " + user.getApellidoPaterno()+" "+ user.getApellidoMaterno()));
							document.add(new Paragraph("Correo: " + user.getCorreo()));

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
			panelCentral.add(btnCredencialpdf);

			JLabel iniciar_1_1 = new JLabel(user.getNombre()+" "+user.getApellidoPaterno());
			iniciar_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
			iniciar_1_1.setBounds(58, 177, 141, 42);
			panelCentral.add(iniciar_1_1);

			JLabel iniciar_1_2 = new JLabel("Correo:");
			iniciar_1_2.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_2.setBounds(84, 262, 87, 42);
			panelCentral.add(iniciar_1_2);

			JLabel iniciar_1_1_1 = new JLabel(user.getCorreo());
			iniciar_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
			iniciar_1_1_1.setBounds(58, 287, 141, 42);
			panelCentral.add(iniciar_1_1_1);

			JLabel iniciar_1_3 = new JLabel("Fecha de nacimineto:");
			iniciar_1_3.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_3.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_3.setBounds(384, 150, 183, 42);
			panelCentral.add(iniciar_1_3);

			JLabel iniciar_1_1_2 = new JLabel(""+user.getFechaNacimiento());
			iniciar_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_2.setFont(new Font("Calibri", Font.BOLD, 14));
			iniciar_1_1_2.setBounds(394, 177, 141, 42);
			panelCentral.add(iniciar_1_1_2);

			JLabel iniciar_1_2_1 = new JLabel("Numero de control:");
			iniciar_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_2_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_2_1.setBounds(397, 262, 170, 42);
			panelCentral.add(iniciar_1_2_1);

			JLabel iniciar_1_1_1_1 = new JLabel(""+user.getId());
			iniciar_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
			iniciar_1_1_1_1.setBounds(394, 287, 141, 42);
			panelCentral.add(iniciar_1_1_1_1);

			JLabel iniciar_1_3_1 = new JLabel("Numero de telefono: ");
			iniciar_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_3_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_3_1.setBounds(695, 150, 177, 42);
			panelCentral.add(iniciar_1_3_1);

			JLabel iniciar_1_1_2_1 = new JLabel(user.getTelefono());
			iniciar_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_1_2_1.setFont(new Font("Calibri", Font.BOLD, 14));
			iniciar_1_1_2_1.setBounds(709, 177, 141, 42);
			panelCentral.add(iniciar_1_1_2_1);

			JLabel iniciar_1_2_1_1 = new JLabel("Fecha de registro:");
			iniciar_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
			iniciar_1_2_1_1.setBounds(709, 262, 163, 42);
			panelCentral.add(iniciar_1_2_1_1);

			JLabel iniciar_1_1_1_1_1 = new JLabel(""+user.getCreatedAt());
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
			txtNombre.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char nombre = e.getKeyChar();
					if (!Character.isLetter(nombre) || txtNombre.getText().length() >= 10) {
						e.consume();
					}
				}
			});
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
			txtApellidoMaterno.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char am = e.getKeyChar();
					if (!Character.isLetter(am) || txtApellidoMaterno.getText().length() >= 10) {
						e.consume();
					}
				}
			});
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
			txtTelefono.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char numero = e.getKeyChar();
					if (!Character.isLetter(numero) || txtTelefono.getText().length() >= 10) {
						e.consume();
					}
				}
			});
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
			txtApellidoPaterno.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char ap = e.getKeyChar();
					if (!Character.isLetter(ap) || txtApellidoPaterno.getText().length() >= 10) {
						e.consume();
					}
				}
			});
			panelCentral.add(txtApellidoPaterno);

			// Fecha de nacimiento con JDatePicker
			JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
			lblFechaNac.setBounds(596, 190, 136, 42); // Ajusté la posición Y
			lblFechaNac.setFont(new Font("Calibri", Font.BOLD, 14));
			panelCentral.add(lblFechaNac);

			// Configuración del modelo
			UtilDateModel model = new UtilDateModel();
			if (user.getFechaNacimiento() != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(user.getFechaNacimiento());
				model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			}
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

			datePicker.setBounds(596, 236, 330, 27);
			panelCentral.add(datePicker);

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
				UserController us = new UserController();
				
				us.update2(user.getId());
				
			});
			panelCentral.add(btnCancelar);

			JButton btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(533, 406, 183, 33);
			btnConfirmar.setBackground(Color.decode("#263C54"));
			btnConfirmar.setForeground(Color.WHITE);
			btnConfirmar.addActionListener(e -> {
			    // Resetear bordes a su estado normal
			    txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    txtApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    txtTelefono.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    txtCorreo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    datePicker.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    
			    // Obtener valores desde los campos de texto
			    String nuevoNombre = txtNombre.getText().trim();
			    String nuevoApellidoPaterno = txtApellidoPaterno.getText().trim();
			    String nuevoApellidoMaterno = txtApellidoMaterno.getText().trim();
			    String nuevoTelefono = txtTelefono.getText().trim();
			    String nuevoCorreo = txtCorreo.getText().trim();

			    boolean camposValidos = true;
			    
			    // Validar campos obligatorios
			    if (nuevoNombre.isEmpty()) {
			        txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(panelCentral, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;
			    }
			    
			    if (nuevoApellidoPaterno.isEmpty()) {
			        txtApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(panelCentral, "El apellido paterno es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    }
			    
			    if (nuevoTelefono.isEmpty()) {
			        txtTelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(panelCentral, "El teléfono es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    }
			    
			    if (nuevoCorreo.isEmpty()) {
			        txtCorreo.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(panelCentral, "El correo electrónico es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    }
			    
			    java.sql.Date nuevaFechaNac = null;
			    if (model.getValue() == null) {
			        datePicker.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(panelCentral, "La fecha de nacimiento es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    } else {
			        nuevaFechaNac = new java.sql.Date(model.getValue().getTime());
			    }
			    
			    // Si hay campos inválidos, no continuar con la actualización
			    if (!camposValidos) {
			        return;
			    }

			    // Llamar al método del modelo para actualizar
			    UsersModel us = new UsersModel();
			    boolean actualizado = us.update(user.getId(), nuevoNombre, nuevoApellidoPaterno, nuevoApellidoMaterno,
			            nuevaFechaNac, nuevoTelefono, nuevoCorreo);

			    if (actualizado) {
			        JOptionPane.showMessageDialog(panelCentral, "Cliente actualizado correctamente", "Éxito",
			                JOptionPane.INFORMATION_MESSAGE);
			        dispose();
			        UserController us1 = new UserController();
			        us1.update2(user.getId());
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
		
		public void AgregarCliente() {

			// Campos de texto para la entrada de datos
			JTextField textFieldNombre;
			JTextField textFieldApellidoMaterno;
			JTextField textFieldTelefono;
			JTextField textFieldApellidoPaterno;
			JTextField textFieldFechaNacimiento;
			JTextField textFieldCorreo;

			// Configuración básica de la ventana
			setTitle("Agregar Cliente");
			setSize(1024, 576);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);

			// Usamos JLayeredPane para superponer componentes
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(900, 650));
			setContentPane(layeredPane);

			// Panel gris central donde van los formularios y botones
			JPanel panelCentral = new JPanel();
			panelCentral.setLayout(null);
			panelCentral.setBackground(Color.decode("#F2F2F2"));
			panelCentral.setBounds(5, 62, 998, 475);
			layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

			// Logotipo centrado arriba
			ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
			Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
			logo.setBounds(465, 11, 70, 70);
			panelCentral.add(logo);

			// Título
			JLabel titulo = new JLabel("AGREGAR CLIENTE");
			titulo.setSize(263, 42);
			titulo.setLocation(370, 94);
			titulo.setHorizontalAlignment(JLabel.CENTER);
			titulo.setFont(new Font("Calibri", Font.BOLD, 24));
			panelCentral.add(titulo);

			// Etiqueta y campo para Nombre
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
			lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
			lblNombre.setBounds(84, 135, 87, 42);
			panelCentral.add(lblNombre);

			textFieldNombre = new JTextField();
			textFieldNombre.setBackground(Color.decode("#D9D9D9"));
			textFieldNombre.setBounds(84, 163, 330, 27);
			textFieldNombre.setColumns(10);
			textFieldNombre.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char nombre = e.getKeyChar();
					if (!Character.isLetter(nombre) || textFieldNombre.getText().length() >= 20) {
						e.consume();
					}
				}
			});
			panelCentral.add(textFieldNombre);

			

			// Etiqueta y campo para Apellido materno
			JLabel lblApellidoMaterno = new JLabel("Apellido materno:");
			lblApellidoMaterno.setHorizontalAlignment(SwingConstants.LEFT);
			lblApellidoMaterno.setFont(new Font("Calibri", Font.BOLD, 14));
			lblApellidoMaterno.setBounds(84, 209, 115, 42);
			panelCentral.add(lblApellidoMaterno);

			textFieldApellidoMaterno = new JTextField();
			textFieldApellidoMaterno.setColumns(10);
			textFieldApellidoMaterno.setBackground(new Color(217, 217, 217));
			textFieldApellidoMaterno.setBounds(84, 236, 330, 27);
			textFieldApellidoMaterno.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char am = e.getKeyChar();
					if (!Character.isLetter(am) || textFieldApellidoMaterno.getText().length() >= 10) {
						e.consume();
					}
				}
			});
			panelCentral.add(textFieldApellidoMaterno);

			// Etiqueta y campo para Teléfono
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
			lblTelefono.setFont(new Font("Calibri", Font.BOLD, 14));
			lblTelefono.setBounds(84, 282, 87, 42);
			panelCentral.add(lblTelefono);

			textFieldTelefono = new JTextField();
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setBackground(new Color(217, 217, 217));
			textFieldTelefono.setBounds(84, 311, 330, 27);
			textFieldTelefono.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char numero = e.getKeyChar();
					if (!Character.isDigit(numero) || textFieldTelefono.getText().length() >= 10) {
						e.consume();
					}
				}
			}); 
			panelCentral.add(textFieldTelefono);

			// Etiqueta y campo para Apellido paterno
			JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
			lblApellidoPaterno.setHorizontalAlignment(SwingConstants.LEFT);
			lblApellidoPaterno.setFont(new Font("Calibri", Font.BOLD, 14));
			lblApellidoPaterno.setBounds(594, 135, 122, 42);
			panelCentral.add(lblApellidoPaterno);

			textFieldApellidoPaterno = new JTextField();
			textFieldApellidoPaterno.setColumns(10);
			textFieldApellidoPaterno.setBackground(new Color(217, 217, 217));
			textFieldApellidoPaterno.setBounds(596, 163, 330, 27);
			textFieldApellidoPaterno.addKeyListener(new KeyAdapter() { /////////////Aqui sirve para solo colocar letras o numeros
				public void keyTyped(KeyEvent e) {
					char ap = e.getKeyChar();
					if (!Character.isLetter(ap) || textFieldApellidoPaterno.getText().length() >= 10) {
						e.consume();
					}
				}
			});
			panelCentral.add(textFieldApellidoPaterno);

			// Etiqueta y campo para Fecha de nacimiento
			JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
			lblFechaNacimiento.setFont(new Font("Calibri", Font.BOLD, 14));
			lblFechaNacimiento.setBounds(596, 209, 136, 42);
			panelCentral.add(lblFechaNacimiento);
			
			UtilDateModel model = new UtilDateModel();

			model.setDate(2000, 0, 1); 
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

			datePicker.setBounds(596, 236, 330, 27);
			panelCentral.add(datePicker);
			
			// Etiqueta y campo para Correo
			JLabel lblCorreo = new JLabel("Correo:");
			lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
			lblCorreo.setFont(new Font("Calibri", Font.BOLD, 14));
			lblCorreo.setBounds(596, 282, 87, 42);
			panelCentral.add(lblCorreo);

			textFieldCorreo = new JTextField();
			textFieldCorreo.setColumns(10);
			textFieldCorreo.setBackground(new Color(217, 217, 217));
			textFieldCorreo.setBounds(596, 311, 330, 27);
			panelCentral.add(textFieldCorreo);

			// Botón Cancelar
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(Color.decode("#B82F2F")); // Rojo
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBounds(175, 406, 183, 33);
			btnCancelar.addActionListener(e -> {
				dispose(); // Cierra esta ventana
				AdministradorCliente();
			});
			panelCentral.add(btnCancelar);

			// Botón Guardar
			JButton btnConfirmar = new JButton("Guardar");
			btnConfirmar.setBackground(Color.decode("#263C54")); // Azul oscuro
			btnConfirmar.setForeground(Color.WHITE);
			btnConfirmar.setBounds(533, 406, 183, 33);
			btnConfirmar.addActionListener(e -> {
			    // Resetear bordes a su estado normal
			    textFieldNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    textFieldApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    textFieldTelefono.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    textFieldCorreo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    datePicker.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			    
			    // Obtener valores desde los campos de texto
			    String nombre = textFieldNombre.getText().trim();
			    String apellidoPaterno = textFieldApellidoPaterno.getText().trim();
			    String apellidoMaterno = textFieldApellidoMaterno.getText().trim();
			    String telefono = textFieldTelefono.getText().trim();
			    String correo = textFieldCorreo.getText().trim();
			    
			    boolean camposValidos = true;
			    
			    // Validar campos obligatorios
			    if (nombre.isEmpty()) {
			        textFieldNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(null, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;
			    }
			    
			    if (apellidoPaterno.isEmpty()) {
			        textFieldApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(null, "El apellido paterno es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;
			    }
			    
			    if (telefono.isEmpty()) {
			        textFieldTelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(null, "El teléfono es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    }
			    
			    if (correo.isEmpty()) {
			        textFieldCorreo.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(null, "El correo electrónico es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    }
			    
			    java.sql.Date fechaNacimiento = null;
			    if (model.getValue() == null) {
			        datePicker.setBorder(BorderFactory.createLineBorder(Color.RED));
			        JOptionPane.showMessageDialog(null, "La fecha de nacimiento es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
			        camposValidos = false;
			        return;

			    } else {
			        fechaNacimiento = new java.sql.Date(model.getValue().getTime());
			    }
			    
			    // Si hay campos inválidos, no continuar con el guardado
			    if (!camposValidos) {
			        return;
			    }
			    
			    UsersModel um = new UsersModel();
			    // agregar el cliente
			    boolean agregado = um.add(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, telefono, correo);

			    if (agregado) {
			        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
			        dispose(); 
			        AdministradorCliente(); 
			    } else {
			        JOptionPane.showMessageDialog(null, "No se pudo agregar el cliente. Verifica que el correo no esté duplicado.", "Error", JOptionPane.ERROR_MESSAGE);
			    }
			});
			panelCentral.add(btnConfirmar);

			// Panel rojo superior (barra de título)
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

			// 2. PANEL GRIS CENTRAL
			JPanel panelCentral = new JPanel();
			panelCentral.setLayout(null);
			panelCentral.setBackground(Color.decode("#D9D9D9"));
			panelCentral.setBounds(270, 71, 486, 460); // (x, y, ancho, alto)
			layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

			// Logotipo
			ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
			Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
			logo.setBounds(213, 23, 70, 70); // posición
			panelCentral.add(logo);

			JLabel lblTitulo = new JLabel("INFORMACIÓN CLIENTE");
			lblTitulo.setSize(263, 42);
			lblTitulo.setLocation(121, 104);
			lblTitulo.setHorizontalAlignment(JLabel.CENTER);
			lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
			panelCentral.add(lblTitulo);

			JButton btnCancelar = new JButton("Regresar");
			btnCancelar.setBackground(Color.decode("#B82F2F")); // Color rojo
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBounds(47, 406, 183, 33);
			btnCancelar.addActionListener(e -> {
//				InformacionCliente(); // Abre la ventana anterior
				dispose(); // Cierra la ventana actual
			});
			panelCentral.add(btnCancelar);

			JButton btnDescargarPDF = new JButton("Descargar PDF");
			btnDescargarPDF.setBackground(Color.decode("#263C54")); // Color azul oscuro
			btnDescargarPDF.setForeground(Color.WHITE);
			btnDescargarPDF.setBounds(281, 406, 183, 33);
			btnDescargarPDF.addActionListener(e -> {
				// Confirmar_4(); // Aquí va la lógica para descargar PDF
			});
			panelCentral.add(btnDescargarPDF);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Calibri", Font.BOLD, 18));
			lblNombre.setBounds(96, 156, 107, 42);
			panelCentral.add(lblNombre);

			JLabel lblNombreValor = new JLabel("Manuel orozco vazquez");
			lblNombreValor.setHorizontalAlignment(SwingConstants.LEFT);
			lblNombreValor.setFont(new Font("Calibri", Font.BOLD, 18));
			lblNombreValor.setBounds(192, 157, 192, 42);
			panelCentral.add(lblNombreValor);

			JLabel lblNumeroControl = new JLabel("Numero de control:");
			lblNumeroControl.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumeroControl.setFont(new Font("Calibri", Font.BOLD, 18));
			lblNumeroControl.setBounds(121, 209, 161, 42);
			panelCentral.add(lblNumeroControl);

			JLabel lblNumeroControlValor = new JLabel("00001");
			lblNumeroControlValor.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumeroControlValor.setFont(new Font("Calibri", Font.BOLD, 18));
			lblNumeroControlValor.setBounds(244, 209, 131, 42);
			panelCentral.add(lblNumeroControlValor);

			JLabel lblCorreo = new JLabel("Correo:");
			lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
			lblCorreo.setBounds(123, 262, 107, 42);
			panelCentral.add(lblCorreo);

			JLabel lblCorreoValor = new JLabel("mov@gmail.com");
			lblCorreoValor.setHorizontalAlignment(SwingConstants.LEFT);
			lblCorreoValor.setFont(new Font("Calibri", Font.BOLD, 18));
			lblCorreoValor.setBounds(213, 262, 162, 42);
			panelCentral.add(lblCorreoValor);

			// 3. PANEL ROJO SUPERIOR (barra de título)
			JPanel barraRoja = new JPanel();
			barraRoja.setBackground(Color.decode("#B44635"));
			barraRoja.setBounds(0, 0, 1024, 60);
			layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

			setVisible(true);
		}
	
}

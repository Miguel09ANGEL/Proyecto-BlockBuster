package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.json.ParseException;

import controller.UserController;
import controller.VideogamesController;
import models.Admins;
import models.AuthModel;
import models.User;
import models.UsersModel;
import models.VideoGames;
import models.VideoGamesModel;

public class AuthViews extends JFrame {
	
	// se manda a llamar a la clase UsersModel
	UsersModel um = new UsersModel();

	public AuthViews() {
		// TODO Auto-generated constructor stub

	}
	
	public void login(List administrador) {

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

		JLabel correo = new JLabel("Ingresar su usuario:");
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
		contraseña.setSize(290, 26);
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
		        // Resetear estilos
		        gmail.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		        String emailIngresado = gmail.getText();
		        String passIngresada = new String(password.getPassword());

		        // Validaciones
		        if(emailIngresado.isEmpty()) {
		            gmail.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            JOptionPane.showMessageDialog(null, "Por favor ingrese su email", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if(passIngresada.isEmpty()) {
		            password.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            JOptionPane.showMessageDialog(null, "Por favor ingrese su contraseña", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Autenticación
		        AuthModel auth = new AuthModel();
		        Admins admin = auth.autenticar(emailIngresado, passIngresada);

		        if(admin != null) {
		            dispose();
		            HomeView hv = new HomeView();
		            hv.Inicio(); // Inicia la vista principal
		        } else {
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
	
	
	
	

	

// estos paneles estan pendientes
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
		logo.setBounds(156, 11, 70, 70);
		panelCentral.add(logo);

		// Título
		JLabel iniciar = new JLabel("PANEL ADMINISTRADOR");
		iniciar.setSize(250, 60);
		iniciar.setLocation(66, 75);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		// Etiqueta contraseña
		JLabel lblContraseñaRegistro = new JLabel("Ingresar contraseña de registro:");
		lblContraseñaRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseñaRegistro.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblContraseñaRegistro.setBounds(49, 133, 250, 26);
		panelCentral.add(lblContraseñaRegistro);

		// Campo contraseña
		JTextField txtContraseñaRegistro = new JTextField();
		txtContraseñaRegistro.setBorder(BorderFactory.createLineBorder(Color.decode("#10A7DE")));
		txtContraseñaRegistro.setBackground(Color.decode("#D9D9D9"));
		txtContraseñaRegistro.setSize(290, 46);
		txtContraseñaRegistro.setLocation(49, 170);
		txtContraseñaRegistro.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(txtContraseñaRegistro);

		// Botón acceder
		JButton acceder = new JButton("Acceder");
		acceder.setBackground(Color.decode("#263C54"));
		acceder.setForeground(Color.WHITE);
		acceder.setFont(new Font("Calibri", Font.BOLD, 15));
		acceder.setBounds(50, 317, 289, 26);
		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				RegistrarAdministrador(); // Abre la segunda ventana
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
		JTextField nombreField; // "Nombre:"
		JTextField apellidoMaternoField; // "Apellido materno:"
		JTextField apellidoPaternoField; // "Apellido paterno:"
		JTextField correoField; // "Ingresar correo:"
		JTextField contrasenaField; // "Contraseña:"
		JTextField confirmarContrasenaField; // "Confirmar contraseña:"

		// Configuración básica de la ventana
		setTitle("Panel Administrador");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// PANEL CENTRAL GRIS
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#F2F2F2"));
		panelCentral.setBounds(115, 81, 767, 430);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(358, 11, 70, 70);
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("REGISTRAR ADMINISTRADOR");
		iniciar.setSize(301, 42);
		iniciar.setLocation(235, 89);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		// Campo: Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombre.setBounds(55, 142, 140, 42);
		panelCentral.add(lblNombre);

		nombreField = new JTextField();
		nombreField.setBackground(Color.decode("#D9D9D9"));
		nombreField.setBounds(55, 174, 272, 27);
		panelCentral.add(nombreField);
		nombreField.setColumns(10);

		// Campo: Apellido materno
		JLabel lblApellidoMaterno = new JLabel("Apellido materno:");
		lblApellidoMaterno.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidoMaterno.setFont(new Font("Calibri", Font.BOLD, 14));
		lblApellidoMaterno.setBounds(55, 212, 140, 42);
		panelCentral.add(lblApellidoMaterno);

		apellidoMaternoField = new JTextField();
		apellidoMaternoField.setColumns(10);
		apellidoMaternoField.setBackground(new Color(217, 217, 217));
		apellidoMaternoField.setBounds(55, 238, 270, 27);
		panelCentral.add(apellidoMaternoField);

		// Campo: Apellido paterno
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		lblApellidoPaterno.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidoPaterno.setFont(new Font("Calibri", Font.BOLD, 14));
		lblApellidoPaterno.setBounds(55, 276, 140, 42);
		panelCentral.add(lblApellidoPaterno);

		apellidoPaternoField = new JTextField();
		apellidoPaternoField.setColumns(10);
		apellidoPaternoField.setBackground(new Color(217, 217, 217));
		apellidoPaternoField.setBounds(55, 304, 270, 27);
		panelCentral.add(apellidoPaternoField);

		// Campo: Correo
		JLabel lblCorreo = new JLabel("Ingresar correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCorreo.setBounds(455, 142, 140, 42);
		panelCentral.add(lblCorreo);

		correoField = new JTextField();
		correoField.setColumns(10);
		correoField.setBackground(new Color(217, 217, 217));
		correoField.setBounds(455, 174, 270, 27);
		panelCentral.add(correoField);

		// Campo: Contraseña
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblContrasena.setBounds(455, 212, 140, 42);
		panelCentral.add(lblContrasena);

		contrasenaField = new JTextField();
		contrasenaField.setColumns(10);
		contrasenaField.setBackground(new Color(217, 217, 217));
		contrasenaField.setBounds(455, 238, 270, 27);
		panelCentral.add(contrasenaField);

		// Campo: Confirmar contraseña
		JLabel lblConfirmarContrasena = new JLabel("Confirmar contraseña:");
		lblConfirmarContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmarContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblConfirmarContrasena.setBounds(455, 276, 160, 42);
		panelCentral.add(lblConfirmarContrasena);

		confirmarContrasenaField = new JTextField();
		confirmarContrasenaField.setColumns(10);
		confirmarContrasenaField.setBackground(new Color(217, 217, 217));
		confirmarContrasenaField.setBounds(455, 304, 270, 27);
		panelCentral.add(confirmarContrasenaField);

		// Botón Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(164, 374, 183, 33);
		btnCancelar.addActionListener(e -> {
			CodigoRegistro(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		// Botón Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(455, 374, 183, 33);
		btnConfirmar.addActionListener(e -> {
			dispose();
			HomeView inicio = new HomeView();
			inicio.Inicio();
		});
		panelCentral.add(btnConfirmar);

		// PANEL ROJO SUPERIOR
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
//			InformacionCliente(); // Abre la ventana anterior
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

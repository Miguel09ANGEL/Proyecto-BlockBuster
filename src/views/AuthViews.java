package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import models.User;
import models.UsersModel;
import models.VideoGames;

public class AuthViews extends JFrame {
	
	// se manda a llamar a la clase UsersModel
	UsersModel um = new UsersModel();

	public AuthViews() {
		// TODO Auto-generated constructor stub

	}

//Paneles Principales Login
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
				RegistrarAdministrador(); // Abre la segunda ventana
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

//	Clientes
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
			
			us.update(user.getId());
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
			String nuevoTelefono = txtTelefono.getText().trim();
			String nuevoCorreo = txtCorreo.getText().trim();

			java.sql.Date nuevaFechaNac = null;
			if (model.getValue() != null) {
				nuevaFechaNac = new java.sql.Date(model.getValue().getTime());
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
				
				us1.update(user.getId());
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
		panelCentral.add(textFieldNombre);

		// Botón Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Rojo
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose(); // Cierra esta ventana
			HomeView administradorclientes = new HomeView();
			administradorclientes.AdministradorCliente();
		});
		panelCentral.add(btnCancelar);


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


		// Botón Guardar
		JButton btnConfirmar = new JButton("Guardar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Azul oscuro
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(533, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			// Acción para confirmar y guardar cliente
			
			String nombre = textFieldNombre.getText().trim();
			String apellidoPaterno = textFieldApellidoPaterno.getText().trim();
			String apellidoMaterno = textFieldApellidoMaterno.getText().trim();
			String telefono = textFieldTelefono.getText().trim();
			String correo = textFieldCorreo.getText().trim();
			
			java.sql.Date fechaNacimiento = null;
			if (model.getValue() != null) {
				fechaNacimiento = new java.sql.Date(model.getValue().getTime());
			}
			
			boolean agregado = um.add(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, telefono, correo);

	        if (agregado) {
	            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
	            dispose(); 
	            new HomeView().AdministradorCliente(); 
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

//	Juegos
	public void DetallesJuego() {
		// Configuración básica de la ventana
		setTitle("Detalles Videojuego");
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

		// Título principal
		JLabel lblTitulo = new JLabel("DETALLES DE VIDEOJUEGO");
		lblTitulo.setForeground(Color.decode("#263C54"));
		lblTitulo.setSize(331, 42);
		lblTitulo.setLocation(394, 11);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Anton", Font.BOLD, 20));
		panelCentral.add(lblTitulo);

		// Año
		JLabel lblAnio = new JLabel("1988");
		lblAnio.setForeground(Color.decode("#3B3741"));
		lblAnio.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnio.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAnio.setBounds(55, 161, 70, 42);
		panelCentral.add(lblAnio);

		// Etiqueta "Acerca de:"
		JLabel lblAcercaDe = new JLabel("Acerca de:");
		lblAcercaDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcercaDe.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAcercaDe.setBounds(379, 72, 97, 42);
		panelCentral.add(lblAcercaDe);

		// Descripción (JTextArea)
		JTextArea descripcion = new JTextArea(
				"Contra es un videojuego de shoot 'em up desarrollado y publicado por Konami, lanzado originalmente como un juego de arcade el 20 de febrero de 1987. En 1988 se lanzó una versión doméstica para Nintendo Entertainment System, junto con puertos para varios formatos de computadora, incluido el MSX2.");
		descripcion.setFont(new Font("Calibri Light", Font.BOLD, 20));
		descripcion.setForeground(Color.decode("#3B3741"));
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(true);
		descripcion.setEditable(false);
		descripcion.setOpaque(false);
		descripcion.setBounds(379, 108, 567, 145);
		panelCentral.add(descripcion);

		// Nombre del juego
		JLabel lblNombreJuego = new JLabel("CONTRA");
		lblNombreJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreJuego.setFont(new Font("Anton", Font.BOLD, 24));
		lblNombreJuego.setBounds(55, 124, 135, 42);
		panelCentral.add(lblNombreJuego);

		// Etiqueta "Clasificación:"
		JLabel lblClasificacionTitulo = new JLabel("Clasificación:");
		lblClasificacionTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacionTitulo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacionTitulo.setBounds(55, 198, 103, 42);
		panelCentral.add(lblClasificacionTitulo);

		// Valor de clasificación
		JLabel lblClasificacionValor = new JLabel("Mayores 18");
		lblClasificacionValor.setForeground(Color.decode("#3B3741"));
		lblClasificacionValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacionValor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacionValor.setBounds(158, 198, 103, 42);
		panelCentral.add(lblClasificacionValor);

		// Etiqueta "Desarrolladores:"
		JLabel lblDesarrolladoresTitulo = new JLabel("Desarrolladores:");
		lblDesarrolladoresTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesarrolladoresTitulo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDesarrolladoresTitulo.setBounds(55, 233, 190, 42);
		panelCentral.add(lblDesarrolladoresTitulo);

		// Nombres desarrolladores (JTextArea)
		JTextArea txtDesarrolladores = new JTextArea(
				"Konami,  Ocean Software, Hamster Corporation, Digital Eclipse, Backbone Entertainment, Paul Owens");
		txtDesarrolladores.setWrapStyleWord(true);
		txtDesarrolladores.setForeground(Color.decode("#3B3741"));
		txtDesarrolladores.setOpaque(false);
		txtDesarrolladores.setLineWrap(true);
		txtDesarrolladores.setFont(new Font("Calibri Light", Font.BOLD, 16));
		txtDesarrolladores.setEditable(false);
		txtDesarrolladores.setBounds(55, 265, 318, 70);
		panelCentral.add(txtDesarrolladores);

		// Etiqueta "Género:"
		JLabel lblGeneroTitulo = new JLabel("Género:");
		lblGeneroTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblGeneroTitulo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblGeneroTitulo.setBounds(55, 333, 70, 42);
		panelCentral.add(lblGeneroTitulo);

		// Valor género
		JLabel lblGeneroValor = new JLabel("Disparos");
		lblGeneroValor.setForeground(Color.decode("#3B3741"));
		lblGeneroValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblGeneroValor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblGeneroValor.setBounds(118, 333, 70, 42);
		panelCentral.add(lblGeneroValor);

		// Etiqueta "Plataforma:"
		JLabel lblPlataformaTitulo = new JLabel("Plataforma:");
		lblPlataformaTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlataformaTitulo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblPlataformaTitulo.setBounds(55, 372, 97, 42);
		panelCentral.add(lblPlataformaTitulo);

		// Valor plataforma
		JLabel lblPlataformaValor = new JLabel("Nintendo");
		lblPlataformaValor.setForeground(Color.decode("#3B3741"));
		lblPlataformaValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlataformaValor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblPlataformaValor.setBounds(148, 372, 97, 42);
		panelCentral.add(lblPlataformaValor);

		// Etiqueta "Precio por renta:"
		JLabel lblPrecioRentaTitulo = new JLabel("Precio por renta:");
		lblPrecioRentaTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioRentaTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPrecioRentaTitulo.setBounds(664, 317, 150, 42);
		panelCentral.add(lblPrecioRentaTitulo);

		// Valor precio renta
		JLabel lblPrecioRentaValor = new JLabel("$100.00MXN");
		lblPrecioRentaValor.setForeground(new Color(153, 0, 0));
		lblPrecioRentaValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioRentaValor.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPrecioRentaValor.setBounds(824, 317, 126, 42);
		panelCentral.add(lblPrecioRentaValor);

		// Etiqueta "Precio por venta:"
		JLabel lblPrecioVentaTitulo = new JLabel("Precio por venta:");
		lblPrecioVentaTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVentaTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPrecioVentaTitulo.setBounds(664, 353, 159, 42);
		panelCentral.add(lblPrecioVentaTitulo);

		// Valor precio venta
		JLabel lblPrecioVentaValor = new JLabel("$800.00MX");
		lblPrecioVentaValor.setForeground(new Color(153, 0, 0));
		lblPrecioVentaValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVentaValor.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPrecioVentaValor.setBounds(824, 353, 126, 42);
		panelCentral.add(lblPrecioVentaValor);

		// Botones
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.decode("#6D91B9"));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditarJuego();

			}
		});
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBounds(394, 417, 183, 33);
		panelCentral.add(btnEditar);

		JButton btnDescargarPDF = new JButton("Descargar (PDF)");
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBounds(605, 417, 183, 33);
		panelCentral.add(btnDescargarPDF);
		btnDescargarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

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

	public void EditarJuego() {
		// Configuración básica de la ventana
		setTitle("Editar Juego");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 650));
		setContentPane(layeredPane);

		// Panel gris central
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

		// Título principal
		JLabel lblTitulo = new JLabel("EDITAR VIDEOJUEGOS");
		lblTitulo.setSize(263, 42);
		lblTitulo.setLocation(369, 78);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// Labels y campos de texto

		JLabel lblNombreJuego = new JLabel("Nombre del Videojuego:");
		lblNombreJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreJuego.setBounds(55, 108, 140, 42);
		panelCentral.add(lblNombreJuego);

		JTextField txtNombreJuego = new JTextField();
		txtNombreJuego.setBackground(Color.decode("#D9D9D9"));
		txtNombreJuego.setBounds(53, 142, 230, 27);
		txtNombreJuego.setColumns(10);
		panelCentral.add(txtNombreJuego);

		JLabel lblAnioLanzamiento = new JLabel("Año de lanzamiento:");
		lblAnioLanzamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnioLanzamiento.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAnioLanzamiento.setBounds(55, 180, 132, 42);
		panelCentral.add(lblAnioLanzamiento);

		JTextField txtAnioLanzamiento = new JTextField();
		txtAnioLanzamiento.setBackground(Color.decode("#D9D9D9"));
		txtAnioLanzamiento.setColumns(10);
		txtAnioLanzamiento.setBounds(53, 214, 153, 27);
		panelCentral.add(txtAnioLanzamiento);

		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponibilidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDisponibilidad.setBounds(227, 180, 97, 42);
		panelCentral.add(lblDisponibilidad);

		JTextField txtDisponibilidad = new JTextField();
		txtDisponibilidad.setBackground(Color.decode("#D9D9D9"));
		txtDisponibilidad.setColumns(10);
		txtDisponibilidad.setBounds(227, 214, 120, 27);
		panelCentral.add(txtDisponibilidad);

		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenero.setFont(new Font("Calibri", Font.BOLD, 14));
		lblGenero.setBounds(55, 259, 140, 42);
		panelCentral.add(lblGenero);

		JTextField txtGenero = new JTextField();
		txtGenero.setBackground(Color.decode("#D9D9D9"));
		txtGenero.setColumns(10);
		txtGenero.setBounds(55, 289, 188, 27);
		panelCentral.add(txtGenero);

		JLabel lblExistencias = new JLabel("Existencias disponibles:");
		lblExistencias.setHorizontalAlignment(SwingConstants.CENTER);
		lblExistencias.setFont(new Font("Calibri", Font.BOLD, 14));
		lblExistencias.setBounds(289, 252, 153, 42);
		panelCentral.add(lblExistencias);

		JTextField txtExistencias = new JTextField();
		txtExistencias.setBackground(Color.decode("#D9D9D9"));
		txtExistencias.setColumns(10);
		txtExistencias.setBounds(288, 289, 215, 27);
		panelCentral.add(txtExistencias);

		JLabel lblPrecioRenta = new JLabel("Precio a renta (MXN):");
		lblPrecioRenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecioRenta.setBounds(55, 327, 140, 42);
		panelCentral.add(lblPrecioRenta);

		JTextField txtPrecioRenta = new JTextField();
		txtPrecioRenta.setBackground(Color.decode("#D9D9D9"));
		txtPrecioRenta.setColumns(10);
		txtPrecioRenta.setBounds(55, 368, 188, 27);
		panelCentral.add(txtPrecioRenta);

		JLabel lblPrecioVenta = new JLabel("Precio a venta (MXN):");
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecioVenta.setBounds(288, 327, 140, 42);
		panelCentral.add(lblPrecioVenta);

		JTextField txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBackground(Color.decode("#D9D9D9"));
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(288, 368, 215, 27);
		panelCentral.add(txtPrecioVenta);

		JLabel lblPlataforma = new JLabel("Plataforma:");
		lblPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlataforma.setBounds(301, 108, 97, 42);
		panelCentral.add(lblPlataforma);

		JTextField txtPlataforma = new JTextField();
		txtPlataforma.setBackground(Color.decode("#D9D9D9"));
		txtPlataforma.setColumns(10);
		txtPlataforma.setBounds(301, 142, 202, 27);
		panelCentral.add(txtPlataforma);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 14));
		lblClasificacion.setBounds(369, 180, 97, 42);
		panelCentral.add(lblClasificacion);

		JTextField txtClasificacion = new JTextField();
		txtClasificacion.setBackground(Color.decode("#D9D9D9"));
		txtClasificacion.setColumns(10);
		txtClasificacion.setBounds(369, 214, 134, 27);
		panelCentral.add(txtClasificacion);

		JLabel lblAcercaDeTitulo = new JLabel("Acerca de:");
		lblAcercaDeTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcercaDeTitulo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAcercaDeTitulo.setBounds(619, 108, 97, 42);
		panelCentral.add(lblAcercaDeTitulo);

		JTextField txtAcercaDeBreve = new JTextField();
		txtAcercaDeBreve.setColumns(10);
		txtAcercaDeBreve.setBackground(Color.decode("#D9D9D9"));
		txtAcercaDeBreve.setBounds(619, 142, 343, 77);
		panelCentral.add(txtAcercaDeBreve);

		JLabel lblAcercaDeDetalle = new JLabel("Acerca de:");
		lblAcercaDeDetalle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDeDetalle.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAcercaDeDetalle.setBounds(619, 220, 97, 42);
		panelCentral.add(lblAcercaDeDetalle);

		JTextField txtAcercaDeDetalle = new JTextField();
		txtAcercaDeDetalle.setBackground(Color.decode("#D9D9D9"));
		txtAcercaDeDetalle.setColumns(10);
		txtAcercaDeDetalle.setBounds(619, 259, 343, 136);
		panelCentral.add(txtAcercaDeDetalle);

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			DetallesJuego(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(533, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			// Acción de confirmar
		});
		panelCentral.add(btnConfirmar);

		// Panel rojo superior (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void AgregarJuego() {
		// Campos de texto
		JTextField txtNombreJuego;
		JTextField txtAnoLanzamiento;
		JTextField txtGenero;
		JTextField txtPrecioRenta;
		JTextField txtDisponibilidad;
		JTextField txtPlataforma;
		JTextField txtClasificacion;
		JTextField txtExistencias;
		JTextField txtPrecioVenta;
		JTextField txtAcercaDeGrande;
		JTextField txtAcercaDePequeno;

		// Configuración básica de la ventana
		setTitle("Panel Administrador");
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
		JLabel lblTitulo = new JLabel("AGREGAR VIDEOJUEGOS");
		lblTitulo.setSize(263, 42);
		lblTitulo.setLocation(369, 78);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// Labels y campos de texto
		JLabel lblNombreJuego = new JLabel("Nombre del Videojuego:");
		lblNombreJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreJuego.setBounds(55, 108, 164, 42);
		panelCentral.add(lblNombreJuego);

		txtNombreJuego = new JTextField();
		txtNombreJuego.setBackground(Color.decode("#D9D9D9"));
		txtNombreJuego.setBounds(53, 142, 230, 27);
		txtNombreJuego.setColumns(10);
		panelCentral.add(txtNombreJuego);

		JLabel lblAnoLanzamiento = new JLabel("Año de lanzamiento :");
		lblAnoLanzamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnoLanzamiento.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAnoLanzamiento.setBounds(55, 180, 132, 42);
		panelCentral.add(lblAnoLanzamiento);

		txtAnoLanzamiento = new JTextField();
		txtAnoLanzamiento.setBackground(Color.decode("#D9D9D9"));
		txtAnoLanzamiento.setColumns(10);
		txtAnoLanzamiento.setBounds(53, 214, 153, 27);
		panelCentral.add(txtAnoLanzamiento);

		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponibilidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDisponibilidad.setBounds(227, 180, 97, 42);
		panelCentral.add(lblDisponibilidad);

		txtDisponibilidad = new JTextField();
		txtDisponibilidad.setBackground(Color.decode("#D9D9D9"));
		txtDisponibilidad.setColumns(10);
		txtDisponibilidad.setBounds(227, 214, 120, 27);
		panelCentral.add(txtDisponibilidad);

		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenero.setFont(new Font("Calibri", Font.BOLD, 14));
		lblGenero.setBounds(55, 259, 140, 42);
		panelCentral.add(lblGenero);

		txtGenero = new JTextField();
		txtGenero.setBackground(Color.decode("#D9D9D9"));
		txtGenero.setColumns(10);
		txtGenero.setBounds(55, 289, 188, 27);
		panelCentral.add(txtGenero);

		JLabel lblExistencias = new JLabel("Existencias disponibles:");
		lblExistencias.setHorizontalAlignment(SwingConstants.LEFT);
		lblExistencias.setFont(new Font("Calibri", Font.BOLD, 14));
		lblExistencias.setBounds(289, 252, 153, 42);
		panelCentral.add(lblExistencias);

		txtExistencias = new JTextField();
		txtExistencias.setBackground(Color.decode("#D9D9D9"));
		txtExistencias.setColumns(10);
		txtExistencias.setBounds(288, 289, 215, 27);
		panelCentral.add(txtExistencias);

		JLabel lblPrecioRenta = new JLabel("Precio a renta (MXN)");
		lblPrecioRenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioRenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecioRenta.setBounds(55, 327, 140, 42);
		panelCentral.add(lblPrecioRenta);

		txtPrecioRenta = new JTextField();
		txtPrecioRenta.setBackground(Color.decode("#D9D9D9"));
		txtPrecioRenta.setColumns(10);
		txtPrecioRenta.setBounds(55, 368, 188, 27);
		panelCentral.add(txtPrecioRenta);

		JLabel lblPrecioVenta = new JLabel("Precio a venta (MXN):");
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioVenta.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecioVenta.setBounds(288, 327, 140, 42);
		panelCentral.add(lblPrecioVenta);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBackground(Color.decode("#D9D9D9"));
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(288, 368, 215, 27);
		panelCentral.add(txtPrecioVenta);

		JLabel lblPlataforma = new JLabel("Plataforma:");
		lblPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlataforma.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlataforma.setBounds(301, 108, 97, 42);
		panelCentral.add(lblPlataforma);

		txtPlataforma = new JTextField();
		txtPlataforma.setBackground(Color.decode("#D9D9D9"));
		txtPlataforma.setColumns(10);
		txtPlataforma.setBounds(301, 142, 202, 27);
		panelCentral.add(txtPlataforma);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 14));
		lblClasificacion.setBounds(369, 180, 97, 42);
		panelCentral.add(lblClasificacion);

		txtClasificacion = new JTextField();
		txtClasificacion.setBackground(Color.decode("#D9D9D9"));
		txtClasificacion.setColumns(10);
		txtClasificacion.setBounds(369, 214, 134, 27);
		panelCentral.add(txtClasificacion);

		JLabel lblAcercaDePequeno = new JLabel("Acerca de:");
		lblAcercaDePequeno.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcercaDePequeno.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAcercaDePequeno.setBounds(619, 108, 97, 42);
		panelCentral.add(lblAcercaDePequeno);

		txtAcercaDePequeno = new JTextField();
		txtAcercaDePequeno.setBackground(new Color(217, 217, 217));
		txtAcercaDePequeno.setColumns(10);
		txtAcercaDePequeno.setBounds(619, 145, 343, 82);
		panelCentral.add(txtAcercaDePequeno);

		JLabel lblAcercaDeGrande = new JLabel("Acerca de:");
		lblAcercaDeGrande.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDeGrande.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAcercaDeGrande.setBounds(619, 220, 97, 42);
		panelCentral.add(lblAcercaDeGrande);

		txtAcercaDeGrande = new JTextField();
		txtAcercaDeGrande.setBackground(Color.decode("#D9D9D9"));
		txtAcercaDeGrande.setColumns(10);
		txtAcercaDeGrande.setBounds(619, 259, 343, 136);
		panelCentral.add(txtAcercaDeGrande);

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(259, 417, 183, 33);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomeView administradorjuegos = new HomeView();
				administradorjuegos.AdministradorJuegos();

			}
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(561, 417, 183, 33);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

//	Renta y Compra
	public void OperacionRentar() {
		// Define estos JTextFields como atributos de clase (arriba, fuera de métodos)
		JTextField txtNombreCliente;
		JTextField txtNombreVideojuego;
		JTextField txtCantidad;

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

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBackground(Color.decode("#D9D9D9"));
		txtNombreCliente.setBounds(85, 161, 255, 27);
		panelCentral.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (rojo)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose(); // Cierra la ventana actual
			UserViews renta = new UserViews();
			renta.Renta();
			// Abre la segunda ventana
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			// Acción confirmar
			// No cierras la ventana? (puedes agregar dispose(); si quieres)
		});
		panelCentral.add(btnConfirmar);

		txtNombreVideojuego = new JTextField();
		txtNombreVideojuego.setColumns(10);
		txtNombreVideojuego.setBackground(new Color(217, 217, 217));
		txtNombreVideojuego.setBounds(405, 161, 263, 27);
		panelCentral.add(txtNombreVideojuego);

		JLabel iniciar_1_1 = new JLabel("Nombre del Videojuego:");
		iniciar_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1.setBounds(405, 120, 160, 42);
		panelCentral.add(iniciar_1_1);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(new Color(217, 217, 217));
		txtCantidad.setBounds(697, 161, 165, 27);
		panelCentral.add(txtCantidad);

		JLabel iniciar_1_1_1 = new JLabel("Cantidad:");
		iniciar_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		iniciar_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		iniciar_1_1_1.setBounds(697, 120, 160, 42);
		panelCentral.add(iniciar_1_1_1);

		// Resto de los JLabel sigue igual...

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

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

		// 2. PANEL GRIS CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#D9D9D9"));
		panelCentral.setBounds(5, 62, 998, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/contra.png"));
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

		JLabel lblNombreCliente = new JLabel("Miguel Garcia");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreCliente.setBounds(226, 102, 112, 42);
		panelCentral.add(lblNombreCliente);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCorreo.setBounds(503, 85, 70, 42);
		panelCentral.add(lblCorreo);

		JLabel lblEmailCliente = new JLabel("mga@gmail.com");
		lblEmailCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEmailCliente.setBounds(466, 102, 149, 42);
		panelCentral.add(lblEmailCliente);

		// Botones
		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserViews renta = new UserViews();
				renta.Renta();

			}
		});
		btnRegresar.setBackground(Color.decode("#B82F2F"));
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnRegresar);

		JButton btnDescargarPDF = new JButton("Descargar (PDF)");
		btnDescargarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBounds(614, 406, 183, 33);
		panelCentral.add(btnDescargarPDF);

		// Logo del juego
		ImageIcon iconoOrigina = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagen = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel lblLogoJuego = new JLabel(new ImageIcon(imagen));
		lblLogoJuego.setBounds(32, 32, 184, 112);
		panelCentral.add(lblLogoJuego);

		JLabel lblTituloJuego = new JLabel("CONTRA");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTituloJuego.setBounds(32, 141, 135, 42);
		panelCentral.add(lblTituloJuego);

		// Información del juego
		JLabel lblAnioJuego = new JLabel("1987");
		lblAnioJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnioJuego.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAnioJuego.setBounds(42, 174, 57, 42);
		panelCentral.add(lblAnioJuego);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacion.setBounds(188, 204, 112, 42);
		panelCentral.add(lblClasificacion);

		JLabel lblValorClasificacion = new JLabel("E");
		lblValorClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorClasificacion.setBounds(185, 232, 97, 42);
		panelCentral.add(lblValorClasificacion);

		JLabel lblDistribuidor = new JLabel("Distribuidores:");
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDistribuidor.setBounds(188, 285, 112, 42);
		panelCentral.add(lblDistribuidor);

		JLabel lblNombreDistribuidor = new JLabel("Konami");
		lblNombreDistribuidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreDistribuidor.setBounds(198, 309, 97, 42);
		panelCentral.add(lblNombreDistribuidor);

		// Fechas de renta
		JLabel lblFechaRenta = new JLabel("Fecha de renta:");
		lblFechaRenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaRenta.setBounds(426, 204, 126, 42);
		panelCentral.add(lblFechaRenta);

		JLabel lblValorFechaRenta = new JLabel("15/02/2025");
		lblValorFechaRenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFechaRenta.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFechaRenta.setBounds(436, 232, 97, 42);
		panelCentral.add(lblValorFechaRenta);

		JLabel lblFechaLimite = new JLabel("Límite de devolución");
		lblFechaLimite.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaLimite.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaLimite.setBounds(407, 285, 157, 42);
		panelCentral.add(lblFechaLimite);

		JLabel lblValorFechaLimite = new JLabel("15/03/2025");
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
		lblCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCantidad.setBounds(700, 174, 57, 42);
		panelCentral.add(lblCantidad);

		JLabel lblValorCantidad = new JLabel("1");
		lblValorCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorCantidad.setBounds(760, 174, 57, 42);
		panelCentral.add(lblValorCantidad);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setFont(new Font("Calibri", Font.BOLD, 14));
		lblProducto.setBounds(700, 205, 70, 42);
		panelCentral.add(lblProducto);

		JLabel lblPrecioProducto = new JLabel("100.00 MXN");
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

		JLabel lblTotal = new JLabel("$108.00 MXN");
		lblTotal.setForeground(new Color(153, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTotal.setBounds(719, 359, 126, 42);
		panelCentral.add(lblTotal);

		// 3. PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	public void OperacionComprar() {
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
		lblTitulo.setSize(263, 42);
		lblTitulo.setLocation(369, 78);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(lblTitulo);

		// Campos de entrada
		JLabel lblNombreCliente = new JLabel("Nombre del cliente");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreCliente.setBounds(85, 120, 160, 42);
		panelCentral.add(lblNombreCliente);

		JTextField txtNombreCliente = new JTextField();
		txtNombreCliente.setBackground(Color.decode("#D9D9D9"));
		txtNombreCliente.setBounds(85, 161, 255, 27);
		txtNombreCliente.setColumns(10);
		panelCentral.add(txtNombreCliente);

		JLabel lblNombreJuego = new JLabel("Nombre del Videojuego:");
		lblNombreJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombreJuego.setBounds(405, 120, 160, 42);
		panelCentral.add(lblNombreJuego);

		JTextField txtNombreJuego = new JTextField();
		txtNombreJuego.setColumns(10);
		txtNombreJuego.setBackground(new Color(217, 217, 217));
		txtNombreJuego.setBounds(405, 161, 263, 27);
		panelCentral.add(txtNombreJuego);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCantidad.setBounds(697, 120, 160, 42);
		panelCentral.add(lblCantidad);

		JTextField txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(new Color(217, 217, 217));
		txtCantidad.setBounds(697, 161, 165, 27);
		panelCentral.add(txtCantidad);

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose();
			UserViews compra = new UserViews();
			compra.Compra();

		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.decode("#263C54"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {

		});
		panelCentral.add(btnConfirmar);

		// Detalles de la compra
		JLabel lblTituloJuego = new JLabel("Nombre de videojuego");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTituloJuego.setBounds(75, 215, 255, 42);
		panelCentral.add(lblTituloJuego);

		JLabel lblPrecio = new JLabel("Precio (MXN):");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPrecio.setBounds(403, 215, 255, 42);
		panelCentral.add(lblPrecio);

		JLabel lblValorJuego = new JLabel("Contra");
		lblValorJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorJuego.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorJuego.setBounds(75, 246, 255, 42);
		panelCentral.add(lblValorJuego);

		JLabel lblValorPrecio = new JLabel("$100");
		lblValorPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorPrecio.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorPrecio.setBounds(403, 246, 255, 42);
		panelCentral.add(lblValorPrecio);

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

		JLabel lblValorFecha = new JLabel("15/05/2025");
		lblValorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFecha.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorFecha.setBounds(403, 320, 255, 42);
		panelCentral.add(lblValorFecha);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTipo.setBounds(687, 215, 101, 42);
		panelCentral.add(lblTipo);

		JLabel lblValorTipo = new JLabel("Venta");
		lblValorTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTipo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorTipo.setBounds(687, 246, 101, 42);
		panelCentral.add(lblValorTipo);

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

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

		// Panel principal
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		panelCentral.setBackground(Color.decode("#D9D9D9"));
		panelCentral.setBounds(5, 62, 998, 475);
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logo del juego
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/contra.png"));
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

		JLabel lblNombreCliente = new JLabel("Miguel Garcia");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreCliente.setBounds(226, 102, 112, 42);
		panelCentral.add(lblNombreCliente);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCorreo.setBounds(503, 85, 70, 42);
		panelCentral.add(lblCorreo);

		JLabel lblEmailCliente = new JLabel("mga@gmail.com");
		lblEmailCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCliente.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEmailCliente.setBounds(466, 102, 149, 42);
		panelCentral.add(lblEmailCliente);

		// Botones
		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(e -> {
			dispose();
			UserViews compra = new UserViews();
			compra.Compra();

		});
		btnRegresar.setBackground(Color.decode("#B82F2F"));
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBounds(185, 406, 183, 33);
		panelCentral.add(btnRegresar);

		JButton btnDescargarPDF = new JButton("Descargar (PDF)");
		btnDescargarPDF.setBackground(Color.decode("#263C54"));
		btnDescargarPDF.setForeground(Color.WHITE);
		btnDescargarPDF.setBounds(614, 406, 183, 33);
		btnDescargarPDF.addActionListener(e -> {

		});
		panelCentral.add(btnDescargarPDF);

		// Imagen del juego
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/images/Contra.png"));
		Image imagenLogo = iconoOriginal.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel lblLogoJuego = new JLabel(new ImageIcon(imagenLogo));
		lblLogoJuego.setBounds(32, 32, 184, 112);
		panelCentral.add(lblLogoJuego);

		JLabel lblTituloJuego = new JLabel("CONTRA");
		lblTituloJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloJuego.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTituloJuego.setBounds(32, 141, 135, 42);
		panelCentral.add(lblTituloJuego);

		// Detalles del juego
		JLabel lblAnioJuego = new JLabel("1987");
		lblAnioJuego.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnioJuego.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAnioJuego.setBounds(42, 174, 57, 42);
		panelCentral.add(lblAnioJuego);

		JLabel lblClasificacion = new JLabel("Clasificación:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClasificacion.setBounds(188, 204, 112, 42);
		panelCentral.add(lblClasificacion);

		JLabel lblValorClasificacion = new JLabel("E");
		lblValorClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorClasificacion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorClasificacion.setBounds(185, 232, 97, 42);
		panelCentral.add(lblValorClasificacion);

		JLabel lblDistribuidor = new JLabel("Distribuidores:");
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDistribuidor.setBounds(188, 285, 112, 42);
		panelCentral.add(lblDistribuidor);

		JLabel lblNombreDistribuidor = new JLabel("Konami");
		lblNombreDistribuidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDistribuidor.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNombreDistribuidor.setBounds(198, 309, 97, 42);
		panelCentral.add(lblNombreDistribuidor);

		// Fechas
		JLabel lblFechaCompra = new JLabel("Fecha de compra:");
		lblFechaCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaCompra.setFont(new Font("Calibri", Font.BOLD, 18));
		lblFechaCompra.setBounds(426, 204, 147, 42);
		panelCentral.add(lblFechaCompra);

		JLabel lblValorFechaCompra = new JLabel("15/02/2025");
		lblValorFechaCompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorFechaCompra.setFont(new Font("Calibri", Font.BOLD, 18));
		lblValorFechaCompra.setBounds(436, 232, 97, 42);
		panelCentral.add(lblValorFechaCompra);

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

		// Detalles de pago
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCantidad.setBounds(700, 174, 57, 42);
		panelCentral.add(lblCantidad);

		JLabel lblValorCantidad = new JLabel("1");
		lblValorCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCantidad.setFont(new Font("Calibri", Font.BOLD, 14));
		lblValorCantidad.setBounds(760, 174, 57, 42);
		panelCentral.add(lblValorCantidad);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setFont(new Font("Calibri", Font.BOLD, 14));
		lblProducto.setBounds(700, 205, 70, 42);
		panelCentral.add(lblProducto);

		JLabel lblPrecioProducto = new JLabel("100.00 MXN");
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

		// Barra superior roja
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

//	Nueva Operacion
	public void EditarPromociones() {
		// Configuración básica de la ventana
		setTitle("Editar Promociones");
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

		// 2. PANEL GRIS CENTRAL
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

		// Título principal
		JLabel tituloPrincipal = new JLabel("EDITAR PROMOCIONES");
		tituloPrincipal.setSize(263, 42);
		tituloPrincipal.setLocation(369, 78);
		tituloPrincipal.setHorizontalAlignment(JLabel.CENTER);
		tituloPrincipal.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(tituloPrincipal);

		// Campos de compra (columna 1)
		JLabel lblCompra1 = new JLabel("Compra de:");
		lblCompra1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra1.setBounds(85, 120, 160, 42);
		panelCentral.add(lblCompra1);

		JTextField txtCompra1 = new JTextField();
		txtCompra1.setBackground(Color.decode("#D9D9D9"));
		txtCompra1.setBounds(85, 147, 189, 27);
		txtCompra1.setColumns(10);
		panelCentral.add(txtCompra1);

		JLabel lblCompra2 = new JLabel("Compra de:");
		lblCompra2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra2.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra2.setBounds(85, 181, 160, 42);
		panelCentral.add(lblCompra2);

		JTextField txtCompra2 = new JTextField();
		txtCompra2.setColumns(10);
		txtCompra2.setBackground(new Color(217, 217, 217));
		txtCompra2.setBounds(85, 207, 189, 27);
		panelCentral.add(txtCompra2);

		JLabel lblCompra3 = new JLabel("Compra de:");
		lblCompra3.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra3.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra3.setBounds(85, 246, 160, 42);
		panelCentral.add(lblCompra3);

		JTextField txtCompra3 = new JTextField();
		txtCompra3.setColumns(10);
		txtCompra3.setBackground(new Color(217, 217, 217));
		txtCompra3.setBounds(85, 273, 189, 27);
		panelCentral.add(txtCompra3);

		JLabel lblCompra4 = new JLabel("Compra de:");
		lblCompra4.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra4.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra4.setBounds(85, 313, 160, 42);
		panelCentral.add(lblCompra4);

		JTextField txtCompra4 = new JTextField();
		txtCompra4.setColumns(10);
		txtCompra4.setBackground(new Color(217, 217, 217));
		txtCompra4.setBounds(85, 339, 189, 27);
		panelCentral.add(txtCompra4);

		// Campos de promoción (columna 2)
		JLabel lblPromo1 = new JLabel("Promocion de:");
		lblPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo1.setBounds(306, 120, 160, 42);
		panelCentral.add(lblPromo1);

		JTextField txtPromo1 = new JTextField();
		txtPromo1.setColumns(10);
		txtPromo1.setBackground(new Color(217, 217, 217));
		txtPromo1.setBounds(306, 150, 189, 27);
		panelCentral.add(txtPromo1);

		JLabel lblPromo2 = new JLabel("Promocion de:");
		lblPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo2.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo2.setBounds(306, 181, 160, 42);
		panelCentral.add(lblPromo2);

		JTextField txtPromo2 = new JTextField();
		txtPromo2.setColumns(10);
		txtPromo2.setBackground(new Color(217, 217, 217));
		txtPromo2.setBounds(306, 210, 189, 27);
		panelCentral.add(txtPromo2);

		JLabel lblPromo3 = new JLabel("Promocion de:");
		lblPromo3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo3.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo3.setBounds(306, 246, 160, 42);
		panelCentral.add(lblPromo3);

		JTextField txtPromo3 = new JTextField();
		txtPromo3.setColumns(10);
		txtPromo3.setBackground(new Color(217, 217, 217));
		txtPromo3.setBounds(306, 276, 189, 27);
		panelCentral.add(txtPromo3);

		JLabel lblPromo4 = new JLabel("Promocion de:");
		lblPromo4.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo4.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo4.setBounds(306, 313, 160, 42);
		panelCentral.add(lblPromo4);

		JTextField txtPromo4 = new JTextField();
		txtPromo4.setColumns(10);
		txtPromo4.setBackground(new Color(217, 217, 217));
		txtPromo4.setBounds(306, 342, 189, 27);
		panelCentral.add(txtPromo4);

		// Campos de compra (columna 3)
		JLabel lblCompra5 = new JLabel("Compra de:");
		lblCompra5.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra5.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra5.setBounds(530, 120, 160, 42);
		panelCentral.add(lblCompra5);

		JTextField txtCompra5 = new JTextField();
		txtCompra5.setColumns(10);
		txtCompra5.setBackground(new Color(217, 217, 217));
		txtCompra5.setBounds(530, 150, 189, 27);
		panelCentral.add(txtCompra5);

		JLabel lblCompra6 = new JLabel("Compra de:");
		lblCompra6.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra6.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra6.setBounds(530, 181, 160, 42);
		panelCentral.add(lblCompra6);

		JTextField txtCompra6 = new JTextField();
		txtCompra6.setColumns(10);
		txtCompra6.setBackground(new Color(217, 217, 217));
		txtCompra6.setBounds(530, 210, 189, 27);
		panelCentral.add(txtCompra6);

		JLabel lblCompra7 = new JLabel("Compra de:");
		lblCompra7.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra7.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra7.setBounds(530, 246, 160, 42);
		panelCentral.add(lblCompra7);

		JTextField txtCompra7 = new JTextField();
		txtCompra7.setColumns(10);
		txtCompra7.setBackground(new Color(217, 217, 217));
		txtCompra7.setBounds(530, 276, 189, 27);
		panelCentral.add(txtCompra7);

		JLabel lblCompra8 = new JLabel("Compra de:");
		lblCompra8.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompra8.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCompra8.setBounds(530, 313, 160, 42);
		panelCentral.add(lblCompra8);

		JTextField txtCompra8 = new JTextField();
		txtCompra8.setColumns(10);
		txtCompra8.setBackground(new Color(217, 217, 217));
		txtCompra8.setBounds(530, 342, 189, 27);
		panelCentral.add(txtCompra8);

		// Campos de promoción (columna 4)
		JLabel lblPromo5 = new JLabel("Promocion de:");
		lblPromo5.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo5.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo5.setBounds(749, 120, 160, 42);
		panelCentral.add(lblPromo5);

		JTextField txtPromo5 = new JTextField();
		txtPromo5.setColumns(10);
		txtPromo5.setBackground(new Color(217, 217, 217));
		txtPromo5.setBounds(749, 150, 189, 27);
		panelCentral.add(txtPromo5);

		JLabel lblPromo6 = new JLabel("Promocion de:");
		lblPromo6.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo6.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo6.setBounds(749, 181, 160, 42);
		panelCentral.add(lblPromo6);

		JTextField txtPromo6 = new JTextField();
		txtPromo6.setColumns(10);
		txtPromo6.setBackground(new Color(217, 217, 217));
		txtPromo6.setBounds(749, 210, 189, 27);
		panelCentral.add(txtPromo6);

		JLabel lblPromo7 = new JLabel("Promocion de:");
		lblPromo7.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo7.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo7.setBounds(749, 246, 160, 42);
		panelCentral.add(lblPromo7);

		JTextField txtPromo7 = new JTextField();
		txtPromo7.setColumns(10);
		txtPromo7.setBackground(new Color(217, 217, 217));
		txtPromo7.setBounds(749, 276, 189, 27);
		panelCentral.add(txtPromo7);

		JLabel lblPromo8 = new JLabel("Promocion de:");
		lblPromo8.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromo8.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPromo8.setBounds(749, 313, 160, 42);
		panelCentral.add(lblPromo8);

		JTextField txtPromo8 = new JTextField();
		txtPromo8.setColumns(10);
		txtPromo8.setBackground(new Color(217, 217, 217));
		txtPromo8.setBounds(749, 342, 189, 27);
		panelCentral.add(txtPromo8);

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F")); // Color de fondo (rojo)
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {

			dispose(); // Cierra la ventana actual
			UserViews promocionautomatica = new UserViews();
			promocionautomatica.PromocionAutomatica();
			// Abre la segunda ventana
		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868")); // Color de fondo (gris oscuro)
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> {
			// Abre la segunda ventana
		});
		panelCentral.add(btnConfirmar);

		setVisible(true);
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

		// PANEL GRIS CENTRAL
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

		// Título principal
		JLabel tituloPrincipal = new JLabel("EDITAR DESCUENTO");
		tituloPrincipal.setSize(263, 42);
		tituloPrincipal.setLocation(369, 78);
		tituloPrincipal.setHorizontalAlignment(JLabel.CENTER);
		tituloPrincipal.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(tituloPrincipal);

		// Columnas de campos
		String[] columnas = { "Mes", "Promoción", "Mes", "Promoción" };
		int[] posicionesX = { 85, 306, 530, 749 };

		// Filas de campos
		String[] filas = { "Frecuencia 1", "Frecuencia 2", "Frecuencia 3", "Frecuencia 4" };
		int[] posicionesY = { 120, 181, 246, 313 };

		// Matriz de campos de texto
		JTextField[][] camposTexto = new JTextField[4][4];

		// Crear etiquetas y campos de texto
		for (int col = 0; col < 4; col++) {
			for (int fila = 0; fila < 4; fila++) {
				// Crear etiqueta
				JLabel etiqueta = new JLabel(col % 2 == 0 ? "Mes de " + filas[fila] : "Promoción de " + filas[fila]);
				etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
				etiqueta.setFont(new Font("Calibri", Font.BOLD, 14));
				etiqueta.setBounds(posicionesX[col], posicionesY[fila], 160, 42);
				panelCentral.add(etiqueta);

				// Crear campo de texto
				camposTexto[col][fila] = new JTextField();
				camposTexto[col][fila].setColumns(10);
				camposTexto[col][fila].setBackground(new Color(217, 217, 217));
				camposTexto[col][fila].setBounds(posicionesX[col], posicionesY[fila] + 27, 189, 27);
				panelCentral.add(camposTexto[col][fila]);
			}
		}

		// Botones
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.decode("#B82F2F"));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(175, 406, 183, 33);
		btnCancelar.addActionListener(e -> {
			dispose();// Cierra la ventana actual
			UserViews descuentofrecuencia = new UserViews();
			descuentofrecuencia.DescuentoFrecuencia();

		});
		panelCentral.add(btnCancelar);

		JButton btnConfirmar = new JButton("ACTUALIZAR");
		btnConfirmar.setBackground(Color.decode("#686868"));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(582, 406, 183, 33);
		btnConfirmar.addActionListener(e -> dispose());
		panelCentral.add(btnConfirmar);

		// PANEL ROJO SUPERIOR (barra de título)
		JPanel barraRoja = new JPanel();
		barraRoja.setBackground(Color.decode("#B44635"));
		barraRoja.setBounds(0, 0, 1024, 60);
		layeredPane.add(barraRoja, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

}

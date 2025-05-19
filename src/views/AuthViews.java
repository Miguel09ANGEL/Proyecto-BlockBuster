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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class AuthViews extends JFrame {

	public AuthViews() {
		// TODO Auto-generated constructor stub

	}

	public void PanelAdministrador() {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 15); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Configuración básica de la ventana
		setTitle("Panel Administrador");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Usamos JLayeredPane para superponer componentes
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(1280, 7200));
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
		panelCentral.setBounds(312, 70, 380, 475); // (x, y, ancho, alto)
		layeredPane.add(panelCentral, JLayeredPane.PALETTE_LAYER);

		// Logotipo
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/images/Block.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
		logo.setBounds(156, 11, 70, 70); // posicion
		panelCentral.add(logo);

		JLabel iniciar = new JLabel("PANEL ADMINISTRADOR");
		iniciar.setSize(250, 60);
		iniciar.setLocation(66, 75);
		iniciar.setHorizontalAlignment(JLabel.CENTER);
		iniciar.setFont(new Font("Calibri", Font.BOLD, 24));
		panelCentral.add(iniciar);

		JLabel correo = new JLabel("Ingresar correo:");
		correo.setHorizontalAlignment(SwingConstants.CENTER);
		correo.setFont(new Font("SansSerif", Font.BOLD, 20));
		correo.setBounds(39, 146, 158, 26);
		panelCentral.add(correo);

		JTextField gmail = new JTextField();
		gmail.setBackground(Color.decode("#D9D9D9"));
		gmail.setSize(290, 30);
		gmail.setLocation(39, 288);
		gmail.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(gmail);

		JLabel contraseña = new JLabel("Contraseña:");
		contraseña.setSize(116, 26);
		contraseña.setLocation(39, 263);
		contraseña.setHorizontalAlignment(JLabel.CENTER);
		contraseña.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelCentral.add(contraseña);

		JTextField password = new JTextField();
		password.setBorder(BorderFactory.createLineBorder(Color.decode("#10A7DE")));
		password.setBackground(Color.decode("#D9D9D9"));
		password.setSize(290, 30);
		password.setLocation(39, 170);
		password.setFont(new Font("Montserrat ", Font.BOLD, 15));
		panelCentral.add(password);

		JButton acceder = new JButton("Acceder");
		acceder.setBackground(Color.decode("#263C54")); // Color de fondo (azul oscuro)
		acceder.setForeground(Color.WHITE); // Color del texto (blanco)
		acceder.setFont(new Font("Calibri", Font.BOLD, 15));
		acceder.setBounds(40, 371, 289, 26); 
		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				Inicio(); 
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

	public void Inicio() {
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

			HomeView ventana = new HomeView();
			ventana.AdministradorJuegos(); // Abre la segunda ventana
			dispose(); // Cierra la ventana actual
		});

		JButton btnRentaYCompra = new JButton("RENTA Y COMPRA");
		btnRentaYCompra.setForeground(Color.WHITE);
		btnRentaYCompra.setFont(new Font("Calibri", Font.BOLD, 16));
		btnRentaYCompra.setBackground(new Color(38, 60, 84));
		btnRentaYCompra.setBounds(10, 242, 237, 100);
		btnRentaYCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HomeView ventana = new HomeView();
				ventana.AdministradorRentaCompra(); // Abre la segunda ventana
				dispose();
			}
		});
		panelIzq.add(btnRentaYCompra);

		JButton btnClientes_3 = new JButton("NUEVA OPERACIÓN");
		btnClientes_3.setForeground(Color.WHITE);
		btnClientes_3.setFont(new Font("Calibri", Font.BOLD, 16));
		btnClientes_3.setBackground(new Color(38, 60, 84));
		btnClientes_3.setBounds(10, 364, 237, 100);
		panelIzq.add(btnClientes_3);

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

}

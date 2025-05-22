package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VideoGamesModel {

	private List<VideoGames> videogames = new ArrayList<>();

	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
	String usuario = "freedb_G_user";
	String contraseña = "%eeFW9csb4$?Dcj";

//	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
//	String usuario = "root";
//	String contraseña = "";

	public VideoGamesModel() {
		// TODO Auto-generated constructor stub
	}

	public List getAllVideogames() {

		String query = "SELECT * FROM videojuegos";

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, contraseña);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Integer id = rs.getInt(1);
				String nombre = rs.getString(2);
				String plataforma = rs.getString(3);
				int añoLanzamiento = rs.getInt(4);
				boolean disponibilidad = rs.getBoolean(5);
				String clasificacion = rs.getString(6);
				String genero = rs.getString(7);
				int existencias = rs.getInt(8);
				BigDecimal precioRenta = rs.getBigDecimal(9);
				BigDecimal precioVenta = rs.getBigDecimal(10);
				String desarrolladoPor = rs.getString(11);
				String descripcion = rs.getString(12);

				videogames.add(new VideoGames(id, nombre, plataforma, añoLanzamiento, disponibilidad, clasificacion,
						genero, existencias, precioRenta, precioVenta, desarrolladoPor, descripcion, null, null));

			}

			rs.close();

			return videogames;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return videogames;
	}

	public boolean removeVideogame(int id) {

		String query = "DELETE FROM videojuegos WHERE id = " + id;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, contraseña);
			stmt = conn.createStatement();

			stmt.executeUpdate(query);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return false;

	}

	public VideoGames getVideojuegos(int id) {

		String query = "select * from videojuegos WHERE id = " + id;

		Connection conn = null;
		Statement stmt = null;

		VideoGames myVideogame = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, contraseña);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String nombre = rs.getString(2);
				String plataforma = rs.getString(3);
				int añoLanzamiento = rs.getInt(4);
				boolean disponibilidad = rs.getBoolean(5);
				String clasificacion = rs.getString(6);
				String genero = rs.getString(7);
				int existencias = rs.getInt(8);
				BigDecimal precioRenta = rs.getBigDecimal(9);
				BigDecimal precioVenta = rs.getBigDecimal(10);
				String desarrolladoPor = rs.getString(11);
				String descripcion = rs.getString(12);

				System.out.println("ID Videojuego: " + id);
				System.out.println("Nombre: " + nombre);
				System.out.println("Plataforma: " + plataforma);

				// Crear objeto VideoGames con los datos obtenidos
				myVideogame = new VideoGames(id, nombre, plataforma, añoLanzamiento, disponibilidad, clasificacion,
						genero, existencias, precioRenta, precioVenta, desarrolladoPor, descripcion, null, null);

			}

			rs.close();

			return myVideogame;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return myVideogame;
	}

	public boolean updateVideogame(int id, String nombre, String plataforma, int añoLanzamiento, boolean disponibilidad,
			String clasificacion, String genero, int existencias, BigDecimal precioRenta, BigDecimal precioVenta,
			String desarrolladoPor, String descripcion) {

		String query = "UPDATE videojuegos SET " + "nombre = '" + nombre + "', " + "plataforma = '" + plataforma + "', "
				+ "año_lanzamiento = " + añoLanzamiento + ", " + "disponibilidad = " + disponibilidad + ", "
				+ "clasificacion = '" + clasificacion + "', " + "genero = '" + genero + "', "
				+ "existencias_disponibles = " + existencias + ", " + "precio_renta = " + precioRenta + ", "
				+ "precio_venta = " + precioVenta + ", " + "desarrollado_por = '" + desarrolladoPor + "', "
				+ "descripcion = " + (descripcion != null ? "'" + descripcion + "'" : "NULL") + " " + "WHERE id = "
				+ id;

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, contraseña);
			stmt = conn.createStatement();

			int rowsAffected = stmt.executeUpdate(query);
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

		public boolean addVideogame(String nombre, String plataforma, int añoLanzamiento, boolean disponibilidad,
				String clasificacion, String genero, int existencias, BigDecimal precioRenta, BigDecimal precioVenta,
				String desarrolladoPor, String descripcion) {
	
			String query = "INSERT INTO videojuegos (nombre, plataforma, año_lanzamiento, "
					+ "disponibilidad, clasificacion, genero, existencias_disponibles, "
					+ "precio_renta, precio_venta, desarrollado_por, descripcion) " + "VALUES ('" + nombre + "', '"
					+ plataforma + "', " + añoLanzamiento + ", " + disponibilidad  + ", '" + clasificacion + "', '"
					+ genero + "', " + existencias + ", " + precioRenta + ", " + precioVenta + ", '" + desarrolladoPor
					+ "', '" + descripcion + "')";
	
			Connection conn = null;
			Statement stmt = null;
	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, usuario, contraseña);
				stmt = conn.createStatement();
	
				int rowsAffected = stmt.executeUpdate(query);
				return rowsAffected > 0;
	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
			return false;
		}

}

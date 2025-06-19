package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.DatabaseConfg;

public class VideoGamesModel {

	private List<VideoGames> videogames = new ArrayList<>();
	DatabaseConfg credentials = new DatabaseConfg();
	
	String url = credentials.URL;
	String user = credentials.USER;
	String password = credentials.PASSWORD;
		
	public VideoGamesModel() {
		// TODO Auto-generated constructor stub
	}

	// trae todos los videojuegos de la base de datos
	public List getAllVideogames() {

		String query = "SELECT * FROM video_games";

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
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

	// elimina videojuegoss de la base de datos
	public boolean deleteVideoGame(int id) {

		String query = "DELETE FROM video_games WHERE id = " + id;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
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

	// trae el videojuego de la bd con el id
	public VideoGames getVideogames(int id) {

		String query = "select * from video_games WHERE id = " + id;

		Connection conn = null;
		Statement stmt = null;

		VideoGames myVideogame = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
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

	// aptualiza el videojuego que esta en la bd
	public boolean updateVideogame(int id, String nombre, String plataforma, int añoLanzamiento, boolean disponibilidad,
			String clasificacion, String genero, int existencias, BigDecimal precioRenta, BigDecimal precioVenta,
			String desarrolladoPor, String descripcion) {

		String query = "UPDATE video_games SET " + "name = '" + nombre + "', " + "platform = '" + plataforma + "', "
				+ "release_year = " + añoLanzamiento + ", " + "is_available = " + disponibilidad + ", "
				+ "classification = '" + clasificacion + "', " + "genre = '" + genero + "', " + "available_stock = "
				+ existencias + ", " + "rent_price = " + precioRenta + ", " + "sale_price = " + precioVenta + ", "
				+ "developed_by = '" + desarrolladoPor + "', " + "description = "
				+ (descripcion != null ? "'" + descripcion + "'" : "NULL") + " " + "WHERE id = " + id;

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
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

	// agrega un videojuego a la  bd
	public boolean addVideogame(String nombre, String plataforma, int añoLanzamiento, boolean disponibilidad,
				String clasificacion, String genero, int existencias, BigDecimal precioRenta, BigDecimal precioVenta,
				String desarrolladoPor, String descripcion) {
	
			String query = "INSERT INTO video_games (name, platform, release_year, "
					+ "is_available, classification, genre, available_stock, "
					+ "rent_price, sale_price, developed_by, description) " + "VALUES ('" + nombre + "', '"
					+ plataforma + "', " + añoLanzamiento + ", " + disponibilidad  + ", '" + clasificacion + "', '"
					+ genero + "', " + existencias + ", " + precioRenta + ", " + precioVenta + ", '" + desarrolladoPor
					+ "', '" + descripcion + "')";
	
			Connection conn = null;
			Statement stmt = null;
	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(url, user, password);
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

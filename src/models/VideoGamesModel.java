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

//	String url = "jdbc:mysql://127.0.0.1:3306/Base_de_datos_renta?useSSL=false";
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
				int disponibilidad = rs.getInt(4);
				BigDecimal preciorenta = rs.getBigDecimal(5);
				BigDecimal precioCompra = rs.getBigDecimal(6);
				String clasificacion = rs.getString(7);
				String desarrolladores = rs.getString(8);
				String genero = rs.getString(9);
				String acercaDe = rs.getString(10);

				videogames.add(new VideoGames(id, nombre, plataforma, disponibilidad, preciorenta, precioCompra,
						clasificacion, desarrolladores, genero, acercaDe, null, null));
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
                Integer disponibilidad = rs.getInt(4);
                BigDecimal precioRenta = rs.getBigDecimal(5);
                BigDecimal precioCompra = rs.getBigDecimal(6);
                String clasificacion = rs.getString(7);
                String desarrolladores = rs.getString(8);
                String genero = rs.getString(9);
                String acercaDe = rs.getString(10);

                System.out.println("ID Videojuego: " + id);
                System.out.println("Nombre: " + nombre);

                myVideogame = new VideoGames(id, nombre, plataforma, disponibilidad, 
                                           precioRenta, precioCompra, clasificacion, 
                                           desarrolladores, genero, acercaDe, null, null);
				
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

}

package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminsModel {
	
	public AdminsModel() {
		
	}
	private List<Admins> administrador = new ArrayList<>();

	
	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
	String usuario = "freedb_G_user";
	String contraseña = "%eeFW9csb4$?Dcj";
	
//	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
//	String usuario = "root";
//	String contraseña = "8163264gA?¡";
	
	public List getAll() {

		String query = "select * from administradores";

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
				String correo = rs.getString(3);

				administrador.add(new Admins(id, nombre, correo));
			}

			rs.close();

			return administrador;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return administrador;
	}

}

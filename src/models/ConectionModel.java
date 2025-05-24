package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectionModel {

//	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
//	String usuario = "freedb_G_user";
//	String contraseña = "%eeFW9csb4$?Dcj";

	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
	String usuario = "root";
	String contraseña = "8163264gA?¡";

	public ConectionModel() {
		String query = "select * from users";
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, contraseña);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				//String lastName = rs.getString(3);
				//Date dob = rs.getDate(4);
				
				System.out.println("empId:" + id);
				System.out.println("firstName:" + name);
				 
				System.out.println("");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {}
		}
	}

	
}

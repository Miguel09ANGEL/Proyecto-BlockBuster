package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthModel {

	private List<Admins> administrators  = new ArrayList<>();

//	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
//	String user = "freedb_G_user";
//	String password = "%eeFW9csb4$?Dcj";
	
	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
	String user = "root";
	String password = "8163264gA?¡";
	
	public List<Admins> getAll() {
        String query = "SELECT * FROM administrators";
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String email = rs.getString("email");
                String pass = rs.getString("password");

                administrators .add(new Admins(id, firstName, lastName, middleName, email, pass));
            }

            rs.close();
            return administrators ;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }

        return administrators ;
    }
	
	 public Admins authenticate(String first_name, String pass) {
	        String query = "SELECT * FROM administrators WHERE first_name = ? AND password = ?";
	        
	        try (Connection conn = DriverManager.getConnection(url, user, password);
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setString(1, first_name);
	            stmt.setString(2, pass);

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String firstName = rs.getString("first_name");
	                String lastName = rs.getString("last_name");
	                String middleName = rs.getString("middle_name");
	                String correo = rs.getString("email");
	                String contraseña = rs.getString("password");

	                return new Admins(id, firstName, lastName, middleName, correo, contraseña);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	
}

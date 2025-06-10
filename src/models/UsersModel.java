package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Maneja las operaciones de base de datos relacionadas con la tabla 'clientes'.
public class UsersModel {

	private List<User> usuarios = new ArrayList<>();

	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
	String user = "freedb_G_user";
	String password = "%eeFW9csb4$?Dcj";

//	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
//	String user = "root";
//	String password = "";

	public UsersModel() {
		// TODO Auto-generated constructor stub
	}
	
	// selecciona todos los clientes de la base de datos.
	public List getAll() {

		String query = "select * from customers";

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
				String apellidoPaterno = rs.getString(3);
				String apellidoMaterno = rs.getString(4);
				Date fechaNacimiento = rs.getDate(5);
				String telefono = rs.getString(6);
				String correo = rs.getString(7);
				Date fechaRegistro = rs.getDate(8);
				

				System.out.println("");

				usuarios.add(new User(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, telefono, correo, fechaRegistro, null));
			}

			rs.close();

			return usuarios;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return usuarios;
	}

	// Elimina un cliente por ID.
	public boolean deleteUser(int id) {
	    String deleteTransactions = "DELETE FROM transactions WHERE customer_id = " + id;
	    String deleteCustomer = "DELETE FROM customers WHERE id = " + id;

	    Connection conn = null;
	    Statement stmt = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(url, user, password);
	        stmt = conn.createStatement();

	        // Primero eliminamos las transacciones asociadas
	        stmt.executeUpdate(deleteTransactions);
	        
	        // Luego eliminamos el cliente
	        int rowsAffected = stmt.executeUpdate(deleteCustomer);

	        return rowsAffected > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}
	// agregamos un usario a la base de datos
	public boolean add(String name, String apellidoPaterno, 
			String apellidoMaterno,java.util.Date fechaNacimiento,String telefono, String correo) {
		
	    String query = "INSERT INTO customers (first_name, last_name, middle_name, date_of_birth, phone, email) "
	    		+ "VALUES ('"+name+"', '"+apellidoPaterno+"', '"+apellidoMaterno+"', '"+fechaNacimiento+"', '"+telefono+"', '"+correo+"')";

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

	// Actualiza la información de un cliente.
	public boolean updateUser(int id, String nombre, String apellidoP, String apellidoM, Date fechaNacimiento, String telefono, String correo) {

		String query = "UPDATE customers SET " +
                "first_name = '" + nombre + "', " +
                "last_name = '" + apellidoP + "', " +
                "middle_name = " + (apellidoM != null ? "'" + apellidoM + "'" : "NULL") + ", " +
                "date_of_birth = '"+ fechaNacimiento + "', " +
                "phone = " + (telefono != null ? "'" + telefono + "'" : "NULL") + ", " +
                "email = '" + correo + "' " +
                "WHERE id = " + id;

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
	
	// Encuentra un cliente por ID.
	public User get(int id) {

		String query = "select * from customers WHERE id = " + id;

		Connection conn = null;
		Statement stmt = null;

		User myUser = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				
				String nombre = rs.getString(2);
				String apellidoPaterno = rs.getString(3);
				String apellidoMaterno = rs.getString(4);
				Date fechaNacimiento = rs.getDate(5);
				String telefono = rs.getString(6);
				String correo = rs.getString(7);
				Date fechaRegistro = rs.getDate(8);


				System.out.println("empId:" + id);
				System.out.println("firstName:" + nombre);

				System.out.println("");

				
				myUser = new User(id, nombre, apellidoPaterno, apellidoMaterno,
						fechaNacimiento, telefono, correo, fechaRegistro, null);
			}

			rs.close();

			return myUser;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return myUser;
	}

}

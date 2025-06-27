package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import utils.DatabaseConfg;

public class TransactionModel {

	DatabaseConfg credentials = new DatabaseConfg();
	
	String url = credentials.URL;
	String user = credentials.USER;
	String password = credentials.PASSWORD;

	// regresa todas las transacciones renta y ventas de la base de datos.
	public List<Transaction> getAllTransactions() {
		List<Transaction> transacciones = new ArrayList<>();
		String query = "SELECT t.*, vg.name AS video_game_name, "
				+ "CONCAT(c.first_name, ' ', c.last_name) AS customer_name " + "FROM transactions t "
				+ "JOIN video_games vg ON t.video_game_id = vg.id " + "JOIN customers c ON t.customer_id = c.id";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer customerId = rs.getInt("customer_id");
				Integer videoGameId = rs.getInt("video_game_id");
				String videoGameName = rs.getString("video_game_name");
				String customerName = rs.getString("customer_name");
				String transactionType = rs.getString("transaction_type");
				Date transactionDate = new Date(rs.getTimestamp("transaction_date").getTime());
				Date returnDate = rs.getTimestamp("return_date") != null
						? new Date(rs.getTimestamp("return_date").getTime())
						: null;
				BigDecimal price = rs.getBigDecimal("price");
				Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
				Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());

				transacciones.add(new Transaction(id, customerId, videoGameId, customerName, videoGameName,
						transactionType, transactionDate, returnDate, price, createdAt, updatedAt));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transacciones;
	}

	// regresa solo las rentas de la bd
	public List<Transaction> getAllRentals() {
		List<Transaction> rentals = new ArrayList<>();
		String query = "SELECT t.*, vg.name AS video_game_name, "
				+ "CONCAT(c.first_name, ' ', c.last_name) AS customer_name " + "FROM transactions t "
				+ "JOIN video_games vg ON t.video_game_id = vg.id " + "JOIN customers c ON t.customer_id = c.id "
				+ "WHERE t.transaction_type = 'rental'";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer customerId = rs.getInt("customer_id");
				Integer videoGameId = rs.getInt("video_game_id");
				String videoGameName = rs.getString("video_game_name");
				String customerName = rs.getString("customer_name");
				String transactionType = rs.getString("transaction_type");
				Date transactionDate = new Date(rs.getTimestamp("transaction_date").getTime());
				Date returnDate = rs.getTimestamp("return_date") != null
						? new Date(rs.getTimestamp("return_date").getTime())
						: null;
				BigDecimal price = rs.getBigDecimal("price");
				Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
				Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());

				rentals.add(new Transaction(id, customerId, videoGameId, customerName, videoGameName, transactionType,
						transactionDate, returnDate, price, createdAt, updatedAt));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rentals;
	}

	// crea un renta o venta e la bd opteniendo el id de el cliente y el videojuego
	public boolean createTransaction(int customerId, int videoGameId, String transactionType, Date returnDate,
	        BigDecimal price, String status) {
	    // Consultas SQL
	    String transactionQuery = "INSERT INTO transactions (customer_id, video_game_id, transaction_type, "
	            + "transaction_date, return_date, price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    String updateStockQuery = "UPDATE video_games SET available_stock = available_stock - 1 WHERE id = ?";
	    String verifyStockQuery = "SELECT available_stock FROM video_games WHERE id = ? FOR UPDATE";
	    String verifyGameQuery = "SELECT id FROM video_games WHERE id = ?";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    boolean success = false;

	    try {
	        // 1. Establecer conexión
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(url, user, password);
	        conn.setAutoCommit(false);

	        System.out.println("=== INICIO DE TRANSACCIÓN ===");
	        System.out.println("Tipo de transacción recibido: '" + transactionType + "'");

	        // 2. Si es rental (alquiler), verificar y actualizar stock
	        if (transactionType != null && transactionType.equalsIgnoreCase("rental")) {
	            System.out.println("Procesando una RENTA - verificando stock...");
	            
	            // Verificar stock
	            pstmt = conn.prepareStatement(verifyStockQuery);
	            pstmt.setInt(1, videoGameId);
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                int currentStock = rs.getInt("available_stock");
	                System.out.println("Stock actual: " + currentStock);
	                
	                if (currentStock <= 0) {
	                    System.out.println("ERROR: Stock insuficiente");
	                    conn.rollback();
	                    return false;
	                }

	                // Actualizar stock
	                pstmt = conn.prepareStatement(updateStockQuery);
	                pstmt.setInt(1, videoGameId);
	                int updated = pstmt.executeUpdate();
	                
	                if (updated == 1) {
	                    System.out.println("Stock actualizado correctamente");
	                } else {
	                    System.out.println("ERROR: No se pudo actualizar el stock");
	                    conn.rollback();
	                    return false;
	                }
	            }
	        } else {
	            System.out.println("Transacción de tipo: " + transactionType + " - No se actualiza stock");
	        }

	        // 3. Crear el registro de transacción
	        System.out.println("Creando registro de transacción...");
	        pstmt = conn.prepareStatement(transactionQuery);
	        pstmt.setInt(1, customerId);
	        pstmt.setInt(2, videoGameId);
	        pstmt.setString(3, transactionType.toLowerCase()); // Asegurar minúsculas para el ENUM
	        pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
	        pstmt.setTimestamp(5, returnDate != null ? new java.sql.Timestamp(returnDate.getTime()) : null);
	        pstmt.setBigDecimal(6, price);
	        pstmt.setString(7, status.toLowerCase()); // Asegurar minúsculas para el ENUM

	        int created = pstmt.executeUpdate();
	        if (created == 1) {
	            conn.commit();
	            System.out.println("Transacción completada con éxito!");
	            success = true;
	        } else {
	            conn.rollback();
	            System.out.println("ERROR: No se pudo crear la transacción");
	        }

	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	        try {
	            if (conn != null) conn.rollback();
	        } catch (Exception ex) {
	            System.out.println("ERROR en rollback: " + ex.getMessage());
	        }
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) {
	                conn.setAutoCommit(true);
	                conn.close();
	            }
	        } catch (Exception e) {
	            System.out.println("ERROR cerrando conexiones: " + e.getMessage());
	        }
	    }

	    return success;
	}
	
	// aqui se optiene toda la informacion de renta realizado por un cliente
	public List<Transaction> getRentalsByUser(int customerId) {
		List<Transaction> transacciones = new ArrayList<>();
		String query = "SELECT t.id, t.customer_id, t.video_game_id, vg.name AS video_game_name, "
				+ "t.transaction_type, t.transaction_date, t.return_date, t.price, " + "t.created_at, t.updated_at "
				+ "FROM transactions t " + "JOIN video_games vg ON t.video_game_id = vg.id "
				+ "WHERE t.customer_id = ? AND t.transaction_type = 'rental'";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Transaction t = new Transaction(rs.getInt("id"), rs.getInt("customer_id"), rs.getInt("video_game_id"),
						null, rs.getString("video_game_name"), rs.getString("transaction_type"),
						new Date(rs.getTimestamp("transaction_date").getTime()),
						rs.getTimestamp("return_date") != null ? new Date(rs.getTimestamp("return_date").getTime())
								: null,
						rs.getBigDecimal("price"), new Date(rs.getTimestamp("created_at").getTime()),
						new Date(rs.getTimestamp("updated_at").getTime()));
				transacciones.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transacciones;
	}

	// aqui se optiene toda la informacion de venta realizado por un cliente
	public List<Transaction> getSimplePurchasesByUser(int customerId) {
		List<Transaction> purchases = new ArrayList<>();
		String query = "SELECT t.id, vg.name AS video_game_name, t.transaction_date, t.price " + "FROM transactions t "
				+ "JOIN video_games vg ON t.video_game_id = vg.id "
				+ "WHERE t.customer_id = ? AND t.transaction_type = 'sale'";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getInt("id"), 
						customerId, 
						0, 
						null, rs.getString("video_game_name"), 
						"sale", 
						new Date(rs.getTimestamp("transaction_date").getTime()), 
						null, 
						rs.getBigDecimal("price"), null, 
						null 
				);
				purchases.add(transaction);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return purchases;
	}
}

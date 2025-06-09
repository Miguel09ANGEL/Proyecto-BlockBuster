package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TransactionModel {

//	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
//	String user = "freedb_G_user";
//	String password = "%eeFW9csb4$?Dcj";

	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
	String user = "root";
	String password = "8163264gA?¡";

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

	public boolean createTransaction(int customerId, int videoGameId, String transactionType, Date returnDate,
			BigDecimal price, String status) {
		String query = "INSERT INTO transactions (customer_id, video_game_id, transaction_type, "
				+ "transaction_date, return_date, price, status) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, customerId);
			pstmt.setInt(2, videoGameId);
			pstmt.setString(3, transactionType);
			pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));

			if (returnDate != null) {
				pstmt.setDate(5, new java.sql.Date(returnDate.getTime()));
			} else {
				pstmt.setNull(5, Types.DATE);
			}

			pstmt.setBigDecimal(6, price);
			pstmt.setString(7, status);

			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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
				Transaction transaction = new Transaction(rs.getInt("id"), // id
						customerId, // customerId
						0, // videoGameId
						null, rs.getString("video_game_name"), // videoGameName
						"sale", // transactionType
						new Date(rs.getTimestamp("transaction_date").getTime()), // transactionDate
						null, // returnDate
						rs.getBigDecimal("price"), null, // createdAt
						null // updatedAt
				);
				purchases.add(transaction);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return purchases;
	}
}

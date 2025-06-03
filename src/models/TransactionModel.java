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

	private List<Transaction> transacciones = new ArrayList<>();

	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
	String user = "freedb_G_user";
	String password = "%eeFW9csb4$?Dcj";
	
//	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
//	String user = "root";
//	String password = "";

	public List<Transaction> getAllTransactions() {
		String query = "SELECT * FROM transactions";

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer customerId = rs.getInt(2);
				Integer videoGameId = rs.getInt(3);
				String transactionType = rs.getString(4);
				Date transactionDate = new Date(rs.getTimestamp(5).getTime());
				Date returnDate = rs.getTimestamp(6) != null ? new Date(rs.getTimestamp(6).getTime()) : null;
				BigDecimal price = rs.getBigDecimal(7);
				Date createdAt = new Date(rs.getTimestamp(8).getTime());
				Date updatedAt = new Date(rs.getTimestamp(9).getTime());

				System.out.println(""); // Salto de línea para separar registros

				transacciones.add(new Transaction(id, customerId, videoGameId, transactionType, transactionDate,
						returnDate, price, createdAt, updatedAt));
			}

			rs.close();
			return transacciones;

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

		return transacciones;
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
	
	
}

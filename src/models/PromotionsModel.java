package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromotionsModel {
	
	private List<Promotions> promocion = new ArrayList<>();

	String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_Base_de_datos_renta?useSSL=false";
	String user = "freedb_G_user";
	String password = "%eeFW9csb4$?Dcj";

//	String url = "jdbc:mysql://127.0.0.1:3306/base_de_datos_renta";
//	String user = "root";
//	String password = "";
	
	//  Esta clase maneja las operaciones de base de datos relacionadas con las promociones.
	public List getAllPromotions() {

		String query = "select * from promotions";

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Integer id = rs.getInt(1);
				BigDecimal purchaseAmount = rs.getBigDecimal(2);
				BigDecimal discountAmount = rs.getBigDecimal(3);
				

				promocion.add(new Promotions(id, purchaseAmount, discountAmount));
			}

			rs.close();

			return promocion;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}

		return promocion;
	}
	
	//	Actualiza una promoción en la base de datos
	public boolean updatePromotion(int id, BigDecimal purchaseAmount, BigDecimal discountAmount) {
	    String query = "UPDATE promotions SET " +
	            "purchase_amount = " + purchaseAmount + ", " +
	            "frequency_discount = " + discountAmount + " " +
	            "WHERE id = " + id;

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
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}
}

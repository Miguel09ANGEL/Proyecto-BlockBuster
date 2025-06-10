package models;

import java.math.BigDecimal;

public class Promotions {
	
	private int id;
	private BigDecimal purchaseAmount;
	private BigDecimal discountAmount;

	public Promotions(int id,BigDecimal purchaseAmount,BigDecimal discountAmount ) {
		this.id = id;
		this.purchaseAmount = purchaseAmount;
		this.discountAmount = discountAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getpurchaseAmount() {
		return purchaseAmount;
	}

	public void setpurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public BigDecimal getdiscountAmount() {
		return discountAmount;
	}

	public void setdiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
	
}

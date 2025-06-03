package models;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {
	
	private int id;
    private int customerId;
    private int videoGameId;
    private String transactionType; // "VENTA" o "RENTA"
    private Date transactionDate;
    private Date returnDate;       
    private BigDecimal price;
    private Date createdAt;       
    private Date updatedAt;      

    // Constructor completo
    public Transaction(int id, int customerId, int videoGameId, String transactionType,
                     Date transactionDate, Date returnDate, BigDecimal price,
                     Date createdAt, Date updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.videoGameId = videoGameId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.returnDate = returnDate;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVideoGameId() {
		return videoGameId;
	}

	public void setVideoGameId(int videoGameId) {
		this.videoGameId = videoGameId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    

}

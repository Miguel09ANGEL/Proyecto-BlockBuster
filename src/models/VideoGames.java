package models;

import java.math.BigDecimal;
import java.sql.Date;

public class VideoGames {
    private int id;
    private String name;
    private String platform;
    private int releaseYear;
    private boolean isAvailable;
    private String classification;
    private String genre;
    private int availableStock;
    private BigDecimal rentPrice;
    private BigDecimal salePrice;
    private String developedBy;
    private String descripcion;
    private Date createdAt;
    private Date updatedAt;

    // Constructor completo
    public VideoGames(int id, String name, String platform, int releaseYear,
                    boolean isAvailable, String classification, String genre,
                    int availableStock, BigDecimal rentPrice,
                    BigDecimal salePrice, String developedBy, String descripcion,
                    Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.isAvailable = isAvailable;
        this.classification = classification;
        this.genre = genre;
        this.availableStock = availableStock;
        this.rentPrice = rentPrice;
        this.salePrice = salePrice;
        this.developedBy = developedBy;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getplatform() {
		return platform;
	}

	public void setplatform(String platform) {
		this.platform = platform;
	}

	public int getreleaseYear() {
		return releaseYear;
	}

	public void setreleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setisAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getclassification() {
		return classification;
	}

	public void setclassification(String classification) {
		this.classification = classification;
	}

	public String getGenero() {
		return genre;
	}

	public void setGenero(String genero) {
		this.genre = genero;
	}

	public int getavailableStock() {
		return availableStock;
	}

	public void setavailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public BigDecimal getrentPrice() {
		return rentPrice;
	}

	public void setPrecioRenta(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}

	public BigDecimal getsalePrice() {
		return salePrice;
	}

	public void setsalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getdevelopedBy() {
		return developedBy;
	}

	public void setdevelopedBy(String developedBy) {
		this.developedBy = developedBy;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

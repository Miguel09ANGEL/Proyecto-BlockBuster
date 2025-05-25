package models;

import java.math.BigDecimal;

public class Promotions {
	
	private int id;
	private BigDecimal compraCantida;
	private BigDecimal promocionCompra;

	public Promotions(int id,BigDecimal compraCantida,BigDecimal promocionCompra ) {
		this.id = id;
		this.compraCantida = compraCantida;
		this.promocionCompra = promocionCompra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCompraCantida() {
		return compraCantida;
	}

	public void setCompraCantida(BigDecimal compraCantida) {
		this.compraCantida = compraCantida;
	}

	public BigDecimal getPromocionCompra() {
		return promocionCompra;
	}

	public void setPromocionCompra(BigDecimal promocionCompra) {
		this.promocionCompra = promocionCompra;
	}
	
	
	
}

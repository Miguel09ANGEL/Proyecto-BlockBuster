package models;

import java.math.BigDecimal;
import java.sql.Date;

public class VideoGames {
	private int id;
	private String nombre;
	private String plataforma;
	private boolean disponibilidad;
	private BigDecimal precioranta;
	private BigDecimal precioCompra;
	private String clasificacion;
	private String desarrolladores;
	private String genero;
	private String acercaDe;
	private Date createdAt;
	private Date updatedAt;

	// Constructor completo
	public VideoGames(int id,String nombre, String plataforma, boolean disponibilidad,
                     BigDecimal precioranta, BigDecimal precioCompra, String clasificacion,
                     String desarrolladores, String genero, String acercaDe,
                     Date createdAt, Date updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.disponibilidad = disponibilidad;
        this.precioranta = precioranta;
        this.precioCompra = precioCompra;
        this.clasificacion = clasificacion;
        this.desarrolladores = desarrolladores;
        this.genero = genero;
        this.acercaDe = acercaDe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public BigDecimal getPrecioranta() {
		return precioranta;
	}

	public void setPrecioranta(BigDecimal precioranta) {
		this.precioranta = precioranta;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getDesarrolladores() {
		return desarrolladores;
	}

	public void setDesarrolladores(String desarrolladores) {
		this.desarrolladores = desarrolladores;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAcercaDe() {
		return acercaDe;
	}

	public void setAcercaDe(String acercaDe) {
		this.acercaDe = acercaDe;
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

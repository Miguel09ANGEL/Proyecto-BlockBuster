package models;

import java.math.BigDecimal;
import java.sql.Date;

public class VideoGames {
    private int id;
    private String nombre;
    private String plataforma;
    private int añoLanzamiento;
    private boolean disponibilidad;
    private String clasificacion;
    private String genero;
    private int existenciasDisponibles;
    private BigDecimal precioRenta;
    private BigDecimal precioVenta;
    private String desarrolladoPor;
    private String descripcion;
    
    private Date createdAt;
    private Date updatedAt;

    // Constructor completo
    public VideoGames(int id, String nombre, String plataforma, int añoLanzamiento,
                    boolean disponibilidad, String clasificacion, String genero,
                    int existenciasDisponibles, BigDecimal precioRenta,
                    BigDecimal precioVenta, String desarrolladoPor, String descripcion,
                    Date createdAt, Date updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.añoLanzamiento = añoLanzamiento;
        this.disponibilidad = disponibilidad;
        this.clasificacion = clasificacion;
        this.genero = genero;
        this.existenciasDisponibles = existenciasDisponibles;
        this.precioRenta = precioRenta;
        this.precioVenta = precioVenta;
        this.desarrolladoPor = desarrolladoPor;
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

	public int getAñoLanzamiento() {
		return añoLanzamiento;
	}

	public void setAñoLanzamiento(int añoLanzamiento) {
		this.añoLanzamiento = añoLanzamiento;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getExistenciasDisponibles() {
		return existenciasDisponibles;
	}

	public void setExistenciasDisponibles(int existenciasDisponibles) {
		this.existenciasDisponibles = existenciasDisponibles;
	}

	public BigDecimal getPrecioRenta() {
		return precioRenta;
	}

	public void setPrecioRenta(BigDecimal precioRenta) {
		this.precioRenta = precioRenta;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getDesarrolladoPor() {
		return desarrolladoPor;
	}

	public void setDesarrolladoPor(String desarrolladoPor) {
		this.desarrolladoPor = desarrolladoPor;
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

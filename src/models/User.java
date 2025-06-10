package models;

import java.sql.Date;


public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String phone;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    // Constructor completo
    public User(int id, String firstName, String lastName, String middleName, 
               Date birthDate, String phone, String email, 
               Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String nombre) {
		this.firstName = nombre;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String apellidoPaterno) {
		this.lastName = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return middleName;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.middleName = apellidoMaterno;
	}

	public Date getFechaNacimiento() {
		return birthDate;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.birthDate = fechaNacimiento;
	}

	public String getTelefono() {
		return phone;
	}

	public void setTelefono(String telefono) {
		this.phone = telefono;
	}

	public String getCorreo() {
		return email;
	}

	public void setCorreo(String correo) {
		this.email = correo;
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

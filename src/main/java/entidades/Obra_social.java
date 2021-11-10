package entidades;

public class Obra_social {
	
	String nombre, apellido;
	Integer id_obra_social;
	
	public Obra_social() {
	}
	
	public Obra_social(String nombre, String apellido, Integer id_obra_social) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_obra_social = id_obra_social;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Integer getId_obra_social() {
		return id_obra_social;
	}

	public void setId_obra_social(Integer id_obra_social) {
		this.id_obra_social = id_obra_social;
	}

}

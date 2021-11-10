package entidades;

public class Consultorio {
	
	String direccion;
	Integer id_consultorio;

	public Consultorio() {
	}
	
	public Consultorio(String direccion, Integer id_consultorio) {
		this.direccion = direccion;
		this.id_consultorio = id_consultorio;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Integer getId_consultorio() {
		return id_consultorio;
	}

	public void setId_consultorio(Integer id_consultorio) {
		this.id_consultorio = id_consultorio;
	}
	
	
}

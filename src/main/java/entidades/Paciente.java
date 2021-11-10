package entidades;

public class Paciente {
	
	String nombre, apellido;
	Integer id_paciente, dni;
	
	public Paciente() {
	}
	
	public Paciente(String nombre, String apellido, Integer id_paciente, Integer dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_paciente = id_paciente;
		this.dni = dni;
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
	
	public Integer getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(Integer id_paciente) {
		this.id_paciente = id_paciente;
	}
	
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

}

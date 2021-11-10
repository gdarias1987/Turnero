package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Medico {
	
	private String nombre, apellido;
	private Integer id_medico, dni;
	private Double precio_consulta;
	private boolean status;

	public Medico() {
	}
	
	public Medico(final String nombre, final String apellido, final Integer id_medico, final Integer dni, final Double precio_consulta, boolean status) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_medico = id_medico;
		this.dni = dni;
		this.precio_consulta = precio_consulta;
		this.status = status;
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
	
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public Integer getId_medico() {
		return id_medico;
	}

	public void setId_medico(Integer id_medico) {
		this.id_medico = id_medico;
	}
	
	public Double getPrecio_consulta() {
		return precio_consulta;
	}

	public void setPrecio_consulta(Double precio_consulta) {
		this.precio_consulta = precio_consulta;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static Medico fromResultSet(ResultSet rs) {
		String apellido = null;
		String nombre = null;
		int dni = 0, id_medico = 0;
		Double precio_consulta = 0D;
		boolean status = false;

		try {
			apellido = rs.getString("apellido");
			nombre = rs.getString("nombre");
			dni = rs.getInt("dni");
			id_medico = rs.getInt("id_medico");
			precio_consulta = rs.getDouble("valor_consulta");
			status = rs.getBoolean("status");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// CREAR MEDICO
		Medico auxMedico = new Medico(nombre, apellido, id_medico, dni, precio_consulta, status);
		return auxMedico;
	}

	public void printoutMedico() {
		System.out.println("*****************************");
		System.out.println("Apellido y nombre: " + this.getApellido() + ", " + this.getNombre());
		System.out.println("DNI: " + this.getDni());
		System.out.println("ID INTERNO: " + this.getId_medico());
		System.out.println("PRECIO CONSULTA: " + this.getPrecio_consulta());
		System.out.println("STATUS: " + this.getStatus());
		System.out.println("*****************************");
		System.out.println("");
	}

	public Object[] medicoToRow() {
		Object[] data = {getApellido(), getNombre(), getDni(), getPrecio_consulta(), getStatus() ? "Activo" : "Inactivo"};
		return data;
	}
}

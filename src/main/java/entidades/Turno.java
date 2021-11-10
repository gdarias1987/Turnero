package entidades;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Turno {
	
	Integer id_turno;
	ZonedDateTime fecha_turno;

	
	public Turno() {
	}
	
	public Turno(ZonedDateTime fecha_turno, Integer id_turno) {
		this.fecha_turno = fecha_turno;
		this.id_turno = id_turno;
	}
	
	public Integer getId_turno() {
		return id_turno;
	}

	public void setId_turno(Integer id_turno) {
		this.id_turno = id_turno;
	}
	
	
	public void testea() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatoCompleto = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.fecha_turno = ZonedDateTime.now(); 
        
        String fechaSola = fecha_turno.format(formatoFecha);
        String horaSola = fecha_turno.format(formatoHora);
        String fechaCompleta = fecha_turno.format(formatoCompleto);
        
        System.out.println(fechaSola);
        System.out.println(horaSola);
        System.out.println(fechaCompleta);
    }
	
	

}

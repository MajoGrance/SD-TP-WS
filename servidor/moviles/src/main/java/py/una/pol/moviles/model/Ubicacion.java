package py.una.pol.moviles.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Ubicacion implements Serializable {
	
	Long id;
	String movil;
	Float latitud;
	Float longitud;
	Timestamp fecha_hora;
	
	List<String> asignaturas;
	
	public Ubicacion(){
		asignaturas = new ArrayList<String>();
	}

	public Ubicacion(Long id, String movil, Float latitud, Float longitud, Timestamp fecha_hora){
		this.id = id;
		this.movil = movil;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fecha_hora = fecha_hora;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
	
	public Timestamp getFechaHora() {
		return fecha_hora;
	}

	public void setFechaHora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

}

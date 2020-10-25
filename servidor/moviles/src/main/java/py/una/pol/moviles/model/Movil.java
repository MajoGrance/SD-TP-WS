package py.una.pol.moviles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Movil implements Serializable {

	String identificador;
	String tipo;
	
	List<String> asignaturas;
	
	public Movil(){
		asignaturas = new ArrayList<String>();
	}

	public Movil(String identificador, String tipo){
		this.identificador = identificador;
		this.tipo = tipo;
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String nombre) {
		this.tipo = nombre;
	}
}

package road_fighter.servidor;

import java.io.Serializable;
import java.util.List;

public class MensajeACliente implements Serializable{




	private static final long serialVersionUID = -3270450619107272291L;
	String texto;
	List<Sala> listaSalas;
	Sala sala;
	int tipo;
	
	public MensajeACliente(String texto, List<Sala> salas, int tipo) {
		this.texto = texto;
		this.listaSalas = salas;
		this.tipo = tipo;
	}
	public MensajeACliente(String texto, int tipo,Sala sala) {
		this.texto = texto;
		this.sala = sala;
		this.tipo = tipo;
	}
	public String getTexto() {
		return texto;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public List<Sala> getSalas() {
		return listaSalas;
	}
	
	public int getTipo() {
		return tipo; 
	}
	
	@Override
	public String toString() {
		return "MensajeACliente [mensaje=" + texto + ", sala=" + sala + ", tipo=" + tipo + "]";
	}
	

}



import java.util.*;


public class AutoJugador extends Auto {


	protected String nombreJugador;
	public AutoJugador(String nombreJugador) {
    	this.nombreJugador = nombreJugador;
    }
    public void habilidadEspecial() {
    	this.velMax+=20;
    }
    public String getNombreJugador() {
    	return nombreJugador;
    }

}
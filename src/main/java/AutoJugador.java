
//import java.util.*;


public class AutoJugador extends Auto {

	private int topeHabilidadEspecial = 5;
	protected String nombreJugador;
	
	public AutoJugador(String nombreJugador, double[] posicion) {
		super(posicion);
    	this.nombreJugador = nombreJugador;
    }
	
    public void habilidadEspecial() {
    	if(topeHabilidadEspecial > 0) {
    		estado.habilidadEspecial();
    		topeHabilidadEspecial--;
    	}
    }
    
    public String getNombreJugador() {
    	return nombreJugador;
    }
}
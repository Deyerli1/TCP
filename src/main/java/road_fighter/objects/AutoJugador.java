package road_fighter.objects;

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
    		this.velMax = 220;
    		topeHabilidadEspecial--;
    	}
    }
        
    public String getNombreJugador() {
    	return nombreJugador;
    }

	@Override
	public void updateHorizontal(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVertical(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelActual(double deltaTime) {
		// TODO Auto-generated method stub
		
	}
}
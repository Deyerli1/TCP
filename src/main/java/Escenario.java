
import java.util.*;

public class Escenario {

	protected Boolean multiplayer;
	protected Mapa mapa;
	protected int cantidadJugadores;
	
	//Tendria tanto auto jugador como auto npc. Y no se cuantos voy a tener, al menos 1 jugador seguro
	protected LinkedList<Auto> autos;
	//O mejor tener autoJugador y autoNpc en listas separadas?
	
	//Por ahora voy a tener 1 jugador seguro y modo offline (?¿)
    public Escenario() {
    	multiplayer = false;
    	mapa = null;
    	cantidadJugadores = 1;
    	autos = new LinkedList<Auto>();
    }


    public void iniciarSingle(Mapa mapaElegido) {
    	this.mapa = mapaElegido;
    }
    
    public void iniciarMulti(int cantidadJugadores, Mapa mapaElegido) {
    	this.cantidadJugadores = cantidadJugadores;
        this.mapa = mapaElegido;
    }
    
    public void agregarAuto(Auto auto) {
    	autos.add(auto);
    }
    
    public Boolean isMultiplayer() {
		return multiplayer;
	}


	public Mapa getMapa() {
		return mapa;
	}


	public LinkedList<Auto> getAutos() {
		return autos;
	}


	public int getCantidadJugadores() {
    	return cantidadJugadores;
    }
}
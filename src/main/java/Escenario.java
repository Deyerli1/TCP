/*import java.util.LinkedList;

import road_fighter.objects.Obstaculo;

public class Escenario {

	private final int MAX_NPC = 5;

	private final int MAX_JUGADORES = 5;
	private final double[][] posiciones = {{50,50}, {50,60}, {50,70}, {50,80}, {50,90}};
	protected Mapa mapa;
	protected int cantidadJugadores;
	
	protected LinkedList<AutoJugador> jugadorList = new LinkedList<AutoJugador>();
	protected LinkedList<AutoNpc> npcList = new LinkedList<AutoNpc>();
	protected LinkedList<Obstaculo> obstaculoList = new LinkedList<Obstaculo>();
	
    public Escenario(int cantidadJugadores, Mapa mapaElegido) {
    	this.cantidadJugadores = cantidadJugadores;
    	this.mapa = mapaElegido;
    }
    
    public void iniciarPartida() {
    }
    
    public void agregarAutoJugador(String nombre) {
    	if(jugadorList.size() < this.cantidadJugadores) {
    		AutoJugador auto = new AutoJugador(nombre, posiciones[jugadorList.size()]);
    		jugadorList.add(auto);
    	}
    }
    
    
    // Que pasa con la generacion cuando el auto se queda quieto
    public void agregarAutoNpc() {
    	if(npcList.size() < MAX_NPC) {
    		npcList.add(AutoNpc.crearAutoNpc());
    	}
    }
    
    public void agregarObstaculo(double x, double y) {
    	obstaculoList.add(Obstaculo.crearObstaculo(y));
    }
    
	public Mapa getMapa() {
		return mapa;
	}

	public int getCantidadAutoJugador() {
		return jugadorList.size();
	}
	
	public int getCantidadAutoNpc() {
		return npcList.size();
	}
	
	public int getCantidadJugadores() {
    	return cantidadJugadores;
    }
}*/
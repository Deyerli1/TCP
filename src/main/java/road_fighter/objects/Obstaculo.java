package road_fighter.objects;

import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public abstract class Obstaculo extends GameObject implements Updatable, Renderable, Collideable {
	protected double x,y;
	protected String sprite;
	protected final int offScreenTolerance = 700;
	protected static double posicionActualJugador;
	public static int obstaculosActuales = 0;
	

	public Obstaculo(double y) {
    	this.y = y;
    	posicionActualJugador = y;
    	obstaculosActuales++;
    }
    
    public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
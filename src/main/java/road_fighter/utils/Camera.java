package road_fighter.utils;

import road_fighter.interfaces.Updatable;
import road_fighter.objects.AutoJugador;

public class Camera implements Updatable {
	
	private AutoJugador auto = null;
	private double x, y;
	
	Camera(AutoJugador auto) {
		this.auto = auto;
		this.x = auto.getX();
		this.y = auto.getY();
	}
	
	public void update(double deltaTime) {
		this.x = auto.getX() + auto.getWidth()/2;
		this.y = auto.getY() + auto.getHeight()/2;
	}
	
	
}

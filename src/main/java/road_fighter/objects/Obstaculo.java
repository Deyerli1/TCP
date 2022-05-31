package road_fighter.objects;
import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public abstract class Obstaculo extends GameObject implements Updatable, Renderable, Collideable {
	protected final double x,y;
	protected String sprite;
	protected ImageView render;
	protected Rectangle collider;

	public Obstaculo(double x, double y) {
    	this.x = x;
    	this.y = y;
    }
    
    public static Obstaculo crearObstaculo(double x, double y) {
		int min = 1;
        int max = 2;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        
        if(value == 1) {
        	return new Pozo(x,y);
        }else {
        	return new ManchaAceite(x,y);
        }
	}
    public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	@Override
	public Shape getCollider() {
		return collider;
	}

}
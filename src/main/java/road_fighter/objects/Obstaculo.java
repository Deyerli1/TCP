package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Despawneable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public abstract class Obstaculo extends GameObject implements Updatable, Renderable, Collideable, Despawneable {
	protected double x,y;
	
	protected final int width = 51;
	protected final int height = 36; 
	
	protected ImageView render;
	private Image obsImg;
	
	protected Rectangle collider;
	protected final double colliderTolerance = 0.75; 
	protected final int colliderWidth = (int) (width * colliderTolerance);
	protected final int colliderHeight = (int) (height * colliderTolerance);

	public Obstaculo(double y, String pathImg) {
    	this.y = y-Config.baseHeight/2;
    	this.x = (int)Math.floor(Math.random()*( 540 - 240 + 1) +240);
    	obsImg = new Image(pathImg, width, height, false, false);
		render = new ImageView(obsImg);
		render.relocate(this.x - colliderWidth / 2, this.y - colliderHeight / 2);
		collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
    }
		
	public Obstaculo(int y, int x, String pathImg, int height, int anguloRotacionCollider) {//meta y cordones
		obsImg = new Image(pathImg, 340, 20, false, false);
        render = new ImageView(obsImg);
        render.setX(x);
        render.setY(y);
        render.relocate(x,y);

        collider = new Rectangle(x,y,20, height);
        collider.setFill(null);
        collider.setStroke(Color.FUCHSIA);
        collider.setStrokeWidth(2);
        collider.setRotate(anguloRotacionCollider);
    }
	
	@Override
	public void update(double deltaTime) {
		if (isOffScreen()) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

	@Override
	public boolean isOffScreen() {		
		return y - Config.offScreenTolerance > Config.posicionActualJugador;
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}
    	
	@Override
	public void destroy() {
		
	}

}
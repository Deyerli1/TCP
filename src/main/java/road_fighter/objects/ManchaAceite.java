package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;
import road_fighter.utils.GameObjectBuilder;

public class ManchaAceite extends Obstaculo {

	protected final int width = 51;
	protected final int height = 36;
	
	protected ImageView render;
	Image pozoImg;
	
	protected Rectangle collider;
	protected final double colliderTolerance = 0.75;
	protected final int colliderWidth = (int) (width * colliderTolerance);
	protected final int colliderHeight = (int) (height * colliderTolerance);
	
    public ManchaAceite(double posicionJugador) {
    	super((int)posicionJugador-Config.baseHeight/2);
    	//this.x = (int)Math.floor(Math.random()*(300-0+1));
    	this.x = 200;
    	pozoImg = new Image("file:src/main/resources/img/aceite.png", width, height, false, false);
		render = new ImageView(pozoImg);
		render.relocate(this.x - colliderWidth / 2, this.y - colliderHeight / 2);
		collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
    }

	@Override
	public void update(double deltaTime) {
		if (isOffScreen()) {
			System.out.println("obstactulos actuales: "+Obstaculo.obstaculosActuales);
			Obstaculo.obstaculosActuales--;
			destroy();
		}
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public boolean isOffScreen() {		
		return y - offScreenTolerance > posicionActualJugador;
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
		GameObjectBuilder.getInstance().remove(this);
	}
}
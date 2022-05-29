package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Pozo extends Obstaculo {
	
	protected final int width = 51;
	protected final int height = 36;
	
	protected ImageView render;
	Image pozoImg;
	
	protected Rectangle collider;
	protected final double colliderTolerance = 0.75;
	protected final int colliderWidth = (int) (width * colliderTolerance);
	protected final int colliderHeight = (int) (height * colliderTolerance);
	
    public Pozo(double x, double y) {
    	super(x,y);
    	pozoImg = new Image("file:src/main/resources/img/flappy-bird.png", width, height, false, false);
		render = new ImageView(pozoImg);
		render.relocate(Math.floor(Math.random()*(500-300+1)+300), 200);
		

		
		collider = new Rectangle(colliderWidth / 2, colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		System.out.println("spawn");
    }

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
}
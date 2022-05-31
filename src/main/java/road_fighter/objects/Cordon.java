package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;

public class Cordon extends Obstaculo {
		
    public Cordon(double x, double y) {
    	super(x,y);
    	this.sprite = "file:src/main/resources/img/cordon.png";
    	Image caminoImage = new Image(sprite, Config.cordonWidth, Config.cordonHeight, false, false);
		render = new ImageView(caminoImage);
		render.relocate(x,y);
		render.setViewOrder(9);
		collider = new Rectangle(x,y,Config.cordonWidth, Config.cordonHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		collider.setStrokeWidth(2);
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
package road_fighter.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Despawneable;
import road_fighter.utils.GameObjectBuilder;

public abstract class AutoNpc extends Auto implements Despawneable {
	
    public AutoNpc(int posicion, String imgPath, int width, int height) {
    	super(posicion-Config.baseHeight/2, width, height);
    	this.imgPath = imgPath;
    	this.x = (int)Math.floor(Math.random()*( (Config.baseWidth-200) - (Config.baseWidth+200)+(Config.baseWidth+200)));
    	autoImg = new Image(imgPath, width, height, false, false);
		render = new ImageView(autoImg);
    	render.setY(this.y);
    	render.setX(this.x);
		render.relocate(this.x - colliderWidth / 2, this.y - colliderHeight / 2);
		collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
    }

    @Override
	public void update(double deltaTime) {
    	super.update(deltaTime);
		if (isOffScreen()) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}
       
    @Override
    public boolean isOffScreen() {		
		return y - Config.offScreenTolerance > Config.posicionActualJugador;
	}
	
}
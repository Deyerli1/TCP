package road_fighter.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Despawneable;
import road_fighter.states.AutoNormal;
import road_fighter.utils.GameObjectBuilder;

public abstract class AutoNpc extends Auto implements Despawneable {
	
    public AutoNpc(int posicion, String imgPath, int width, int height) {
    	super(posicion-Config.baseHeight/2, width, height);
    	autoImg = new Image(imgPath, width, height, false, false);
    	this.imgPath = imgPath;
    	this.x = (int)Math.floor(Math.random()*( 540 - 240 + 1) +240);
		render = new ImageView(autoImg);
    	render.setY(this.y);
    	render.setX(this.x);
		render.relocate(this.x - colliderWidth / 2, this.y - colliderHeight / 2);
		render.setViewOrder(8);
		collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		estado = new AutoNormal(this); 
    }

    @Override
	public void update(double deltaTime) {
    	super.update(deltaTime);
		if (isOffScreen()) {
			GameObjectBuilder.getInstance().remove(this);
		}else {
			habilidadEspecial();
		}
	}
       
    @Override
    public boolean isOffScreen() {		
		return y - Config.offScreenTolerance > Config.posicionActualJugador;
	}
	
}
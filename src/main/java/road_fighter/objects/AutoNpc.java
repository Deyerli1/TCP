package road_fighter.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Collideable;

public abstract class AutoNpc extends Auto implements Collideable {
	
    public AutoNpc(int posicion, String imgPath) {
    	super(posicion-Config.baseHeight/2);
    	this.x = (int)Math.floor(Math.random()*( (Config.baseWidth-200) - (Config.baseWidth+200)+(Config.baseWidth+200)));
    	autoImg = new Image(imgPath, width, height, false, false);
		render = new ImageView(autoImg);
		render.relocate(this.x - colliderWidth / 2, this.y - colliderHeight / 2);
		collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
    }

	
}




















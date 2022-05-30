package road_fighter.objects;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class Camino extends GameObject implements Updatable, Renderable {
	protected final double x;
	protected final double y;

	protected String sprite;
	private ImageView render;
	protected ArrayList<Cordon> cordones; // TODO. Cada instancia de camino tiene sus obstaculos?
	
	Camino(double x, double y, double posicionJugador){
		this.x = x;
		this.y = y;
		this.sprite = "file:src/main/resources/img/road.png";
		Image caminoImage = new Image(sprite, Config.caminoWidth, Config.caminoHeight, false, false);
		render = new ImageView(caminoImage);
		render.relocate(x, y);
		render.setViewOrder(10);
	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

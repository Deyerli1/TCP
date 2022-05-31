package road_fighter.objects;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Shape;
import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class Camino extends GameObject implements Updatable, Renderable {
	protected final double x;
	protected final double y;
	protected double posicionJugador;
	protected String sprite;
	protected Cordon cordonIzq; // TODO. Cada instancia de camino tiene sus obstaculos?
	protected Cordon cordonDer; // TODO. Cada instancia de camino tiene sus obstaculos?
	private ImageView render;
	private HBox test;
	
	public Camino(double x, double y){
		this.x = x;
		this.y = y;
		this.sprite = "file:src/main/resources/img/road2.png";
		Image caminoImage = new Image(sprite, Config.caminoWidth, Config.caminoHeight, false, false);
		render = new ImageView(caminoImage);
		render.relocate(x,y);
		render.setViewOrder(10);
		this.cordonDer = new Cordon(x,y);
		this.cordonIzq = new Cordon(-x,y);
		test = new HBox(this.cordonIzq.getRender(),render,this.cordonDer.getRender());
		test.setViewOrder(9);
		test.relocate(x, y);
	}
	
	public Camino(double movimiento){
		this.x = 20;
		this.y = -100-(movimiento*Config.baseHeight);
		this.sprite = "file:src/main/resources/img/road2.png";
		Image caminoImage = new Image(sprite, Config.caminoWidth, Config.caminoHeight, false, false);
		render = new ImageView(caminoImage);
		render.relocate(x,y);
		render.setViewOrder(10);
		this.cordonDer = new Cordon(x,y);
		this.cordonIzq = new Cordon(-x,y);
		test = new HBox(this.cordonIzq.getRender(),render,this.cordonDer.getRender());
		test.setViewOrder(9);
		test.relocate(x, y);
	}
	
	@Override
	public Node getRender() {
		return test;
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

package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Updatable, Renderable {
	//private VBox render;
	private ImageView render;
	private double posX = 0;

	private final int mapWidth = 800;
	private final int mapHeight = 800;

	public Background() {
		Image backgroundImage = new Image("file:src/main/resources/img/background.png", mapWidth, mapHeight, false, false);

		//ImagePattern image_pattern = new ImagePattern(backgroundImage);

		//Rectangle mapa = new Rectangle(Config.baseWidth + mapWidth, Config.baseHeight - mapHeight);
		//mapa.setFill(image_pattern);

		//render = new VBox(mapa);
		// TODO zIndex list
		//render.setViewOrder(10);
		
		render = new ImageView(backgroundImage);
		
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		//posX += -Config.baseSpeed * deltaTime * 0.01;
	}

	@Override
	public void destroy() { }

}

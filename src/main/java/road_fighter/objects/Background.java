package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Updatable, Renderable {
	private VBox render;
	private double posX = 0;

	private final int mapWidth = 136;
	private final int mapHeight = 152;

	public Background() {
		Image backgroundImage = new Image("file:src/main/resources/img/background.png", mapWidth, mapHeight, false, false);

		ImagePattern image_pattern = new ImagePattern(backgroundImage, mapWidth, mapHeight, mapWidth, mapHeight,
				false);

		Rectangle mapa = new Rectangle(Config.baseWidth + mapWidth, Config.baseHeight - mapHeight);
		mapa.setFill(image_pattern);


		render = new VBox(mapa);
		// TODO zIndex list
		render.setViewOrder(10);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		posX += -Config.baseSpeed * deltaTime * 0.01;
		render.setTranslateX(posX % mapWidth);
	}

	@Override
	public void destroy() { }

}

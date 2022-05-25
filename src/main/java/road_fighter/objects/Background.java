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

	private final int cityWidth = 136;
	private final int cityHeight = 152;
	private final int grassHeight = 100;

	public Background() {
		Image backgroundImage = new Image("file:src/main/resources/img/background.png", cityWidth, cityHeight, false, false);

		ImagePattern image_pattern = new ImagePattern(backgroundImage, cityWidth, cityHeight, cityWidth, cityHeight,
				false);

		Rectangle sky = new Rectangle(Config.baseWidth + cityWidth, Config.baseHeight - cityHeight - grassHeight);
		Rectangle city = new Rectangle(Config.baseWidth + cityWidth, cityHeight);
		Rectangle grass = new Rectangle(Config.baseWidth + cityWidth, grassHeight);

		sky.setFill(Color.rgb(84, 192, 201));
		city.setFill(image_pattern);
		grass.setFill(Color.rgb(100, 224, 117));

		render = new VBox(sky, city, grass);
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
		render.setTranslateX(posX % cityWidth);
	}

	@Override
	public void destroy() { }

}

package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Renderable {
	private ImageView render;

	private final int mapWidth = 800;
	private final int mapHeight = 20000;

	public Background() {
		Image backgroundImage = new Image("file:src/main/resources/img/background.png", mapWidth, mapHeight, false, false);
		render = new ImageView(backgroundImage);
		render.setY(-19200);
	}
	
	public Background(String imgPath, int y, int width, int height) {
        Image backgroundImage = new Image(imgPath, width, height, false, false);
        render = new ImageView(backgroundImage);
        render.setY(y);
        render.setViewOrder(10);
    }

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() { }

}

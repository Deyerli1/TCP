package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Renderable {
	private ImageView render;
	
	public Background(String imgPath, int y, int width, int height) {
		Image backgroundImage = new Image(imgPath, width, height, false, false);
		render = new ImageView(backgroundImage);
		render.setY(y);
		render.setViewOrder(10);
	}

	public void setY(int y) {
		render.setY(y);
	}
	public void setX(int x) {
		render.setX(x);
	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() { }

}

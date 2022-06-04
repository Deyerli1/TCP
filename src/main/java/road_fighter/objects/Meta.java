package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Meta extends Obstaculo {

	public Meta(int y) {
		 super(-y, 50, "file:src/main/resources/img/meta.png", 500, 90);
		 collider.setX(400);
		 collider.setY(render.getY()-300);
	}

	@Override
	public void update(double deltaTime) {

	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void destroy() {

	}

}

package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Meta extends Obstaculo {

	public Meta(int y) {
		 super(-y, 230, "file:src/main/resources/img/obstaculos/meta.png", 500, 90);
		 collider.setX(230);
		 collider.setY(render.getY()-300);
		 render.setViewOrder(9);
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

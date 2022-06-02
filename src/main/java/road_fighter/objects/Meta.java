package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Meta extends Obstaculo {

	public Meta(int y) {
		 super(-y, 300, "file:src/main/resources/img/meta.png", 500, 90);
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

	}

}

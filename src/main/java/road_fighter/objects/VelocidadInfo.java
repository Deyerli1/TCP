package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class VelocidadInfo extends GameObject implements Renderable, Updatable {
	private static int y = 5;

	private Text text;
	private static VBox render;
	private static double vel;

	public VelocidadInfo() {
		text = new Text();
		vel = 0;
		render = new VBox(text);
		render.setTranslateY(y);

		text.setFont(Font.font("MONOSPACED", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		text.setFill(Color.WHITE);
	}
	
	public static void setValores(double _vel, double _y) {
		vel = _vel;
		render.setTranslateY(_y-300);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		text.setText(vel+" Km/h");
	}

	@Override
	public void destroy() {
	}

}

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

public class NombreJugador extends GameObject implements Renderable, Updatable {
	private int y = 5;
	private int x = 10;

	private Text text;
	private static VBox render;
	private static String nombreJugador;

	public NombreJugador(String _nombreJugador) {
		text = new Text();
		nombreJugador = _nombreJugador;
		render = new VBox(text);
		render.setTranslateY(y);
		render.setTranslateX(x-10);

		text.setFont(Font.font("MONOSPACED", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		text.setFill(Color.WHITE);
	}
	
	public static void setValores(double x, double _y) {
		render.setTranslateX(x-10);
		render.setTranslateY(_y+50);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		text.setText(nombreJugador);
	}

	@Override
	public void destroy() {
	}

}
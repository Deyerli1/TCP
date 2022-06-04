package road_fighter.objects.menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class MenuItem extends GameObject implements Renderable{
	private final int Y = Config.baseHeight * 3 / 5;
	
	private final int ITEM_WIDTH = 200;
	private final int ITEM_HEIGHT = 100;
	
	private Image img;
	private ImageView render;
//	private Text text;
//	private VBox render;

	public MenuItem(String name) {
		img = new Image(name, ITEM_WIDTH, ITEM_HEIGHT, false, false);

		render = new ImageView(img);
		render.setViewOrder(1);
    	render.setX(190);
    	render.setY(-150);
		
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {	}
}

package road_fighter.objects.menu;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class MenuItem extends GameObject implements Renderable{

	//Tamaño original 500x87
	private final int ITEM_WIDTH = 300;
	private final int ITEM_HEIGHT = 53;
	
	private Image img;
	private ImageView render;
	private boolean selected = false;
	private Effect shadow = new DropShadow(10, Color.BLACK);
//	private Effect blur = new BoxBlur(1, 1, 3);
	
	public MenuItem(String path, int x, int y) {
		img = new Image(path, ITEM_WIDTH, ITEM_HEIGHT, false, false);
		render = new ImageView(img);
		render.setViewOrder(2);
    	render.setX(x);
    	render.setY(y);
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(this.selected) {
			render.setEffect(shadow);
		}else {
			render.setEffect(null);
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}


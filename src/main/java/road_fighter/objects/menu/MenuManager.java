/*package road_fighter.objects.menu;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class MenuManager extends GameObject implements Renderable{
	List<MenuItem> lista = new ArrayList<MenuItem>();
	private VBox box = new VBox(-5);;
	public MenuManager() {
		generarItem("Un jugador", "file:src/main/resources/img/familySedan.png", 300, -100);
		generarItem("Opciones", "file:src/main/resources/img/familySedan.png", 300, -300);
		
	}
	
	public void generarItem(String name, String path,int x, int y) {
		MenuItem nuevo = new MenuItem( path, x, y);
		lista.add(nuevo);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getRender() {
		return null;
	}
	
}
*/
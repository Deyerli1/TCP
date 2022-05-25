package road_fighter;
import road_fighter.objects.Background;
import road_fighter.objects.Auto;
import road_fighter.objects.AutoJugador;
import road_fighter.objects.menu.TextoComenzar;
import road_fighter.objects.menu.Title;
import road_fighter.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MenuSceneHandler extends SceneHandler {

	private Auto player;
	private Background background;
	private Title title;
	private TextoComenzar textoComenzar;

	private Group rootGroup;

	public MenuSceneHandler(RoadFighterGame r) {
		super(r);	
	}

	protected void prepareScene() {
		System.out.println("en prepareScene");
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
		System.out.println("fin prepareScene");
	}

	protected void defineEventHandlers() {
		System.out.println("en defineEventHandlers");
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					r.startGame();
				}
			}
		};

		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case UP:
				case W:
				case SPACE:
				case ENTER:
					r.startGame();
					break;
				case Q:
				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		};
		System.out.println("fin defineEventHandlers");
	}
	
	

	public void load() {
		System.out.println("en load");
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		
		player = new AutoJugador(Config.baseWidth - 75, Config.baseHeight / 3, null);
		
		background = new Background();

		title = new Title();
		textoComenzar = new TextoComenzar();

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(background, player,title, textoComenzar);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
		System.out.println("fin load");
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
	}

}

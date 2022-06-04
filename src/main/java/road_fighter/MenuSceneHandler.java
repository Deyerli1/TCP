package road_fighter;
import road_fighter.objects.Background;
import road_fighter.objects.Reproductor;
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

	private Background background;
	private Title title;
	private TextoComenzar textoComenzar;
	
	private final String PATH_MUSICA_MENU = "src/main/resources/snd/ambient.mp3";
	
	private Group rootGroup;

	public MenuSceneHandler(RoadFighterGame r) {
		super(r);	
	}

	//configuracion de tamaño de pantalla
	protected void prepareScene() {
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	protected void defineEventHandlers() {
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					r.startGame();
				}
			}
		};

		keyEventHandlerPress = new EventHandler<KeyEvent>() {
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
		
		keyEventHandlerRelease = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case UP:
				case W:
				case SPACE:
				case ENTER:
					//r.startGame();
					break;
				case Q:
				case ESCAPE:
					//System.exit(0);
					break;
				default:
					break;
				}
			}
		};
	}
	
	

	public void load() {
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		
		background = new Background();
		title = new Title();
		textoComenzar = new TextoComenzar();
		reproductor = new Reproductor(PATH_MUSICA_MENU);
		
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(background, title, textoComenzar, reproductor);
		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
	}

}

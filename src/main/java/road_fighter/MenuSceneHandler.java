package road_fighter;
import road_fighter.objects.Background;
import road_fighter.objects.Reproductor;
import road_fighter.objects.menu.MenuItem;
import road_fighter.objects.menu.TextoComenzar;
import road_fighter.objects.menu.Title;
import road_fighter.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MenuSceneHandler extends SceneHandler {

	private final int MENU_MAP_WIDTH = Config.baseWidth;
	private final int MENU_MAP_HEIGHT = Config.baseHeight;
	
	private final String PATH_FONDO_MENU = "file:src/main/resources/img/backgroundMenu.png";
	private final String PATH_MUSICA_MENU = "src/main/resources/snd/ambient.mp3";
	private Background background;
	private Title title;
	private TextoComenzar textoComenzar;
	private MenuItem unJugador;
	private MenuItem options;
	private Group rootGroup;
	public ParallelCamera camera = new ParallelCamera();
	
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
				case DOWN:
				case S:
					cambiarMenu();
					break;
				case SPACE:
				case ENTER:
					accionMenu();
					break;
				case Q:
				case ESCAPE:
					accionEscape();
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
	
	public void accionMenu() {
		if(unJugador.isSelected()) {
			r.startGame();
		}else {
			irAOpciones();
		}
	}
	
	public void accionEscape() {
		if(options.isSelected()) {
			irAMenu();
		}else {
			System.exit(0);			
		}
	}
	
	public void cambiarMenu(){
		
		if(unJugador.isSelected()) {
			this.unJugador.setSelected(false);
			this.options.setSelected(true);
		}else {
			this.unJugador.setSelected(true);
			this.options.setSelected(false);
		}
	}
	
	public void irAOpciones() {
		camera.translateXProperty().set(800);//vista vertical
		background.setX(800);
	}
	
	public void irAMenu() {
		camera.translateXProperty().set(0);//vista vertical
		background.setX(0);
	}
	
	public void load() {
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		scene.setCamera(camera);
		
		background = new Background(PATH_FONDO_MENU, 0, MENU_MAP_WIDTH, MENU_MAP_HEIGHT);
		title = new Title();
		reproductor = new Reproductor(PATH_MUSICA_MENU);
		unJugador = new MenuItem("file:src/main/resources/img/singleplayer.png", 250, 500);
		options = new MenuItem("file:src/main/resources/img/options.png", 250, 600);
		
		MenuItem volumen = new MenuItem("file:src/main/resources/img/music.png", 1000, 500);

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(background, title, reproductor, unJugador, options, volumen);
		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
			unJugador.setSelected(true);
		}
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
	}

}

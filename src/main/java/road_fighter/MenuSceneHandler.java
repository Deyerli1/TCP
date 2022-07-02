package road_fighter;
import road_fighter.objects.Background;
import road_fighter.objects.Reproductor;
import road_fighter.objects.menu.MenuItem;
import road_fighter.objects.menu.Title;
import road_fighter.servidor.Servidor;
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
	
	private final String PATH_FONDO_MENU = "file:src/main/resources/img/fondos/backgroundMenu.png";
	private final String PATH_VOLUMEN_MENU = "file:src/main/resources/img/menu/music.png";
	private final String PATH_UN_JUGADOR_MENU = "file:src/main/resources/img/menu/singleplayer.png";
	private final String PATH_MUSICA_MENU = "src/main/resources/snd/ambient.mp3";
	private Background background;
	private Title title;
	private MenuItem unJugador;
	private Group rootGroup;
	private MenuItem musicaMenu;
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
				case LEFT:
				case A:
					bajarVol();
					break;
				case RIGHT:
				case D:
					subirVol();
					break;
				case SPACE:
				case ENTER:
					accionMenu();
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
	
	public void accionMenu() {
		if(unJugador.isSelected()) {
			System.out.println(">>>>>>>>>>>>>>> INICIANDO SINGLEPLAYER");
			//r.startGame();
			r.hostGameMulti();
		}else {//es multijugador
			System.out.println(">>>>>>>>>>>>>>> INICIANDO MULTIPLAYER");
			r.joinGameMulti();
		}
	}
	
	public void cambiarMenu(){
		if(unJugador.isSelected()) {
			this.unJugador.setSelected(false);
			this.musicaMenu.setSelected(true);
		}else{
			this.unJugador.setSelected(true);
			this.musicaMenu.setSelected(false);
		}
	}
	
	private void bajarVol() {
		if(!unJugador.isSelected()) {
			cambiarVolumen(-0.1);
		}
	}
	
	private void subirVol() {
		if(!unJugador.isSelected()) {
			cambiarVolumen(0.1);
		}
	}
	
	private void cambiarVolumen(double slide) {
		double volNuevo = Config.volumenMusica+ slide;
		if(volNuevo>=0 && volNuevo<=1) {
			reproductor.setVolume(volNuevo);
		}
	}
	
	public void load() {
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		scene.setCamera(camera);
		
		background = new Background(PATH_FONDO_MENU, 0, MENU_MAP_WIDTH, MENU_MAP_HEIGHT);
		title = new Title();
		reproductor = new Reproductor(PATH_MUSICA_MENU);
		unJugador = new MenuItem(PATH_UN_JUGADOR_MENU, 250, 500);
		
		musicaMenu = new MenuItem(PATH_VOLUMEN_MENU, 250, 600);

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(background, title, reproductor, unJugador, musicaMenu);
		
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

package road_fighter;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.objects.Auto;
import road_fighter.objects.AutoJugador;
import road_fighter.objects.Background;
import road_fighter.objects.Cordon;
import road_fighter.objects.Meta;
import road_fighter.objects.NPCBuilder;
import road_fighter.objects.NombreJugador;
import road_fighter.objects.ObstaculoBuilder;
import road_fighter.objects.Reproductor;
import road_fighter.objects.VelocidadInfo;
import road_fighter.servidor.Cliente;
import road_fighter.states.AutoExplotado;
import road_fighter.states.AutoNormal;
import road_fighter.utils.GameObjectBuilder;


public class GameSceneHandler extends SceneHandler {
	
	private final int MAP_WIDTH = 800;
	private final int MAP_HEIGHT = 20000;

	private final String PATH_FONDO_GAME = "file:src/main/resources/img/fondos/background.png";
	
	private Auto player;
	private List<Cliente> usuariosConectados;
	private Background background;
	private ObstaculoBuilder obstaculoBuilder;
	private Cordon cordonIzquierdo;
	private Cordon cordonDerecho;
	private Meta meta;
	private NPCBuilder npcBuilder;
	private VelocidadInfo velInfo;
	private NombreJugador nombreAuto;
	public ParallelCamera camera = new ParallelCamera();
	public boolean online;
	

	private boolean animacionActiva = false;
	private boolean started = false;
	
	private TranslateTransition tt = new TranslateTransition();

	public GameSceneHandler(RoadFighterGame r) {
		super(r);
	}

	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.rgb(103, 137, 43));
	}

	protected void defineEventHandlers() {
		keyEventHandlerPress = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case W:
					makeAction();
					player.setAcelerar(true);
					break;
				case A:
					player.setDoblarIzquierda(true);
					break;
				case D:
					player.setDoblarDerecha(true);
					break;
				case ESCAPE:
					System.exit(0);
					break;
				case SPACE:
					player.habilidadEspecial();
				case R:
					//TODO
					//r.startGam();
				default:
				}
			}
		};

		keyEventHandlerRelease = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case W:
					player.setAcelerar(false);
					
					break;
				case A:
					player.setDoblarIzquierda(false);
					break;
				case D:
					player.setDoblarDerecha(false);
					break;
				default:
				}
			}
		};

	}
	
	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);
				
		String nombreJugador = "pepito";
		player = new AutoJugador(nombreJugador, 400, 600);
		cordonIzquierdo = new Cordon(210);
		cordonDerecho = new Cordon(572);
		velInfo = new VelocidadInfo();
		scene.setCamera(camera);
		camera.translateYProperty().set(-100);//vista vertical
		
		background = new Background(PATH_FONDO_GAME, -19200, MAP_WIDTH, MAP_HEIGHT);
		obstaculoBuilder = new ObstaculoBuilder();
		npcBuilder = new NPCBuilder();
		meta = new Meta(18650);
		reproductor = new Reproductor(Config.PATH_MUSICA);
		nombreAuto = new NombreJugador(nombreJugador);

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, player, obstaculoBuilder, npcBuilder, cordonIzquierdo, cordonDerecho, meta, velInfo, reproductor, nombreAuto);
		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}
	
	public void load(boolean fullStart, List<Cliente> usuariosConectados) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);
		cordonIzquierdo = new Cordon(210);
		cordonDerecho = new Cordon(572);
		velInfo = new VelocidadInfo();
		scene.setCamera(camera);
		camera.translateYProperty().set(-100);//vista vertical
		background = new Background(PATH_FONDO_GAME, -19200, MAP_WIDTH, MAP_HEIGHT);
		obstaculoBuilder = new ObstaculoBuilder();
		npcBuilder = new NPCBuilder();
		meta = new Meta(18650);
		reproductor = new Reproductor(Config.PATH_MUSICA);
		this.usuariosConectados = usuariosConectados;
		
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		for(Cliente cliente : usuariosConectados) {
			gameOB.add(cliente.getPlayer());
		}
		player = usuariosConectados.get(0).getPlayer();
		gameOB.add(background, obstaculoBuilder, npcBuilder, cordonIzquierdo, cordonDerecho, meta, velInfo, reproductor);
		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}
	
	public void restart() {
		cleanData();
		load(false);
	}
	
	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();
		started = false;
		Config.baseSpeed = 250;
	}

	private void makeAction() {
		if (!started) {
			started = true;
			obstaculoBuilder.startBuilding(2 * NANOS_IN_SECOND);
			npcBuilder.startBuilding(2 * NANOS_IN_SECOND);
		}
	}
	
	public void update(double delta) {
		super.update(delta);
		camera.translateYProperty().set(player.getY() - Config.baseHeight/2 - 200);
		checkColliders();
		Config.posicionJugador = player.getY();
		if(!player.isGanador()) {
			if(player.getEstado().getClass()  == AutoExplotado.class && !animacionActiva) {
				animacionActiva = true;
				tt = new TranslateTransition(Duration.millis(50), scene.getRoot());
				tt.setFromX(-20f);
				tt.setByX(20f);
				tt.setCycleCount(6);
				tt.setAutoReverse(true);
				tt.playFromStart();
				tt.setOnFinished(event -> {
					scene.getRoot().setTranslateX(0);
				});
			}else if(player.isBorracho() && !animacionActiva) {
				animacionActiva = true;
				tt = new TranslateTransition( Duration.seconds(2), scene.getRoot());
				tt.setFromX(-20f);
				tt.setByX(20f);
				tt.setCycleCount(6);
				tt.setAutoReverse(true);
				tt.playFromStart();
				tt.setOnFinished(event -> {
					scene.getRoot().setTranslateX(0);
					tt.stop();
				});
			}
			else if(player.getEstado().getClass() == AutoNormal.class) {
				animacionActiva = false;
				player.setBorracho(false);
			}
		}
		else {
			obstaculoBuilder.stopBuilding();
			npcBuilder.stopBuilding();
		}
	}
	

	private void checkColliders() {
		List<Collidator> collidators = GameObjectBuilder.getInstance().getCollidators();
		List<Collideable> collideables = GameObjectBuilder.getInstance().getCollideables();

		for (int i = 0; i < collidators.size(); i++) {
			Collidator collidator = collidators.get(i);
			for (int j = i + 1; j < collidators.size(); j++) {
				Collidator otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(otherCollidator);
					otherCollidator.collide(collidator);
				}
			}

			for (int j = 0; j < collideables.size(); j++) {
				Collideable collideable = collideables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collideable.getCollider());

				// TODO test times
				// XXX Si el substract es distinto???
				// Check intersects
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(collideable); 
				} else {
					// Check contains
					Bounds collideableBounds = collideable.getCollider().getBoundsInLocal();
					Bounds collidatorBounds = collidator.getCollider().getBoundsInLocal();
					if (collideableBounds.contains(collidatorBounds.getCenterX(), collidatorBounds.getCenterY())) {
						collidator.collide(collideable);
					}
				}
			}
		}
	}
	
	public void unload() {
		cleanData();
		super.unload();
	}
}


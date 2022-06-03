package road_fighter;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.objects.Auto;
import road_fighter.objects.AutoJugador;
import road_fighter.objects.Background;
import road_fighter.objects.Cordon;
import road_fighter.objects.Meta;
import road_fighter.objects.NPCBuilder;
import road_fighter.objects.ObstaculoBuilder;
//import road_fighter.objects.Radio;
import road_fighter.utils.GameObjectBuilder;


public class GameSceneHandler extends SceneHandler {
	
	private Auto player;
	private Background background;
	private ObstaculoBuilder obstaculoBuilder;
	private Cordon cordonIzquierdo;
	private Cordon cordonDerecho;
	private Meta meta;
	private NPCBuilder npcBuilder;
	public ParallelCamera camera = new ParallelCamera();
	//private Radio radio;

	boolean started = false;
	boolean ended = false;

	public GameSceneHandler(RoadFighterGame r) {
		super(r);
	}

	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
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
				case S:
					//TODO
					//player.setFrenar(false);
					break;
				case A:
					player.setDoblarIzquierda(true);
					break;
				case D:
					player.setDoblarDerecha(true);
					break;
				case R:
					//TODO
					//prenderRadio();
				case ESCAPE:
					System.exit(0);
					break;
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
				
		player = new AutoJugador("pepito", 400, 600);
		cordonIzquierdo = new Cordon(95);
		cordonDerecho = new Cordon(694);
		scene.setCamera(camera);
		camera.translateYProperty().set(-100);//vista vertical
		
		background = new Background();
		obstaculoBuilder = new ObstaculoBuilder();
		npcBuilder = new NPCBuilder();
		meta = new Meta(20000);
		//radio = new Radio(Config.playerCenter, Config.baseHeight / 2, player);

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, player, obstaculoBuilder, npcBuilder, cordonIzquierdo, cordonDerecho, meta);
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
		ended = false;
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
	
	private void prenderRadio() {
		//radio.start();
	}
	
	public void update(double delta) {
		super.update(delta);
		camera.translateYProperty().set(player.getY() - Config.baseHeight/2 - 200);
		checkColliders();
		Config.posicionJugador = player.getY();
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


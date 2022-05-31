package road_fighter;

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
import road_fighter.objects.Camino;
import road_fighter.objects.CaminoBuilder;
import road_fighter.objects.ObstaculoBuilder;
import road_fighter.objects.Radio;
import road_fighter.utils.GameObjectBuilder;


public class GameSceneHandler extends SceneHandler {
	
	private Auto player;
	private Background background;
	private ObstaculoBuilder obstaculoBuilder;
	private CaminoBuilder caminoBuilder;
	private Radio radio;
	public ParallelCamera camera = new ParallelCamera();

	// TODO pause
	// boolean paused = false;
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
		System.out.println("defineEventHandlers");
		keyEventHandlerPress = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				System.out.println("tecla recibida");
				switch (e.getCode()) {
				case W:
					player.setAcelerar(true);
					break;
				case S:
					player.setAcelerar(false);
					break;
				case A:
					player.setDoblarIzquierda(true);
					break;
				case D:
					player.setDoblarDerecha(true);
					break;
				case R:
					prenderRadio();
				default:
					//player.desacelerar();
				}
			}
		};

		keyEventHandlerRelease = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				System.out.println("tecla soltada");
				switch (e.getCode()) {
				case W:
					makeAction();
					player.setAcelerar(false);
					break;
				case S:
					//player.setAcelerar(false);
					break;
				case A:
					player.setDoblarIzquierda(false);
					break;
				case D:
					player.setDoblarDerecha(false);
					break;
				case P:
					//makeAction();
					break;
				case R:
					//prenderRadio();
				default:
					//player.desacelerar();
				}
			}
		};

	}
	
	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);
		
		player = new AutoJugador("pepito", new double[] {200,600});
		scene.setCamera(camera);
		camera.translateYProperty().set(-100);//vista vertical
		camera.translateXProperty().set(player.getX() - Config.baseWidth/2);
		background = new Background();
		obstaculoBuilder = new ObstaculoBuilder();
		caminoBuilder = new CaminoBuilder();
		Camino primerCamino = new Camino(20,-100);
		//radio = new Radio(Config.playerCenter, Config.baseHeight / 2, player);

		// Add to builder
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, player, obstaculoBuilder,caminoBuilder,primerCamino);
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
			caminoBuilder.startBuilding(NANOS_IN_SECOND * 2);
		}
	}
	
	private void prenderRadio() {
		//radio.start();
	}
	
	public void update(double delta) {
		super.update(delta);
		camera.translateYProperty().set(player.getY() - Config.baseHeight/2);
		camera.translateXProperty().set(player.getX() - Config.baseWidth/2);
		checkColliders();
		obstaculoBuilder.setPosicionJugador(player.getY());
		caminoBuilder.setPosicionJugador(player.getY());
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


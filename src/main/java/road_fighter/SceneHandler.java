package road_fighter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import road_fighter.interfaces.Updatable;
import road_fighter.objects.Reproductor;
import road_fighter.servidor.Servidor;
import road_fighter.utils.GameObjectBuilder;

public abstract class SceneHandler {
	protected final long NANOS_IN_SECOND = 1_000_000_000;
	protected final double NANOS_IN_SECOND_D = 1_000_000_000.0;

	protected int frames = 0;
	protected int last_fps_frame = 0;
	protected AtomicInteger fps = new AtomicInteger(0);
	
	protected AnimationTimer gameTimer;
	protected long previousNanoFrame;
	protected long previousNanoSecond;
	protected RoadFighterGame r;
	protected Reproductor reproductor;
	protected Scene scene;
	
	protected EventHandler<KeyEvent> keyEventHandlerPress;
	protected EventHandler<KeyEvent> keyEventHandlerRelease;
	protected EventHandler<MouseEvent> mouseEventHandler;

	public SceneHandler(RoadFighterGame r) {
			this.r = r;
			prepareScene();
			defineEventHandlers();
	}
	
	public void oneSecondUpdate(double delta) {
		fps.set(frames - last_fps_frame);
		last_fps_frame = frames;
	}

	public Scene getScene() {
		return scene;
	}
	
	public void update(double delta) {
		frames++;
		List<Updatable> updatables = GameObjectBuilder.getInstance().getUpdatables();

		for (Updatable updatable : updatables) {
			updatable.update(delta);
		}
	}

	protected void addTimeEventsAnimationTimer() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long currentNano) {
				// Update tick
				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
				previousNanoFrame = currentNano;
				
				// Update second
				if (currentNano - previousNanoSecond > NANOS_IN_SECOND) {
					oneSecondUpdate((currentNano - previousNanoSecond) / NANOS_IN_SECOND_D);
					previousNanoSecond = currentNano;
				}
	
			}
		};
	
		previousNanoSecond = System.nanoTime();
		previousNanoFrame = System.nanoTime();
		gameTimer.start();
	}

	protected void addInputEvents() {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandlerPress);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandlerRelease);
	}

	protected void removeInputEvents() {
		scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyEventHandlerPress);
		scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyEventHandlerRelease);
		scene.removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventHandler);
	}
	
	protected abstract void prepareScene();
	protected abstract void defineEventHandlers();

	protected void unload() {
		GameObjectBuilder.getInstance().removeAll();
		gameTimer.stop();
		removeInputEvents();
	}
	
	
}


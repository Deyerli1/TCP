package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class ObstaculoBuilder extends GameObject implements Updatable {
	private final long NANOS_IN_SECOND = 1_000_000_000;
	private boolean running = false;
	private long pipeTime;

	public ObstaculoBuilder() {

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			long currentNano = System.nanoTime();

			if (currentNano - pipeTime > 0) {
				pipeTime = currentNano + (long) (Config.pipesPerSecond * NANOS_IN_SECOND);
				createObstaculo();
			}
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
		this.pipeTime = System.nanoTime() + delayInNano;
	}

	public void stopBuilding() {
		running = false;
	}

	public void createObstaculo() {
		Pozo pozo = new Pozo(200,300);
		GameObjectBuilder.getInstance().add(pozo);
	}
	
	@Override
	public void destroy() {	}
	
}


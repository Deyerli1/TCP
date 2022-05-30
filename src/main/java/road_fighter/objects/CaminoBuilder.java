package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class CaminoBuilder extends GameObject implements Updatable{
	private final long NANOS_IN_SECOND = 1_000_000_000;
	private boolean running = false;
	private long caminoTime;
	protected double movimiento=0;
	protected double posicionJugador;
	
	public CaminoBuilder() {

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			long currentNano = System.nanoTime();

			if (currentNano - caminoTime > 0) {
				caminoTime = currentNano + (long) (Config.caminosPerSecond * NANOS_IN_SECOND);
				createCamino();
			}
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
		this.caminoTime = System.nanoTime() + delayInNano;
	}

	public void stopBuilding() {
		running = false;
	}

	public void createCamino() {
		Camino camino = new Camino(200,300-movimiento, posicionJugador);
		GameObjectBuilder.getInstance().add(camino);
		movimiento+=300;
	}
	
	public void setPosicionJugador(double posicionJugador) {
		this.posicionJugador = posicionJugador;
	}
	
	@Override
	public void destroy() {	}
	
}


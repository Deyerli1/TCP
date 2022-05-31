package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class CaminoBuilder extends GameObject implements Updatable{
	private final long NANOS_IN_SECOND = 1_000_000_000;
	private final long maxFrontRenderDistance = 2000;
	private boolean running = false;
	private long caminoTime;
	protected double movimiento=1;
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
		if(Math.abs(-movimiento*Config.baseHeight - posicionJugador) < maxFrontRenderDistance) {
		Camino camino = new Camino(movimiento);
		movimiento++;
		System.out.println("Movimient" + movimiento);
		GameObjectBuilder.getInstance().add(camino);
	}else {
		System.out.println("No render, too far " + movimiento*Config.baseHeight + " " + posicionJugador);
		System.out.println(Math.abs(-movimiento*Config.baseHeight - posicionJugador));
	}
	}
	
	public void setPosicionJugador(double posicionJugador) {
		this.posicionJugador = posicionJugador;
	}
	
	@Override
	public void destroy() {	}
	
}


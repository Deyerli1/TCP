package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Buildable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class ObstaculoBuilder extends GameObject implements Buildable {
	private boolean running = false;
	protected final int distanciaAJugador = 300;
	private final int OBSTACULO_COOLDOWN = 200;
	private int tiempoParaProximaObstaculo;
	
	public ObstaculoBuilder() {

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			if (tiempoParaProximaObstaculo <= 0
					&& ((Config.posicionActualJugador - 50) > Config.posicionJugador)) {
				Config.posicionActualJugador = Config.posicionJugador;
				tiempoParaProximaObstaculo = OBSTACULO_COOLDOWN;
				create();
			}
			tiempoParaProximaObstaculo--;
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
	}

	public void stopBuilding() {
		running = false;
	}

	public void create() {
		Obstaculo obs;

		if ((int) Math.floor(Math.random() * (10 - 1 + 1) + 1) > 7) {
			obs = new Pozo(Config.posicionJugador - distanciaAJugador);
		} else {
			obs = new ManchaAceite(Config.posicionJugador - distanciaAJugador);
		}
		GameObjectBuilder.getInstance().add(obs);
	}

	@Override
	public void destroy() {
	}

}

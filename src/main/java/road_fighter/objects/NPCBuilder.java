package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Buildable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class NPCBuilder extends GameObject implements Buildable {
	private boolean running = false;
	protected final int distanciaAJugador = 300;
	private final int NPC_COOLDOWN = 200;
	private int tiempoParaProximoNpc;

	public NPCBuilder() {

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			if (tiempoParaProximoNpc <= 0
					&& ((Config.posicionActualJugador - 50) > Config.posicionJugador)) {
				Config.posicionActualJugador = Config.posicionJugador;
				tiempoParaProximoNpc = NPC_COOLDOWN;
				create();
			}
			tiempoParaProximoNpc--;
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
	}

	public void stopBuilding() {
		running = false;
	}

	public void create() {
		Auto obs;
		if ((int) Math.floor(Math.random() * (10 - 1 + 1) + 1) > 3) {
			obs = new Fijo(Config.posicionJugador - distanciaAJugador);
		} else {
			obs = new Movil(Config.posicionJugador - distanciaAJugador);
		}
		GameObjectBuilder.getInstance().add(obs);
	}

	@Override
	public void destroy() {
	}

}
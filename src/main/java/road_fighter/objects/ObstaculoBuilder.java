package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Buildable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class ObstaculoBuilder extends GameObject implements Buildable {
	private boolean running = false;
	protected final int distanciaAJugadorSpawn = 300;
	private final int OBSTACULO_COOLDOWN = 200;
	private int tiempoParaProximoObstaculo;
	
	public ObstaculoBuilder() {

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			if (tiempoParaProximoObstaculo <= 0
					&& ((Config.posicionActualJugador - 50) > Config.posicionJugador)) {
				Config.posicionActualJugador = Config.posicionJugador;
				tiempoParaProximoObstaculo = OBSTACULO_COOLDOWN;
				create();
			}
			tiempoParaProximoObstaculo--; 
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

		if(running) {
			if ((int) Math.floor(Math.random() * (10 - 1 + 1) + 1) > 5) {
				obs = new Pozo(Config.posicionJugador - distanciaAJugadorSpawn);
			} else {
				obs = new ManchaAceite(Config.posicionJugador - distanciaAJugadorSpawn);
			}
			GameObjectBuilder.getInstance().add(obs);
		}
	}

	@Override
	public void destroy() {
	}

}

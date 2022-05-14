import static org.junit.Assert.*;

import org.junit.Test;

public class Obstaculos {

	private String name = "Pepito";
	long deltaTime = 1;
	private static double[] posicion = {50,50};
	
	@Test
	public void crearObstaculos() {
		Pozo pozo = new Pozo(50,50);
		assertEquals(pozo.getX(), 50, 0.0);
		ManchaAceite mancha = new ManchaAceite(50,50);
		assertEquals(mancha.getX(), 50, 0.0);
	}
	
	@Test
	public void chocarAutoConPozo() {
		Pozo pozo = new Pozo(50,50);
		Auto auto = new AutoJugador(name,posicion);
		auto.chocar(pozo, deltaTime);
		assertEquals(auto.estado.getEstado(),"explotado");
	}
	
	@Test
	public void chocarAutoConMancha() {
		ManchaAceite mancha = new ManchaAceite(50,50);
		Auto auto = new AutoJugador(name,posicion);
		auto.chocar(mancha, deltaTime);
		assertEquals(auto.estado.getEstado(),"desestabilizado");
	}

}

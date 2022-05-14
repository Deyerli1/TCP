import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EscenarioTest {

	@Test
	public void creacionEscenario() {
		Mapa mapa = new Mapa();
		Escenario escenario = new Escenario(2, mapa);
        assertEquals(escenario.getCantidadJugadores(), 2, 0.0);
	}
	
	@Test
	public void agregarAutos() {
		Mapa mapa = new Mapa();
		Escenario escenario = new Escenario(2, mapa);
		escenario.agregarAutoJugador("Carlos");
		assertEquals(escenario.getCantidadAutoJugador(), 1, 0.0);
		escenario.agregarAutoJugador("Carla");
		assertEquals(escenario.getCantidadAutoJugador(), 2, 0.0);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 1, 0.0);
	}
	
	@Test
	public void agregarAutoJugadorMax() {
		Mapa mapa = new Mapa();
		Escenario escenario = new Escenario(2, mapa);
		escenario.agregarAutoJugador("Carlos");
		assertEquals(escenario.getCantidadAutoJugador(), 1, 0.0);
		escenario.agregarAutoJugador("Pepe");
		assertEquals(escenario.getCantidadAutoJugador(), 2, 0.0);
		escenario.agregarAutoJugador("Pepa");
		assertEquals(escenario.getCantidadAutoJugador(), 2, 0.0);
	}
	
	@Test
	public void agregarAutoNpcMax() {
		Mapa mapa = new Mapa();
		Escenario escenario = new Escenario(2, mapa);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 1, 0.0);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 2, 0.0);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 3, 0.0);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 4, 0.0);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 5, 0.0);
		escenario.agregarAutoNpc();
		assertEquals(escenario.getCantidadAutoNpc(), 5, 0.0);
	}
	
	
}

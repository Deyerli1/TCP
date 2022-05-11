import static org.junit.Assert.*;

import org.junit.Test;

public class EscenarioTest {

	@Test
	public void creacionEscenario() {
		
		Escenario escenario = new Escenario();
        assertEquals(escenario.getCantidadJugadores(), 1, 0.0);
        assertFalse(escenario.isMultiplayer());
		
	}
	
	@Test
	public void agregarAutoJugador() {
		
		AutoJugador auto = new AutoJugador("Carlos");
		
		Escenario escenario = new Escenario();
		escenario.agregarAuto(auto);
		assertEquals(escenario.getAutos().size(), 1, 0.0);
		
		AutoJugador otroAuto = new AutoJugador("Carla");
		escenario.agregarAuto(otroAuto);
		assertEquals(escenario.getAutos().size(), 2, 0.0);
	
	}

	@Test
	public void agregarAutoNpc() {
		
		AutoNpc auto = new AutoNpc("Bot 1");
		
		Escenario escenario = new Escenario();
		escenario.agregarAuto(auto);
		assertEquals(escenario.getAutos().size(), 1, 0.0);
		
	}
	
	@Test
	public void iniciarPartidaUnJugador() {
		
		Mapa mapa = new Mapa();

		Escenario escenario = new Escenario();
		escenario.iniciarSingle(mapa);
		assertEquals(escenario.getMapa(), mapa);
		
	}
}

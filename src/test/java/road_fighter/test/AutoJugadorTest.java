package road_fighter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import road_fighter.objects.Auto;
import road_fighter.objects.AutoJugador;
import road_fighter.objects.Pozo;
import road_fighter.states.AutoDesestabilizado;

class AutoJugadorTest {

	@Test
	void choquePozo() {
    	Auto auto = new AutoJugador("Pepito", 400, 600);
        auto.collide(new Pozo(600));
        assertEquals(auto.getEstado(), AutoDesestabilizado.class);
	}
	
	

}

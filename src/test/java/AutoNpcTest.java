import static org.junit.Assert.*;

import org.junit.Test;

public class AutoNpcTest {

	long deltaTime = 1;
    private static double[] posicion = {50,50};
	
	@Test
	public void creacionNpc() {
    	AutoNpc auto = new Movil("Pepito", posicion);
        assertEquals(auto.getX(), 50, 0.0);
        assertEquals(auto.getY(), 50, 0.0);
    }
	
	@Test
    public void doblar() {
		AutoNpc auto = new Movil("Pepito", posicion);
		
		assertFalse(auto.isIzquierda());
        assertTrue(auto.isDerecha());
		while(auto.getX() != 40) {
			auto.updateAuto(deltaTime);
		}
        assertFalse(auto.isIzquierda());
        assertTrue(auto.isDerecha());
    }
	
	@Test
    public void acelerar() {
		AutoNpc auto = new Fijo("Pepito", posicion);
		
		auto.updateAuto(deltaTime);
		auto.updateAuto(deltaTime);
		auto.updateAuto(deltaTime);
		assertEquals(auto.getVelActual(),60,0.0);
		assertEquals(auto.getY(),170,0.0);
		
    }

}














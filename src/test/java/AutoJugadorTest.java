import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class AutoJugadorTest {
    
    long deltaTime = 1;
    private static final double NANOS_IN_SECOND_D = 1_000_000_000.0;

    @Test
    public void creacionJugador() {
    	
    	AutoJugador auto = new AutoJugador("Pepito");
        assertEquals(auto.getX(), 50, 0.0);
        assertEquals(auto.getY(), 50, 0.0);
        assertEquals(auto.getNombreJugador(),"Pepito");
    }
    
    @Test
    public void doblar() {
    	
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setDoblarDerecha(true);
        auto.updateHorizontal(deltaTime);
        auto.updateHorizontal(deltaTime);
        auto.updateHorizontal(deltaTime);
        assertEquals(auto.getX(), 53, 0.0);
        auto.setDoblarDerecha(false);
        auto.updateHorizontal(deltaTime);
        assertEquals(auto.getX(), 53, 0.0);
        auto.setDoblarIzquierda(true);
        auto.updateHorizontal(deltaTime);
        assertEquals(auto.getX(), 52, 0.0);
      
    }
    
    @Test
    public void doblarIzqYDer() {
    	
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setDoblarDerecha(true);
    	auto.setDoblarIzquierda(true);
        auto.updateHorizontal(deltaTime);
        assertEquals(auto.getX(), 49, 0.0);
      
    }
    
    @Test
    public void doblarExplotado() {
    	
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setDoblarDerecha(true);
        auto.explotar(deltaTime);
        auto.updateHorizontal(deltaTime);
        auto.updateHorizontal(deltaTime);
        assertEquals(auto.getX(), 50, 0.0);
    }
    
    @Test
    public void doblarDesestabilizado() {
    	
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setDoblarDerecha(true);
        auto.desestabilizar(deltaTime);
        auto.updateHorizontal(deltaTime);
        auto.updateHorizontal(deltaTime);
        assertEquals(auto.getX(), 52, 0.0);
    }
    
    @Test
    public void acelerar() {
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	auto.updateAuto(deltaTime);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 170, 0.0);
    	assertEquals(auto.getVelActual(), 60, 0.0);
    	auto.setAcelerar(false);
    	auto.updateAuto(deltaTime);
       	assertEquals(auto.getVelActual(), 40, 0.0);
    	assertEquals(auto.getY(), 210, 0.0);
    }
    
    @Test
    public void acelerarExplotado() {
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	auto.explotar(deltaTime);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertEquals(auto.getVelActual(), 0, 0.0);
    	
    }
    @Test
    public void acelerarDesestabilizado() {
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	auto.desestabilizar(deltaTime);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 90, 0.0);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	
    }
    
    @Test
    public void acelerarMax() {
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setAcelerar(true);
    	while (auto.getVelActual()<auto.getVelMax()) {
			auto.updateAuto(deltaTime);
		}
    	assertEquals(auto.getVelActual(), auto.getVelMax(), 0.0);
    	assertEquals(auto.getY(), 1150, 0.0);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), auto.getVelMax(), 0.0);
    	assertEquals(auto.getY(), 1350, 0.0);
    }
    
    @Test
    public void acelerarYDesacelerarACero() {
    	AutoJugador auto = new AutoJugador("Pepito");
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	auto.setAcelerar(false);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertEquals(auto.getVelActual(),0, 0.0);
      	auto.updateAuto(deltaTime);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertEquals(auto.getVelActual(),0, 0.0);
    }
  
    /*this.velActual = 0;
    this.x = 50;
    this.y = 50;
    this.desestabilizado = false;
    this.explotado = false;
    this.doblarIzquierda = false;
    this.doblarDerecha = false;
    this.decremento=20;
    this.velActual=20;
    this.velMax = 200;*/

}
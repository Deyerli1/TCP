import static org.junit.Assert.*;

import org.junit.Test;

import road_fighter.objects.Auto;
import road_fighter.objects.AutoJugador;
import road_fighter.objects.Pozo;
import road_fighter.states.AutoDesestabilizado;

public class AutoJugadorTest {


    @Test
    public void choquePozo() {
    	Auto auto = new AutoJugador("Pepito", 400, 600);
    	auto.collide(new Pozo(600));
       	assertEquals(auto.getEstado(), AutoDesestabilizado.class);
    }
    
    @Test
    public void doblar() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setDoblarDerecha(true);
        auto.updateAuto(deltaTime);
        auto.updateAuto(deltaTime);
        auto.updateAuto(deltaTime);//doblo tres veces
        assertEquals(auto.getX(), 53, 0.0);
        auto.setDoblarDerecha(false);//dejo de doblar
        auto.updateAuto(deltaTime);
        assertEquals(auto.getX(), 53, 0.0);
        auto.setDoblarIzquierda(true);//doblo otra vez pero al otro lado
        auto.updateAuto(deltaTime);
        assertEquals(auto.getX(), 52, 0.0);
    }
    
    @Test
    public void doblarIzqYDer() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setDoblarDerecha(true);
    	auto.setDoblarIzquierda(true);//doblo al mismo tiempo para los dos lados
        auto.updateAuto(deltaTime);
        assertEquals(auto.getX(), 49, 0.0);
    }
    
    @Test
    public void doblarExplotado() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setDoblarDerecha(true);
        auto.explotar(deltaTime);
        auto.updateAuto(deltaTime);
        auto.updateAuto(deltaTime);
        assertEquals(auto.getX(), 50, 0.0);
    }
    
    @Test
    public void doblarDesestabilizado() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setDoblarDerecha(true);
        auto.desestabilizar(deltaTime);
        auto.updateAuto(deltaTime);
        auto.updateAuto(deltaTime);
        assertEquals(auto.getX(), 52, 0.0);
    }
    
    @Test
    public void acelerar() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
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
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
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
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
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
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
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
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
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
    
    @Test
    public void verificarExplotar() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	assertEquals(auto.getY(), 70, 0.0);
    	auto.explotar(deltaTime);
    	auto.setDoblarDerecha(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getX(), 50, 0.0);
    	assertEquals(auto.getVelActual(), 0, 0.0);
    	assertEquals(auto.getY(), 70, 0.0);
    	assertFalse(auto.isAcelerar());
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), 0, 0.0);
    	assertEquals(auto.getY(), 70, 0.0);
//    	auto.setDoblarDerecha(true);
//    	auto.updateAuto(deltaTime+10);
//    	assertEquals(auto.getX(), 50, 0.0);
//    	assertEquals(auto.getVelActual(), 200, 0.0);
//    	assertEquals(auto.getY(), 2270, 0.0);
    }
    
    @Test
    public void verificarDesestabilizado() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setAcelerar(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	assertEquals(auto.getY(), 70, 0.0);
    	auto.desestabilizar(deltaTime);
    	auto.setDoblarIzquierda(true);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	assertEquals(auto.getY(), 90, 0.0);
    	assertEquals(auto.getX(), 51, 0.0);
    	assertTrue(auto.isAcelerar());
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), 20, 0.0);
    	assertEquals(auto.getY(), 110, 0.0);
    	assertEquals(auto.getX(), 52, 0.0);
    }
    
    @Test
    public void habilidadEspecial() {
    	AutoJugador auto = new AutoJugador("Pepito", posicion);
    	auto.setAcelerar(true);
    	while (auto.getVelActual()<auto.getVelMax()) {
			auto.updateAuto(deltaTime);
		}
    	assertEquals(auto.getVelActual(), auto.getVelMax(), 0.0);
    	assertEquals(auto.getY(), 1150, 0.0);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), auto.getVelMax(), 0.0);
    	assertEquals(auto.getY(), 1350, 0.0);
    	auto.habilidadEspecial();
    	assertEquals(auto.getVelMax(), 220 , 0.0);
    	auto.updateAuto(deltaTime);
    	assertEquals(auto.getVelActual(), auto.getVelMax(), 0.0);
    }
    

}
//import java.util.*;

public abstract class Auto {

	protected AutoEstado estado;

	public Auto(double[] posicion) {
		estado = new AutoNormal(posicion);
	}
	
	public Auto(double[] posicion, double velMax) {
		estado = new AutoNormal(posicion, velMax);
	}

	public void desestabilizar(double deltaTime) {
		estado = estado.desestabilizar(deltaTime);
	}

	public void explotar(double deltaTime) {
		estado = estado.explotar(deltaTime);
	}
	
	public void normalizar(double deltaTime) {
		estado = estado.normalizar(deltaTime);
	}
	
	public void setDoblarIzquierda (boolean doblarIzquierda) {
		estado.setDoblarIzquierda(doblarIzquierda);
	}
	
	public void setDoblarDerecha(boolean doblarDerecha) {
		estado.setDoblarDerecha(doblarDerecha);
	}
	
	public void setAcelerar(boolean acelerar) {
		estado.setAcelerar(acelerar);
	}

	public void updateAuto(double deltaTime) {
//		estado = estado.updateEstado(deltaTime);
		estado.updateHorizontal(deltaTime);
		estado.updateVertical(deltaTime);
	}
	
	public double getX() {
		return estado.getX();
	}
	
	public double getY() {
		return estado.getY();
	}
	
	public double getVelActual() {
		return estado.getVelActual();
	}

	public double getVelMax() {
		return estado.getVelMax();
	}
	
	public boolean isAcelerar() {
		return estado.isAcelerar();
	}
	
	public boolean isDerecha() {
		return estado.isDerecha();
	}
	
	public boolean isIzquierda() {
		return estado.isIzquierda();
	}
	
	


}
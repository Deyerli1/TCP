package road_fighter.objects;

import road_fighter.states.AutoEstado;
import road_fighter.states.AutoNormal;

public abstract class Auto {

	protected boolean doblarIzquierda, doblarDerecha, acelerar;
	protected double x, y;
	protected double decremento, velMax, velActual, velDoblar;
	protected final int ACELERACION = 20; /// placeholder
	protected AutoEstado estado;

	public Auto(double[] posicion) {
		this.acelerar=false;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velActual = 0;
		this.decremento = 20;
		this.velMax = 200;
		this.velDoblar = 1;
		this.x = posicion[0];
		this.y = posicion[1];
		estado = new AutoNormal(this);
	}
		
	public Auto(double[] posicion, double velMax) {//para los npcs
		this.acelerar=true;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velActual = 0;
		this.decremento = 20;
		this.velMax = velMax;
		this.velDoblar = 1;
		this.x = posicion[0];//posiciones deberian ser randoms
		this.y = posicion[1];
		estado = new AutoNormal(this);
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
	
	public void updateAuto(double deltaTime) {
//		estado = estado.updateEstado(deltaTime);
		updateHorizontal(deltaTime);
		updateVertical(deltaTime);
	}
	
	public abstract void updateHorizontal(double deltaTime);
	
	public abstract void updateVertical(double deltaTime);
		
	public void chocar(Pozo pozo,double deltaTime) {
		estado = estado.explotar(deltaTime);
	}

	public void chocar(Cordon cordon,double deltaTime) {
		estado = estado.explotar(deltaTime);
	}
	
	public void chocar(ManchaAceite mancha, double deltaTime) {
		estado = estado.desestabilizar(deltaTime);
	}

	public void chocar(AutoJugador auto,double deltaTime) {
		
	}
	public void chocar(AutoNpc bot,double deltaTime) {
		
	}
	
	public abstract void habilidadEspecial();
	
	
	
	//GETTERS AND SETTERS
	
	public void setY(double deltaTime) {
		// ver que pasa cuando cruzas la meta
		this.y += deltaTime * this.velActual;
	}
	
	public void setX(double x) {
		// ver que pasa al chocar con los cordones
		this.x = x;
	}
	
	public void setVelActual(double nuevaVel) {
		this.velActual = nuevaVel;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getVelActual() {
		return velActual;
	}
	
	public double getVelMax() {
		return velMax;
	}
	
	public int getAceleracion() {
		return ACELERACION;
	}
	
	
	
	
	
	
}
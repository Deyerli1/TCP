package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.states.AutoEstado;
import road_fighter.states.AutoNormal;

public abstract class Auto extends GameObject implements Updatable, Renderable, Collidator {

	protected boolean doblarIzquierda, doblarDerecha, acelerar = false;
	protected double x, y;
	protected double decremento, velMax, velActual, velDoblar;
	protected final int ACELERACION = 1; /// placeholder
	private int velocidadDoblado = 1;
	protected AutoEstado estado;
	
	protected final int width = 32;
	protected final int height = 50;
	
	protected ImageView render;
	protected Image autoImg;

	
	protected Rectangle collider;
	protected final double colliderTolerance = 0.9;
	protected final int colliderWidth = (int) (width * colliderTolerance);
	protected final int colliderHeight = (int) (height * colliderTolerance);

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
	
	public void doblarDerecha() {
		this.estado.doblarDerecha(this.x);
	}
	
	public void doblarIzquierda() {
		this.estado.doblarIzquierda(this.x);
	}
	
	public void acelerar() {
		this.estado.acelerar(this.y);
	}
		
	//GETTERS AND SETTERS
	
	public boolean isDoblarIzquierda() {
		return doblarIzquierda;
	}

	public boolean isDoblarDerecha() {
		return doblarDerecha;
	}

	public boolean isAcelerar() {
		return acelerar;
	}

	public void setY(double y) {
		this.y = y;
		render.setY(this.y);
		collider.setY(this.y- colliderHeight/2);
	}
	
	public void setX(double x) {
		// ver que pasa al chocar con los cordones
		//System.out.println(this.x);
		this.x = x;
		//System.out.println(this.x);
		render.setX(this.x);
		collider.setX(this.x- colliderWidth/2);
	}
	
	public void setVelActual(double nuevaVel) {
		this.velActual = nuevaVel;
		
	}
	
	public void setAcelerar(boolean valor) {
		this.acelerar = valor;
	}
	
	public void setDoblarIzquierda(boolean valor) {
		if(getVelActual() > 0) {
			this.doblarIzquierda = valor;
		}
	}
	
	public void setDoblarDerecha(boolean valor) {
		System.out.println("doblando");
		if(getVelActual() > 0) {
			this.doblarDerecha = valor;
		}
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
	
    public int getVelocidadDoblado() {
    	return this.velocidadDoblado;
    }

	@Override
	public void update(double deltaTime) {
doblarDerecha();
doblarIzquierda();
acelerar();//setea Y


		//render.setX(this.x);
		//collider.setX(this.x- colliderWidth/2);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void collide(Collideable collideable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.states.AutoEstado;
import road_fighter.states.AutoNormal;
import road_fighter.states.AutoExplotado;
import road_fighter.states.AutoDesestabilizado;

public abstract class Auto extends GameObject implements Updatable, Renderable, Collidator {

	protected boolean doblarIzquierda, doblarDerecha, acelerar = false;
	protected double x, y;
	protected double decremento, velMax, velActual, velDoblar;
	protected final int ACELERACION = 1; /// placeholder
	private int velocidadDoblado = 1;
	protected AutoEstado estado;
	private double tiempoPenalizacion = 0;
	private final double tiempoInmunidadMaximo = 200;
	private boolean deltaTimeMaloSeteado = false;
	
	protected final int width = 32;
	protected final int height = 50;
	
	protected ImageView render;
	protected Image autoImg;
	
	protected final long NANOS_IN_SECOND = 1_000_000_000;
	protected final double NANOS_IN_SECOND_D = 1_000_000_000.0;
		
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
		//camera.translateXProperty().set(this.x);
		//camera.translateXProperty().set(this.y);
		
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

	public void desestabilizar() {
		estado = estado.desestabilizar();
	}	

	public void explotar() {
		estado = estado.explotar();
	}
	
	public void inmunizar() {
		estado = estado.inmunizar();
	}
	
	public void inmunizar(double deltaTimeActual) {
		if((estado.getClass() == AutoExplotado.class || estado.getClass() == AutoDesestabilizado.class)) {
			tiempoPenalizacion++;
			System.out.println(tiempoPenalizacion);
		}
		else if (tiempoPenalizacion > 0){
			tiempoPenalizacion--;
			System.out.println(tiempoPenalizacion);
		}
		
		if( (estado.getClass() == AutoExplotado.class || estado.getClass() == AutoDesestabilizado.class) && tiempoPenalizacion > tiempoInmunidadMaximo ) {
			System.out.println("deltaTimeActual "+deltaTimeActual);
			tiempoPenalizacion = 50;
			estado = estado.normalizar();
		}
		
		if(tiempoPenalizacion == 0)
			deltaTimeMaloSeteado = false;
	}
	
	public void updateAuto(double deltaTime) {
//		estado = estado.updateEstado(deltaTime);
		
		updateHorizontal(deltaTime);
		updateVertical(deltaTime);
	}
	
	public abstract void updateHorizontal(double deltaTime);
	
	public abstract void updateVertical(double deltaTime);
			
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
	
	public void setImg(Image img) {
		render.setImage(img);
		System.out.println("seteando imagen");
	}

	public void setY(double y) {
		this.y = y;
		render.setY(this.y);
		collider.setY(this.y- colliderHeight/2);
	}
	
	public void setX(double x) {
		// ver que pasa al chocar con los cordones

		this.x = x;
		render.setX(this.x);
		collider.setX(this.x- colliderWidth/2);
	}
	
	public void setVelActual(double nuevaVel) {
		this.velActual = nuevaVel;
		
	}
	
	public void setAcelerar(boolean valor) {
		this.acelerar = valor;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setDoblarIzquierda(boolean valor) {
		if(getVelActual() > 0) {
			this.doblarIzquierda = valor;
		}
		else {
			this.doblarIzquierda = false;
		}
	}
	
	public void setDoblarDerecha(boolean valor) {
		if(getVelActual() > 0) {
			this.doblarDerecha = valor;
		}
		else {
			this.doblarDerecha = false;
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
    
    public void setDeltaTimeMalo (double deltaTime) {
    	if(estado.getClass() != AutoNormal.class && !deltaTimeMaloSeteado) {
			deltaTimeMaloSeteado = true;
		}
    }
    
	@Override
	public void update(double deltaTime) {
		setDeltaTimeMalo(deltaTime);
		inmunizar(deltaTime);
		doblarDerecha();
		doblarIzquierda();
		acelerar();//setea Y
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void collide(Collideable collideable) {
		//hitAudio.play();
			if (!deltaTimeMaloSeteado && collideable.getClass() == Pozo.class) {
				this.explotar();
			} else if (!deltaTimeMaloSeteado && collideable.getClass() == ManchaAceite.class){
				this.desestabilizar();
			}
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
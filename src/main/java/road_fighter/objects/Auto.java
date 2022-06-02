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

public abstract class Auto extends GameObject implements Updatable, Renderable, Collidator, Collideable {

	protected boolean doblarIzquierda, doblarDerecha, acelerar = false;
	protected double x, y;
	protected double velMax, velActual, velDoblar;
	protected final int ACELERACION = 1; /// placeholder
	protected int velocidadDoblado = 10;
	protected AutoEstado estado;
	private double tiempoPenalizacion = 0;
	private final double tiempoPenalizacionMaximo = 120;
	private boolean deltaTimeMaloSeteado = false;
	private final int choquesMaximos = 5;
	private int choquesActuales = 0;
	protected String imgPath;
	
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
		this.velMax = 200;
		this.x = posicion[0];
		this.y = posicion[1];
	}
		
	public Auto(int posicion) {//para los npcs
		this.acelerar=true;
		this.velActual = 0;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velMax = 150;
		this.velocidadDoblado = 1;
		this.y = posicion;
		estado = new AutoNormal(this, 1);
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
		}
		else if (tiempoPenalizacion > 0){
			tiempoPenalizacion--;
		}
		
		if( (estado.getClass() == AutoExplotado.class || estado.getClass() == AutoDesestabilizado.class) && tiempoPenalizacion > tiempoPenalizacionMaximo ) {
			tiempoPenalizacion = 50;//para que la inmunidad dure un cierto tiempo
			estado = estado.normalizar();
		}
		
		if(tiempoPenalizacion == 0)
			deltaTimeMaloSeteado = false;
	}	
	
	public void updateAuto(double deltaTime) {
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
	
	public String getImgPath() {
		return imgPath;
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
			if (!deltaTimeMaloSeteado && (collideable.getClass() == Pozo.class || choquesActuales == choquesMaximos || collideable.getClass() == Cordon.class) ) {
				choquesActuales = 0;
				this.explotar();
			} else if (!deltaTimeMaloSeteado && (collideable.getClass() == ManchaAceite.class || collideable.getClass() == AutoJugador.class || collideable.getClass() == Movil.class || collideable.getClass() == Fijo.class) ){
				choquesActuales++;
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
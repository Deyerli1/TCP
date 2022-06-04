package road_fighter.objects;

import road_fighter.utils.AudioResources;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
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

	protected double x, y;
	protected double velMax, velActual, velDoblar;
	private double tiempoPenalizacion = 0;//el tiempo que paso desde que inicio la penalizacion
	private final double TIEMPO_PENALIZACION_MAXIMO = 120, VEL_MAX_JUGADOR = 120, VEL_MAX_NPC = 100;
	
	protected final int ACELERACION = 1;
	protected int velocidadDoblado = 10;
	private final int CHOQUES_MAXIMOS = 5;//choques antes de explotar
	private int choquesActuales = 0;
	
	protected boolean doblarIzquierda, doblarDerecha, acelerar = false;
	protected boolean penalizado = false, habilidadEspecialActiva = false, ganador = false, explotado = false;
	
	protected AutoEstado estado;
	protected String imgPath;
	
	private AudioClip desestabilizacionAudio;
	private AudioClip explosionAudio;
	private AudioClip motorAudio;
	private AudioClip whatUpWithThat;//sonido adicional al desestabilizar, solo para jugadores
	
	protected ImageView render;
	protected Image autoImg;
		
	protected Rectangle collider;
	protected final double colliderTolerance = 0.9;
	protected final int colliderWidth;
	protected final int colliderHeight;

	public Auto(int x, int y, int width, int height) {
		this.acelerar=false;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velActual = 0;
		this.velMax = VEL_MAX_JUGADOR;
		this.x = x;
		this.y = y;
		colliderWidth = (int) (width * colliderTolerance);
		colliderHeight = (int) (height * colliderTolerance);
		initAudios();
	}
		
	public Auto(int y, int width, int height) {//para los npcs
		this.acelerar=true;
		this.velActual = 0;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velMax = VEL_MAX_NPC;
		this.velocidadDoblado = 1;
		this.y = y;
		colliderWidth = (int) (width * colliderTolerance);
		colliderHeight = (int) (height * colliderTolerance);
		initAudios();
	}

	public void desestabilizar() {
		estado = estado.desestabilizar();
	}	

	public void explotar() {
		this.velActual = 0;
		estado = estado.explotar();
	}
		
	public void normalizar() {
		if((estado.getClass() == AutoExplotado.class || estado.getClass() == AutoDesestabilizado.class || habilidadEspecialActiva)) {
			tiempoPenalizacion++;
		}
		else if (tiempoPenalizacion > 0){
			tiempoPenalizacion--;
		}
		
		if(tiempoPenalizacion > TIEMPO_PENALIZACION_MAXIMO ) {
			tiempoPenalizacion = 50;//para que la inmunidad dure un cierto tiempo
			habilidadEspecialActiva = false;
			this.velMax = VEL_MAX_JUGADOR;
			estado = estado.normalizar();
		}
		
		if(tiempoPenalizacion == 0)
			penalizado = false;
	}	
	
	private void initAudios() {
		whatUpWithThat = AudioResources.getWhatUpWithThat();
		explosionAudio = AudioResources.getExplosionAudio();
		motorAudio = AudioResources.getMotorAudio();
		
		setAudioVol(1);
	}
		
	public void doblarDerecha() {
		this.estado.doblarDerecha(this.x);
	}
	
	public void doblarIzquierda() {
		this.estado.doblarIzquierda(this.x);
	}
	
	public void acelerar() {
		this.estado.acelerar(this.y);
	}
	
	public abstract void habilidadEspecial();
		
	//SETTERS
	
	public void setVelMax(double velMax) {
		this.velMax = velMax;
	}
	
	private void setAudioVol(int vol) {
		whatUpWithThat.setVolume(vol);
		explosionAudio.setVolume(vol);
		motorAudio.setVolume(vol);
	}
	
	public void setImg(Image img) {
		render.setImage(img);
	}
	
	public void setAutoAngle(int angulo) {
		render.setRotate(angulo);
		collider.setRotate(angulo);
	}

	public void setY(double y) {
		this.y = y;
		render.setY(this.y);
		collider.setY(this.y- colliderHeight/2);
	}
	
	public void setX(double x) {
		this.x = x;
		render.setX(this.x);
		collider.setX(this.x- colliderWidth/2);
	}
	
	public void setVelActual(double nuevaVel) {
		this.velActual = nuevaVel;
	}
	
	public void setAcelerar(boolean valor) {
		this.acelerar = valor;
		if(isAcelerar() && !motorAudio.isPlaying()) {
			motorAudio.play();
		}
	}
	
	public void setPenalizado () {
    	if(estado.getClass() != AutoNormal.class && !penalizado) {
    		penalizado = true;
		}
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
	
	//GETTERS
	
	public boolean isGanador() {
		return ganador;
	}
	
 
	public AutoEstado getEstado() {
		return estado;
	}

	public boolean isDoblarIzquierda() {
		return doblarIzquierda;
	}

	public boolean isDoblarDerecha() {
		return doblarDerecha;
	}

	public boolean isAcelerar() {
		return acelerar;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public double getVelMaxJugador() {
		return VEL_MAX_JUGADOR;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
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
    
    //OVERRIDES
    
	@Override
	public void update(double deltaTime) {
		setPenalizado();
		normalizar();
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
			if ( (!penalizado || estado.getClass() == AutoDesestabilizado.class ) && (collideable.getClass() == Pozo.class || choquesActuales == CHOQUES_MAXIMOS || collideable.getClass() == Cordon.class) ) {
				choquesActuales = 0;
				explosionAudio.play();
				this.explotar();
			} else if (!penalizado && (collideable.getClass() == ManchaAceite.class || collideable.getClass() == AutoJugador.class || collideable.getClass() == Movil.class || collideable.getClass() == Fijo.class) ){
				choquesActuales++;
				if( (AutoJugador.class).toString() == "class road_fighter.objects.AutoJugador")
					whatUpWithThat.play();
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
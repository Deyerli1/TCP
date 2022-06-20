package road_fighter.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import road_fighter.interfaces.Collideable;
import road_fighter.states.AutoGanador;
import road_fighter.states.AutoNormal;



public class AutoJugador extends Auto {
	 
	private int topeHabilidadEspecial = 5;
	private static final String fotoJugador = "file:src/main/resources/img/jugador/familySedan.png";
	private static final int width = 35;
	private static final int height = 70;
	public NombreJugador nombreAuto;
	public VelocidadInfo velInfo;
	
	public AutoJugador(String nombreJugador, int x, int y) {
		super(x, y, width, height);
		autoImg = new Image(fotoJugador, width, height, false, false);
    	this.imgPath = fotoJugador;
    	render = new ImageView(autoImg);
    	render.setX(x);
    	render.setY(y);
		render.relocate(x - width/2, y - height/2);

		collider = new Rectangle(x - colliderWidth/2 , y - colliderHeight/2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setX(x- colliderWidth/2);
		collider.setY(y- colliderHeight/2);
		collider.setStroke(Color.BLUE);
		estado = new AutoNormal(this);
		
		nombreAuto = new NombreJugador(nombreJugador);
		velInfo = new VelocidadInfo();
    }
	
	private void ganar() {
		render.setX(x-300);
		render.setY(y-350);
		estado = new AutoGanador(this);
	}
	
	public boolean isGanador() {
		return ganador;
	}
		
	@Override
    public void habilidadEspecial() {
    	if(topeHabilidadEspecial > 0 && !penalizado) {
    		habilidadEspecialActiva = true;
    		setVelMax(250);
    		topeHabilidadEspecial--;
    	}
    }

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public void collide(Collideable collideable) {
			super.collide(collideable);
			if ( collideable.getClass() == Meta.class && !ganador) {
				ganador = true;
				ganar();
			}
	}
	
	@Override
	public void update(double deltaTime) {
		super.update(deltaTime);
		VelocidadInfo.setValores(velActual, y);
		NombreJugador.setValores(this.x, this.y);
	}
}
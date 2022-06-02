package road_fighter.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import road_fighter.states.AutoNormal;



public class AutoJugador extends Auto {

	private int topeHabilidadEspecial = 5;
	protected String nombreJugador;
	private static final String fotoJugador = "file:src/main/resources/img/familySedan.png";
	
	public AutoJugador(String nombreJugador, double[] posicion) {
		super(posicion);
		autoImg = new Image(fotoJugador, width, height, false, false);
    	this.imgPath = fotoJugador;
		this.nombreJugador = nombreJugador;
    	render = new ImageView(autoImg);
    	render.setX(posicion[0]);
    	render.setY(posicion[1]);
		render.relocate(posicion[0] - width/2, posicion[1] - height/2);

		collider = new Rectangle(posicion[0] - colliderWidth/2 , posicion[1] - colliderHeight/2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setX(posicion[0]- colliderWidth/2);
		collider.setY(posicion[1]- colliderHeight/2);
		collider.setStroke(Color.BLUE);
		estado = new AutoNormal(this);
    }
	
    public void habilidadEspecial() {
    	if(topeHabilidadEspecial > 0) {
    		this.velMax = 220;
    		topeHabilidadEspecial--;
    	}
    }
        
    public String getNombreJugador() {
    	return nombreJugador;
    }


    @Override
	public void updateHorizontal(double deltaTime) {
    	
	}

	@Override
	public void updateVertical(double deltaTime) {
	}
}
package road_fighter.objects;

public class Movil extends AutoNpc {
	
	private static final String fotoMovil = "file:src/main/resources/img/NpcMovil.png";

    public Movil(double posicionJugador) {
    	super((int)posicionJugador, fotoMovil);
    	this.doblarDerecha=true;
    }
    
  public void setX(double x) {
	if(this.isDoblarDerecha()) {
		if(this.getX() > 600) {
			setDoblarIzquierda(true);
			setDoblarDerecha(false);
		}
	}
	else if (this.isDoblarIzquierda() ) {
		if(this.getX() < 100) {
			setDoblarDerecha(true);
			setDoblarIzquierda(false);    			
		}
		
	}
	this.x = x;
	render.setX(this.x);
	collider.setX(this.x- colliderWidth/2);
}

	@Override
	public void updateHorizontal(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVertical(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilidadEspecial() {
		// TODO Auto-generated method stub
		
	}

}
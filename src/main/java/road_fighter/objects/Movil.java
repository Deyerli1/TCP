package road_fighter.objects;
public class Movil extends AutoNpc {

    public Movil(String nombre, double[] posicion) {
    	super("Movil", posicion);
    	this.setDoblarDerecha(true);
    }
    
    public void updateAuto(double deltaTime) {
    	super.updateAuto(deltaTime);
    	if(this.isDerecha()) {
    		if(this.getX() == 100) {
    			setDoblarIzquierda(true);
    			setDoblarDerecha(false);
    		}
    	}
    	else if (this.isIzquierda() ) {
    		if(this.getX() == 40) {
    			setDoblarDerecha(true);
    			setDoblarIzquierda(false);    			
    		}
    	}
	}

}

import java.util.*;

/**
 * 
 */
public abstract class Auto {

	protected double velMax, velActual, x,y;
    //protected void modificacionDeVelocidad;
    //protected void apariencia;
    protected boolean desestabilizado, explotado, doblarIzquierda, acelerar;
    protected Direccion teclaDireccion;
    public Auto() {
    	this.velActual=0;
    	this.x=50;
    	this.y=50;
    	this.desestabilizado=false; 
    	this.explotado=false;
    	this.doblarIzquierda=false;
    	this.acelerar=false;
    }
    /**
     * @param teclaDireccion
     */
    public void doblar() {
        // TODO implement here
    	if (!desestabilizado && !explotado && this.velActual > 0) { 
            if (doblarIzquierda) { //Izquierda
                this.x--;
            	//render.setScaleX(-1);
                //runningAnimation.play();
            } else{ //Derecha
                this.x++;
            	//render.setScaleX(1);
                //runningAnimation.play();
            }
        }
    }

    /**
     * @param dv
     */
    public void acelerar() {
        // TODO implement here
    	if (!desestabilizado && !explotado && this.velMax > this.velActual && acelerar) {
    		this.velActual++;
    		//Implementar un metodo para moverse
    		moverse();
        	//render.setScaleX(-1);
            //runningAnimation.play();
        }
    }

    
    public void desacelerar() {
        // TODO implement here
    	if (!explotado && this.velActual > 0) {
    		this.velActual--;
    		//Implementar un metodo para moverse
    		moverse();
        	//render.setScaleX(-1);
            //runningAnimation.play();
        }
    }

    /**
     * 
     */
    public void desestabilizar() {
        // TODO implement here
    	this.desestabilizado=true;
    	desacelerar();
    }

    /**
     * 
     */
    public void explotar() {
        // TODO implement here
    	this.explotado=true;
    }
    
    public void moverse() {
    	setY();
    }
	public void setX(double x) {
		this.x = x;
	}
	public void setY() {
		this.y+=this.velActual;
	}

}
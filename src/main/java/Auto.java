import java.util.*;

public  class Auto {

	protected final int ACELERACION = 20; /// placeholder
	protected double x, y;
	protected double decremento,velMax, velActual, velDoblar;
	// protected void modificacionDeVelocidad;
	// protected void apariencia;
	protected boolean desestabilizado, explotado; 
	protected boolean doblarIzquierda, doblarDerecha;

	public Auto() {
		this.velActual = 0;
		this.x = 50;
		this.y = 50;
		this.desestabilizado = false;
		this.explotado = false;
		this.doblarIzquierda = false;
		this.doblarDerecha = false;
		this.decremento=20;
		this.velActual=20;
	}

///////////////////// METODOS DE SETEO DE BOOLEANOS INTENROS 
	public void setAcelerar(int input, double deltaTime) {
		if (!desestabilizado && !explotado) {
			this.setVelActual(ACELERACION*input*deltaTime);
		}
	}

	public void desestabilizar() {
		this.desestabilizado = true;
	}

	public void explotar() {
		this.explotado = true;
	}
		
	public void setDoblarIzquierda(boolean doblarIzquierda) {
		this.doblarIzquierda = doblarIzquierda;
	}

	public void setDoblarDerecha(boolean doblarDerecha) {
		this.doblarDerecha = doblarDerecha;
	}
///////////////////////////////////////////////////////////
	
/// METODOS DE UPDATEO DE POSICION INTERNA //////
	
	public void updateHorizontal(double deltaTime) {//doblar
		if(!explotado) {
			int direction = doblarIzquierda ? -1 : (doblarDerecha ? 1 : 0);
			this.setX(x + direction * this.velDoblar * deltaTime);
		}
	}
	
	public void updateVertical(double deltaTime) {
		if(!explotado) {
			this.setY(deltaTime);
		}
	}
	
	public void setY(double deltaTime) {
		//ver que pasa cuando cruzas la meta
		this.y+=deltaTime*this.velActual;
	}
	
	public void setX(double x) {
		//ver que pasa al chocar con los cordones
		this.x+=x;
	}
	
	public void setVelActual(double aceleracion) {
		double aceleracionFinal = this.velActual + aceleracion;
		if( (aceleracionFinal < this.velMax) && (aceleracionFinal >= 0) ) {
			this.velActual = aceleracionFinal;
		}
	}
/////////////////////////////////////////////////

}
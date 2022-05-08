import java.util.*;

public abstract class Auto {

	protected final int ACELERACION = 20; /// placeholder
	protected final int DURACION_MALA = 5; /// placeholder
	protected double x, y;
	protected double decremento, velMax, velActual, velDoblar;
	protected double ultimoDeltaTimeMalo;
	// protected void modificacionDeVelocidad;
	// protected void apariencia;
	protected boolean desestabilizado, explotado;
	protected boolean doblarIzquierda, doblarDerecha, acelerar;

	public Auto() {
		this.velActual = 0;
		this.x = 50;
		this.y = 50;
		this.desestabilizado = false;
		this.explotado = false;
		this.doblarIzquierda = false;
		this.doblarDerecha = false;
		this.acelerar = false;
		this.decremento = 20;
		this.velMax = 200;
		this.velDoblar = 1;
	}

///////////////////// METODOS DE SETEO INTERNOS 
//	public void setAcelerar(int input, double deltaTime) {
//		if (!desestabilizado && !explotado) {
//			this.setVelActual(ACELERACION*input*deltaTime);
//		}
//	}

	public void desestabilizar(double deltaTime) {
		this.desestabilizado = true;
		this.ultimoDeltaTimeMalo = deltaTime;
	}

	public void explotar(double deltaTime) {
		this.explotado = true;
		this.ultimoDeltaTimeMalo = deltaTime;
		this.velActual = 0;
		this.acelerar = false;
	}

	public void setAcelerar(boolean acelerar) { ///Quiza sea mejor ponerle acelerando y mas verboso todos estos metodos
		this.acelerar = acelerar;
	}

	public void setDoblarIzquierda(boolean doblarIzquierda) {
		this.doblarIzquierda = doblarIzquierda;
	}

	public void setDoblarDerecha(boolean doblarDerecha) {
		this.doblarDerecha = doblarDerecha;
	}
///////////////////////////////////////////////////////////

/// METODOS DE UPDATEO DE POSICION INTERNA //////

	public void updateHorizontal(double deltaTime) {// doblar
		if (!explotado) {
			int direction;
			if (!desestabilizado) {
				direction = doblarIzquierda ? -1 : (doblarDerecha ? 1 : 0);
			} else {
				direction = 1;
				/// Ver esto mejor cuando veamos colisiones
				/// para ver que pasa cuando chocamos.
			}
			this.setX(x + direction * this.velDoblar * deltaTime);
		}
	}

	public void updateVertical(double deltaTime) {
		if (!explotado) {
			this.setVelActual(deltaTime);
			this.setY(deltaTime);
		}
	}

	public void updateAuto(double deltaTime) {
		updateDesestabilizado(deltaTime);
		updateExplotado(deltaTime);
		updateHorizontal(deltaTime);
		updateVertical(deltaTime);
	}

	private void updateDesestabilizado(double deltaTime) {
		if (desestabilizado && this.ultimoDeltaTimeMalo + this.DURACION_MALA < deltaTime) {
			desestabilizado = false;
		}
	}
	//oli

	private void updateExplotado(double deltaTime) {
		if (explotado && this.ultimoDeltaTimeMalo + this.DURACION_MALA < deltaTime) {
			explotado = false;
		}
	}

	public void setY(double deltaTime) {
		// ver que pasa cuando cruzas la meta
		this.y += deltaTime * this.velActual;
	}

	public void setX(double x) {
		// ver que pasa al chocar con los cordones
		this.x = x;
	}

	public void setVelActual(double deltaTime) {
		if (!desestabilizado && !explotado) {
			int sentido = this.acelerar ? 1 : -1;
			double nuevaVel = this.velActual + ACELERACION * sentido * deltaTime;
			if ((nuevaVel < this.velMax) && (nuevaVel >= 0)) {
				this.velActual = nuevaVel;
			} else if (nuevaVel < 0) {
				this.velActual = 0;
			} else {
				this.velActual = this.velMax;
			}
		}

	}
/////////////////////////////////////////////////

/// METODOS GETTERS //////

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelActual() {
		return velActual;
	}

	public boolean isDesestabilizado() {
		return desestabilizado;
	}

	public boolean isExplotado() {
		return explotado;
	}

	public double getVelMax() {
		return velMax;
	}
	
	public boolean isAcelerar() {
		return acelerar;
	}

}
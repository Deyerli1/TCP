
public class AutoNormal extends AutoEstado {

	AutoNormal(double[] posicion) {
		super(posicion);
	}
	
	AutoNormal(double[] posicion, double velMax) {//para los npc
		super(posicion,velMax);
	}

	@Override
	public AutoEstado desestabilizar(double deltaTime) {
		double[] posicion = { this.x, this.y };
		return new AutoDesestabilizado(posicion, this.velActual, deltaTime);
	}

	@Override
	public AutoEstado explotar(double deltaTime) {
		double[] posicion = { this.x, this.y };
		return new AutoExplotado(posicion, deltaTime);
	}

	@Override
	public void setAcelerar(boolean acelerar) { /// Quiza sea mejor ponerle acelerando y mas verboso todos estos metodos
		this.acelerar = acelerar;
	}

	@Override
	public void setDoblarIzquierda(boolean doblarIzquierda) {
		this.doblarIzquierda = doblarIzquierda;
	}

	@Override
	public void setDoblarDerecha(boolean doblarDerecha) {
		this.doblarDerecha = doblarDerecha;
	}

	@Override
	public AutoEstado normalizar(double deltaTime) {
		return null;
	}

	@Override
	public void updateHorizontal(double deltaTime) {
		int direction = doblarIzquierda ? -1 : (doblarDerecha ? 1 : 0);
		this.setX(x + direction * this.velDoblar * deltaTime);
	}

	@Override
	public void updateVertical(double deltaTime) {
		this.setVelActual(deltaTime);
		this.setY(deltaTime);
	}
	
//	@Override
//	public void updateEstado(double deltaTime) {
//		
//	}

	@Override
	public void setVelActual(double deltaTime) {
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
	
	public void habilidadEspecial() {
		//limitar cantidad de usos
		this.velMax = 220;
	}

}

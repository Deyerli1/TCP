
public abstract class AutoEstado {
	
	protected boolean doblarIzquierda, doblarDerecha, acelerar;
	protected double x, y;
	protected double decremento, velMax, velActual, velDoblar;
	protected final int ACELERACION = 20; /// placeholder
	
	AutoEstado(double[] posicion){
		this.acelerar=false;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velActual = 0;
		this.decremento = 20;
		this.velMax = 200;
		this.velDoblar = 1;
		this.x = posicion[0];
		this.y = posicion[1];
	}
	
	AutoEstado(double[] posicion, double velMax){//para los npcs
		this.acelerar=true;
		this.doblarDerecha=false;
		this.doblarIzquierda=false;
		this.velActual = 0;
		this.decremento = 20;
		this.velMax = velMax;
		this.velDoblar = 1;
		this.x = posicion[0];//posiciones deberian ser randoms
		this.y = posicion[1];
	}
	
	public abstract AutoEstado desestabilizar(double deltaTime);
	
	public abstract AutoEstado explotar(double deltaTime);
	
	public abstract AutoEstado normalizar(double deltaTime);
	
	public abstract void setAcelerar(boolean acelerar);
	
	public abstract void setDoblarIzquierda(boolean doblarIzquierda);
	
	public abstract void setDoblarDerecha(boolean doblarDerecha);
	
	public abstract void updateHorizontal(double deltaTime);
	
	public abstract void updateVertical(double deltaTime);
	
//	public abstract void updateEstado(double deltaTime);
	
	public abstract void habilidadEspecial();
	
	public void setY(double deltaTime) {
		// ver que pasa cuando cruzas la meta
		this.y += deltaTime * this.velActual;
	}
	
	public void setX(double x) {
		// ver que pasa al chocar con los cordones
		this.x = x;
	}
	
	public abstract void setVelActual(double deltaTime);
	
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

		public double getVelMax() {
			return velMax;
		}
		
		public boolean isAcelerar() {
			return acelerar;
		}
		
		public boolean isDerecha() {
			return doblarDerecha;
		}
		
		public boolean isIzquierda() {
			return doblarIzquierda;
		}

}

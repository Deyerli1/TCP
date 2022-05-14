
public class AutoDesestabilizado extends AutoEstado {

	protected final int DURACION_MALA = 5; /// placeholder
	protected double ultimoDeltaTimeMalo;
	
	AutoDesestabilizado(double[] posicion, double velActual, double deltaTime) {
		super(posicion);
		this.acelerar = true;
		this.velActual = velActual;
		ultimoDeltaTimeMalo = deltaTime;
	}

	@Override
	public AutoEstado desestabilizar(double deltaTime) {
		return null; //??
	}

	@Override
	public AutoEstado explotar(double deltaTime) {
		double[] posicion = {this.x,this.y};
		return new AutoExplotado(posicion, deltaTime);
	}

	@Override
	public AutoEstado normalizar(double deltaTime) {
		double[] posicion = {this.x,this.y};
		return new AutoNormal(posicion);
	}

	@Override
	public void setAcelerar(boolean acelerar) {
		this.acelerar = acelerar;
	}

	@Override
	public void setDoblarIzquierda(boolean doblarIzquierda) {
		this.doblarIzquierda = false;
	}

	@Override
	public void setDoblarDerecha(boolean doblarDerecha) {
		this.doblarDerecha = false;
	}

	@Override
	public void updateHorizontal(double deltaTime) {
		this.x += 1;
	}
	
	@Override
	public void updateVertical(double deltaTime) {
		this.setVelActual(deltaTime);
		this.setY(deltaTime);
	}

	@Override
	public void setVelActual(double deltaTime) {
		//mantiene la velocidad como esta
	}
	
	@Override
	public void habilidadEspecial() {
	}
}

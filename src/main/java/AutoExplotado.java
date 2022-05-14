
public class AutoExplotado extends AutoEstado{

	protected final int DURACION_MALA = 5; /// placeholder
	protected double ultimoDeltaTimeMalo;
	
	AutoExplotado(double[] posicion, double deltaTime) {
		super(posicion);
		ultimoDeltaTimeMalo = deltaTime;
	}

	@Override
	public AutoEstado desestabilizar(double deltaTime) {
		return null;
	}

	@Override
	public AutoEstado explotar(double deltaTime) {
		return null;
	}

	@Override
	public AutoEstado normalizar(double deltaTime) {
		double[] posicion = {this.x,this.y};
		return new AutoNormal(posicion);
	}

	@Override
	public void setAcelerar(boolean acelerar) {
		this.acelerar = false;
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
		//no hace nada
	}

	@Override
	public void updateVertical(double deltaTime) {
		//no hace nada
	}
	
//	public void updateEstado(double deltaTime) {
//		if(ultimoDeltaTimeMalo + 5 < deltaTime) {
//			this.normalizar();
//		}
//	}

	@Override
	public void setVelActual(double deltaTime) {
		this.velActual=0;
	}
	
	@Override
	public void habilidadEspecial() {
	}
	
	public String getEstado() {
		return "explotado";
	}

}

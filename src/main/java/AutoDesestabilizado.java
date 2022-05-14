
public class AutoDesestabilizado extends AutoEstado {

	AutoDesestabilizado(double[] posicion) {
		super(posicion);
	}

	@Override
	public AutoEstado desestabilizar(double deltaTime) {
		return null; //??
	}

	@Override
	public AutoEstado explotar(double deltaTime) {
		double[] posicion = {this.x,this.y};
		return new AutoExplotado(posicion);
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
		this.x += 1;
	}
	
	@Override
	public void updateVertical(double deltaTime) {
		this.setVelActual(deltaTime);
		this.setY(deltaTime);
	}

	@Override
	public void setVelActual(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

}

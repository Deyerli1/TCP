package road_fighter.states;

import road_fighter.objects.Auto;

public class AutoDesestabilizado extends AutoEstado {

	protected final int DURACION_MALA = 5; /// placeholder
	protected double ultimoDeltaTimeMalo;
	
	AutoDesestabilizado(Auto auto) {
		super(auto);
	}

	@Override
	public AutoEstado desestabilizar(double deltaTime) {
		return this; //??
	}

	@Override
	public AutoEstado explotar(double deltaTime) {
		return new AutoExplotado(this.auto);
	}

	@Override
	public AutoEstado normalizar(double deltaTime) {
		return new AutoNormal(this.auto);
	}

	@Override
	public void setVelActual() {
		//mantiene la velocidad como esta
	}
		
	@Override
	public String getEstado() {
		return "desestabilizado";
	}

	@Override
	public void setVelActual(int sentido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doblarDerecha(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doblarIzquierda(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acelerar(double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desacelerar(double y) {
		// TODO Auto-generated method stub
		
	}
	
	

}

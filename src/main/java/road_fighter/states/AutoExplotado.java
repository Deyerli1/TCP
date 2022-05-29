package road_fighter.states;

import road_fighter.objects.Auto;

public class AutoExplotado extends AutoEstado{

	protected final int DURACION_MALA = 5; /// placeholder
	protected double ultimoDeltaTimeMalo;
	
	AutoExplotado(Auto auto) {
		super(auto);
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
		return new AutoNormal(this.auto);
	}

	@Override
	public void setVelActual() {
		auto.setVelActual(0);
	}
		
	public String getEstado() {
		return "explotado";
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

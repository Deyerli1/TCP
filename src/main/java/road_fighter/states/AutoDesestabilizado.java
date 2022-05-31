package road_fighter.states;

import road_fighter.objects.Auto;

public class AutoDesestabilizado extends AutoEstado {

	protected final int DURACION_MALA = 5; /// placeholder
	protected double ultimoDeltaTimeMalo;
	
	AutoDesestabilizado(Auto auto) {
		super(auto);
	}

	@Override
	public AutoEstado desestabilizar() {
		return this;
	}

	@Override
	public AutoEstado explotar() {
		return new AutoExplotado(this.auto);
	}

	@Override
	public AutoEstado normalizar() {
		return new AutoNormal(this.auto);
	}
	
	@Override
	public AutoEstado inmunizar() {
		return new AutoInmune(this.auto);
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
		
		
	}
	
	

}

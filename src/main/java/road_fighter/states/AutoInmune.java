package road_fighter.states;

import road_fighter.objects.Auto;

public class AutoInmune extends AutoEstado{
	
	AutoInmune(Auto auto) {
		super(auto);
	}

	@Override
	public AutoEstado desestabilizar() {
		return new AutoDesestabilizado(this.auto);
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
		return this;
	}
		
	@Override
	public void setVelActual(int sentido) {
		double nuevaVel = auto.getVelActual() + auto.getAceleracion()*sentido;
		if ( nuevaVel > auto.getVelMax() ) {
			nuevaVel = auto.getVelMax();
		} 
		
		auto.setVelActual(nuevaVel > 0 ? nuevaVel : 0);
	}
		

	@Override
	public void doblarDerecha(double x) {
		if(this.auto.isDoblarDerecha()) {
			auto.setX(x+this.auto.getVelocidadDoblado());
		}

	}

	@Override
	public void doblarIzquierda(double x) {
		if(this.auto.isDoblarIzquierda()) {
			auto.setX(x-this.auto.getVelocidadDoblado());
		}
	}
	
	@Override
	public void acelerar(double y) {
		setVelActual(this.auto.isAcelerar() ? 1 : -1);
		if(this.auto.getVelActual() > 0)
			auto.setY(y-this.auto.getVelActual()/20);
	}

}


package road_fighter.states;

import road_fighter.objects.Auto;

public class AutoNormal extends AutoEstado {

	public AutoNormal(Auto auto) {
		super(auto);
	}
	
	@Override
	public AutoEstado desestabilizar(double deltaTime) {		
		return new AutoDesestabilizado(this.auto);
	}

	@Override
	public AutoEstado explotar(double deltaTime) {
		return new AutoExplotado(this.auto);
	}

	@Override
	public AutoEstado normalizar(double deltaTime) {
		return null;
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
	public String getEstado() {
		return "normal";
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
			auto.setY(y-this.auto.getVelActual()/50);
	}

	
}

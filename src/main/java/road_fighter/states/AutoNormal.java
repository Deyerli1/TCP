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
	public void setVelActual() {
		double nuevaVel = auto.getVelActual() + auto.getAceleracion();
		if ( nuevaVel > auto.getVelMax() ) {
			nuevaVel = auto.getVelMax();
		} 
		auto.setVelActual(nuevaVel);
	}
		
	@Override
	public String getEstado() {
		return "normal";
	}

}

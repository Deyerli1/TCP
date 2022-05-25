package road_fighter.states;

import road_fighter.objects.Auto;

public abstract class AutoEstado {
	
	protected Auto auto;
	
	AutoEstado(Auto auto){
		this.auto = auto;
	}
		
	public abstract AutoEstado desestabilizar(double deltaTime);
	
	public abstract AutoEstado explotar(double deltaTime);
	
	public abstract AutoEstado normalizar(double deltaTime);
	
	public abstract void setVelActual();
			
	public abstract String getEstado();

}

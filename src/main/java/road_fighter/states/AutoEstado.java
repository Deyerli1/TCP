package road_fighter.states;

import road_fighter.objects.Auto;

public abstract class AutoEstado{
	
	protected Auto auto;
	
	AutoEstado(Auto auto){
		this.auto = auto;
	}
		
	public abstract AutoEstado desestabilizar();
	
	public abstract AutoEstado explotar();
	
	public abstract AutoEstado normalizar();
	
	public abstract AutoEstado inmunizar();
	
	public abstract void setVelActual(int sentido);
			
	public abstract String getEstado();
	
	public abstract void doblarDerecha(double x);
	
	public abstract void doblarIzquierda(double x);
	
	public abstract void acelerar(double y);


}

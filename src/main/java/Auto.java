import java.util.*;

public abstract class Auto {

	protected final int ACELERACION = 20; /// placeholder
	protected final int DURACION_MALA = 5; /// placeholder
	protected double x, y;
	protected double ultimoDeltaTimeMalo;
	protected AutoEstado estado;


	public Auto(double[] posicion) {
		estado = new AutoNormal(posicion);
	}

	public void desestabilizar(double deltaTime) {
		estado = estado.desestabilizar(deltaTime);
	}

	public void explotar(double deltaTime) {
		estado = estado.explotar(deltaTime);
	}


	public void updateAuto(double deltaTime) {
		estado.updateHorizontal(deltaTime);
		estado.updateVertical(deltaTime);
	}


}
package road_fighter.states;

import javafx.scene.image.Image;
import road_fighter.objects.Auto;

public class AutoExplotado extends AutoEstado{

	protected final int DURACION_MALA = 5; /// placeholder
	
	AutoExplotado(Auto auto) {
		super(auto);
		System.out.println("explotando");
		auto.setImg (new Image("file:src/main/resources/img/explotado.png", auto.getWidth(), auto.getHeight(), false, false));
	}

	@Override
	public AutoEstado desestabilizar() {
		return new AutoDesestabilizado(this.auto);
	}

	@Override
	public AutoEstado explotar() {
		return this;
	}

	@Override
	public AutoEstado normalizar() {
		return new AutoNormal(this.auto);
	}
	
	@Override
	public AutoEstado inmunizar() {
		return new AutoInmune(this.auto);
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
		
		
	}
}

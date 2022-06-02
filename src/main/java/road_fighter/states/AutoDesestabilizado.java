package road_fighter.states;

import javafx.scene.image.Image;
import road_fighter.objects.Auto;

public class AutoDesestabilizado extends AutoEstado {

	private int direccionDesvio;
	
	AutoDesestabilizado(Auto auto) {
		super(auto);
		direccionDesvio = (int)Math.floor(Math.random()*(1-(-1)+1)+(-1));
		auto.setImg (new Image("file:src/main/resources/img/desestabilizado.png", auto.getWidth(), auto.getHeight(), false, false));
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
		if(this.auto.isDoblarDerecha()) {
			auto.setX(x-this.auto.getVelocidadDoblado());
		}
	}
	
	@Override
	public void doblarIzquierda(double x) {
		if(this.auto.isDoblarIzquierda()) {
			auto.setX(x+this.auto.getVelocidadDoblado());
		}

	}

	@Override
	public void acelerar(double y) {
		setVelActual(this.auto.isAcelerar() ? 1 : -1);
		if(this.auto.getVelActual() > 0) {
			auto.setY(y-this.auto.getVelActual()/16);
			auto.setX(auto.getX()+direccionDesvio*2);
		}
	}
	
	

}

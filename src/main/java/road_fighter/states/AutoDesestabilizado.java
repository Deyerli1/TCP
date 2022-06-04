package road_fighter.states;

import javafx.scene.image.Image;
import road_fighter.objects.Auto;

public class AutoDesestabilizado extends AutoEstado {

	private int direccionDesvio;
	
	AutoDesestabilizado(Auto auto) {
		super(auto);
		direccionDesvio = (int)Math.floor(Math.random()*(1-(-1)+1)+(-1));
		auto.setImg (new Image(auto.getImgPath(), auto.getWidth(), auto.getHeight(), false, false));
		auto.setAutoAngle(direccionDesvio*20);
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
		auto.setAutoAngle(0);
		return new AutoNormal(this.auto);
	}
	
	@Override
	public void setVelActual(int sentido) {

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
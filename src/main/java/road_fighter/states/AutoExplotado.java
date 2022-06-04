package road_fighter.states;

import javafx.scene.image.Image;
import road_fighter.objects.Auto;

public class AutoExplotado extends AutoEstado{
	
	private static String imgPath = "file:src/main/resources/img/explotado.gif";
	
	AutoExplotado(Auto auto) {
		super(auto);
		auto.setImg (new Image(imgPath, auto.getWidth(), auto.getHeight(), false, false));
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
		auto.setAutoAngle(0);
		auto.setX(400);
		return new AutoNormal(this.auto);
	}
	
	@Override
	public void setVelActual(int sentido) {
		//en el constructor se setea en 0
	}

	@Override
	public void doblarDerecha(double x) {
		//no se permite doblar cuando esta explotado
	}

	@Override
	public void doblarIzquierda(double x) {
		//no se permite doblar cuando esta explotado
	}

	@Override
	public void acelerar(double y) {
		//no se permite acelerar cuando esta explotado
	}
}

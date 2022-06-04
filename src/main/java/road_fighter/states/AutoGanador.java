package road_fighter.states;

import javafx.scene.image.Image;
import road_fighter.objects.Auto;

public class AutoGanador extends AutoEstado{
	
	private static String imgPath = "file:src/main/resources/img/ganador.gif";
	
	public AutoGanador(Auto auto) {
		super(auto);
		auto.setImg (new Image(imgPath, 600, 400, false, false));
		
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

package road_fighter.objects;

public class Movil extends AutoNpc {

	private static final String fotoMovil = "file:src/main/resources/img/MovilCamion.png";
	private static final int width = 40;
	private static final int height = 100;

	public Movil(double posicionJugador) {
		super((int) posicionJugador, fotoMovil, width, height);
		this.doblarDerecha = true;
	}

	public void setX(double x) {
		if (this.isDoblarDerecha()) {
			if (this.getX() > 600) {
				setDoblarIzquierda(true);
				setDoblarDerecha(false);
			}
		} else if (this.isDoblarIzquierda()) {
			if (this.getX() < 100) {
				setDoblarDerecha(true);
				setDoblarIzquierda(false);
			}

		}
		this.x = x;
		render.setX(this.x);
		collider.setX(this.x - colliderWidth / 2);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
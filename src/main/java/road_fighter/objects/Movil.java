package road_fighter.objects;

import road_fighter.utils.GameObjectBuilder;

public class Movil extends AutoNpc {

	private static final String fotoMovil = "file:src/main/resources/img/npcs/MovilCamion.png";
	private static final int width = 40;
	private static final int height = 100;
	private static final int CERVEZA_COOLDOWN = 200;
	private static int tiempoUltimaCerveza = 0;

	public Movil(double posicionJugador) {
		super((int) posicionJugador, fotoMovil, width, height);
		this.doblarDerecha = true;
	}

	public void setX(double x) {
		if (this.isDoblarDerecha()) {
			if (this.getX() > 540) {
				setDoblarIzquierda(true); 
				setDoblarDerecha(false);
			}
		} else if (this.isDoblarIzquierda()) {
			if (this.getX() < 240) {
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

	@Override
	public void habilidadEspecial() {
		if(tiempoUltimaCerveza >= CERVEZA_COOLDOWN) {
			LataCerveza lata = new LataCerveza(this.x, this.y);
			GameObjectBuilder.getInstance().add(lata);
			tiempoUltimaCerveza = 0;
		}
		else {
			tiempoUltimaCerveza++;
		}
	}

}
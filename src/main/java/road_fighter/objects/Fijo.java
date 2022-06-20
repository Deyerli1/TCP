package road_fighter.objects;

public class Fijo extends AutoNpc {
	
	private static final int width = 40;
	private static final int height = 100;
	
	private static final String fotoFijo = "file:src/main/resources/img/npcs/FijoColectivo.png";

    public Fijo(double posicionJugador) {
    	super((int)posicionJugador, fotoFijo, width, height);
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
		// TODO Auto-generated method stub
		
	}

}
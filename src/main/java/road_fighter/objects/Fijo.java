package road_fighter.objects;

public class Fijo extends AutoNpc {
	
	private static final String fotoFijo = "file:src/main/resources/img/NpcFijo.png";

	
    public Fijo(double posicionJugador) {
    	super((int)posicionJugador, fotoFijo);
    }

	@Override
	public void updateHorizontal(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVertical(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilidadEspecial() {
		// TODO Auto-generated method stub
		
	}

}
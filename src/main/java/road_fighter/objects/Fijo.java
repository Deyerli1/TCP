package road_fighter.objects;

public class Fijo extends AutoNpc {

    public Fijo(double posicionJugador) {
    	super((int)posicionJugador, "file:src/main/resources/img/NpcFijo.png");
    	System.out.println("-------------------------------------------------");
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
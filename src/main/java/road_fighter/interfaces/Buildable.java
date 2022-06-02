package road_fighter.interfaces;

public interface Buildable extends Updatable {
	
	public void startBuilding(long delayInNano);

	public void stopBuilding();

	public void create();
}

package road_fighter;

public class Config {
	public final static int baseHeight = 800;
	public final static int baseWidth = 800;
	public final static int groundHeight = 80;

	public final static double gravity = 1300;
	public final static double jumpForce = 500;
	public static double baseSpeed = 250;

	public final static double emptySpace = 0.25;

	public final static double pipesPerSecond = 1.3;
	public final static int playerCenter = baseWidth / 3;
	
	public static int maxScore = 0;
	
	public static double posicionActualJugador = 0;
	public static double posicionJugador;//esta es la actual
	public static final int offScreenTolerance = 700;

	private Config() {
	}
	

}

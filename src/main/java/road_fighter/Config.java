package road_fighter;

public class Config {
	public final static int baseHeight = 800;
	public final static int baseWidth = 800;

	public static double baseSpeed = 250;
	
	public static double posicionActualJugador = 0;
	public static double posicionJugador;//esta es la actual
	public static final int offScreenTolerance = 700;
	
	public static final String PATH_MUSICA = "src/main/resources/snd/ambient.mp3";

	public static double volumenMusica = 0.5;
	
	public static int puerto = 42069;
	
	private Config() {
	}
	

}
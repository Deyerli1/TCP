package road_fighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import road_fighter.servidor.Cliente;
import road_fighter.servidor.Servidor;

public class RoadFighterGame extends Application {
	private Stage stage;

	private MenuSceneHandler menuSceneHandler;

	private GameSceneHandler gameSceneHandler;
	
	public Servidor servidor;
	public Cliente cliente;
	
	@Override
	public void start(Stage stage) {
		this.stage = stage;
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene); 

		menuSceneHandler.load();
		
		stage.getIcons().add(new Image("file:src/main/resources/ico/logo.png"));
		stage.setTitle("Road Fighter FXGame | TCP Programacion Avanzada | Los Borbotones");
		
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public void startGame() {
		menuSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}
	
	public void host(int puerto) {
		servidor = new Servidor(puerto);
		agregarJugador();
	}
	
	public void agregarJugador() {
		//new Lobby();
		cliente.main(null);
	}
	
	
}
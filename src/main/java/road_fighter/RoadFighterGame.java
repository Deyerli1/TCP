package road_fighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import road_fighter.servidor.Lobby;

public class RoadFighterGame extends Application {
	private Stage stage;

	private MenuSceneHandler menuSceneHandler;
	private GameSceneHandlerMulti gameSceneHandlerMulti;
	private GameSceneHandler gameSceneHandler;
				
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

	public void iniciarJuego() {
		launch();
	}

	public void startGame() {
		menuSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}
	
	public void startGameMulti() {
		menuSceneHandler.unload();
		Lobby l = new Lobby();
		gameSceneHandlerMulti = new GameSceneHandlerMulti(this);
		Scene scene = gameSceneHandlerMulti.getScene();
		//stage.setScene(scene);
	}
	
	public void iniciarJuegoMulti() {
		gameSceneHandlerMulti.load(true);
	}
	
}
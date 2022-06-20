package road_fighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RoadFighterGame extends Application {
	private Stage stage;

	private MenuSceneHandler menuSceneHandler;
	private GameSceneHandlerMulti gameSceneHandlerMulti;
	private GameSceneHandler gameSceneHandler;
				
	@Override
	public void start(Stage stage) {
		System.out.println("RoadFighterGame.start() si llega aca hay que ponerse feliz :)");
		this.stage = stage;
		menuSceneHandler = new MenuSceneHandler(this);
		System.out.println("2");
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene); 
		System.out.println("3");
		menuSceneHandler.load();
		
		stage.getIcons().add(new Image("file:src/main/resources/ico/logo.png"));
		stage.setTitle("Road Fighter FXGame | TCP Programacion Avanzada | Los Borbotones");
		System.out.println("4");
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
		gameSceneHandlerMulti = new GameSceneHandlerMulti(this);
		Scene scene = gameSceneHandlerMulti.getScene();
		stage.setScene(scene);
		
		
		//ESTA LINEA DE ACA HACE QUE SE VEA EL JUEGO, NO SE TIENE QUE EJECUTAR TODAVIA
		gameSceneHandlerMulti.load(true);
	}
	
}
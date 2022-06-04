package road_fighter.objects;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import road_fighter.Config;
import road_fighter.utils.GameObject;

public class Reproductor extends GameObject {
	private MediaPlayer mediaPlayer;
	private Media loop;
	private boolean started = false;

	public Reproductor(String mediaPath) {
		loop = new Media(new File(mediaPath).toURI().toString());
		mediaPlayer = new MediaPlayer(loop);
		mediaPlayer.setVolume(Config.volumenMusica);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	public void start() {
		started = true;
	}
	
	public boolean isStarted() {
		return started;
	}

	public void setVolume(double volume) {
		Config.volumenMusica = volume;
		mediaPlayer.setVolume(Config.volumenMusica);
	}
	
	@Override
	public void destroy() {
		mediaPlayer.stop();
	}
}

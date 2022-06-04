package road_fighter.utils;

import javafx.scene.media.AudioClip;

public final class AudioResources {

    private static AudioClip create(String name) {
    	return new AudioClip(ClassLoader.getSystemResource(name).toString());
    }

	public static AudioClip getExplosionAudio() {
		return create("sfx/explosionAudio.wav");
	}

	public static AudioClip getMotorAudio() {
		return create("sfx/motor.wav");
	}
	
	public static AudioClip getWhatUpWithThat() {
		return create("sfx/whatUpWithThat.wav");
	}
	
	public static AudioClip getDesestabilizadoAudio() {
		return create("sfx/desestabilizado.wav");
	}
	
}

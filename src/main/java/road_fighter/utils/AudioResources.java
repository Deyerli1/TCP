package road_fighter.utils;

import javafx.scene.media.AudioClip;

public final class AudioResources {

    private static AudioClip create(String name) {
    	return new AudioClip(ClassLoader.getSystemResource(name).toString());
    }

	public static AudioClip getDieAudio() {
		return create("sfx/die.wav");
	}

	public static AudioClip getExplosionAudio() {
		return create("sfx/explosionAudio.wav");
	}

	public static AudioClip getMotorAudio() {
		return create("sfx/motor.wav");
	}

	public static AudioClip getPointAudio() {
		return create("sfx/point.wav");
	}
	
	public static AudioClip getWhatUpWithThat() {
		return create("sfx/whatUpWithThat.wav");
	}
	
}

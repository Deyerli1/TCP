package road_fighter.utils;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class IndividualSpriteAnimation extends Transition {
	private final ImageView imageView;
	private final Image[] images;
	private int[] frameNumbers;
	private int lastIndex;

	public IndividualSpriteAnimation(Image[] images, ImageView imageView, Duration duration) {
		this.images = images;
		this.imageView = imageView;
		this.frameNumbers = new int[images.length];
		for (int i = 0; i < images.length; i++) {
			this.frameNumbers[i] = i;
		}
		
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}
	
	public void setCustomFrames(int[] frameNumbers) {
		this.frameNumbers = frameNumbers;
	}

	protected void interpolate(double k) {
		final int index = frameNumbers[Math.min((int) Math.floor(k * frameNumbers.length), frameNumbers.length - 1)];
		if (index != lastIndex) {
			imageView.setImage(images[index]);
			lastIndex = index;
		}
	}
}
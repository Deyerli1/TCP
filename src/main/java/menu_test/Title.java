package menu_test;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import road_fighter.Config;
import interfaces.Renderable;
import utils.GameObject;

public class Title extends ImageView implements Renderable {
	
	private Text text;
	private final int Y = Config.baseHeight / 3 - 35;
	private final int TITLE_WIDTH = 450;
	private final int TITLE_HEIGHT = 300;
	private final TranslateTransition idleAnimation;
	private final Duration translateDuration = Duration.millis(1000);
	private Image img;
	private ImageView render;
	
	public Title() {
		img = new Image("file:src/main/resources/img/title.png", TITLE_WIDTH, TITLE_HEIGHT, false, false);

		render = new ImageView(img);
		render.setViewOrder(1);
    	render.setX(190);
    	render.setY(-150);
		
		idleAnimation = initIdleAnimation();
	}

	@Override
	public Node getRender() {
		return render;
	}
	
	private TranslateTransition initIdleAnimation() {
		TranslateTransition translateTransition = new TranslateTransition(translateDuration, render);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setFromY(Y -10);
		translateTransition.setToY(Y + 10);
		translateTransition.setAutoReverse(true);
		translateTransition.play();
		return translateTransition;
	}

//	public void setDisable(boolean estado) {
//		render.setDisable(estado);
//	}
	
	public void playAnimation() {
		idleAnimation.play();
	}
	
//	@Override
//	public void destroy() {
//		idleAnimation.stop();
//	}
	
    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}
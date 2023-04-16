package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubscene extends SubScene{
	
	private final static String font_path = "src/model/Resources/kenvector_future.ttf";
	private final static String background_image = "model/Resources/blue_panel.png";
	
	private boolean isHidden = true;
	
	public GameSubscene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage background = new BackgroundImage(new Image(background_image,600,400,false,true), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(background));
		setLayoutX(1024);
		setLayoutY(180);
	}
	
	public void moveSubscene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if(isHidden) {
			transition.setToX(-676);
			isHidden = false;
		}
		else {
			transition.setToX(0);
			isHidden =true;
		}
		transition.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}

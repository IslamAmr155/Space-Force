package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox{
	private ImageView circleImage;
	private ImageView shipImage;
	
	private String circleNotChosen = "view/Resources/shipchooser/grey_circle.png";
	private String circleChosen = "view/Resources/shipchooser/blue_boxTick.png";
	
	private ship ship;
	
	private boolean isCircleChosen;
	
	public ShipPicker(ship ship) {
		circleImage = new ImageView(circleNotChosen);
		shipImage = new ImageView(ship.getURL());
		this.ship = ship;
		isCircleChosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(shipImage);
	}
	
	public ship getShip() {
		return ship;
	}
	
	public boolean getIsCircleChosen() {
		return isCircleChosen;
	}
	
	public void setIsCircleChosen(boolean is) {
		isCircleChosen = is;
		String imageToSet = isCircleChosen ? circleChosen : circleNotChosen;
		circleImage.setImage(new Image(imageToSet));
	}
}

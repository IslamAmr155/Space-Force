package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class InfoLabel extends Label{
	public final static String Font_Path = "src/model/Resources/kenvector_future.ttf";
	public final static String Background_Image = "view/Resources/shipchooser/blue_button13.png";
	
	public InfoLabel(String text) {
		setPrefWidth(380);
		setPrefHeight(49);
		setText(text);
		setWrapText(true);
		setLabelFont();
		setAlignment(Pos.CENTER);
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,380,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		setBackground(new Background(background));
	}
	
	public InfoLabel(ImageView image) {
		setPrefWidth(80);
		setPrefHeight(49);
		setWrapText(true);
		setGraphic(image);
		setLabelFont();
		setAlignment(Pos.CENTER);
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,80,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		setBackground(new Background(background));
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.loadFont("Verdana", 23));
	}
}
}
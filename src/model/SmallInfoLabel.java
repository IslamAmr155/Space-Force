package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallInfoLabel extends Label{
	
	private final static String font_path = "src/model/Resources/kenvector_future.ttf";
	
	public SmallInfoLabel(String text) {
		setPrefHeight(50);
		setPrefWidth(130);
		BackgroundImage backgroundImage =new BackgroundImage(new Image("/view/Resources/buttonBlue.png",130,50,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		setBackground(new Background(backgroundImage));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10,10,10,10));
		setText(text);
		setLabelFont();
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(font_path)), 15));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana",15));
		}
	}
}

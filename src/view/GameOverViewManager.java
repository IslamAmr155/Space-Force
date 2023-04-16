package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.GameButton;
import model.InfoLabel;
import model.ship;

public class GameOverViewManager {
	private AnchorPane overPane;
	private Scene overScene;
	private Stage overStage;
	
	private Stage menuStage;
	private ship chosenShip;
	private int points;
	
	private final int width = 1024;
	private final int height = 768;
	
	private GridPane gridPane1;
	private final static String background_image = "view/Resources/SpaceGIF3.gif";
	
	File file = new File("High_Score.dat");
	
	public GameOverViewManager(Stage menuStage,ship chosenShip, int points) throws IOException {
		this.menuStage = menuStage;
		this.chosenShip = chosenShip;
		this.points = points;
		intializeStage();
		createBackground();
		createGameOver();
		createHighScoreLabel();
		createScoreLabel();
		createRetryButton();
		createMainMenuButton();
		overStage.setTitle("Space Force");
		overStage.getIcons().add(new Image(GameOverViewManager.class.getResourceAsStream("/view/Resources/shipchooser/playerLife3_blue.png")));
		overStage.setResizable(false);
	}
	
	private void intializeStage() {
		overPane = new AnchorPane();
		overScene = new Scene(overPane,width,height);
		overStage = new Stage();
		overStage.setScene(overScene);
		overStage.show();
	}
	
	private void createBackground() {
		gridPane1 = new GridPane();
		ImageView backgrounfImage1 = new ImageView(background_image);
		gridPane1.getChildren().add(backgrounfImage1);
		overPane.getChildren().addAll(gridPane1);
	}
	
	private void createGameOver() {
		ImageView logo = new ImageView("view/resources/Game Over1.png");
		logo.setLayoutX(-110);
		logo.setLayoutY(-330);
		logo.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				logo.setEffect(new DropShadow());
			}
	});
		logo.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				logo.setEffect(null);
			}
	});
		
		overPane.getChildren().add(logo);
	}
	
	private void createHighScoreLabel() throws IOException {
		Integer highScore = 0;
		FileReader readFile = null;
		BufferedReader reader = null;
		FileWriter writeFile = null;
		BufferedWriter writer = null;
		try 
		{
			readFile = new FileReader(file);
			reader = new BufferedReader(readFile);
			highScore = Integer.parseInt(reader.readLine());
		}
		catch (Exception e){}
		finally 
		{
			if (reader != null)
				reader.close();
		}
		
		String highScoreText = "";
		if (points > highScore)
		{
			highScoreText = highScoreText + "New High Score: " + points;
			writeFile = new FileWriter(file);
			writer = new BufferedWriter(writeFile);
			writer.write(Integer.toString(points));
			if (writer != null)
				writer.close();
		}
		else
			highScoreText = highScoreText + "High Score: " + highScore;
		
		InfoLabel high = new InfoLabel(highScoreText);
		high.setLayoutX(320);
		high.setLayoutY(450);
		overPane.getChildren().add(high);
	}
	
	private void createScoreLabel() {
		InfoLabel notice = new InfoLabel("SCORE: " + points);  
		notice.setLayoutX(320);
		notice.setLayoutY(300);
		overPane.getChildren().add(notice);
	}
	
	private void createRetryButton() {
		GameButton retry = new GameButton("RETRY");
		retry.setPrefWidth(190);
		retry.setLayoutX(150);
		retry.setLayoutY(600);
		retry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				overStage.close();
				GameViewManager gameManager = new GameViewManager();
				gameManager.creatNewGame(menuStage, chosenShip);
			}
		});
		overPane.getChildren().add(retry);
	}
	
	private void createMainMenuButton(){
		GameButton menu = new GameButton("MAIN MENU");
		menu.setPrefWidth(190);
		menu.setLayoutX(650);
		menu.setLayoutY(600);
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				overStage.close();
				ViewManager viewManager = null;
				try {
					viewManager = new ViewManager();
				} catch (FileNotFoundException e) {}
				Stage stage = viewManager.getMainStage();
				stage.show();
			}
		});
		overPane.getChildren().add(menu);
	}
	
}



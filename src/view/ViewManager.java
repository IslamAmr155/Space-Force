package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameButton;
import model.GameSubscene;
import model.InfoLabel;
import model.ShipPicker;
import model.ship;

public class ViewManager {
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private GameSubscene ControlsSubscene;
	private GameSubscene PowerUpsSubscene;
	private GameSubscene EnemiesSubscene;
	private GameSubscene ShipsSubscene;
	private GameSubscene CurrentSubscene;
	
	private final int width = 1024;
	private final int height = 768;
	
	private final static int menu_button_start_x = 100;
	private final static int menu_button_start_y = 200;
	
	public final static String Font_Path = "src/model/Resources/kenvector_future.ttf";
	public final static String Background_Image = "view/Resources/shipchooser/blue_button13.png";
	private String[] powerUpsUrls = {"view/Resources/powerups/pill_red.png","view/Resources/powerups/beam2.png","view/Resources/powerups/bolt_gold.png","view/Resources/powerups/shield_silver.png","view/Resources/powerups/bolt_silver.png"};
	private String[] enemyUrls = {"view/resources/enemyShipChooser/smallenemyRed4.png","view/resources/enemyShipChooser/smallenemyBlack2.png","view/resources/enemyShipChooser/smallenemyBlack3.png","view/resources/enemyShipChooser/smallenemyGreen5.png","view/resources/enemyShipChooser/smallenemyBlue1.png"};	
	List<GameButton> menuButtons;
	List<ShipPicker> shipsList;
	private ship chosenShip;
	
	private GridPane gridPane1;
	private final static String background_image = "view/Resources/SpaceGIF3.gif";
	
	File click2Path = new File("src\\audio\\click2.mp3");
	File rollover1Path = new File("src\\audio\\rollover1.mp3");
	
	Media click2 = new Media(click2Path.toURI().toString());
	Media rollover1 = new Media(rollover1Path.toURI().toString());
	MediaPlayer playClick2 = new MediaPlayer(click2);
	MediaPlayer playRollOver1 = new MediaPlayer(rollover1);
	
	public ViewManager() throws FileNotFoundException {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,width,height);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		createBackground();
		createButtons();
		createLogo();
		createSubscene();
		mainStage.setTitle("Space Force");
		mainStage.getIcons().add(new Image(ViewManager.class.getResourceAsStream("/view/Resources/shipchooser/playerLife3_blue.png")));
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void createButtons() {
		createPlayButton();
		createControlsButton();
		createPowerUpsButton();
		createEnemiesButton();
		createExitButton();
	}
	
	private void createPlayButton() {
		GameButton PlayButton = new GameButton("PLAY");
		addMenuButton(PlayButton);
		
		PlayButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(ShipsSubscene);
			}
			
		});
	}
	
	private void createControlsButton() {
		GameButton ControlsButton = new GameButton("CONTROLS");
		addMenuButton(ControlsButton);
		
		ControlsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(ControlsSubscene);
			}
			
		});
	}
	
	private void createPowerUpsButton() {
		GameButton PowerUpsButton = new GameButton("POWERUPS");
		addMenuButton(PowerUpsButton);
		
		PowerUpsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(PowerUpsSubscene);
			}
			
		});
	}
	
	private void createEnemiesButton() {
		GameButton EnemiesButton = new GameButton("ENEMIES");
		addMenuButton(EnemiesButton);
		
		EnemiesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(EnemiesSubscene);
			}
			
		});
	}
	
	private void createExitButton() {
		GameButton ExitButton = new GameButton("EXIT");
		addMenuButton(ExitButton);
		
		ExitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.close();
			}
			
		});
	}
	
	private void addMenuButton(GameButton button) {
		button.setLayoutX(menu_button_start_x);
		button.setLayoutY(menu_button_start_y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createSubscene() throws FileNotFoundException {
		createShipChosenSubscene();
		createControlsSubscene();
		createPowerUpsSubscene();
		createEnemySubscene();
	}
	
	private void createShipChosenSubscene(){
		ShipsSubscene = new GameSubscene();
		mainPane.getChildren().add(ShipsSubscene);
		InfoLabel chooseLabel = new InfoLabel("CHOOSE YOUR SHIP");
		chooseLabel.setLayoutX(110);
		chooseLabel.setLayoutY(25);
		ShipsSubscene.getPane().getChildren().add(chooseLabel);
		ShipsSubscene.getPane().getChildren().add(createShipsToChoose());
		ShipsSubscene.getPane().getChildren().add(createStartButton());
	}
	
	private void createControlsSubscene() {
		ControlsSubscene = new GameSubscene();
		mainPane.getChildren().add(ControlsSubscene);
		
		ControlsSubscene.getPane().getChildren().add(createKeys());
		ControlsSubscene.getPane().getChildren().add(createDirections());
	}
	
	private void createPowerUpsSubscene() throws FileNotFoundException {
		PowerUpsSubscene = new GameSubscene();
		mainPane.getChildren().add(PowerUpsSubscene);
		
		PowerUpsSubscene.getPane().getChildren().add(createPowerUpsPics());
		PowerUpsSubscene.getPane().getChildren().add(createPowerUpsDescription());
	}
	
	private void createEnemySubscene() throws FileNotFoundException {
		EnemiesSubscene = new GameSubscene();
		mainPane.getChildren().add(EnemiesSubscene);
		
		EnemiesSubscene.getPane().getChildren().add(createEnemyShips());
		EnemiesSubscene.getPane().getChildren().add(createEnemyCharacteristics());
		EnemiesSubscene.getPane().getChildren().add(createHealth());
		EnemiesSubscene.getPane().getChildren().add(createDamage());
		EnemiesSubscene.getPane().getChildren().add(createSpeed());
		EnemiesSubscene.getPane().getChildren().add(createFireRate());
		EnemiesSubscene.getPane().getChildren().add(createEffect());
	}
	
	private VBox createKeys() {
		VBox box = new VBox();
		box.setSpacing(30);
		
		InfoLabel w = new InfoLabel("W");
		InfoLabel a = new InfoLabel("A");
		InfoLabel s = new InfoLabel("S");
		InfoLabel d = new InfoLabel("D");
		InfoLabel spaceBar = new InfoLabel("SPACEBAR");
		
		w.setPrefWidth(180);
		a.setPrefWidth(180);
		s.setPrefWidth(180);
		d.setPrefWidth(180);
		spaceBar.setPrefWidth(180);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,180,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		w.setBackground(new Background(background));
		a.setBackground(new Background(background));
		s.setBackground(new Background(background));
		d.setBackground(new Background(background));
		spaceBar.setBackground(new Background(background));
		
		box.getChildren().add(w);
		box.getChildren().add(a);
		box.getChildren().add(s);
		box.getChildren().add(d);
		box.getChildren().add(spaceBar);
		
		box.setLayoutX(30);
		box.setLayoutY(20);
		
		return box;
	}
	
	private VBox createDirections() {
		VBox box = new VBox();
		box.setSpacing(30);
		
		InfoLabel up = new InfoLabel("MOVE UP");
		InfoLabel down = new InfoLabel("MOVE DOWN");
		InfoLabel right = new InfoLabel("MOVE RIGHT");
		InfoLabel left = new InfoLabel("MOVE LEFT");
		InfoLabel fire = new InfoLabel("FIRE LASER");
	
		up.setPrefWidth(300);
		down.setPrefWidth(300);
		right.setPrefWidth(300);
		left.setPrefWidth(300);
		fire.setPrefWidth(300);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,300,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		up.setBackground(new Background(background));
		down.setBackground(new Background(background));
		right.setBackground(new Background(background));
		left.setBackground(new Background(background));
		fire.setBackground(new Background(background));
		
		box.getChildren().add(up);
		box.getChildren().add(down);
		box.getChildren().add(right);
		box.getChildren().add(left);
		box.getChildren().add(fire);
		
		box.setLayoutX(270);
		box.setLayoutY(20);
		
		return box;
	}
	
	private VBox createPowerUpsPics() {
		VBox box = new VBox();
		box.setSpacing(30);
		
		ImageView heal = new ImageView(new Image(powerUpsUrls[0]));
		ImageView doubleLaser = new ImageView(new Image(powerUpsUrls[1]));
		ImageView rate = new ImageView(new Image(powerUpsUrls[2]));
		ImageView shield = new ImageView(new Image(powerUpsUrls[3]));
		ImageView speed = new ImageView(new Image(powerUpsUrls[4]));
		
		InfoLabel healLabel = new InfoLabel(heal);
		InfoLabel doubleLabel = new InfoLabel(doubleLaser);
		InfoLabel rateLabel = new InfoLabel(rate);
		InfoLabel shieldLabel = new InfoLabel(shield);
		InfoLabel speedLabel = new InfoLabel(speed);
		
		box.getChildren().add(healLabel);
		box.getChildren().add(doubleLabel);
		box.getChildren().add(rateLabel);
		box.getChildren().add(shieldLabel);
		box.getChildren().add(speedLabel);
		
		box.setLayoutX(40);
		box.setLayoutY(20);
		
		return box;
	}
	
	private VBox createPowerUpsDescription() throws FileNotFoundException {
		VBox box = new VBox();
		box.setSpacing(30);
		
		InfoLabel heal = new InfoLabel("This item will heal you, if you're not already at full health");
		InfoLabel doubleLaser = new InfoLabel("Makes your ship shoot two laser shots at once");
		InfoLabel rate = new InfoLabel("Increases the fire rate of your ship");
		InfoLabel shield = new InfoLabel("Blocks enemy laser shots");
		InfoLabel speed = new InfoLabel("Increases the movement speed of your ship");
		
		heal.setPrefWidth(450);
		doubleLaser.setPrefWidth(450);
		rate.setPrefWidth(450);
		shield.setPrefWidth(450);
		speed.setPrefWidth(450);
		
		heal.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 10));
		doubleLaser.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 13));
		rate.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 14));
		shield.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 14));
		speed.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 14));
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,450,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		heal.setBackground(new Background(background));
		doubleLaser.setBackground(new Background(background));
		rate.setBackground(new Background(background));
		shield.setBackground(new Background(background));
		speed.setBackground(new Background(background));
		
		box.getChildren().add(heal);
		box.getChildren().add(doubleLaser);
		box.getChildren().add(rate);
		box.getChildren().add(shield);
		box.getChildren().add(speed);
		
		box.setLayoutX(130);
		box.setLayoutY(20);
		
		return box;
	}
	
	private VBox createEnemyShips() {
		VBox box = new VBox();
		box.setSpacing(10);
		
		ImageView red = new ImageView(new Image(enemyUrls[0]));
		ImageView black1 = new ImageView(new Image(enemyUrls[1]));
		ImageView black2 = new ImageView(new Image(enemyUrls[2]));
		ImageView green = new ImageView(new Image(enemyUrls[3]));
		ImageView blue = new ImageView(new Image(enemyUrls[4]));

		box.getChildren().add(red);
		box.getChildren().add(black1);
		box.getChildren().add(black2);
		box.getChildren().add(green);
		box.getChildren().add(blue);
		
		box.setLayoutX(25);
		box.setLayoutY(40);
		
		return box;
	}
	
	private HBox createEnemyCharacteristics() throws FileNotFoundException {
		HBox box = new HBox();
		box.setSpacing(3);
		
		InfoLabel health = new InfoLabel("Health");
		InfoLabel damage = new InfoLabel("Damage");
		InfoLabel speed = new InfoLabel("Speed");
		InfoLabel rate = new InfoLabel("Fire Rate");
		InfoLabel effect = new InfoLabel("Effect");
		
		health.setPrefWidth(92);
		damage.setPrefWidth(92);
		speed.setPrefWidth(92);
		rate.setPrefWidth(92);
		effect.setPrefWidth(92);
		
		health.setPrefHeight(25);
		damage.setPrefHeight(25);
		speed.setPrefHeight(25);
		rate.setPrefHeight(25);
		effect.setPrefHeight(25);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,92,25,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		health.setBackground(new Background(background));
		damage.setBackground(new Background(background));
		speed.setBackground(new Background(background));
		rate.setBackground(new Background(background));
		effect.setBackground(new Background(background));
	
		health.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		damage.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		speed.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		rate.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 11));
		effect.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		
		box.getChildren().add(health);
		box.getChildren().add(damage);
		box.getChildren().add(speed);
		box.getChildren().add(rate);
		box.getChildren().add(effect);
		
		box.setLayoutX(100);
		box.setLayoutY(15);
		
		return box;
	}
	
	private VBox createHealth() {
		VBox box = new VBox();
		box.setSpacing(10);
		
		InfoLabel red = new InfoLabel("2");
		InfoLabel black1 = new InfoLabel("1");
		InfoLabel black2 = new InfoLabel("2");
		InfoLabel green = new InfoLabel("1");
		InfoLabel blue = new InfoLabel("2");
		
		red.setPrefWidth(92);
		black1.setPrefWidth(92);
		black2.setPrefWidth(92);
		green.setPrefWidth(92);
		blue.setPrefWidth(92);
		
		red.setPrefHeight(60);
		black1.setPrefHeight(60);
		black2.setPrefHeight(60);
		green.setPrefHeight(60);
		blue.setPrefHeight(60);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,92,60,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		red.setBackground(new Background(background));
		black1.setBackground(new Background(background));
		black2.setBackground(new Background(background));
		green.setBackground(new Background(background));
		blue.setBackground(new Background(background));
		
		box.getChildren().add(red);
		box.getChildren().add(black1);
		box.getChildren().add(black2);
		box.getChildren().add(green);
		box.getChildren().add(blue);
		
		box.setLayoutX(100);
		box.setLayoutY(40);
		
		return box;
	}
	
	private VBox createDamage() {
		VBox box = new VBox();
		box.setSpacing(10);
		
		InfoLabel red = new InfoLabel("1");
		InfoLabel black1 = new InfoLabel("1");
		InfoLabel black2 = new InfoLabel("2");
		InfoLabel green = new InfoLabel("2");
		InfoLabel blue = new InfoLabel("1");
		
		red.setPrefWidth(92);
		black1.setPrefWidth(92);
		black2.setPrefWidth(92);
		green.setPrefWidth(92);
		blue.setPrefWidth(92);
		
		red.setPrefHeight(60);
		black1.setPrefHeight(60);
		black2.setPrefHeight(60);
		green.setPrefHeight(60);
		blue.setPrefHeight(60);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,92,60,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		red.setBackground(new Background(background));
		black1.setBackground(new Background(background));
		black2.setBackground(new Background(background));
		green.setBackground(new Background(background));
		blue.setBackground(new Background(background));
		
		box.getChildren().add(red);
		box.getChildren().add(black1);
		box.getChildren().add(black2);
		box.getChildren().add(green);
		box.getChildren().add(blue);
		
		box.setLayoutX(195);
		box.setLayoutY(40);
		
		return box;
	}
	
	private VBox createSpeed() {
		VBox box = new VBox();
		box.setSpacing(10);
		
		InfoLabel red = new InfoLabel("6");
		InfoLabel black1 = new InfoLabel("4");
		InfoLabel black2 = new InfoLabel("5");
		InfoLabel green = new InfoLabel("4");
		InfoLabel blue = new InfoLabel("5");
		
		red.setPrefWidth(92);
		black1.setPrefWidth(92);
		black2.setPrefWidth(92);
		green.setPrefWidth(92);
		blue.setPrefWidth(92);
		
		red.setPrefHeight(60);
		black1.setPrefHeight(60);
		black2.setPrefHeight(60);
		green.setPrefHeight(60);
		blue.setPrefHeight(60);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,92,60,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		red.setBackground(new Background(background));
		black1.setBackground(new Background(background));
		black2.setBackground(new Background(background));
		green.setBackground(new Background(background));
		blue.setBackground(new Background(background));
		
		box.getChildren().add(red);
		box.getChildren().add(black1);
		box.getChildren().add(black2);
		box.getChildren().add(green);
		box.getChildren().add(blue);
		
		box.setLayoutX(291);
		box.setLayoutY(40);
		
		return box;
	}
	
	private VBox createFireRate() throws FileNotFoundException {
		VBox box = new VBox();
		box.setSpacing(10);
		
		InfoLabel red = new InfoLabel("1.65 sec");
		InfoLabel black1 = new InfoLabel("1.75 sec");
		InfoLabel black2 = new InfoLabel("1.5 sec");
		InfoLabel green = new InfoLabel("1.6 sec");
		InfoLabel blue = new InfoLabel("1.55 sec");
		
		red.setPrefWidth(92);
		black1.setPrefWidth(92);
		black2.setPrefWidth(92);
		green.setPrefWidth(92);
		blue.setPrefWidth(92);
		
		red.setPrefHeight(60);
		black1.setPrefHeight(60);
		black2.setPrefHeight(60);
		green.setPrefHeight(60);
		blue.setPrefHeight(60);
		
		red.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 16));
		black1.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 16));
		black2.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 16));
		green.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 16));
		blue.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 16));
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,92,60,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		red.setBackground(new Background(background));
		black1.setBackground(new Background(background));
		black2.setBackground(new Background(background));
		green.setBackground(new Background(background));
		blue.setBackground(new Background(background));
		
		box.getChildren().add(red);
		box.getChildren().add(black1);
		box.getChildren().add(black2);
		box.getChildren().add(green);
		box.getChildren().add(blue);
		
		box.setLayoutX(387);
		box.setLayoutY(40);
		
		return box;
	}
	
	private VBox createEffect() throws FileNotFoundException {
		VBox box = new VBox();
		box.setSpacing(10);
		
		InfoLabel red = new InfoLabel("Lower movement speed");
		InfoLabel black1 = new InfoLabel("None");
		InfoLabel black2 = new InfoLabel("None");
		InfoLabel green = new InfoLabel("No power- ups");
		InfoLabel blue = new InfoLabel("Lower  fire rate");
		
		red.setPrefWidth(92);
		black1.setPrefWidth(92);
		black2.setPrefWidth(92);
		green.setPrefWidth(92);
		blue.setPrefWidth(92);
		
		red.setPrefHeight(60);
		black1.setPrefHeight(60);
		black2.setPrefHeight(60);
		green.setPrefHeight(60);
		blue.setPrefHeight(60);
		
		BackgroundImage background = new BackgroundImage(new Image(Background_Image,92,60,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null); 
		red.setBackground(new Background(background));
		black1.setBackground(new Background(background));
		black2.setBackground(new Background(background));
		green.setBackground(new Background(background));
		blue.setBackground(new Background(background));
		
		red.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		green.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		blue.setFont(Font.loadFont(new FileInputStream(new File(Font_Path)), 12));
		
		box.getChildren().add(red);
		box.getChildren().add(black1);
		box.getChildren().add(black2);
		box.getChildren().add(green);
		box.getChildren().add(blue);
		
		box.setLayoutX(483);
		box.setLayoutY(40);
		
		return box;
	}
	
	private HBox createShipsToChoose() {
		HBox box = new HBox();
		box.setSpacing(90);
		shipsList = new ArrayList<>();
		for(ship s : ship.values()) {
			ShipPicker ShipPick = new ShipPicker(s);
			shipsList.add(ShipPick);
			box.getChildren().add(ShipPick);
			ShipPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					for (ShipPicker sp : shipsList) {
						sp.setIsCircleChosen(false);
					}
					if(playClick2.getStatus() != MediaPlayer.Status.READY) 
						playClick2.seek(Duration.seconds(0.0));
					playClick2.play();
					ShipPick.setIsCircleChosen(true);
					chosenShip = ShipPick.getShip();
				}
				
			});
		}
		box.setLayoutX(64);
		box.setLayoutY(100);
		return box;
	}
	
	private GameButton createStartButton() {
		GameButton StartButton = new GameButton("START");
		StartButton.setLayoutX(350);
		StartButton.setLayoutY(300);
		StartButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (chosenShip != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.creatNewGame(mainStage, chosenShip);
				}
			}
			
		});
		return StartButton;
	}

	private void showSubscene(GameSubscene subscene) {
		if (CurrentSubscene != null) {
			if(CurrentSubscene != subscene) {
				CurrentSubscene.moveSubscene();
				subscene.moveSubscene();
				CurrentSubscene = subscene;
			}
			else {
				subscene.moveSubscene();
				CurrentSubscene = null;
			}
		}
		else {
			subscene.moveSubscene();
			CurrentSubscene = subscene;
		}
	}	
	private void createBackground() {
		gridPane1 = new GridPane();
		ImageView backgrounfImage1 = new ImageView(background_image);
		gridPane1.getChildren().add(backgrounfImage1);
		mainPane.getChildren().addAll(gridPane1);
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/SpaceForce4.png");
		logo.setLayoutX(175);
		logo.setLayoutY(-100);
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
		
		mainPane.getChildren().add(logo);
	}
}

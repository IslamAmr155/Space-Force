package view;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.SmallInfoLabel;
import model.enemyShip;
import model.ship;

public class GameViewManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private ship Ship;
	private Stage menuStage;
	private ImageView chosenShip;
	
	private boolean isWPressed;
	private boolean isSPressed;
	private boolean isAPressed;
	private boolean isDPressed;
	private boolean isSpacePressed;
	private int angle;
	private AnimationTimer gameTimer;
	private AnimationTimer mainTimer;
	private boolean minimized = true;
	
	private int speedOfShip = 5;
	
	private GridPane gridPane1;
	private final static String background_image = "view/Resources/SpaceGIF2.gif";
	
	private String[] brownMeteorsURLs = {"view/Resources/meteorBrown_med1.png","view/Resources/meteorBrown_med3.png","view/Resources/meteorBrown_big1.png"};
	private String[] greyMeteorsURLs = {"view/Resources/meteorGrey_med1.png","view/Resources/meteorGrey_med2.png","view/Resources/meteorGrey_big3.png"};
	private int[] brownMeteorsid = {0,0,0};
	private int[] greyMeteorsid = {0,0,0};
	private ImageView[] brownMeteors;
	private ImageView[] greyMeteors;
	private boolean canbehit = true;
	private Random RandomPositionGenerator;
	
	private String[] brownCrash = {"view/Resources/playerShip1_damage1.png","view/Resources/playerShip1_damage2.png","view/Resources/playerShip1_damage3.png"};
	private String[] greyCrash = {"view/Resources/playerShip2_damage1.png","view/Resources/playerShip2_damage2.png","view/Resources/playerShip3_damage3.png"};
	private boolean[] brownCheck = {true,true,true};
	private boolean[] greyCheck = {true,true,true};
	
	private boolean pickUp = true; 
	private ImageView[] powerUps = new ImageView[10];
	private int[] powerUpsID = new int[10];
	private String[] powerUpsUrls = {"view/Resources/powerups/pill_red.png","view/Resources/powerups/beam2.png","view/Resources/powerups/bolt_gold.png","view/Resources/powerups/shield_silver.png","view/Resources/powerups/bolt_silver.png"};
	private String[] negativePowerUpsUrls = {"view/Resources/powerups/powerupRed_bolt.png","view/Resources/powerups/numeralX.png","view/Resources/powerups/powerupBlue_bolt.png"};
	private ImageView shieldImage = new ImageView(new Image("view/Resources/powerups/shield1.png")); 
	private ArrayList<ProgressBar> ProgressBars;
	private ArrayList<ImageView> picturesOfProgressBars;
	private boolean doubleLaser = false;
	private boolean higherFireRate = false;
	private boolean shield = false;
	boolean shieldFlag = true;
	private boolean speed = false;
	private long startofDoubleLaser = 0;
	private long startofHigherFireRate = 0;
	private long startofShield = 0;
	private long startofSpeed = 0;
	private ProgressBar doubleLaserBar;
	private ProgressBar higherFireRateBar;
	private ProgressBar shieldBar;
	private ProgressBar speedBar;
	
	private ArrayList<Node> weapons;
	private long timeOfLastShot = 0;
	private int fireRate = 750;
	
	private ImageView[] playerLifes;
	private int playerLife;
	private int points = 0;
	private SmallInfoLabel pointsLabel;
	
	private int ship_radius = 33;
	private int enemy_ship_radius = 33;
	private int mid_meteor_radius = 22;
	private int big_meteor_radius = 50;
	private int laser_radius = 27;
	
	private String[] enemyShipsUrls = {"view/resources/enemyShipChooser/enemyRed4.png","view/resources/enemyShipChooser/enemyBlack2.png","view/resources/enemyShipChooser/enemyBlack3.png","view/resources/enemyShipChooser/enemyGreen5.png","view/resources/enemyShipChooser/enemyBlue1.png"};	
	private ImageView[] enemies;
	private int[] enemyHealth;
	private int[] enemyDamage;
	private int[] enemyFireRate;
	private int[] enemySpeed;
	private int[] enemyEffect;
	private ArrayList<Integer> enemyeffect;
	private ArrayList<Integer> enemydamage;
	private boolean[] enemyShipCheck = {true,true,true,true,true,true};
	private ArrayList<Node> enemyWeapons;
	private long[] enemyTimeOfLastShot = {0,0,0};
	private ArrayList<ProgressBar> negativeProgressBars;
	private ArrayList<ImageView> picturesOfNegativeProgressBars;
	private long startOfSlow;
	private long startOfPick;
	private long startOfSlowerFireRate;
	private boolean slow = false;
	private boolean pick = false;
	private boolean slowerFireRate = false;
	private ProgressBar slowBar;
	private ProgressBar pickBar;
	private ProgressBar slowerFireRateBar;
	
	private Image explosion = new Image("view/resources/enemyShipChooser/explosion.gif");
	private ImageView[] explosions = new ImageView[6];
	private long[] explosionDurstion = new long[6]; 
	
	File playerLaserPath = new File("src\\audio\\playerLaser.mp3");
	File shieldUpPath = new File("src\\audio\\shieldUp.mp3");
	File shieldDownPath = new File("src\\audio\\shieldDown.mp3");
	File explosionPath = new File("src\\audio\\explosion_audio.mp3");
	File enemyLaserPath = new File("src\\audio\\enemyLaser.mp3");
	File losePath = new File("src\\audio\\lose.mp3");
	File pickUpPath = new File("src\\audio\\pickUp.mp3");
	File meteorPath = new File("src\\audio\\meteor_destruction.mp3");
	
	Media playerLaser = new Media(playerLaserPath.toURI().toString());
	Media shieldUp = new Media(shieldUpPath.toURI().toString());
	Media shieldDown = new Media(shieldDownPath.toURI().toString());
	Media explosionSound = new Media(explosionPath.toURI().toString());
	Media enemyLaser = new Media(enemyLaserPath.toURI().toString());
	Media lose = new Media(losePath.toURI().toString());
	Media pickUpSound = new Media(pickUpPath.toURI().toString());
	Media meteorDestruction = new Media(meteorPath.toURI().toString());
	
	MediaPlayer playPlayerLaser = new MediaPlayer(playerLaser);
	MediaPlayer playShieldUp = new MediaPlayer(shieldUp);
	MediaPlayer playShieldDown = new MediaPlayer(shieldDown);
	MediaPlayer playExplosion = new MediaPlayer(explosionSound);
	MediaPlayer playEnemyLaser = new MediaPlayer(enemyLaser);
	MediaPlayer playLose = new MediaPlayer(lose);
	MediaPlayer playPickUp = new MediaPlayer(pickUpSound);
	MediaPlayer playMeteor = new MediaPlayer(meteorDestruction);
	
	private static final int game_width = 600;
	private static final int game_height = 800;
		
	public GameViewManager() {
		intializeStage();
		createKeyListeners();
		RandomPositionGenerator = new Random();
		weapons = new ArrayList<>();
		ProgressBars = new ArrayList<>();
		picturesOfProgressBars = new ArrayList<>();
		enemyWeapons = new ArrayList<>();
		negativeProgressBars = new ArrayList<>();
		picturesOfNegativeProgressBars = new ArrayList<>();
		enemyeffect = new ArrayList<>();
		enemydamage = new ArrayList<>();
		gameStage.setTitle("Space Force");
		gameStage.getIcons().add(new Image(GameViewManager.class.getResourceAsStream("/view/Resources/shipchooser/playerLife3_blue.png")));
		gameStage.setResizable(false);
		//gameStage.initStyle(StageStyle.UTILITY);
		playMeteor.setVolume(0.2);
	}
	
	private void intializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane,game_width,game_height);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}
	
	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case W:
					isWPressed = true;
					break;
				case S:
					isSPressed = true;
					break;
				case A:
					isAPressed = true;
					break;
				case D:
					isDPressed = true;
					break;
				case SPACE:
					isSpacePressed = true;
					break;
				default:
					break;
				}
			}
			
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case W:
					isWPressed = false;
					break;
				case S:
					isSPressed = false;
					break;
				case A:
					isAPressed = false;
					break;
				case D:
					isDPressed = false;
					break;
				case SPACE:
					isSpacePressed = false;
					break;
				default:
					break;
				}
			}
			
		});
	}
	
	public void creatNewGame(Stage menuStage,ship chosenShip) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		Ship = chosenShip;
		createBackground();
		createShip(chosenShip);
		createGameLoop(chosenShip);
		createGameElemets(chosenShip);
		gameStage.show();
	}
	
	private void createShip(ship chosenShip) {
		this.chosenShip = new ImageView(chosenShip.getURL());
		this.chosenShip.setLayoutX(game_width/2);
		this.chosenShip.setLayoutY(game_height - 90);
		gamePane.getChildren().add(this.chosenShip);
	}
	
	private void createGameLoop(ship Ship) {
		mainTimer = new AnimationTimer() {
			public void handle(long mainEvent) {
				if (minimized)
				gameTimer = new AnimationTimer() {
					
				@Override
				public void handle(long event) {
					moveShip();
					fireLaser(Ship,doubleLaser(Ship));
					checkIfLaserHit();
					moveGameElements();
					checkIfElementsAtEndOfScreen();
					//moveBackground();
					try {
						checkIfElementsCollide();
					} catch (IOException e) {} 
					randomizerForPowerUps();
					powerUpsListener(Ship);
					decreaseProgressBars();
					determinePositionOfProgressBar();
					shield();
					endExplode();
					fireEnemyLaser();
					try {
						checkIfEnemyLaserHit();
					} catch (IOException e) {}
					determinePositionOfNegativeProgressBar();
					decreaseNegativeProgressBars();
					pick();
					higherOrLowerFireRate();
					speedOrSlow();
				}
		};
		if (gameStage.isIconified()) {
			gameTimer.stop();
			minimized = true;
		}
		else if (minimized && !gameStage.isIconified()) {
			gameTimer.start();
			minimized = false;
		}
			}
		};
		mainTimer.start();
	}
	
	private void fireLaser(ship Ship,boolean doubleLaser) {
		long timeNow = System.currentTimeMillis();
		long time = timeNow - timeOfLastShot;
		if (time > fireRate && isSpacePressed) {
		    timeOfLastShot = timeNow;
		    if(isSpacePressed) {
				if(!doubleLaser) {
				Rectangle laser = new Rectangle(9,54);
				laser.setFill(new ImagePattern(new Image(Ship.getUrlLaser())));
				laser.relocate(chosenShip.getLayoutX() + chosenShip.getBoundsInLocal().getWidth() / 2 - 4, chosenShip.getLayoutY() - 48);
				weapons.add(laser);
				gamePane.getChildren().add(laser);
				}
				else {
					Rectangle laser1 = new Rectangle(9,54);
					Rectangle laser2 = new Rectangle(9,54);
					laser1.setFill(new ImagePattern(new Image(Ship.getUrlLaser())));
					laser2.setFill(new ImagePattern(new Image(Ship.getUrlLaser())));
					laser1.relocate(chosenShip.getLayoutX(), chosenShip.getLayoutY());
					laser2.relocate(chosenShip.getLayoutX() + chosenShip.getBoundsInLocal().getWidth(), chosenShip.getLayoutY());
					weapons.add(laser1);
					gamePane.getChildren().add(laser1);
					weapons.add(laser2);
					gamePane.getChildren().add(laser2);
				}
				if(playPlayerLaser.getStatus() != MediaPlayer.Status.READY) 
					playPlayerLaser.seek(Duration.seconds(0.0));
				playPlayerLaser.play();
		    }
		}
	}
	
	private void fireEnemyLaser() {
		long timeNow = System.currentTimeMillis();
		for (int i=0;i<2;i++) {
			if(gamePane.getChildren().contains(enemies[i])) {
				long time = timeNow - enemyTimeOfLastShot[i];
				if(time > enemyFireRate[i] && enemies[i].getLayoutY() > 0) {
					enemyTimeOfLastShot[i] = timeNow;
					Rectangle enemyLaser = new Rectangle(9,57);
					for (enemyShip es : enemyShip.values()) {
						if(enemyEffect[i] == es.getEffect()) {
							enemyLaser.setFill(new ImagePattern(new Image(es.getUrlLaser())));
							enemyeffect.add(es.getEffect());
							enemydamage.add(es.getDamage());
						}
					}
					enemyLaser.relocate(enemies[i].getLayoutX() + enemies[i].getBoundsInLocal().getWidth() / 2 - 4, enemies[i].getLayoutY() + 58);
					enemyWeapons.add(enemyLaser);
					gamePane.getChildren().add(enemyLaser);
					if(playEnemyLaser.getStatus() != MediaPlayer.Status.READY) 
						playEnemyLaser.seek(Duration.seconds(0.0));
					playEnemyLaser.play();
				}
			}
		}
	}
	
	private void moveShip() {
		if (isAPressed && !isDPressed) {
			if (angle > -30)
				angle -=5;
			chosenShip.setRotate(angle);
			if(chosenShip.getLayoutX() > 0)
				chosenShip.setLayoutX(chosenShip.getLayoutX() - speedOfShip);
		}
		
		if(isDPressed && !isAPressed) {
			if (angle < 30)
				angle +=5;
			chosenShip.setRotate(angle);
			if(chosenShip.getLayoutX() < 501)
				chosenShip.setLayoutX(chosenShip.getLayoutX() + speedOfShip);
		}
		
		if(isAPressed && isDPressed) {
			if (angle < 0)
				angle +=5;
			else if (angle > 0)
				angle -=5;
			chosenShip.setRotate(angle);
		}
		
		if(!isAPressed && !isDPressed) {
			if (angle < 0)
				angle +=5;
			else if (angle > 0)
				angle -=5;
			chosenShip.setRotate(angle);
		}
		
		if (isWPressed && !isSPressed) {
			if(chosenShip.getLayoutY() > 0)
				chosenShip.setLayoutY(chosenShip.getLayoutY() - speedOfShip);
		}
		
		if(isSPressed && !isWPressed) {
			if(chosenShip.getLayoutY() < 721)
				chosenShip.setLayoutY(chosenShip.getLayoutY() + speedOfShip);
		}
	}
	
	private void createBackground() {
		gridPane1 = new GridPane();
		ImageView backgrounfImage1 = new ImageView(background_image);
		gridPane1.getChildren().add(backgrounfImage1);
		gamePane.getChildren().addAll(gridPane1);
	}
	
	private void createGameElemets(ship chosenShip) {
		int meteorid;
		brownMeteors = new ImageView[2];
		for (int i=0;i<brownMeteors.length;i++) {
			meteorid = RandomPositionGenerator.nextInt(3);
			brownMeteorsid[i] = meteorid;
			brownMeteors[i] = new ImageView(brownMeteorsURLs[meteorid]);
			setNewElementPosition(brownMeteors[i]);
			gamePane.getChildren().add(brownMeteors[i]);
		}
		
		greyMeteors = new ImageView[2];
		for (int i=0;i<2;i++) {
			meteorid = RandomPositionGenerator.nextInt(3);
			greyMeteorsid[i] = meteorid;
			greyMeteors[i] = new ImageView(greyMeteorsURLs[meteorid]);
			setNewElementPosition(greyMeteors[i]);
			gamePane.getChildren().add(greyMeteors[i]);
		}
		
		/////////////////////////////////////////////////////////////////
		
		playerLife = 2;
		pointsLabel = new SmallInfoLabel("POINTS: 00");
		points = 0;
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		playerLifes = new ImageView[3];
		
		for (int i=0;i<playerLifes.length;i++) {
			playerLifes[i] = new ImageView(chosenShip.getUrlLife());
			playerLifes[i].setLayoutX(455 + (i * 50));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
		enemies = new ImageView[2];
		enemyHealth = new int[2];
		enemyDamage = new int[2];
		enemyFireRate = new int[2];
		enemySpeed = new int[2];
		enemyEffect = new int[2];
		int enemy;
		for (int i=0;i<2;i++) {
			enemy = RandomPositionGenerator.nextInt(5);
			enemies[i] = new ImageView(enemyShipsUrls[enemy]);
			for (enemyShip es : enemyShip.values()) {
				if(enemyShipsUrls[enemy] == es.getUrlship()) {
					enemyHealth[i] = es.getHealth();
					enemyDamage[i] = es.getDamage();
					enemyFireRate[i] = es.getFirerate();
					enemySpeed[i] = es.getSpeed();
					enemyEffect[i] = es.getEffect();
					break;
				}
			}
			setNewElementPosition(enemies[i]);
			gamePane.getChildren().add(enemies[i]);
		}
	}
	
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(RandomPositionGenerator.nextInt(370));
		image.setLayoutY(-(RandomPositionGenerator.nextInt(3200) + 600));
	}
	
	private void moveGameElements() {
		for(int i=0;i<brownMeteors.length;i++) {
			brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY()+7);
			brownMeteors[i].setRotate(brownMeteors[i].getRotate()+4);
		}
		
		for(int i=0;i<2;i++) {
			greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY()+7);
			greyMeteors[i].setRotate(greyMeteors[i].getRotate()+4);
		}
		
		for(int i=0;i<weapons.size();i++) 
			weapons.get(i).relocate(weapons.get(i).getLayoutX(), weapons.get(i).getLayoutY() - 9);
		
		for (int i=0;i<powerUps.length;i++)
			if(powerUps[i] != null)
				powerUps[i].relocate(powerUps[i].getLayoutX(), powerUps[i].getLayoutY() + 7);
		
		for(int i=0;i<2;i++) {
			enemies[i].setLayoutY(enemies[i].getLayoutY() + enemySpeed[i]);
		}
		
		for(int i=0;i<enemyWeapons.size();i++)
			enemyWeapons.get(i).relocate(enemyWeapons.get(i).getLayoutX(), enemyWeapons.get(i).getLayoutY() + 9);
	}
	
	private void checkIfElementsAtEndOfScreen() {
		int meteorid;
		for (int i=0;i<brownMeteors.length;i++) 
			if(brownMeteors[i].getLayoutY() > 900) {
				brownCheck[i] = true;
				meteorid = RandomPositionGenerator.nextInt(3);
				brownMeteorsid[i] = meteorid;
				brownMeteors[i].setImage(new Image(brownMeteorsURLs[meteorid]));
				setNewElementPosition(brownMeteors[i]);	
			}
		
		for (int i=0;i<greyMeteors.length;i++) 
			if(greyMeteors[i].getLayoutY() > 900) 
			{
				greyCheck[i] = true;
				meteorid = RandomPositionGenerator.nextInt(3);
				greyMeteorsid[i] = meteorid;
				greyMeteors[i].setImage(new Image(greyMeteorsURLs[meteorid]));
				setNewElementPosition(greyMeteors[i]);	
			}
		
		for(int i=0;i<weapons.size();i++) 
			if(weapons.get(i).getLayoutY() <= -10) {
				gamePane.getChildren().remove(weapons.get(i));
				weapons.remove(i);
			}
		
		for(int i=0;i<enemyWeapons.size();i++) 
			if(enemyWeapons.get(i).getLayoutY() > 900) {
				gamePane.getChildren().remove(enemyWeapons.get(i));
				enemyWeapons.remove(i);
				enemyeffect.remove(i);
				enemydamage.remove(i);
			}
		
		for(int i=0;i<powerUps.length;i++) 
			if(powerUps[i] != null)
			if(powerUps[i].getLayoutY() > 900) {
				gamePane.getChildren().remove(powerUps[i]);
				powerUps[i] = null;
			}
		
		int enemy;
		for (int i=0;i<2;i++) {
			if(enemies[i].getLayoutY() > 900) {
				if(!gamePane.getChildren().contains(enemies[i]))
					gamePane.getChildren().add(enemies[i]);
				enemyShipCheck[i] = true;
				enemy = RandomPositionGenerator.nextInt(5);
				enemies[i].setImage(new Image(enemyShipsUrls[enemy]));
				for (enemyShip es : enemyShip.values()) {
					if(enemyShipsUrls[enemy] == es.getUrlship()) {
						enemyHealth[i] = es.getHealth();
						enemyDamage[i] = es.getDamage();
						enemyFireRate[i] = es.getFirerate();
						enemySpeed[i] = es.getSpeed();
						enemyEffect[i] = es.getEffect();
						break;
					}
				}
				setNewElementPosition(enemies[i]);
			}
		}
	}
	
	private void removeLife(int num) throws IOException {
		for (int i=0;i<num;i++) {
			gamePane.getChildren().remove(playerLifes[playerLife]);
			playerLife--;
			if (playerLife < 0) {
				if(playLose.getStatus() != MediaPlayer.Status.READY) 
					playLose.seek(Duration.seconds(0.0));
				playLose.play();
				gameStage.close();
				gameTimer.stop();
				mainTimer.stop();
				GameOverViewManager overManager = new GameOverViewManager(menuStage,Ship,points);
			}
		}
	}
	
	private void checkIfElementsCollide() throws IOException {
		for (int i=0;i<brownMeteors.length;i++) {
			switch(brownMeteorsid[i]) {
			case 0:
			case 1:{
				if(mid_meteor_radius + ship_radius > calculateDistance(chosenShip.getLayoutX() + 49,brownMeteors[i].getLayoutX() +23,chosenShip.getLayoutY() + 48,brownMeteors[i].getLayoutY() + 20)) {
					int meteorCrash = RandomPositionGenerator.nextInt(2);
					brownMeteors[i].setImage(new Image(brownCrash[meteorCrash]));
					if(brownCheck[i]) {
						removeLife(1);
						brownCheck[i] = false;
						if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
							playMeteor.seek(Duration.seconds(0.0));
						playMeteor.play();
					}
				}
				break;
			}
			case 2:
				if(big_meteor_radius + ship_radius > calculateDistance(chosenShip.getLayoutX() + 49,brownMeteors[i].getLayoutX() +50,chosenShip.getLayoutY() + 48,brownMeteors[i].getLayoutY() + 42)) {
					brownMeteors[i].setImage(new Image(brownCrash[2]));
					if(brownCheck[i]) {
						removeLife(1);
						brownCheck[i] = false;
						if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
							playMeteor.seek(Duration.seconds(0.0));
						playMeteor.play();
					}
				}
				break;
			}
		}
		
		for (int i=0;i<greyMeteors.length;i++) {
			switch(greyMeteorsid[i]) {
			case 0:
			case 1:{
				if(mid_meteor_radius + ship_radius > calculateDistance(chosenShip.getLayoutX() + 49,greyMeteors[i].getLayoutX() +23,chosenShip.getLayoutY() + 48,greyMeteors[i].getLayoutY() + 20)) {
					int meteorCrash = RandomPositionGenerator.nextInt(2);
					greyMeteors[i].setImage(new Image(greyCrash[meteorCrash]));
					if(greyCheck[i]) {
						removeLife(1);
						greyCheck[i] = false;
						if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
							playMeteor.seek(Duration.seconds(0.0));
						playMeteor.play();
					}
				}
				break;
			}
			case 2:
				if(big_meteor_radius + ship_radius > calculateDistance(chosenShip.getLayoutX() + 49,greyMeteors[i].getLayoutX() +50,chosenShip.getLayoutY() + 48,greyMeteors[i].getLayoutY() + 42)) {
					greyMeteors[i].setImage(new Image(greyCrash[2]));
					if(greyCheck[i]) {
						removeLife(1);
						greyCheck[i] = false;
						if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
							playMeteor.seek(Duration.seconds(0.0));
						playMeteor.play();
					}
				}
				break;
			}
		}
		
		for (int i=0;i<enemies.length;i++) {
			if(enemy_ship_radius + ship_radius > calculateDistance(chosenShip.getLayoutX() + 49,enemies[i].getLayoutX() +49,chosenShip.getLayoutY() + 48,enemies[i].getLayoutY() + 48)){
				enemyHealth[i]--;
				if(enemyShipCheck[i]) {
					removeLife(1);
					enemyShipCheck[i] = false;
				if(enemyHealth[i] == 0) {
					points++;
					if(points < 10)
						pointsLabel.setText("POINTS: 0" + points);
					else
						pointsLabel.setText("POINTS; " + points);
					explode(enemies[i].getLayoutX(),enemies[i].getLayoutY(),System.currentTimeMillis());
					gamePane.getChildren().remove(enemies[i]);
					enemyShipCheck[i] = false;
				}
				}
			}
		}
	}
	
	private double calculateDistance(double x1,double x2,double y1,double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	private void checkIfLaserHit() {
		try {
			int wsize = weapons.size();
			int esize = enemies.length;
			for(int i=0;i<wsize;i++) {
				for(int j=0;j<esize;j++) {
					if((laser_radius + enemy_ship_radius > calculateDistance(weapons.get(i).getLayoutX() + 4,enemies[j].getLayoutX() + 49,weapons.get(i).getLayoutY(),enemies[j].getLayoutY() + 48)) && enemyShipCheck[j]) {
						enemyHealth[j]--;
						gamePane.getChildren().remove(weapons.get(i));
						weapons.remove(i);
						wsize = weapons.size();
						if(enemyHealth[j] == 0) {
							points++;
							if(points < 10)
								pointsLabel.setText("POINTS: 0" + points);
							else
								pointsLabel.setText("POINTS; " + points);
							explode(enemies[j].getLayoutX(),enemies[j].getLayoutY(),System.currentTimeMillis());
							gamePane.getChildren().remove(enemies[j]);
							enemyShipCheck[j] = false;
						}
						break;
					}
				}
			}	
			wsize = weapons.size();
		for(int i=0;i<wsize;i++)
			brownloop: for (int j=0;j<brownMeteors.length;j++)
			{
					switch(brownMeteorsid[j]) {
					case 0:
					case 1:
						if(laser_radius + mid_meteor_radius > calculateDistance(weapons.get(i).getLayoutX() + 4,brownMeteors[j].getLayoutX() +23,weapons.get(i).getLayoutY(),brownMeteors[j].getLayoutY() + 20)) {
							int meteorCrash = RandomPositionGenerator.nextInt(2);
							brownMeteors[j].setImage(new Image(brownCrash[meteorCrash]));
							if(brownCheck[j]) {
								brownCheck[j] = false;
								gamePane.getChildren().remove(weapons.get(i));
								weapons.remove(i);
								wsize = weapons.size();
								if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
									playMeteor.seek(Duration.seconds(0.0));
								playMeteor.play();
								break brownloop;
							}
						}
						break;
					case 2:
						if(laser_radius + big_meteor_radius > calculateDistance(weapons.get(i).getLayoutX() + 4,brownMeteors[j].getLayoutX() +50,weapons.get(i).getLayoutY(),brownMeteors[j].getLayoutY() + 42)) {
							brownMeteors[j].setImage(new Image(brownCrash[2]));
							if(brownCheck[j]) {
								brownCheck[j] = false;
								gamePane.getChildren().remove(weapons.get(i));
								weapons.remove(i);
								wsize = weapons.size();
								if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
									playMeteor.seek(Duration.seconds(0.0));
								playMeteor.play();
								break brownloop;
							}
						}
						break;
					}
				}
		wsize = weapons.size();
		for(int i=0;i<wsize;i++)
			greyloop: for (int j=0;j<greyMeteors.length;j++)
			{
					switch(greyMeteorsid[j]) {
					case 0:
					case 1:
						if(laser_radius + mid_meteor_radius > calculateDistance(weapons.get(i).getLayoutX() + 4,greyMeteors[j].getLayoutX() +23,weapons.get(i).getLayoutY(),greyMeteors[j].getLayoutY() + 20)) {
							int meteorCrash = RandomPositionGenerator.nextInt(2);
							greyMeteors[j].setImage(new Image(greyCrash[meteorCrash]));
							if(greyCheck[j]) {
								greyCheck[j] = false;
								gamePane.getChildren().remove(weapons.get(i));
								weapons.remove(i);
								wsize = weapons.size();
								if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
									playMeteor.seek(Duration.seconds(0.0));
								playMeteor.play();
								break greyloop;
							}
						}
						break;
					case 2:
						if(laser_radius + big_meteor_radius > calculateDistance(weapons.get(i).getLayoutX() + 4,greyMeteors[j].getLayoutX() +50,weapons.get(i).getLayoutY(),greyMeteors[j].getLayoutY() + 42)) {
							greyMeteors[j].setImage(new Image(greyCrash[2]));
							if(greyCheck[j]) {
								greyCheck[j] = false;
								gamePane.getChildren().remove(weapons.get(i));
								weapons.remove(i);
								wsize = weapons.size();
								if(playMeteor.getStatus() != MediaPlayer.Status.READY) 
									playMeteor.seek(Duration.seconds(0.0));
								playMeteor.play();
								break greyloop;
							}
						}
						break;
					}
				}
	}catch(IndexOutOfBoundsException e){}
	}
	
	private void checkIfEnemyLaserHit() throws IOException {
		try {
		for (int i=0;i<enemyWeapons.size();i++) {
			if(laser_radius + ship_radius > calculateDistance(enemyWeapons.get(i).getLayoutX() + 4,chosenShip.getLayoutX() + 49,enemyWeapons.get(i).getLayoutY(),chosenShip.getLayoutY() + 48)) {
				gamePane.getChildren().remove(enemyWeapons.get(i));
				enemyWeapons.remove(i);
				if(canbehit) {
					removeLife(enemydamage.get(i));
					if(enemyeffect.get(i) == 0) {
						startOfSlow = System.currentTimeMillis();
						if(!slow) {
							createSlowProgressBar();
							slow = true;
						}
						else
							slowBar.setProgress(1);
						speedOrSlow();
					}
						else if (enemyeffect.get(i) == 3){
						startOfPick = System.currentTimeMillis();
						if(!pick) {
							createPickProgressBar();
							pick = true;
						}
						else
							pickBar.setProgress(1);
						pick();
					}
						else if(enemyeffect.get(i) == 4){
						startOfSlowerFireRate = System.currentTimeMillis();
						if(!slowerFireRate) {
							createSlowerFireRateProgressBar();
							slowerFireRate = true;
						}
						else
							slowerFireRateBar.setProgress(1);
						higherOrLowerFireRate();
						}
					}
				enemydamage.remove(i);
				enemyeffect.remove(i);
				}
			}
		}catch(IndexOutOfBoundsException e) {}
	}
	
	void explode(double x,double y,long t) {
		for (int i=0;i<6;i++) {
			if(explosions[i] == null) {
				explosions[i] = new ImageView(explosion);
				explosions[i].setLayoutX(x);
				explosions[i].setLayoutY(y);
				explosionDurstion[i] = t;
				gamePane.getChildren().add(explosions[i]);
				if(playExplosion.getStatus() != MediaPlayer.Status.READY) 
					playExplosion.seek(Duration.seconds(0.0));
				playExplosion.play();
			}
		}
	}
	
	void endExplode() {
		long time = System.currentTimeMillis();
		for (int i=0;i<6;i++) {
			if(explosions[i] != null && time - explosionDurstion[i] > 400 ) {
				gamePane.getChildren().remove(explosions[i]);
				explosions[i] = null;
			}
		}
	}
	
	private void randomizerForPowerUps() {
		int power = RandomPositionGenerator.nextInt(2500);
		if (power == 0)
			healingPowerUp();
		else if (power == 1)
			doubleLaserPowerUp();
		else if (power == 2)
			higherFireRatePowerUp();
		else if (power == 3)
			shieldPowerUp();
		else if (power == 4)
			speedPowerUp();
	}
	
	private void powerUpsListener(ship Ship) {
		 for (int i=0;i<powerUps.length;i++)
			 if(powerUps[i] != null && pickUp && powerUps[i].getBoundsInParent().intersects(chosenShip.getBoundsInParent())) {
				 if(powerUpsID[i] == 0 && playerLife != 2) {
					 if(playPickUp.getStatus() != MediaPlayer.Status.READY) 
							playPickUp.seek(Duration.seconds(0.0));
					 playPickUp.play();
					 gamePane.getChildren().remove(powerUps[i]);
					 powerUps[i] = null;
					 addlife(Ship);
				 }
				 else if (powerUpsID[i] == 1) {
					 if(playPickUp.getStatus() != MediaPlayer.Status.READY) 
							playPickUp.seek(Duration.seconds(0.0));
					 playPickUp.play();
					 gamePane.getChildren().remove(powerUps[i]);
					 powerUps[i] = null;
					 startofDoubleLaser = System.currentTimeMillis();
					 chosenShip.setImage(new Image(Ship.getUrlShip2()));
					 if(shield)
						 shieldImage.relocate(chosenShip.getLayoutX() - 12, chosenShip.getLayoutY() - 10);
					 if(!doubleLaser) {
						 createDoubleLaserProgressBar();
						 doubleLaser = true;
					 }
					 else {
						 doubleLaserBar.setProgress(1);
					 }
					 doubleLaser(Ship);
				 }
				 else if (powerUpsID[i] == 2) {
					 if(playPickUp.getStatus() != MediaPlayer.Status.READY) 
							playPickUp.seek(Duration.seconds(0.0));
					 playPickUp.play();
					 gamePane.getChildren().remove(powerUps[i]);
					 powerUps[i] = null;
					 startofHigherFireRate = System.currentTimeMillis();
					 if(!higherFireRate) {
						 createHigherFireRateProgressBar();
						 higherFireRate = true;
					 }
					 else {
						 higherFireRateBar.setProgress(1);
					 }
					 higherOrLowerFireRate();
				 }
				 else if (powerUpsID[i] == 3) {
					 gamePane.getChildren().remove(powerUps[i]);
					 powerUps[i] = null;
					 startofShield = System.currentTimeMillis();
					 if(doubleLaser)
						 shieldImage.relocate(chosenShip.getLayoutX() - 12, chosenShip.getLayoutY() - 10);
					 if(!shield) {
						 createShieldProgressBar();
						 gamePane.getChildren().add(shieldImage);
						 shield = true;
						 if(playShieldUp.getStatus() != MediaPlayer.Status.READY) 
								playShieldUp.seek(Duration.seconds(0.0));
						 playShieldUp.play();
					 }
					 else {
						 shieldBar.setProgress(1);
						 if(playPickUp.getStatus() != MediaPlayer.Status.READY) 
								playPickUp.seek(Duration.seconds(0.0));
					 }
					 shield();
				 }
				 else if (powerUpsID[i] == 4) {
					 if(playPickUp.getStatus() != MediaPlayer.Status.READY) 
							playPickUp.seek(Duration.seconds(0.0));
					 playPickUp.play();
					 gamePane.getChildren().remove(powerUps[i]);
					 powerUps[i] = null;
					 startofSpeed = System.currentTimeMillis();
					 if(!speed) {
						 createSpeedProgressBar();
						 speed = true;
					 }
					 else {
						 speedBar.setProgress(1);
					 }
					 speedOrSlow();
				 }
			 }
	}
	
	private void createDoubleLaserProgressBar() {
		doubleLaserBar = new ProgressBar();
		doubleLaserBar.setStyle("-fx-accent:#bfbfbf;");
		doubleLaserBar.setProgress(1);
		doubleLaserBar.setPrefWidth(100);
		doubleLaserBar.setLayoutX(490);
		ProgressBars.add(doubleLaserBar);
		gamePane.getChildren().add(doubleLaserBar);
		
		ImageView d= new ImageView((new Image(powerUpsUrls[1])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void createHigherFireRateProgressBar() {
		higherFireRateBar = new ProgressBar();
		higherFireRateBar.setStyle("-fx-accent:#ffff33;");
		higherFireRateBar.setProgress(1);
		higherFireRateBar.setPrefWidth(100);
		higherFireRateBar.setLayoutX(490);
		ProgressBars.add(higherFireRateBar);
		gamePane.getChildren().add(higherFireRateBar);
		
		ImageView d= new ImageView((new Image(powerUpsUrls[2])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void createShieldProgressBar() {
		shieldBar = new ProgressBar();
		shieldBar.setStyle("-fx-accent:#000066;");
		shieldBar.setProgress(1);
		shieldBar.setPrefWidth(100);
		shieldBar.setLayoutX(490);
		ProgressBars.add(shieldBar);
		gamePane.getChildren().add(shieldBar);
		
		ImageView d= new ImageView((new Image(powerUpsUrls[3])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void createSpeedProgressBar() {
		speedBar = new ProgressBar();
		speedBar.setStyle("-fx-accent:#b30000;");
		speedBar.setProgress(1);
		speedBar.setPrefWidth(100);
		speedBar.setLayoutX(490);
		ProgressBars.add(speedBar);
		gamePane.getChildren().add(speedBar);
		
		ImageView d= new ImageView((new Image(powerUpsUrls[4])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void createSlowProgressBar() {
		slowBar = new ProgressBar();
		slowBar.setStyle("-fx-accent:#ff6600");
		slowBar.setProgress(1);
		slowBar.setPrefWidth(100);
		slowBar.setLayoutX(490);
		negativeProgressBars.add(slowBar);
		gamePane.getChildren().add(slowBar);
		
		ImageView d= new ImageView((new Image(negativePowerUpsUrls[0])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfNegativeProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void createPickProgressBar(){
		pickBar = new ProgressBar();
		pickBar.setStyle("-fx-accent:#009933");
		pickBar.setProgress(1);
		pickBar.setPrefWidth(100);
		pickBar.setLayoutX(490);
		negativeProgressBars.add(pickBar);
		gamePane.getChildren().add(pickBar);
		
		ImageView d= new ImageView((new Image(negativePowerUpsUrls[1])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfNegativeProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void createSlowerFireRateProgressBar() {
		slowerFireRateBar = new ProgressBar();
		slowerFireRateBar.setStyle("-fx-accent:#0099cc");
		slowerFireRateBar.setProgress(1);
		slowerFireRateBar.setPrefWidth(100);
		slowerFireRateBar.setLayoutX(490);
		negativeProgressBars.add(slowerFireRateBar);
		gamePane.getChildren().add(slowerFireRateBar);
		
		ImageView d= new ImageView((new Image(negativePowerUpsUrls[2])));
		d.setFitWidth(20);
		d.setFitHeight(20);
		d.setLayoutX(460);
		picturesOfNegativeProgressBars.add(d);
		gamePane.getChildren().add(d);
	}
	
	private void determinePositionOfProgressBar() {
		for (int i=0;i<ProgressBars.size();i++) {
			ProgressBars.get(i).setLayoutY(120 + i * 25);
			picturesOfProgressBars.get(i).setLayoutY(120 + i * 25);
		}
	}
	
	private void decreaseProgressBars() {
		for (int i=0;i<ProgressBars.size();i++)
		{
			BigDecimal progress = new BigDecimal(String.format("%.4f", ProgressBars.get(i).getProgress()));
			if(progress.doubleValue() > 0) {
				progress = new BigDecimal(String.format("%.4f", progress.doubleValue() - 0.001));
				ProgressBars.get(i).setProgress(progress.doubleValue());
			}
			else
			{
				gamePane.getChildren().remove(ProgressBars.get(i));
				ProgressBars.remove(i);
				gamePane.getChildren().remove(picturesOfProgressBars.get(i));
				picturesOfProgressBars.remove(i);
			}
		}
	}
	
	private void determinePositionOfNegativeProgressBar() {
		for (int i=0;i<negativeProgressBars.size();i++) {
			negativeProgressBars.get(i).setLayoutY(250 + i * 25);
			picturesOfNegativeProgressBars.get(i).setLayoutY(250 + i * 25);
		}
	}
	
	private void decreaseNegativeProgressBars() {
		for (int i=0;i<negativeProgressBars.size();i++)
		{
			BigDecimal progress = new BigDecimal(String.format("%.4f", negativeProgressBars.get(i).getProgress()));
			if(progress.doubleValue() > 0) {
				progress = new BigDecimal(String.format("%.4f", progress.doubleValue() - 0.002));
				negativeProgressBars.get(i).setProgress(progress.doubleValue());
			}
			else
			{
				gamePane.getChildren().remove(negativeProgressBars.get(i));
				negativeProgressBars.remove(i);
				gamePane.getChildren().remove(picturesOfNegativeProgressBars.get(i));
				picturesOfNegativeProgressBars.remove(i);
			}
		}
	}
	
	private void healingPowerUp() {
		for(int i=0;i<powerUps.length;i++)
			if(powerUps[i] == null)
			{
				powerUps[i]= new ImageView((new Image(powerUpsUrls[0])));
				powerUpsID[i] = 0;
				setNewElementPosition(powerUps[i]);
				gamePane.getChildren().add(powerUps[i]);
				return;
			}
	}
	
	private void doubleLaserPowerUp() {
		for(int i=0;i<powerUps.length;i++)
			if(powerUps[i] == null)
			{
				powerUps[i]= new ImageView((new Image(powerUpsUrls[1])));
				powerUpsID[i] = 1;
				setNewElementPosition(powerUps[i]);
				gamePane.getChildren().add(powerUps[i]);
				return;
			}
	}
	
	private void higherFireRatePowerUp() {
		for(int i=0;i<powerUps.length;i++)
			if(powerUps[i] == null)
			{
				powerUps[i]= new ImageView((new Image(powerUpsUrls[2])));
				powerUpsID[i] = 2;
				setNewElementPosition(powerUps[i]);
				gamePane.getChildren().add(powerUps[i]);
				return;
			}
	}
	
	private void shieldPowerUp() {
		for(int i=0;i<powerUps.length;i++)
			if(powerUps[i] == null)
			{
				powerUps[i]= new ImageView((new Image(powerUpsUrls[3])));
				powerUpsID[i] = 3;
				setNewElementPosition(powerUps[i]);
				gamePane.getChildren().add(powerUps[i]);
				return;
			}
	}
	
	private void speedPowerUp() {
		for(int i=0;i<powerUps.length;i++)
			if(powerUps[i] == null)
			{
				powerUps[i]= new ImageView((new Image(powerUpsUrls[4])));
				powerUpsID[i] = 4;
				setNewElementPosition(powerUps[i]);
				gamePane.getChildren().add(powerUps[i]);
				return;
			}
	}
	
	private void addlife(ship Ship) {
		playerLife++;
		playerLifes[playerLife] = new ImageView(Ship.getUrlLife());
		playerLifes[playerLife].setLayoutX(455 + (playerLife * 50));
		playerLifes[playerLife].setLayoutY(80);
		gamePane.getChildren().add(playerLifes[playerLife]);
	}
	
	private boolean doubleLaser(ship Ship) {
		long timeNow = System.currentTimeMillis();
		long time = timeNow - startofDoubleLaser;
		if(time <= 16000)
			return true;
		else {
			chosenShip.setImage(new Image(Ship.getURL()));
			if(shield)
				shieldImage.relocate(chosenShip.getLayoutX() - 20, chosenShip.getLayoutY() - 10);
			doubleLaser = false;
			return false;
		}
	}
	
	private void shield(){
		long timeNow = System.currentTimeMillis();
		long time = timeNow - startofShield;
		if(time <= 16000) {
			canbehit = false;
			shieldFlag = false;
			if(doubleLaser)
				shieldImage.relocate(chosenShip.getLayoutX() - 12, chosenShip.getLayoutY() - 10);
			else
				shieldImage.relocate(chosenShip.getLayoutX() - 20, chosenShip.getLayoutY() - 10);
		}
		else {
			shield = false;
			gamePane.getChildren().remove(shieldImage);
			if(!shieldFlag) {
				shieldFlag = true;
				if(playShieldDown.getStatus() != MediaPlayer.Status.READY) 
					playShieldDown.seek(Duration.seconds(0.0));
				playShieldDown.play();
			}
			canbehit = true;
		}
	}
	
	private void pick() {
		long timeNow = System.currentTimeMillis();
		long time = timeNow - startOfPick;
		if(time <= 8000)
			pickUp = false;
		else {
			pick = false;
			pickUp = true;
		}
	}

	private void speedOrSlow() {
		long timeNow = System.currentTimeMillis();
		long timeSpeed = timeNow - startofSpeed;
		long timeSlow = timeNow - startOfSlow;
		if(timeSpeed > 16000)
			speed = false;
		if(timeSlow > 8000)
			slow = false;
		if((!speed && !slow) || (speed && slow))
			speedOfShip = 5;
		else if (speed && !slow)
			speedOfShip = 7;
		else if (!speed && slow)
			speedOfShip = 3;
	}
	
	private void higherOrLowerFireRate() {
		long timeNow = System.currentTimeMillis();
		long timeHigh = timeNow - startofHigherFireRate;
		long timeSlow = timeNow - startOfSlowerFireRate;
		if(timeHigh > 16000)
			higherFireRate = false;
		if(timeSlow > 8000)
			slowerFireRate = false;
		if((!higherFireRate && !slowerFireRate) || (higherFireRate && slowerFireRate))
			fireRate = 750;
		else if (higherFireRate && !slowerFireRate)
			fireRate = 500;
		else if (!higherFireRate && slowerFireRate)
			fireRate = 1000;
	}
}

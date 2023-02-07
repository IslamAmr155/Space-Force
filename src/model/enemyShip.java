package model;

public enum enemyShip {
	kamikaze("view/resources/enemyShipChooser/enemyRed4.png","view/resources/enemyShipChooser/laserRed13.png",2,1,1650,6,0),
	normal("view/resources/enemyShipChooser/enemyBlack2.png","view/resources/enemyShipChooser/laserRed15.png",1,1,1750,4,1),
	upgraded("view/resources/enemyShipChooser/enemyBlack3.png","view/resources/enemyShipChooser/laserRed15.png",2,2,1500,5,2),
	noPowerUp("view/resources/enemyShipChooser/enemyGreen5.png","view/resources/enemyShipChooser/laserGreen03.png",1,2,1600,4,3),
	noShooting("view/resources/enemyShipChooser/enemyBlue1.png","view/resources/enemyShipChooser/laserBlue13.png",2,1,1550,5,4);
	
	private String urlShip;
	private String urlLaser;
	private int health;
	private int damage;
	private int firerate;
	private int speed;
	private int effect;
	
	private enemyShip(String urlShip,String urlLaser,int health,int damage,int fireRate,int speed,int effect) {
		this.urlShip = urlShip;
		this.urlLaser = urlLaser;
		this.health = health;
		this.damage = damage;
		this.firerate = fireRate;
		this.speed = speed;
		this.effect = effect;
	}
	
	public String getUrlship() {
		return urlShip;
	}
	
	public String getUrlLaser() {
		return urlLaser;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getFirerate() {
		return firerate;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getEffect() {
		return effect;
	}
}

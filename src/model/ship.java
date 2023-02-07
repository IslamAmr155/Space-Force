package model;

public enum ship {
	blue("view/Resources/shipchooser/playerShip3_blue.png", "view/Resources/shipchooser/playerLife3_blue.png" , "view/Resources/shipchooser/laserBlue01.png", "view/Resources/shipchooser/playerShip2_blue.png"),
	green("view/Resources/shipchooser/playerShip3_green.png", "view/Resources/shipchooser/playerLife3_green.png", "view/Resources/shipchooser/laserGreen11.png", "view/Resources/shipchooser/playerShip2_green.png"),
	red("view/Resources/shipchooser/playerShip3_red.png", "view/Resources/shipchooser/playerLife3_red.png", "view/Resources/shipchooser/laserRed01.png", "view/Resources/shipchooser/playerShip2_red.png");
	
	private String urlShip;
	private String urlLife;
	private String urlLaser;
	private String urlShip2;
	
	private ship(String urlShip,String urlLife,String urlLaser,String urlShip2) {
		this.urlShip = urlShip;
		this.urlLife = urlLife;
		this.urlLaser = urlLaser;
		this.urlShip2 = urlShip2;
	}
	
	public String getURL() {
		return urlShip;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
	
	public String getUrlLaser() {
		return urlLaser;
	}
	
	public String getUrlShip2() {
		return urlShip2;
	}

}

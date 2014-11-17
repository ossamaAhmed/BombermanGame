package bomberMan.gamePlay.Model;

public class PowerUp extends GameObject {
	
	//Private variables
	private PowerUpType type;
	
	//Constructor
	public PowerUp(){
		type=null;
	}
	
	public PowerUp(int xPos, int yPos, String locationImage, PowerUpType type, String namePowerUp){
		super(xPos, yPos,locationImage, namePowerUp);
		this.type = type;
		
	}
	
	//getters and setters
	public void setPowerUp(PowerUpType type){
		this.type = type;
	}
	
	public PowerUpType getPowerUpType()
	{
		return this.type;
	}

}

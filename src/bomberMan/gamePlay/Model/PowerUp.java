package bomberMan.gamePlay.Model;

public class PowerUp extends GameObject {
	
	//Private variables
	private PowerUpType type;
	
	//Constructor
	public PowerUp(){
		type=null;
	}
	
	public PowerUp(int xPos, int yPos, String locationImage, PowerUpType namePowerUp, String typeObject){
		super(xPos, yPos,locationImage,typeObject ); // type object is supposed to be equal to PowerUp
		this.type = namePowerUp;
		
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

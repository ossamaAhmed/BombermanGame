package bomberMan.gamePlay.Model;

public class PowerUp extends GameObject {
	
	//Private variables
	private PowerUpType type;
	
	//Constructor
	public PowerUp(){
		type=null;
	}
	
	public PowerUp(PowerUpType type){
		this.type=type;
	}
	
	//getters and setters
	public void setPowerUp(PowerUpType type){
		this.type=type;
	}
	
	public PowerUpType getPowerUpType()
	{
		return this.type;
	}

}

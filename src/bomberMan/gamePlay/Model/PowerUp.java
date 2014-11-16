package bomberMan.gamePlay.Model;

public class PowerUp extends GameObject {
	
	//Private variables
	private PowerUpType type;
	
	//Constructor
	public PowerUp(){
		type=null;
	}
	
	public PowerUp(PowerUpType type){
		this.type = type;
		if(this.type == PowerUpType.BOMBS){this.setType("BOMBS");}
		if(this.type == PowerUpType.WALLPASS){this.setType("WALLPASS");}
		if(this.type == PowerUpType.DETONATOR){this.setType("DETONATOR");}
		if(this.type == PowerUpType.FLAMES){this.setType("FLAMES");}
		if(this.type == PowerUpType.SPEED){this.setType("SPEED");}
		if(this.type == PowerUpType.BOMBPASS){this.setType("BOMBPASS");}
		if(this.type == PowerUpType.FLAMEPASS){this.setType("FLAMEPASS");}
		if(this.type == PowerUpType.MYSTERY){this.setType("MYSTERY");}
		
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

package bomberMan.Login.Model;

import java.util.ArrayList;
import bomberMan.gamePlay.Model.GameBoard;


public class User 
{
	//private variables
	private ArrayList <GameBoard> mySavedGames=new ArrayList<GameBoard>();
	private ArrayList <Record> myRecords=new ArrayList<Record>();
	private String name;
	private String userName;
	private String password;
	private int score;
	private int unlockedLevel;
	
	public User(String fullName, String userName, String password, int score, int unlockedLevel)
	{
		this.name = fullName;
		this.userName=userName;
		this.password=password;
		this.score = score;
		this.unlockedLevel = unlockedLevel;
	}
	
	public String getFullName() {
		return this.name;
	}
	
	public void setFullName(String name) {
		this.name = name;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getUnlockedLevel() {
		return unlockedLevel;
	}
	
	public void setUnlockedLevel(int unlockedLevel) {
		this.unlockedLevel = unlockedLevel;
	}
	

}

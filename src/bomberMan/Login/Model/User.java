package bomberMan.Login.Model;

import java.util.ArrayList;
import bomberMan.gamePlay.Model.GameBoard;


public class User 
{
	//private variables
	private ArrayList <GameBoard> mySavedGames=new ArrayList<GameBoard>();
	private ArrayList <Record> myRecords=new ArrayList<Record>();
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	public User(String firstName, String lastName, String userName, String password)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.password=password;
	}
	
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	

}

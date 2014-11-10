package bomberMan.Login.Model;

public class Record 
{
	private int score;
	private User myUser;
	
	public Record(int score, User myUser)
	{
		this.score=score;
		this.myUser=myUser;
	}
	public int getScore()
	{
		return score;
	}
	public User getUser()
	{
		return this.myUser;
	}
	public int compare(Record anotherRecord)
	{
		if(this.score>anotherRecord.getScore())
			return 1;
		else if(this.score<anotherRecord.getScore())
			return -1;
		else
			return 0;
	}
	


}

package bomberMan.Login.Model;

import java.util.Comparator;

public class Record 
{
	private int score;
	private String userName;
	
	public Record(int score, String myUser)
	{
		this.score=score;
		this.userName=myUser;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public String getUser()
	{
		return this.userName;
	}
	
	public static Comparator<Record> COMPARE_BY_SCORE = new Comparator<Record>() {
		public int compare(Record one, Record other) {
			if(one.getScore()>other.getScore())
				return -1;
			else if(one.getScore()<other.getScore())
				return 1;
			else
				return 0;
		}
	};


}

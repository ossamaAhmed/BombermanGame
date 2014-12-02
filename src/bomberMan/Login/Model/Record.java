/* 
 * File: Record.java
 * -----------------------
 * This Class contains each user's username and score and is used by the Leaderboard class to arrange the users in descending 
 * order of their scores
 */
/**
 * This class is used by within an ArrayList in the Leaderboard class and is passed as an object to it
 *
 *
 */
package bomberMan.Login.Model;

import java.util.Comparator;

public class Record 
{
	private int score;
	private String userName;

	/**
	 * 
	 * @param score
	 * @param myUser
	 */
	public Record(int score, String myUser)
	{
		this.score=score;
		this.userName=myUser;
	}

	/**
	 * Getter function returns the score of the user
	 * @return Integer
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Getter function returns username
	 * @return Integer
	 */
	public String getUser()
	{
		return this.userName;
	}

	/** 
	 * This method compares the scores of two records
	 * 
	 * return an integer: -1 if the score in the first record is greater than second, 1 if the score in the first record
	 *  is less than the second, 0 if the scores of the two records are equal
	 */
	
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

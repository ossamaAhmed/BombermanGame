package LoginModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import bomberMan.Login.Model.Record;

public class recordTest {
	
	@Test
	public void testCompareTo() {
		Record r1 = new Record(100, "user1");
		Record r2 = new Record(200, "user2");
		Record r3 = new Record(300, "user3");
		Record r4 = new Record(200, "user4");
		
		// r2 score is equal to r4 score
		assertEquals(Record.COMPARE_BY_SCORE.compare(r2, r4), 0);
		
		// r1 score is < r2 score
		assertEquals(Record.COMPARE_BY_SCORE.compare(r1, r2), 1);
		
		// r3 score is > r2 score
		assertEquals(Record.COMPARE_BY_SCORE.compare(r3, r2), -1);	
	}

}

package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Test;

public class VariableTest {
	@Test
	public void verifyVariableName() 
	{
		Variable variable = new Variable();
		variable.setName("X");
		assertEquals("X", variable.getName());
	}
	@Test
	public void verifyCanAddScoreToVariable() throws Exception {
		Variable variable = new Variable();
		variable.addScore(1.2);
		assertEquals(1, variable.getScores().size());
		
	}
	@Test
	public void verifyCountOfEnteredScores() throws Exception {
		fail("write me!");
		
	}
}

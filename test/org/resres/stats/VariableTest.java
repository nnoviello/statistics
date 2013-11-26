package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VariableTest {
	private Variable variable;
	@Before
	public void setUp() throws Exception
	{
		
		variable = new Variable();
	}
	@Test
	public void verifyVariableName() 
	{
		variable.setName("X");
		assertEquals("X", variable.getName());
	}
	@Test
	public void verifyCanAddScoreToVariable() throws Exception {
		assertEquals(0, variable.getScores().size());
		variable.addScore(1.2);
		assertEquals(1, variable.getScores().size());
		
	}
	@Test
	public void verifyCountOfEnteredScores() throws Exception {
		assertEquals(0, variable.getN()); 
		variable.addScore(1.2);
		assertEquals(1, variable.getN()); 
		variable.addScore(1.2);
		assertEquals(2, variable.getN()); 
	}
}

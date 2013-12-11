package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SumOfScoresCommandTest
{

	private Variable variable;
	private Command command;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 	
		variable.setName("X"); 
		variable.addScore(1.2); 
		variable.addScore(1.0); 
		command = new SumOfScoresCommand(null, variable, true); 
	}
	@Test
	public void verifyCommandInvokesSumOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.sumOfScores, .001);
		command.execute(); 
		assertEquals(2.2, variable.sumOfScores, .001);
	}
	@Test
	public void verifyCommandRetrievesResult() throws Exception
	{
		assertNull(command.getResult()); 
		command.execute(); 
		assertEquals(2.2, command.getResult(), .001); 
	}
	@Test(expected=IllegalArgumentException.class)
	public void verifyThrowsIfInitializedWithNull() throws Exception
	{
		command = new SumOfScoresCommand(null, null, true); 
	}
}

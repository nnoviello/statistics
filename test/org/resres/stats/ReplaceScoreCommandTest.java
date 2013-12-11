package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReplaceScoreCommandTest
{

	private Variable variable;
	private Command command;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 	
		variable.setName("X"); 
		command = new AddScoreCommand(null, variable, 1.2, 0, true); 
		command.execute(); 
		command = new ReplaceScoreCommand(null, variable, 1.4, 0, true); 
	}
	@Test
	public void verifyCommandInvokesAddScoreOnTargetVariable() throws Exception
	{
		assertEquals(1.2, variable.getScores().get(0), .001);
		command.execute(); 
		assertEquals(1.4, variable.getScores().get(0), .001);
		assertEquals(1, variable.getScores().size());
	}
	@Test
	public void verifyCommandRetrievesScoreValueJustReplacedAsResult() throws Exception
	{
		assertNull("null prior to execution", command.getResult()); 
		command.execute(); 
		assertEquals("score commands just return value of this score",1.4, command.getResult(), .001); 
	}

}

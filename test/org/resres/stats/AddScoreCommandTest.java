package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddScoreCommandTest
{
	private Variable variable;
	private Command command;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 	
		variable.setName("X"); 
		command = new AddScoreCommand(null, variable, 1.2, 0, true); 
	}
	@Test
	public void verifyCommandInvokesAddScoreOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.getFrequency(1.2));
		command.execute(); 
		assertEquals(1, variable.getFrequency(1.2));
	}
	@Test
	public void verifyCommandRetrievesScoreValueJustAddedAsResult() throws Exception
	{
		assertNull("null prior to execution", command.getResult()); 
		command.execute(); 
		assertEquals("score commands just return value of this score",1.2, command.getResult(), .001); 
	}

}

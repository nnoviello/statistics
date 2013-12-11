package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeleteScoreCommandTest
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
		command = new AddScoreCommand(null, variable, 1.0, 1, true); 
		command.execute(); 
		command = new DeleteScoreCommand(null, variable, 0, true); 
	}
	@Test
	public void verifyCommandInvokesDeleteScoreOnTargetVariable() throws Exception
	{
		assertEquals(2, variable.getN());
		command.execute(); 
		assertEquals(1, variable.getN());
		assertEquals(1, variable.getFrequency(1.0));
	}
	@Test
	public void verifyCommandRetrievesScoreValueJustDeletedAsResult() throws Exception
	{
		assertNull(command.getResult()); 
		command.execute(); 
		assertEquals("score commands just show the affected score.",1.2, command.getResult(), .001); 
	}

}

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
		command = new AddScoreCommand(variable, 1.2, true); 
	}
	@Test
	public void verifyCommandInvokesAddScoreOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.getFrequency(1.2));
		command.execute(); 
		assertEquals(1, variable.getFrequency(1.2));
	}
	@Test
	public void verifyCommandRetrievesFrequencyOfScoreJustAddedAsResult() throws Exception
	{
		assertEquals(0, command.getResult(), .001); 
		command.execute(); 
		assertEquals("frequency of this score",1, command.getResult(), .001); 
	}

}

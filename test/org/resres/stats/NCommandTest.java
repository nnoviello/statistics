package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NCommandTest
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
		command = new NCommand(variable, true); 
	}
	@Test
	public void verifyCommandInvokesNOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.n);
		command.execute(); 
		assertEquals(2, variable.n);
	}
	@Test
	//TODO refactor commands return integer
	public void verifyCommandRetrievesResult() throws Exception
	{
		command.execute(); 
		assertEquals(2, command.getResult(), .001); 
	}
}

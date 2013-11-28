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
		command = new NCommand(variable); 
	}
	@Test
	public void verifyCommandInvokesNOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.n);
		command.execute(); 
		assertEquals(2, variable.n);
	}
	@Test
	//TODO refactor to take integer
	public void verifyCommandRetrievesResult() throws Exception
	{
		command.execute(); 
		assertEquals(2, command.getResult(), .001); 
	}
	@Test
	public void verifyNCommandExplainsItself() throws Exception
	{
		assertEquals("Count the number of scores of X, giving n.", command.explain()); 
	}
}

package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MeanCommandTest
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
		command = new MeanCommand(variable); 
	}
	@Test
	public void verifyCommandInvokesMeanOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.mean, .001);
		command.execute(); 
		assertEquals(1.1, variable.mean, .001);
	}
	@Test
	//TODO refactor to take integer
	public void verifyCommandRetrievesResult() throws Exception
	{
		command.execute(); 
		assertEquals(1.1, command.getResult(), .001); 
	}
	
}

package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SumCommandTest
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
		command = new SumCommand(variable); 
	}
	@Test
	public void verifyCommandInvokesSumOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.sum, .001);
		command.execute(); 
		assertEquals(2.2, variable.sum, .001);
	}
	@Test
	public void verifySumCommandExplainsItself() throws Exception
	{
		assertEquals("Add all the scores of variable X to create the Sum.", command.explain()); 
	}
	//TODO test for null variable
}

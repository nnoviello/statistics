package org.resres.stats;

import static org.junit.Assert.*;

import java.util.List;

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
		command = new MeanCommand(variable, true); 
	}
	@Test
	public void verifyCommandInvokesMeanOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.mean, .001);
		command.execute(); 
		assertEquals(1.1, variable.mean, .001);
	}
	@Test
	public void verifyCommandRetrievesResult() throws Exception
	{
		command.execute(); 
		assertEquals(1.1, command.getResult(), .001); 
	}
	@Test
	public void verifyMeanInvokesTwoSubcommands() throws Exception
	{
		List<Command> subcommands = command.getSubcommands(); 
		assertTrue(subcommands.get(0) instanceof NCommand); 
		assertTrue(subcommands.get(1) instanceof SumCommand); 
		assertEquals(2, subcommands.size()); 
	}
	
}

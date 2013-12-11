package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeviationCommandTest
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
		command = new DeviationCommand(null, variable, 0, true); 
	}
	@Test
	public void verifyCommandRetrievesCorrespondingDeviationFromTargetVariable() throws Exception
	{
		assertEquals(0, variable.deviations.size()); 
		command.execute(); 
		assertEquals(0.1, variable.deviations.get(0), .001);
	}
	@Test
	public void verifyCommandRetrievesScoreValueJustAddedAsResult() throws Exception
	{
		assertNull("null prior to execution", command.getResult()); 
		command.execute(); 
		assertEquals(0.1, command.getResult(), .001); 
	}

}

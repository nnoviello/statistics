package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquaredDeviationCommandTest
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
		command = new SquaredDeviationCommand(null, variable, 0, true); 
	}
	@Test
	public void verifyCommandRetrievesCorrespondingSquaredDeviationFromTargetVariable() throws Exception
	{
		assertEquals(0, variable.squaredDeviations.size()); 
		command.execute(); 
		assertEquals(0.01, variable.squaredDeviations.get(0), .001);
	}
	@Test
	public void verifyCommandRetrievesSquaredDeviationForScoreAsResult() throws Exception
	{
		assertNull("null prior to execution", command.getResult()); 
		command.execute(); 
		assertEquals(0.01, command.getResult(), .001); 
	}


}

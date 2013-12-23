package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddScoreStepTest
{
	private Variable variable;
	private Step step;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 	
		variable.setName("X"); 
		step = new AddScoreStep(null, variable, 1.2, 0, true); 
	}
	@Test
	public void verifyStepInvokesAddScoreOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.getFrequency(1.2));
		step.execute(); 
		assertEquals(1, variable.getFrequency(1.2));
	}
	@Test
	public void verifyStepRetrievesScoreValueJustAddedAsResult() throws Exception
	{
		assertNull("null prior to execution", step.getResult()); 
		step.execute(); 
		assertEquals("score steps just return value of this score",1.2, step.getResult(), .001); 
	}

}

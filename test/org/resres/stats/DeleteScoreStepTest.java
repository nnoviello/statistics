package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeleteScoreStepTest
{

	private Variable variable;
	private Step step;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 	
		variable.setName("X"); 
		step = new AddScoreStep(null, variable, 1.2, 0, true); 
		step.execute(); 
		step = new AddScoreStep(null, variable, 1.0, 1, true); 
		step.execute(); 
		step = new DeleteScoreStep(null, variable, 0, true); 
	}
	@Test
	public void verifyCommandInvokesDeleteScoreOnTargetVariable() throws Exception
	{
		assertEquals(2, variable.getN());
		step.execute(); 
		assertEquals(1, variable.getN());
		assertEquals(1, variable.getFrequency(1.0));
	}
	@Test
	public void verifyCommandRetrievesScoreValueJustDeletedAsResult() throws Exception
	{
		assertNull(step.getResult()); 
		step.execute(); 
		assertEquals("score steps just show the affected score.",1.2, step.getResult(), .001); 
	}

}

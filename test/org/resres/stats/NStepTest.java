package org.resres.stats;

import static org.junit.Assert.*;

import org.grayleaves.problem.Step;
import org.junit.Before;
import org.junit.Test;

public class NStepTest
{
	private Variable variable;
	private Step step;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 	
		variable.setName("X"); 
		variable.addScore(1.2); 
		variable.addScore(1.0); 
		step = new NStep(null, variable, true); 
	}
	@Test
	public void verifyStepInvokesNOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.n);
		step.execute(); 
		assertEquals(2, variable.n);
	}
	@Test
	public void verifyStepRetrievesResult() throws Exception
	{
		assertNull(step.getResult()); 
		step.execute(); 
		assertEquals(2, step.getResult(), .001); 
	}
}

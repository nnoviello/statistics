package org.resres.stats;

import static org.junit.Assert.*;

import org.grayleaves.problem.Step;
import org.junit.Before;
import org.junit.Test;

public class SumOfScoresStepTest
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
		step = new SumOfScoresStep(null, variable, true); 
	}
	@Test
	public void verifyStepInvokesSumOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.sumOfScores, .001);
		step.execute(); 
		assertEquals(2.2, variable.sumOfScores, .001);
	}
	@Test
	public void verifyStepRetrievesResult() throws Exception
	{
		assertNull(step.getResult()); 
		step.execute(); 
		assertEquals(2.2, step.getResult(), .001); 
	}
	@Test(expected=IllegalArgumentException.class)
	public void verifyThrowsIfInitializedWithNull() throws Exception
	{
		step = new SumOfScoresStep(null, null, true); 
	}
}

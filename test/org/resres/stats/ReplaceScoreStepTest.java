package org.resres.stats;

import static org.junit.Assert.*;

import org.grayleaves.problem.Step;
import org.junit.Before;
import org.junit.Test;

public class ReplaceScoreStepTest
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
		step = new ReplaceScoreStep(null, variable, 1.4, 0, true); 
	}
	@Test
	public void verifyStepInvokesAddScoreOnTargetVariable() throws Exception
	{
		assertEquals(1.2, variable.getScores().get(0), .001);
		step.execute(); 
		assertEquals(1.4, variable.getScores().get(0), .001);
		assertEquals(1, variable.getScores().size());
	}
	@Test
	public void verifyStepRetrievesScoreValueJustReplacedAsResult() throws Exception
	{
		assertNull("null prior to execution", step.getResult()); 
		step.execute(); 
		assertEquals("score steps just return value of this score",1.4, step.getResult(), .001); 
	}

}

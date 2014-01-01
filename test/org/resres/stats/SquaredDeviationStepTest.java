package org.resres.stats;

import static org.junit.Assert.*;

import org.grayleaves.problem.Step;
import org.junit.Before;
import org.junit.Test;

public class SquaredDeviationStepTest
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
		step = new SquaredDeviationStep(null, variable, 0, true); 
	}
	@Test
	public void verifyStepRetrievesCorrespondingSquaredDeviationFromTargetVariable() throws Exception
	{
		assertEquals(0, variable.squaredDeviations.size()); 
		step.execute(); 
		assertEquals(0.01, variable.squaredDeviations.get(0), .001);
	}
	@Test
	public void verifyStepRetrievesSquaredDeviationForScoreAsResult() throws Exception
	{
		assertNull("null prior to execution", step.getResult()); 
		step.execute(); 
		assertEquals(0.01, step.getResult(), .001); 
	}


}

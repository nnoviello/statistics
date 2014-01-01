package org.resres.stats;

import static org.junit.Assert.*;

import org.grayleaves.problem.Step;
import org.junit.Before;
import org.junit.Test;

public class DeviationStepTest
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
		step = new DeviationStep(null, variable, 0, true); 
	}
	@Test
	public void verifyCommandRetrievesCorrespondingDeviationFromTargetVariable() throws Exception
	{
		assertEquals(0, variable.deviations.size()); 
		step.execute(); 
		assertEquals(0.1, variable.deviations.get(0), .001);
	}
	@Test
	public void verifyCommandRetrievesScoreValueJustAddedAsResult() throws Exception
	{
		assertNull("null prior to execution", step.getResult()); 
		step.execute(); 
		assertEquals(0.1, step.getResult(), .001); 
	}

}

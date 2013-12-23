package org.resres.stats;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MeanStepTest
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
		step = new MeanStep(null, variable, true); 
	}
	@Test
	public void verifyStepInvokesMeanOnTargetVariable() throws Exception
	{
		assertEquals(0, variable.mean, .001);
		step.execute(); 
		assertEquals(1.1, variable.mean, .001);
	}
	@Test
	public void verifyStepRetrievesResult() throws Exception
	{
		assertNull(step.getResult()); 
		step.execute(); 
		assertEquals(1.1, step.getResult(), .001); 
	}
	@Test
	public void verifyMeanInvokesTwoLittleSteps() throws Exception
	{
		List<Step> littleSteps = step.getLittleSteps(); 
		assertTrue(littleSteps.get(0) instanceof NStep); 
		assertTrue(littleSteps.get(1) instanceof SumOfScoresStep); 
		assertEquals(2, littleSteps.size()); 
	}
	
}

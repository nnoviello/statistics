package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.grayleaves.problem.StepEnum;
import org.grayleaves.problem.StepSequence;
import org.junit.Test;

public class StepSequenceTest
{

	private StepSequence stepSequence;

	@Test
	public void verifyCreatesId() throws Exception
	{
		stepSequence = new StepSequence(StepEnum.TESTING_STEP);
		assertEquals("testingStep", stepSequence.getId()); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP, "0");
		assertEquals("optionally, add a suffix for uniqueness",
				"testingStep0", stepSequence.getId()); 
		// how do we get the right parameters for the StepFactory? 
		// list of steps to enable.  
		// StatisticsController has a Teacher
		// 
	}
}

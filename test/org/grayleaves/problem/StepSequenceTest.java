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
		// list of steps to enable.  
	}
	@Test
	public void verifyTracksIndexWhenOneOfMultiple() throws Exception
	{
		stepSequence = new StepSequence(StepEnum.TESTING_STEP);
		stepSequence.setIndex(3); 
		assertEquals(3, stepSequence.getIndex()); 
	}
	@Test
	public void verifyUpdatedWithStepOnceCreated() throws Exception
	{
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0");
		Step step = new TestingStep(null, null, 0, true); 
		step.execute(); 
		stepSequence.setStep(step); 
	}
	@Test
	public void verifyBuildsInterfaceUpdate() throws Exception
	{	
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0");
		stepSequence.addInterfaceUpdate(stepSequence, VisibilityEnum.ENABLED, true); 
		// stepSequenceId classChange stringValueChange/doubleValueChange
	}
}

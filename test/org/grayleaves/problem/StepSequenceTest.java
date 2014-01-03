package org.grayleaves.problem;

import static org.junit.Assert.*;

import java.util.Map;

import org.grayleaves.problem.StepEnum;
import org.grayleaves.problem.StepSequence;
import org.junit.Before;
import org.junit.Test;

public class StepSequenceTest
{

	private StepSequence stepSequence;
	private Problem problem;

	@Before
	public void setUp() throws Exception
	{
		problem = new TestingProblem(null); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0", problem);
		problem.addStepSequence(stepSequence); 
	}
	
	@Test
	public void verifyCreatesId() throws Exception
	{
		stepSequence = new StepSequence(StepEnum.TESTING_STEP, problem);
		assertEquals("testingStep", stepSequence.getId()); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP, "0", problem);
		assertEquals("optionally, add a suffix for uniqueness",
				"testingStep0", stepSequence.getId()); 
	}
	@Test
	public void verifyTracksIndexWhenOneOfMultiple() throws Exception
	{
		stepSequence = new StepSequence(StepEnum.TESTING_STEP, problem);
		stepSequence.setIndex(3); 
		assertEquals(3, stepSequence.getIndex()); 
	}
	@Test
	public void verifyBuildsVisibilityOnlyInterfaceUpdate() throws Exception
	{	
		stepSequence.addVisibilityOnlyInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 

		TestingStep step = new TestingStep(null, "1", 0, true); 
		step.execute(); 
		stepSequence.updateStep(step); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue(interfaceUpdate instanceof VisibilityOnlyInterfaceUpdate); 
		assertEquals("testingStep1", ((VisibilityOnlyInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals("enabled",((VisibilityOnlyInterfaceUpdate) interfaceUpdate).visibility);  
		// stepSequenceId classChange stringValueChange/doubleValueChange explanation
	}
	@Test
	public void verifySecondExecutionOfVisibilityOnlyInterfaceUpdateReturnsNullInterfaceUpdate() throws Exception
	{
		stepSequence.addVisibilityOnlyInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
		
		TestingStep step = new TestingStep(null, "1", 0, true); 
		step.execute(); 
		stepSequence.updateStep(step); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue(interfaceUpdate instanceof VisibilityOnlyInterfaceUpdate); 
		
		TestingStep step2 = new TestingStep(null, "2", 0, true); 
		step2.execute(); 
		stepSequence.updateStep(step2); 
		interfaceUpdateMap = stepSequence.execute(); 
		interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue("although we executed a second time, visibility changes have already been invoked," +
				"so there is no interfaceUpdate",interfaceUpdate instanceof NullInterfaceUpdate);
	}
	@Test
	public void verifyBuildsVisibilityAndDataInterfaceUpdate() throws Exception
	{	
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED, "testingStep0"); 
		
		TestingStep step = new TestingStep(null, "2.1", 0, true); 
		step.execute(); 
		stepSequence.updateStep(step); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue(interfaceUpdate instanceof VisibilityAndDataInterfaceUpdate); 
		assertEquals("testingStep1", ((VisibilityAndDataInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals("enabled",((VisibilityAndDataInterfaceUpdate) interfaceUpdate).visibility);  
		assertEquals(2.1,((VisibilityAndDataInterfaceUpdate) interfaceUpdate).data, .001);  
		assertEquals("explanation of data step",((VisibilityAndDataInterfaceUpdate) interfaceUpdate).explanation);  
		//  explanation
	}
	@Test
	public void verifyBuildsDataOnlyInterfaceUpdateIfThisIsSecondExecution() throws Exception
	{	
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED, "testingStep0"); 
		
		TestingStep step = new TestingStep(null, "2.1", 0, true); 
		step.execute(); 
		stepSequence.updateStep(step); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue(interfaceUpdate instanceof VisibilityAndDataInterfaceUpdate); 
		assertEquals("testingStep1", ((VisibilityAndDataInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals("enabled",((VisibilityAndDataInterfaceUpdate) interfaceUpdate).visibility);  
		assertEquals(2.1,((VisibilityAndDataInterfaceUpdate) interfaceUpdate).data, .001);  
		assertEquals("explanation of data step",((VisibilityAndDataInterfaceUpdate) interfaceUpdate).explanation);  

		TestingStep step2 = new TestingStep(null, "3.2", 0, true); 
		step2.execute(); 
		stepSequence.updateStep(step2); 
		interfaceUpdateMap = stepSequence.execute(); 
		interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue("executed a second time, no visibility changes, but data is updated",
				interfaceUpdate instanceof DataOnlyInterfaceUpdate);
		assertEquals("testingStep1", ((DataOnlyInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals(3.2,((DataOnlyInterfaceUpdate) interfaceUpdate).data, .001);  
		assertEquals("explanation of data step",((DataOnlyInterfaceUpdate) interfaceUpdate).explanation);  
	}
	@Test
	public void verifyThrowsIfDataStepSequenceIdDoesntMatchExistingStepSequences() throws Exception
	{
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED, "nonExistentTestingStep0"); 
		try 
		{
			@SuppressWarnings("unused")
			Step dataStep = stepSequence.getStepFromStepSequence("nonExistentTestingStep0", "testingStep1");  
			fail("should throw"); 
		}
		catch (ProblemException e)
		{
			assertEquals(StepSequence.ATTEMPTED_INTERFACE_UPDATE+"testingStep1"+StepSequence.DATA_STEP_SEQUENCE_ID_NOT_FOUND+"nonExistentTestingStep0", e.getMessage()); 
		}
	}
	@Test
	public void verifyThrowsIfDataStepSequenceDoesNotHaveStep() throws Exception
	{
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED, "testingStep0"); 
		try 
		{
			@SuppressWarnings("unused")
			Step dataStep = stepSequence.getStepFromStepSequence("testingStep0", "testingStep1");  
			fail("should throw"); 
		}
		catch (ProblemException e)
		{
			assertEquals(StepSequence.ATTEMPTED_INTERFACE_UPDATE+"testingStep1"+StepSequence.DATA_STEP_SEQUENCE+"testingStep0"+StepSequence.HAS_NO_STEP, e.getMessage()); 
		}
	}
	@Test
	public void verifyThrowsIfDataStepSequenceHasStepButStepHasNotExecuted() throws Exception
	{
		// not sure this will ever happen 
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED, "testingStep0"); 
		TestingStep step = new TestingStep(null, "2.1", 0, true); 
//		step.execute(); 
		stepSequence.updateStep(step); 
		try 
		{
			@SuppressWarnings("unused")
			Step dataStep = stepSequence.getStepFromStepSequence("testingStep0", "testingStep1");  
			fail("should throw"); 
		}
		catch (ProblemException e)
		{
			assertEquals(StepSequence.ATTEMPTED_INTERFACE_UPDATE+"testingStep1"+StepSequence.DATA_STEP_SEQUENCE+"testingStep0"+StepSequence.STEP_HAS_NOT_YET_EXECUTED, e.getMessage()); 
		}
	}
}

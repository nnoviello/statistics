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
	public void verifySortsByOrder() throws Exception
	{
		//TODO revisit need for this
		stepSequence = new StepSequence(StepEnum.TESTING_STEP, problem);
		stepSequence.setOrder(3); 
		
	}
	@Test
	public void verifyBuildsVisibilityOnlyInterfaceUpdate() throws Exception
	{	
		stepSequence.addVisibilityOnlyInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence, "1"); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		checkVisibilityInterface(interfaceUpdateMap, "testingStep1");  
		// stepSequenceId classChange stringValueChange/doubleValueChange explanation
	}

	protected void checkVisibilityInterface(Map<String, InterfaceUpdate> interfaceUpdateMap, String stepSequenceId)
	{
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get(stepSequenceId); 
		assertTrue(interfaceUpdate instanceof VisibilityOnlyInterfaceUpdate); 
		assertEquals(stepSequenceId, ((VisibilityOnlyInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals("enable",((VisibilityOnlyInterfaceUpdate) interfaceUpdate).visibility);
	}
	protected void updateWithTestingStep(StepSequence stepSequence, String field)
	{
		TestingStep step = new TestingStep(null, field, 0, true); 
		step.execute(); 
		stepSequence.updateStep(step);
	}
	@Test
	public void verifySecondExecutionOfVisibilityOnlyInterfaceUpdateReturnsNullInterfaceUpdate() throws Exception
	{
		stepSequence.addVisibilityOnlyInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence, "1");
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue(interfaceUpdate instanceof VisibilityOnlyInterfaceUpdate); 
		updateWithTestingStep(stepSequence, "2"); 
		interfaceUpdateMap = stepSequence.execute(); 
		interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue("although we executed a second time, visibility changes have already been invoked," +
				"so there is no interfaceUpdate",interfaceUpdate instanceof NullInterfaceUpdate);
	}
	@Test
	public void verifyBuildsVisibilityAndDataInterfaceUpdate() throws Exception
	{	
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence, "2.1"); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		checkVisibilityAndDataInterface(interfaceUpdateMap, "testingStep1", 2.1);
	}
	protected void checkVisibilityAndDataInterface(
			Map<String, InterfaceUpdate> interfaceUpdateMap, String stepSequenceId, double data)
	{
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get(stepSequenceId); 
		assertTrue(interfaceUpdate instanceof VisibilityAndDataInterfaceUpdate); 
		assertEquals(stepSequenceId, ((VisibilityAndDataInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals("enable",((VisibilityAndDataInterfaceUpdate) interfaceUpdate).visibility);  
		assertEquals(data,((VisibilityAndDataInterfaceUpdate) interfaceUpdate).data, .001);  
		assertEquals("explanation of data step",((VisibilityAndDataInterfaceUpdate) interfaceUpdate).explanation);
	}
	@Test
	public void verifyBuildsDataOnlyInterfaceUpdateIfThisIsSecondExecution() throws Exception
	{	
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence, "2.1"); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.execute(); 
		checkVisibilityAndDataInterface(interfaceUpdateMap, "testingStep1", 2.1);  
		updateWithTestingStep(stepSequence, "3.2"); 
		interfaceUpdateMap = stepSequence.execute(); 
		InterfaceUpdate interfaceUpdate = interfaceUpdateMap.get("testingStep1"); 
		assertTrue("executed a second time, no visibility changes, but data is updated",
				interfaceUpdate instanceof DataOnlyInterfaceUpdate);
		assertEquals("testingStep1", ((DataOnlyInterfaceUpdate) interfaceUpdate).stepSequenceId);  
		assertEquals(3.2,((DataOnlyInterfaceUpdate) interfaceUpdate).data, .001);  
		assertEquals("explanation of data step",((DataOnlyInterfaceUpdate) interfaceUpdate).explanation);  
	}
	@Test
	public void verifyStepSequenceCanHaveLittleStepSequencesAddedManually() throws Exception
	{
		stepSequence.addVisibilityOnlyInterfaceUpdate("testingStep0-out", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence, "1"); 
		stepSequence.addLittleStepSequence(new StepSequence(StepEnum.TESTING_STEP, "0a", problem));
		stepSequence.getLittleStepSequences().get(0).addVisibilityOnlyInterfaceUpdate("testingStep0a-out", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence.getLittleStepSequences().get(0), "11"); 
		stepSequence.addLittleStepSequence(new StepSequence(StepEnum.TESTING_STEP, "0b", problem));
		stepSequence.getLittleStepSequences().get(1).addVisibilityOnlyInterfaceUpdate("testingStep0b-out", VisibilityEnum.ENABLED); 
		updateWithTestingStep(stepSequence.getLittleStepSequences().get(1), "12"); 
		assertEquals(2, stepSequence.getLittleStepSequences().size()); 
		Map<String, InterfaceUpdate> interfaceUpdateMap = stepSequence.executeStepSequences(); 
//		System.out.println(stepSequence.toString()); 
//		System.out.println(stepSequence.printInterfaceUpdates()); 
//		System.out.println(stepSequence.getLittleStepSequences().get(0).toString()); 
//		System.out.println(stepSequence.getLittleStepSequences().get(0).printInterfaceUpdates()); 
//		System.out.println(stepSequence.getLittleStepSequences().get(1).toString()); 
//		System.out.println(stepSequence.getLittleStepSequences().get(1).printInterfaceUpdates()); 
		checkVisibilityInterface(interfaceUpdateMap, "testingStep0-out");  
		checkVisibilityInterface(interfaceUpdateMap, "testingStep0a-out");  
		checkVisibilityInterface(interfaceUpdateMap, "testingStep0b-out");  
	}
	@Test
	public void verifyTreatsSelfAsSingleStepIfNotMarkedAsList() throws Exception
	{
		problem = new TestingProblem(null, 3); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0", problem);
		stepSequence.setList(false); 
		stepSequence.buildLittleStepSequences(); 
		assertEquals(0, stepSequence.getLittleStepSequences().size()); 
	}
	@Test
	public void verifyGenerateListCanHaveGeneratedInterfaceUpdates() throws Exception
	{
		problem = new TestingProblem(null, 3); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0", problem);
		stepSequence.setUpdateForTesting(new TestingUpdate(1, "testingStep"));
		stepSequence.setList(true); 
		stepSequence.buildLittleStepSequences(); 
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); // inherit on down
		assertEquals(3, stepSequence.getLittleStepSequences().size()); 
		assertEquals(StepEnum.TESTING_STEP, stepSequence.getLittleStepSequences().get(0).getStepEnum()); 
		assertEquals("awkward name...","testingStep20", stepSequence.getLittleStepSequences().get(2).getId()); 
		//TODO verifyGenerateListCanHaveGeneratedInterfaceUpdates
		//TODO verify Data interface updates add steps to problem's step map 
	}
	@Test
	public void verifyBuildsListOfStepsIfMarkedAsList() throws Exception
	{
		problem = new TestingProblem(null, 3); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0", problem);
		stepSequence.setUpdateForTesting(new TestingUpdate(1, "testingStep"));
		stepSequence.setList(true); 
		stepSequence.buildLittleStepSequences(); 
		assertEquals(3, stepSequence.getLittleStepSequences().size()); 
		assertEquals(StepEnum.TESTING_STEP, stepSequence.getLittleStepSequences().get(0).getStepEnum()); 
		assertEquals("awkward name...","testingStep20", stepSequence.getLittleStepSequences().get(2).getId()); 
	}
	@Test
	public void verifyLittleStepsCanBeCombinationOfManualAndGeneratedList() throws Exception
	{
		problem = new TestingProblem(null, 3); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP,"0", problem);
		stepSequence.setUpdateForTesting(new TestingUpdate(1, "testingStep"));
		stepSequence.setList(true); 
		stepSequence.buildLittleStepSequences(); 
		assertEquals(3, stepSequence.getLittleStepSequences().size()); 
		stepSequence.addLittleStepSequence(new StepSequence(StepEnum.TESTING_STEP, problem));
		assertEquals(4, stepSequence.getLittleStepSequences().size()); 
	}
//	@Test
	public void verifyBuildsLabelInterfaceUpdate() throws Exception
	{
		//TODO verifyBuildsLabelInterfaceUpdate
	}
	@Test
	public void verifyThrowsIfDataStepSequenceIdDoesntMatchExistingStepSequences() throws Exception
	{
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
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
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
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
		stepSequence.addVisibilityAndDataInterfaceUpdate("testingStep1", VisibilityEnum.ENABLED); 
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

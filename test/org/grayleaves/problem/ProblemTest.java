package org.grayleaves.problem;

import static org.junit.Assert.*;

import java.util.List;

import org.grayleaves.problem.AbstractProblem;
import org.grayleaves.problem.Problem;
import org.grayleaves.problem.StepEnum;
import org.grayleaves.problem.StepException;
import org.grayleaves.problem.StepSequence;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class ProblemTest
{
	private String jsonInput = "{\"updateJavaClass\":\"org.grayleaves.problem.TestingUpdate\",\"id\":5,\"field\":\"value1\",\"someArray\":[1,2,9],\"updateStep\":\"testingStep\"}";
	private String jsonInputMismatchedUpdateStep = "{\"updateJavaClass\":\"org.grayleaves.problem.TestingUpdate\",\"id\":5,\"field\":\"value1\",\"someArray\":[1,2,9],\"updateStep\":\"testingStepMissing\"}";
	private String jsonInputMismatchedStepSequenceId = "{\"updateJavaClass\":\"org.grayleaves.problem.TestingUpdate\",\"id\":99,\"field\":\"value1\",\"someArray\":[1,2,9],\"updateStep\":\"testingStep\"}";
	private Problem problem;
	private Teacher teacher;
	private StepSequence stepSequence;
	@Before
	public void setUp() throws Exception
	{
		teacher = new Teacher(); 
		problem = new TestingProblem(teacher); 
		stepSequence = new StepSequence(StepEnum.TESTING_STEP, "5");  
		problem.addStepSequence(stepSequence);
	}
	@Test
	public void verifyMultipleStepsGenerateUniqueIds() throws Exception
	{
		problem.addStepSequence(new StepSequence(StepEnum.TESTING_STEP));
		try 
		{
			problem.addStepSequence(new StepSequence(StepEnum.TESTING_STEP));
		}
		catch (StepException e)
		{
			assertEquals(AbstractProblem.DUPLICATE_STEP_SEQUENCE+StepEnum.TESTING_STEP.getName()+AbstractProblem.DUPLICATE_STEP_SEQUENCE_CONTINUATION, e.getMessage()); 
		}
	}
	@Test
	public void verifyAcceptsUpdatesInJsonFormat() throws Exception
	{
		TestingUpdate update = (TestingUpdate) problem.buildUpdate(jsonInput); 
		assertTrue(update instanceof TestingUpdate); 
		TestingUpdate testingUpdate = (TestingUpdate) update;  
		assertEquals(5, testingUpdate.id);
	}
	@Test
	public void verifyBuildsStepSequenceFollowedByStep() throws Exception
	{
		Update update = problem.buildUpdate(jsonInput); 
		assertEquals(stepSequence, problem.buildStepSequence(update)); 
		Step step = problem.buildStep(update); 
		assertTrue(step instanceof TestingStep); 
	}
	@Test
	public void verifyThrowsIfNoMatchingStepEnumForUpdateStepFromUpdate() throws Exception
	{
		Update update = problem.buildUpdate(jsonInputMismatchedUpdateStep); 
		try 
		{
			@SuppressWarnings("unused")
			StepEnum stepEnum = ((AbstractProblem) problem).buildStepEnum(update); 
			fail("should throw"); 
		} 
		catch (ProblemException e)
		{
			assertEquals(AbstractProblem.MISMATCHED_UPDATE_STEP_FROM_INPUT+"testingStepMissing"+AbstractProblem.MISMATCHED_UPDATE_POSSIBLE_CAUSES, e.getMessage()); 
		}
	}
	@Test
	public void verifyThrowsIfNoMatchingStepSequenceFromUpdate() throws Exception
	{
		Update update = problem.buildUpdate(jsonInputMismatchedStepSequenceId); 
		try
		{
			@SuppressWarnings("unused")
			StepSequence builtStepSequence = problem.buildStepSequence(update); 
			fail("should throw"); 
		}
		catch (ProblemException e)
		{
			assertEquals(AbstractProblem.MISMATCHED_STEP_SEQUENCE_ID_FROM_INPUT+"testingStep99"+AbstractProblem.MISMATCHED_STEP_SEQUENCE_ID_POSSIBLE_CAUSES, e.getMessage()); 
		}
	}
	@Test
	public void verifyCreatesAndExecutesStepFromJsonInput() throws Exception
	{
		problem.update(jsonInput); 
		assertTrue(((TestingProblem) problem).getTestingStep().hasExecuted());
	}
}

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
//	private String jsonInput = "{\"id\":0,\"htmlId\":\"field1\",\"beforeVisibility\":null,\"afterVisibility\":null,\"beforeValue\":\"value1\",\"afterValue\":\"1.2\",\"updateType\":\"addScore\",\"index\":0}";
//	private String jsonInputArray = "{\"id\":0,\"htmlId\":\"field1\",\"beforeVisibility\":[],\"afterVisibility\":null,\"beforeValue\":\"value1\",\"afterValue\":\"1.2\",\"updateType\":\"addScore\",\"index\":0}";
	private String jsonInput = "{\"updateClass\":\"org.resres.stats.controller.Update\",\"id\":5,\"field\":\"value1\",\"someArray\":[1,2,3],\"updateStep\":\"testingStep\"}";
	private Problem problem;
	@Before
	public void setUp() throws Exception
	{
		problem = new TestingProblem(); 
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
//		TestingUpdate update = problem. 
	}
//	public void updateProblem(String jsonUpdate)
//	{
//	Gson gson = new Gson();
//	StatisticsUpdate update = gson.fromJson(jsonUpdate, StatisticsUpdate.class); 
//	double score = Double.parseDouble(update.afterValue); 
//	Step step = StepFactory.buildStep(stepMap.get(update.updateType), null, variable, score, update.index, true); 
//	step.execute(); 

	@Test
	public void verifyBuildStep() throws Exception
	{
		assertTrue(problem.buildStep("testingStep") instanceof TestingStep); 
	}
	private class TestingProblem extends AbstractProblem
	{

		@Override
		public Step buildStep(String stepSequenceId)
		{
			return new TestingStep();
		}
		
	}
	private class TestingStep implements Step
	{

		@Override
		public void execute()
		{
		}

		@Override
		public String explain()
		{
			return null;
		}

		@Override
		public boolean explicitlyInvoked()
		{
			return false;
		}

		@Override
		public List<Step> getLittleSteps()
		{
			return null;
		}

		@Override
		public Double getResult()
		{
			return null;
		}
		
	}
}

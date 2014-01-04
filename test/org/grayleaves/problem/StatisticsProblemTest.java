package org.grayleaves.problem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.resres.stats.AddScoreStep;
import org.resres.stats.DeleteScoreStep;
import org.resres.stats.DeviationStep;
import org.resres.stats.MeanStep;
import org.resres.stats.NStep;
import org.resres.stats.ReplaceScoreStep;
import org.resres.stats.SquaredDeviationStep;
import org.resres.stats.SumOfScoresStep;
import org.resres.stats.SumOfSquaredDeviationsStep;

public class StatisticsProblemTest
{
	private Teacher teacher;
	private StatisticsProblem problem;
	private String jsonInput = "{\"updateJavaClass\":\"org.grayleaves.problem.StatisticsUpdate\",\"id\":0,\"htmlId\":\"addScore5\",\"beforeVisibility\":[],\"afterVisibility\":null,\"beforeValue\":\"value1\",\"afterValue\":\"1.2\",\"updateStep\":\"addScore\",\"index\":0}";
	private Map<String, StepEnum> stepMap;
	@Before
	public void setUp() throws Exception
	{
		teacher = new Teacher(); 
		problem = new StatisticsProblem(teacher); 
		problem.getVariable().addScore(1.1); // keep delete score happy
		StepSequence stepSequence = new StepSequence(StepEnum.ADD_SCORE, "5", problem);  
		stepSequence.setIndex(0); 
		assertEquals("addScore5", stepSequence.getId()); 
		problem.addStepSequence(stepSequence);
	}
	@Test
	public void verifyStepsBuiltForStatisticsProblemFromInput() throws Exception
	{
		Update update = problem.buildUpdate(jsonInput); //combine 
		StepSequence stepSequence = problem.buildStepSequence(update); 
		Step step = problem.buildStep(update, stepSequence); 
		assertTrue(step instanceof  AddScoreStep); 
		step.execute(); 
		assertEquals(1.2, step.getResult(), .001);
	}
	@Test
	public void verifyStepsBuiltForStatisticsProblem() throws Exception
	{
		assertTrue(step(StepEnum.DELETE_SCORE) instanceof DeleteScoreStep); 
		assertTrue(step(StepEnum.REPLACE_SCORE) instanceof ReplaceScoreStep); 
		assertTrue(step(StepEnum.N) instanceof NStep); 
		assertTrue(step(StepEnum.SUM_OF_SCORES) instanceof SumOfScoresStep); 
		assertTrue(step(StepEnum.MEAN) instanceof MeanStep); 
		assertTrue(step(StepEnum.DEVIATION) instanceof DeviationStep); 
		assertTrue(step(StepEnum.SQUARED_DEVIATION) instanceof SquaredDeviationStep); 
		assertTrue(step(StepEnum.SUM_OF_SQUARED_DEVIATIONS) instanceof SumOfSquaredDeviationsStep); 
//		assertTrue(step(StepEnum.VARIABLE_NAME) instanceof VariableNameStep); 
//		assertTrue(step(StepEnum.VARIANCE) instanceof VarianceStep); 
//		assertTrue(step(StepEnum.STANDARD_DEVIATION) instanceof StandardDeviationStep); 
	}
	protected Step step(StepEnum stepEnum) throws ProblemException
	{
		StatisticsUpdate update = new StatisticsUpdate(stepEnum.getName(), "2.0");  
		Step step = problem.buildStep(update, new StepSequence(stepEnum, problem)); 
		return step; 
	}
	@Test
	public void verifyAllStepsAreMapped() throws Exception
	{
		stepMap = problem.getStepMap(); 
		checkStepEnum(StepEnum.VARIABLE_NAME); 
		checkStepEnum(StepEnum.ADD_SCORE); 
		checkStepEnum(StepEnum.DELETE_SCORE); 
		checkStepEnum(StepEnum.REPLACE_SCORE); 
		checkStepEnum(StepEnum.DEVIATION); 
		checkStepEnum(StepEnum.MEAN); 
		checkStepEnum(StepEnum.N); 
		checkStepEnum(StepEnum.SQUARED_DEVIATION); 
		checkStepEnum(StepEnum.SUM_OF_SCORES); 
		checkStepEnum(StepEnum.SUM_OF_SQUARED_DEVIATIONS); 
		checkStepEnum(StepEnum.VARIANCE); 
		checkStepEnum(StepEnum.STANDARD_DEVIATION);

	}
	protected void checkStepEnum(StepEnum stepEnum)
	{
		assertEquals(stepEnum, stepMap.get(stepEnum.getName()));
	}
	@Test
	public void verifyProblemHasDefaultVariable() throws Exception
	{
		assertNotNull(problem.getVariable()); 
	}
//	assertEquals(stepSequence, problem.buildStepSequence(update)); 
//	assertTrue(step instanceof TestingStep); 
//	public void updateProblem(String jsonUpdate)
//	{
//	Gson gson = new Gson();
//	StatisticsUpdate update = gson.fromJson(jsonUpdate, StatisticsUpdate.class); 
//	double score = Double.parseDouble(update.afterValue); 
//	Step step = StepFactory.buildStep(stepMap.get(update.updateType), null, variable, score, update.index, true); 
//	step.execute(); 
//	StepSequence stepSequence = buildStepSequence(update); 
//	StepEnum stepEnum = getStepMap().get(update.getUpdateStep());
//	switch (stepEnum) // only one in this case, but there could be others.
//	{
//	case TESTING_STEP: return new TestingStep(teacher, ((TestingUpdate) update).field, stepSequence.getIndex(), true); 
//	default: return null; 
//	}

}

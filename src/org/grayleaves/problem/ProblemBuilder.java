package org.grayleaves.problem;

public class ProblemBuilder
{

	private static final String OUTPUT = "-output";
	private static final String LABEL = "-label";
	private Teacher teacher;

	public Problem buildProblem(ProblemEnum problemEnum) throws ProblemException
	{
		Problem problem = null;
		switch (problemEnum)
		{
		case BASIC_STATISTICS: problem = buildBasicStatisticsProblem(); 
		}
		return problem;
	}

	private Problem buildBasicStatisticsProblem() throws ProblemException
	{
		StatisticsProblem problem = new StatisticsProblem(teacher); 
		buildStepSequences(problem);
		return problem;
	}

	protected void buildStepSequences(StatisticsProblem problem) throws ProblemException
	{
		try
		{
			buildNameStepSequence(problem);
			buildScoreStepSequences(problem);
			buildSumOfScoresSequence(problem);
			buildNStepSequence(problem);
			buildMeanStepSequences(problem);
			buildDeviationsStepSequence(problem);
			buildSquaredDeviationsStepSequence(problem);
			buildSumOfSquaredDeviationsStepSequence(problem);
			buildVarianceStepSequence(problem);
			buildStandardDeviationStepSequence(problem);
		}
		catch (StepException e)
		{
			throw new ProblemException(e.getMessage()); 
		}
	}


	protected void buildNameStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = new StepSequence(StepEnum.VARIABLE_NAME, problem); // visibility changes? 
		problem.addStepSequence(stepSequence); 
	}
	private void buildScoreStepSequences(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = new StepSequence(StepEnum.SCORE, "0", problem); 
		stepSequence.setIndex(0); 
		updateButtonVisibility(stepSequence, StepEnum.SUM_OF_SCORES, VisibilityEnum.ENABLED);
		updateButtonVisibility(stepSequence, StepEnum.N, VisibilityEnum.DISABLED);
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.SCORE.getName()+"1", VisibilityEnum.ENABLED); 
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.SCORE.getName()+"1"+LABEL, VisibilityEnum.ENABLED); 
		problem.addStepSequence(stepSequence); 
		for (int i = 1; i < 10; i++)
		{
			makeNextScoreStepVisible(problem, i, i+1); 
		}
	}

	protected void makeNextScoreStepVisible(StatisticsProblem problem, int current, int next)
			throws StepException
	{
		StepSequence stepSequence;
		stepSequence = new StepSequence(StepEnum.SCORE, current+"", problem); 
		stepSequence.setIndex(current); 
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.SCORE.getName()+next, VisibilityEnum.ENABLED); 
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.SCORE.getName()+next+LABEL, VisibilityEnum.ENABLED); 
		problem.addStepSequence(stepSequence);
	}
	private void buildSumOfScoresSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = enableAndShowOutputForButton(problem, StepEnum.SUM_OF_SCORES);
		updateButtonVisibility(stepSequence, StepEnum.N, VisibilityEnum.ENABLED);
		updateButtonVisibility(stepSequence, StepEnum.MEAN, VisibilityEnum.DISABLED);
		problem.addStepSequence(stepSequence);
	}
	private void buildNStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = enableAndShowOutputForButton(problem, StepEnum.N);
		updateButtonVisibility(stepSequence, StepEnum.MEAN, VisibilityEnum.ENABLED);
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.DEVIATION.getName(), VisibilityEnum.DISABLED); 
		problem.addStepSequence(stepSequence);
	}
	private void buildMeanStepSequences(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = enableAndShowOutputForButton(problem, StepEnum.MEAN);
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.DEVIATION.getName(), VisibilityEnum.ENABLED);
		for (int i = 0; i < 10; i++)
		{
			makeDeviationOrSquaredDeviationOutputsDisabled(stepSequence, StepEnum.DEVIATION, i); 
		} 
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.SQUARED_DEVIATION.getName(), VisibilityEnum.DISABLED); 
		problem.addStepSequence(stepSequence);
	}
//problem.getVariable().getScores().size()
	private void makeDeviationOrSquaredDeviationOutputsDisabled(
			StepSequence stepSequence, StepEnum stepEnum, int current)
	{
		stepSequence.addVisibilityOnlyInterfaceUpdate(stepEnum.getName()+current+OUTPUT, VisibilityEnum.DISABLED); 
	}

	private void buildDeviationsStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = new StepSequence(StepEnum.DEVIATION, problem); 
		stepSequence.addVisibilityOnlyInterfaceUpdate(StepEnum.SQUARED_DEVIATION.getName(), VisibilityEnum.ENABLED);
		for (int i = 0; i < 10; i++)
		{
			makeDeviationOrSquaredDeviationVisibleAndPopulated(stepSequence, StepEnum.DEVIATION, i); 
			makeDeviationOrSquaredDeviationOutputsDisabled(stepSequence, StepEnum.SQUARED_DEVIATION, i); 
		} 
		updateButtonVisibility(stepSequence, StepEnum.SUM_OF_SQUARED_DEVIATIONS, VisibilityEnum.DISABLED);
		problem.addStepSequence(stepSequence);
	}

	protected void makeDeviationOrSquaredDeviationVisibleAndPopulated(
			StepSequence stepSequence, StepEnum stepEnum, int current)
	{
		stepSequence.addVisibilityAndDataInterfaceUpdate(stepEnum.getName()+current+OUTPUT, VisibilityEnum.ENABLED, stepEnum.getName()+current);
	}

	private void buildSquaredDeviationsStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = new StepSequence(StepEnum.SQUARED_DEVIATION, problem); 
		updateButtonVisibility(stepSequence, StepEnum.SUM_OF_SQUARED_DEVIATIONS, VisibilityEnum.ENABLED);
		for (int i = 0; i < 9; i++)
		{
			makeDeviationOrSquaredDeviationVisibleAndPopulated(stepSequence, StepEnum.SQUARED_DEVIATION, i); 
		} 
		updateButtonVisibility(stepSequence, StepEnum.VARIANCE, VisibilityEnum.DISABLED);
		problem.addStepSequence(stepSequence);
	}

	private void buildSumOfSquaredDeviationsStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = enableAndShowOutputForButton(problem, StepEnum.SUM_OF_SQUARED_DEVIATIONS);
		updateButtonVisibility(stepSequence, StepEnum.VARIANCE, VisibilityEnum.ENABLED);
		updateButtonVisibility(stepSequence, StepEnum.STANDARD_DEVIATION, VisibilityEnum.DISABLED);
		problem.addStepSequence(stepSequence);
	}

	private void buildVarianceStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = enableAndShowOutputForButton(problem, StepEnum.VARIANCE);
		updateButtonVisibility(stepSequence, StepEnum.STANDARD_DEVIATION, VisibilityEnum.ENABLED);
		problem.addStepSequence(stepSequence);
	}

	protected void updateButtonVisibility(StepSequence stepSequence, StepEnum stepEnum, VisibilityEnum visibilityEnum)
	{
		stepSequence.addVisibilityOnlyInterfaceUpdate(stepEnum.getName(), visibilityEnum);
		stepSequence.addVisibilityOnlyInterfaceUpdate(stepEnum.getName()+OUTPUT, visibilityEnum);
	}

	protected StepSequence enableAndShowOutputForButton(StatisticsProblem problem, StepEnum stepEnum)
	{
		StepSequence stepSequence = new StepSequence(stepEnum, problem); 
		stepSequence.addVisibilityAndDataInterfaceUpdate(stepEnum.getName()+OUTPUT, VisibilityEnum.ENABLED, stepEnum.getName());
		return stepSequence;
	}

	private void buildStandardDeviationStepSequence(StatisticsProblem problem) throws StepException
	{
		StepSequence stepSequence = enableAndShowOutputForButton(problem, StepEnum.STANDARD_DEVIATION);
		problem.addStepSequence(stepSequence);
	}
	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher; 
	}

}

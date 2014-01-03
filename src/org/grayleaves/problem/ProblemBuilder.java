package org.grayleaves.problem;

public class ProblemBuilder
{

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
		addEnumsToMap(problem); 
		buildStepSequences(problem);

		return problem;
	}

	protected void buildStepSequences(StatisticsProblem problem) throws ProblemException
	{
		try
		{
			problem.addStepSequence(buildNameStepSequence(problem));
			problem.addStepSequence(buildScoreStepSequences(problem));
			problem.addStepSequence(buildSumOfScoresSequence(problem));
			problem.addStepSequence(buildNStepSequence(problem));
			problem.addStepSequence(buildDeviationsStepSequence(problem));
			problem.addStepSequence(buildSquaredDeviationsStepSequence(problem));
			problem.addStepSequence(buildSumOfSquaredDeviationsStepSequence(problem));
			problem.addStepSequence(buildVarianceStepSequence(problem));
			problem.addStepSequence(buildStandardDeviationStepSequence(problem));
		}
		catch (StepException e)
		{
			throw new ProblemException(e.getMessage()); 
		}
	}

	protected StepSequence buildNameStepSequence(StatisticsProblem problem)
	{
		StepSequence stepSequence = new StepSequence(StepEnum.ADD_SCORE, "0", problem); 
		return stepSequence;
	}
	private StepSequence buildScoreStepSequences(StatisticsProblem problem)
	{
		return null;
	}
	private StepSequence buildSumOfScoresSequence(StatisticsProblem problem)
	{
		return null;
	}
	
	private StepSequence buildNStepSequence(StatisticsProblem problem)
	{
		return null;
	}

	private StepSequence buildDeviationsStepSequence(StatisticsProblem problem)
	{
		return null;
	}

	private StepSequence buildSquaredDeviationsStepSequence(
			StatisticsProblem problem)
	{
		return null;
	}

	private StepSequence buildSumOfSquaredDeviationsStepSequence(
			StatisticsProblem problem)
	{
		return null;
	}

	private StepSequence buildVarianceStepSequence(StatisticsProblem problem)
	{
		return null;
	}

	private StepSequence buildStandardDeviationStepSequence(
			StatisticsProblem problem)
	{
		return null;
	}


	protected void addEnumsToMap(StatisticsProblem problem)
	{
		problem.addStepMapEntry(StepEnum.VARIABLE_NAME); 
		problem.addStepMapEntry(StepEnum.ADD_SCORE); 
		problem.addStepMapEntry(StepEnum.DELETE_SCORE); 
		problem.addStepMapEntry(StepEnum.DEVIATION); 
		problem.addStepMapEntry(StepEnum.MEAN); 
		problem.addStepMapEntry(StepEnum.N); 
		problem.addStepMapEntry(StepEnum.SQUARED_DEVIATION); 
		problem.addStepMapEntry(StepEnum.SUM_OF_SCORES); 
		problem.addStepMapEntry(StepEnum.SUM_OF_SQUARED_DEVIATIONS); 
		problem.addStepMapEntry(StepEnum.VARIANCE); 
		problem.addStepMapEntry(StepEnum.STANDARD_DEVIATION);
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher; 
	}

}

package org.grayleaves.problem;


public class TestingProblem extends AbstractProblem
{

	private Teacher teacher;
	private TestingStep testingStep;

	public TestingProblem(Teacher teacher)
	{
		this.teacher = teacher; 
		getStepMap().put(StepEnum.TESTING_STEP.getName(), StepEnum.TESTING_STEP); 
	}

	@Override
	public Step buildStep(Update update, StepSequence stepSequence) throws ProblemException
	{
		StepEnum stepEnum = buildStepEnum(update);
		switch (stepEnum) // only one in this case, but there could be others.
		{
		case TESTING_STEP: return new TestingStep(teacher, ((TestingUpdate) update).field, stepSequence.getIndex(), true); 
		default: return null; 
		}
	}
	@Override
	public void update(String jsonInput) throws ProblemException
	{
		super.update(jsonInput);
	}
	protected TestingStep getTestingStep()
	{
		return testingStep;
	}

	@Override
	protected void tempPostExecution(Step step)
	{
		testingStep = (TestingStep) step; 
	}

}

package org.grayleaves.problem;

public class TestingProblem extends AbstractProblem
{

	private Teacher teacher;

	public TestingProblem(Teacher teacher)
	{
		this.teacher = teacher; 
		getStepMap().put(StepEnum.TESTING_STEP.getName(), StepEnum.TESTING_STEP); 
	}

	@Override
	public Step buildStep(Update update) throws ProblemException
	{
		StepSequence stepSequence = buildStepSequence(update); 
		StepEnum stepEnum = buildStepEnum(update);
		switch (stepEnum) // only one in this case, but there could be others.
		{
		case TESTING_STEP: return new TestingStep(teacher, ((TestingUpdate) update).field, stepSequence.getIndex(), true); 
		default: return null; 
		}
	}
}

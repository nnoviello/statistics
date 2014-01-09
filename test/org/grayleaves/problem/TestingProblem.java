package org.grayleaves.problem;

import java.util.Map;


public class TestingProblem extends AbstractProblem
{

	private Teacher teacher;
	private TestingStep testingStep;
	private int steps;

	public TestingProblem(Teacher teacher)
	{
		this(teacher, 1); 
	}

	public TestingProblem(Teacher teacher, int steps)
	{
		this.teacher = teacher; 
		this.steps = steps; 
		addStepMapEntry(StepEnum.TESTING_STEP);
//		getStepMap().put(StepEnum.TESTING_STEP.getName(), StepEnum.TESTING_STEP); 
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
	public Map<String, InterfaceUpdate> update(String jsonInput) throws ProblemException
	{
		return super.update(jsonInput);
	}
	protected TestingStep getTestingStep()
	{
		return testingStep;
	}

	@Override
	public int getNumberOfSteps(StepSequence stepSequence)
	{
		return steps;
	}
}

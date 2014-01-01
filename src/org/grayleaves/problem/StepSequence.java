package org.grayleaves.problem;

public class StepSequence
{

	private String id;
	private StepEnum stepEnum;

	public StepSequence(StepEnum stepEnum)
	{
		this(stepEnum,"");
	}

	public StepSequence(StepEnum stepEnum, String suffix)
	{
		this.stepEnum = stepEnum; 
		id = stepEnum.getName()+suffix; 
	}

	public String getId()
	{
		return id;
	}

	public StepEnum getStepEnum()
	{
		return stepEnum;
	}

}

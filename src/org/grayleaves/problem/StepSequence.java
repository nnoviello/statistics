package org.grayleaves.problem;

public class StepSequence
{

	private String id;
	private StepEnum stepEnum;
	private int index;
	private Step step;

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
	public void setIndex(int index)
	{
		this.index = index; 
	}
	public int getIndex()
	{
		return index;
	}

	public void addInterfaceUpdate(StepSequence stepSequence, VisibilityEnum visibility,
			boolean updateResult)
	{
	}

	public void setStep(Step step)
	{
		this.step = step; 
	}

}

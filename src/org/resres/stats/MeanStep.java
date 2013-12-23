package org.resres.stats;

public class MeanStep extends AbstractStep implements Step
{

	private static final String EXPLANATION = "Divide sum of scores by n of ";
	private static final String RESULT = ", giving the mean.";

	public MeanStep(Teacher teacher, Variable variable, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
	}
	@Override
	public void execute()
	{
		super.execute(); 
		variable.calculateMean(); 
	}

	@Override
	public Double getResult()
	{
		return (executed) ? variable.getMean() : null;
	}
	@Override
	protected void buildLittleSteps()
	{
		littleSteps.add(new NStep(teacher, variable, false)); 
		littleSteps.add(new SumOfScoresStep(teacher, variable, false)); 
	}
}

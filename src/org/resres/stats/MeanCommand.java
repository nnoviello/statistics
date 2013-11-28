package org.resres.stats;

public class MeanCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Divides sum of scores by n of ";
	private static final String RESULT = ", giving the mean.";

	public MeanCommand(Variable variable)
	{
		super(variable, EXPLANATION, RESULT);
	}

	@Override
	public void execute()
	{
		variable.calculateN();
		variable.calculateSum();
		variable.calculateMean(); 
	}

	@Override
	public double getResult()
	{
		return variable.getMean();
	}

}

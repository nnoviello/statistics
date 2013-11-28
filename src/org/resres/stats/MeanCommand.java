package org.resres.stats;

public class MeanCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Divides sum of scores by n of ";
	private static final String RESULT = ", giving the mean.";

	public MeanCommand(Teacher teacher, Variable variable)
	{
		super(teacher, variable, EXPLANATION, RESULT); 
	}
	public MeanCommand(Variable variable)
	{
		this(null, variable);
	}

	@Override
	public void execute()
	{
		variable.calculateN();
		variable.calculateSum();
		variable.calculateMean(); 
		super.execute(); 
	}

	@Override
	public double getResult()
	{
		return variable.getMean();
	}

}

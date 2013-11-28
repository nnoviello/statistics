package org.resres.stats;

public class NCommand extends AbstractCommand implements Command
{
	private static final String EXPLANATION ="Count the number of scores of ";
	private static final String RESULT = ", giving n.";

	public NCommand(Variable variable)
	{
		super(variable, EXPLANATION, RESULT);
	}

	@Override
	public void execute()
	{
		variable.calculateN(); 
	}

	@Override
	public double getResult()
	{
		return (double) variable.getN();
	}

}

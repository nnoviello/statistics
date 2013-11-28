package org.resres.stats;

public class SumCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Add all the scores of variable ";
	private static final String RESULT = ", giving the sum of scores.";

	public SumCommand(Variable variable)
	{
		super(variable, EXPLANATION, RESULT);
	}

	@Override
	public void execute()
	{
		variable.calculateSum();
	}
	@Override
	public double getResult()
	{
		return variable.getSum();
	}

}

package org.resres.stats;

public class SumCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Add all the scores of variable ";
	private static final String RESULT = ", giving the sum of scores.";

	public SumCommand(Teacher teacher, Variable variable)
	{
		super(teacher, variable, EXPLANATION, RESULT); 
	}
	public SumCommand(Variable variable)
	{
		this(null, variable);
	}

	@Override
	public void execute()
	{
		variable.calculateSum();
		super.execute(); 
	}
	@Override
	public double getResult()
	{
		return variable.getSum();
	}

}

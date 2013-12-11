package org.resres.stats;

public class NCommand extends AbstractCommand implements Command
{
	private static final String EXPLANATION ="Count the number of scores of ";
	private static final String RESULT = ", giving n.";

	public NCommand(Teacher teacher, Variable variable, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
	}
	@Override
	public void execute()
	{
		variable.calculateN(); 
		super.execute(); 
	}

	@Override
	public Double getResult()
	{
		return (executed) ? (double) variable.getN() : null;
	}

}

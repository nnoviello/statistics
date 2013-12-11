package org.resres.stats;


public class DeviationCommand extends AbstractCommand implements Command
{
	private static final String EXPLANATION ="Subtract the mean from a score on variable ";
	private static final String RESULT =", giving the deviation for that score.";
	private int index;
	private Double deviation;

	public DeviationCommand(Teacher teacher, Variable variable, int index, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
		this.index = index; 
	}
	@Override
	public void execute()
	{
		deviation = variable.getDeviations().get(index); 
		super.execute();
	}
	@Override
	public Double getResult()
	{
		return (executed) ? deviation : null;
	}

}

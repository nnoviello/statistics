package org.resres.stats;

public class SumOfSquaredDeviationsCommand extends AbstractCommand implements Command
{
	private static final String EXPLANATION = "Add all the squared deviations of variable ";
	private static final String RESULT = ", giving the sum of squared deviations.";
	private double sumOfSquaredDeviations;

	public SumOfSquaredDeviationsCommand(Teacher teacher, Variable variable, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
	}
	@Override
	public void execute()
	{
		sumOfSquaredDeviations = variable.getSumOfSquaredDeviations(); 
		super.execute();
	}
	
	@Override
	public Double getResult()
	{
		return (executed) ? sumOfSquaredDeviations : null;
	}

}

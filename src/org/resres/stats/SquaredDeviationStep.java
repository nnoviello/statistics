package org.resres.stats;

public class SquaredDeviationStep extends AbstractStep implements Step
{
	private static final String EXPLANATION ="Square the deviation for a score on variable ";
	private static final String RESULT =", giving the squared deviation for that score.";
	private int index;
	private Double squaredDeviation;

	public SquaredDeviationStep(Teacher teacher, Variable variable, int index,
			boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
		this.index = index; 
	}
	@Override
	public void execute()
	{
		squaredDeviation = variable.getSquaredDeviations().get(index);
		super.execute();
	}
	@Override
	public Double getResult()
	{
		return (executed) ? squaredDeviation : null;
	}

}

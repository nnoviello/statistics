package org.resres.stats;

import org.grayleaves.problem.Step;
import org.grayleaves.problem.Teacher;


public class DeviationStep extends AbstractStep implements Step
{
	private static final String EXPLANATION ="Subtract the mean from a score on variable ";
	private static final String RESULT =", giving the deviation for that score.";
	private int index;
	private Double deviation;

	public DeviationStep(Teacher teacher, Variable variable, int index, boolean invoked)
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

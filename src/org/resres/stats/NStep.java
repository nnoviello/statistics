package org.resres.stats;

import org.grayleaves.problem.Step;
import org.grayleaves.problem.Teacher;

public class NStep extends AbstractStep implements Step
{
	private static final String EXPLANATION ="Count the number of scores of ";
	private static final String RESULT = ", giving n.";

	public NStep(Teacher teacher, Variable variable, boolean invoked)
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

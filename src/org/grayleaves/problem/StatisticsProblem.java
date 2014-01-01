package org.grayleaves.problem;

import org.resres.stats.Variable;

public class StatisticsProblem extends AbstractProblem implements Problem
{

	private Teacher teacher;
	private Variable variable;

	public StatisticsProblem(Teacher teacher)
	{
		this.teacher = teacher; 
	}

	@Override
	public Step buildStep(String stepSequenceId)
	{
		return null;
	}

	public void setVariable(Variable variable)
	{
		this.variable = variable; 
	}

}

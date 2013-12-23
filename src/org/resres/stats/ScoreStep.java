package org.resres.stats;

public class ScoreStep extends AbstractStep
{

	protected static final String RESULT = ".";
	protected int index;
	protected double score;

	public ScoreStep(Teacher teacher, Variable variable, String explanation,
			String result, boolean invoked, double score, int index)
	{
		super(teacher, variable, explanation, result, invoked);
		this.index = index; 
		this.score = score; 
	}

	@Override
	public Double getResult()
	{
		return (executed) ? score : null; 
	}

}

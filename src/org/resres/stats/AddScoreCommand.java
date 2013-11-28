package org.resres.stats;

public class AddScoreCommand extends AbstractCommand implements Command
{
	private static final String EXPLANATION ="Add a score to variable ";
	private static final String RESULT = ".";
	private double score;

	public AddScoreCommand(Variable variable)
	{
		super(variable, EXPLANATION, RESULT);
	}
	public AddScoreCommand(Teacher teacher, Variable variable, double score)
	{
		super(teacher, variable, EXPLANATION, RESULT); 
		this.score = score; 
	}
	public AddScoreCommand(Variable variable, double score)
	{
		this(null, variable, score);
	}
	@Override
	public void execute()
	{
		variable.addScore(score);
		super.execute(); 
	}
	@Override
	public double getResult()
	{
		return (double) variable.getFrequency(score);
	}
}

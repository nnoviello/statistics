package org.resres.stats;

public class AddScoreCommand extends AbstractCommand implements Command
{
	private static final String EXPLANATION ="Add a score to variable ";
	private static final String RESULT = ".";
	private double score;

	public AddScoreCommand(Variable variable, boolean invoked)
	{
		super(variable, EXPLANATION, RESULT, invoked);
	}
	public AddScoreCommand(Teacher teacher, Variable variable, double score, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
		this.score = score; 
	}
	public AddScoreCommand(Variable variable, double score, boolean invoked)
	{
		this(null, variable, score, invoked);
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

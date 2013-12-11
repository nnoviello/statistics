package org.resres.stats;

public class AddScoreCommand extends ScoreCommand implements Command
{
	private static final String EXPLANATION ="Add a score to variable ";

	public AddScoreCommand(Teacher teacher, Variable variable, double score, int index, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked, score, index); 
	}
	@Override
	public void execute()
	{
		variable.addScore(index, score);
		super.execute(); 
	}
}

package org.resres.stats;

public class ReplaceScoreCommand extends ScoreCommand implements Command
{
	private static final String EXPLANATION ="Add a score to variable ";

	public ReplaceScoreCommand(Teacher teacher, Variable variable, double score,
			int index, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked, score, index);
	}
	@Override
	public void execute()
	{
		variable.replaceScore(index, score); 
		super.execute();
	}
}

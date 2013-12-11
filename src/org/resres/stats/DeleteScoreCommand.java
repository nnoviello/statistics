package org.resres.stats;


public class DeleteScoreCommand extends ScoreCommand implements Command
{
	private static final String EXPLANATION ="Delete a score from variable ";

	public DeleteScoreCommand(Teacher teacher, Variable variable, int index, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked, variable.getScores().get(index), index);
	}
	@Override
	public void execute()
	{
		variable.deleteScore(index);
		super.execute(); 
	}


}

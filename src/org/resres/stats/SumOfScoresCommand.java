package org.resres.stats;

public class SumOfScoresCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Add all the scores of variable ";
	private static final String RESULT = ", giving the sum of scores.";

	public SumOfScoresCommand(Teacher teacher, Variable variable, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
	}

	@Override
	public void execute()
	{
		variable.calculateSumOfScores();
		super.execute(); 
	}
	@Override
	public Double getResult()
	{
		return (executed) ? variable.getSumOfScores() : null;
	}

}

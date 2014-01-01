package org.resres.stats;

import org.grayleaves.problem.Step;
import org.grayleaves.problem.Teacher;


public class DeleteScoreStep extends ScoreStep implements Step
{
	private static final String EXPLANATION ="Delete a score from variable ";

	public DeleteScoreStep(Teacher teacher, Variable variable, int index, boolean invoked)
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

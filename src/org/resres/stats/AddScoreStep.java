package org.resres.stats;

import org.grayleaves.problem.Step;
import org.grayleaves.problem.Teacher;

public class AddScoreStep extends ScoreStep implements Step
{
	private static final String EXPLANATION ="Add a score to variable ";

	public AddScoreStep(Teacher teacher, Variable variable, double score, int index, boolean invoked)
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

package org.resres.stats;

import org.grayleaves.problem.Step;
import org.grayleaves.problem.Teacher;

public class ReplaceScoreStep extends ScoreStep implements Step
{
	private static final String EXPLANATION ="Add a score to variable ";

	public ReplaceScoreStep(Teacher teacher, Variable variable, double score,
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

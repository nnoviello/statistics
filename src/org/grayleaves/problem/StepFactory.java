package org.grayleaves.problem;

import org.resres.stats.AddScoreStep;
import org.resres.stats.DeleteScoreStep;
import org.resres.stats.ReplaceScoreStep;
import org.resres.stats.ScoreStep;
import org.resres.stats.Variable;

public class StepFactory
{
	public static ScoreStep buildStep(StepEnum stepEnum, Teacher teacher,
			Variable variable, double score, int index, boolean invoked)
	{
		switch (stepEnum)
		{
		case ADD_SCORE: return new AddScoreStep(teacher, variable, score, index, invoked); 
		case DELETE_SCORE: return new DeleteScoreStep(teacher, variable, index, invoked); 
		case REPLACE_SCORE: return new ReplaceScoreStep(teacher, variable, score, index, invoked); 
		default:  return null; 
		}
	}
}

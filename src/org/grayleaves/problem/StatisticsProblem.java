package org.grayleaves.problem;

import org.resres.stats.AddScoreStep;
import org.resres.stats.DeleteScoreStep;
import org.resres.stats.ReplaceScoreStep;
import org.resres.stats.Variable;

public class StatisticsProblem extends AbstractProblem implements Problem
{

	private Teacher teacher;
	private Variable variable;

	public StatisticsProblem(Teacher teacher)
	{
		this.teacher = teacher; 
		addStepMapEntry(StepEnum.ADD_SCORE); 
		addStepMapEntry(StepEnum.DELETE_SCORE); 
		addStepMapEntry(StepEnum.REPLACE_SCORE); 
		variable = new Variable(); 
	}
	@Override
	public Step buildStep(Update update, StepSequence stepSequence)
	{
		StepEnum stepEnum = getStepMap().get(update.getUpdateStep());
		double score = Double.parseDouble(((StatisticsUpdate) update).afterValue);
		int index = stepSequence.getIndex(); 
		boolean explictlyInvoked = true; 
		switch (stepEnum) 
		{
		case ADD_SCORE: return new AddScoreStep(teacher, variable, score, index, explictlyInvoked); 
		case DELETE_SCORE: return new DeleteScoreStep(teacher, variable, index, explictlyInvoked); 
		case REPLACE_SCORE: return new ReplaceScoreStep(teacher, variable, score, index, explictlyInvoked); 
		default:  return null; 
		}
	}

	public void setVariable(Variable variable)
	{
		this.variable = variable; 
	}
	public Variable getVariable()
	{
		return variable;
	}
}

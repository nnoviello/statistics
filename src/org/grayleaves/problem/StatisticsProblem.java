package org.grayleaves.problem;

import org.resres.stats.AddScoreStep;
import org.resres.stats.DeleteScoreStep;
import org.resres.stats.DeviationStep;
import org.resres.stats.MeanStep;
import org.resres.stats.NStep;
import org.resres.stats.ReplaceScoreStep;
import org.resres.stats.SquaredDeviationStep;
import org.resres.stats.SumOfScoresStep;
import org.resres.stats.SumOfSquaredDeviationsStep;
import org.resres.stats.Variable;

public class StatisticsProblem extends AbstractProblem implements Problem
{

	private Teacher teacher;
	private Variable variable;

	public StatisticsProblem(Teacher teacher)
	{
		this.teacher = teacher; 
		addEnumsToMap(); 
		variable = new Variable(); 
	}
	@Override
	public Step buildStep(Update update, StepSequence stepSequence)
	{
		StepEnum stepEnum = getStepMap().get(update.getUpdateStep());
//		double score = parseScore(update);
		int index = stepSequence.getIndex(); 
		boolean explicitlyInvoked = true; 
		switch (stepEnum) 
		{
		case ADD_SCORE: return new AddScoreStep(teacher, variable, parseScore(update), index, explicitlyInvoked); 
		case DELETE_SCORE: return new DeleteScoreStep(teacher, variable, index, explicitlyInvoked); 
		case REPLACE_SCORE: return new ReplaceScoreStep(teacher, variable, parseScore(update), index, explicitlyInvoked); 
		case N: return new NStep(teacher, variable, explicitlyInvoked);  
		case MEAN: return new MeanStep(teacher, variable, explicitlyInvoked);  
		case SUM_OF_SCORES: return new SumOfScoresStep(teacher, variable, explicitlyInvoked);  
		case DEVIATION: return new  DeviationStep(teacher, variable, index, explicitlyInvoked);  
		case SQUARED_DEVIATION: return new SquaredDeviationStep(teacher, variable, index, explicitlyInvoked);  
		case SUM_OF_SQUARED_DEVIATIONS: return new SumOfSquaredDeviationsStep(teacher, variable, explicitlyInvoked);  
		default:  return null; 
		}
	}
	@Override
	public int getNumberOfSteps(StepSequence stepSequence)
	{
		switch (stepSequence.getStepEnum())
		{
		case DEVIATION: return getVariable().getN(); 
		case SQUARED_DEVIATION: return getVariable().getN(); 
		default: return 1; 
		}
	}
	protected double parseScore(Update update)
	{
		double score = Double.parseDouble(((StatisticsUpdate) update).afterValue);
		return score;
	}

	public void setVariable(Variable variable)
	{
		this.variable = variable; 
	}
	public Variable getVariable()
	{
		return variable;
	}
	protected void addEnumsToMap()
	{
		addStepMapEntry(StepEnum.VARIABLE_NAME); 
		addStepMapEntry(StepEnum.ADD_SCORE); 
		addStepMapEntry(StepEnum.DELETE_SCORE); 
		addStepMapEntry(StepEnum.REPLACE_SCORE); 
		addStepMapEntry(StepEnum.DEVIATION); 
		addStepMapEntry(StepEnum.MEAN); 
		addStepMapEntry(StepEnum.N); 
		addStepMapEntry(StepEnum.SQUARED_DEVIATION); 
		addStepMapEntry(StepEnum.SUM_OF_SCORES); 
		addStepMapEntry(StepEnum.SUM_OF_SQUARED_DEVIATIONS); 
		addStepMapEntry(StepEnum.VARIANCE); 
		addStepMapEntry(StepEnum.STANDARD_DEVIATION);
	}

}

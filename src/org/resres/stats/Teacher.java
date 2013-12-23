package org.resres.stats;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class Teacher implements Observer
{
	private static final String NO_EARLIER_STEPS_TO_EXPLAIN = "No earlier steps to explain.";
	private static final String NO_LATER_STEPS_TO_EXPLAIN = "No later steps to explain.";
	private Stack<Step> previousSteps;
	private Stack<Step> nextSteps;
	public Teacher()
	{
		previousSteps = new Stack<Step>(); 
		nextSteps = new Stack<Step>(); 
	}
	public String explainPreviousStep()
	{
		return explainStep(true); 
	}
	public String explainNextStep()
	{
		return explainStep(false); 
	}
	//TODO test update with nextCommands
	@Override
	public void update(Observable objStep, Object unused)
	{
		previousSteps.push((Step) objStep);  
	}
	public Step getPreviousStep()
	{
		return getStep(true); 
	}
	public Step getPreviousDetailedStep()
	{ //TODO consider getPreviousLittleStep
		return getDetailedStep(true); 
	}
	public Step getNextStep()
	{
		return getStep(false); 
	}
	public Step getNextDetailedStep()
	{
		return getDetailedStep(false); 
	}
	private String explainStep(boolean backward)
	{
		String explanation = null;
		Step step = getStep(backward); 
		if (step == null)
		{
			explanation = (backward) ? NO_EARLIER_STEPS_TO_EXPLAIN : NO_LATER_STEPS_TO_EXPLAIN; 
		}
		else explanation = step.explain(); 
		return explanation; 
	}
	
	private Step getDetailedStep(boolean backward)
	{
		Step step = null; 
		if (backward)
		{
			step = shiftOneStep(previousSteps, nextSteps); 
		}
		else step = shiftOneStep(nextSteps, previousSteps);
		return step;
	}
	private Step shiftOneStep(Stack<Step> fromSteps,
			Stack<Step> toSteps)
	{
		if (fromSteps.empty()) return null; 
		Step step = fromSteps.pop(); 
		toSteps.push(step); 
		return step;
	}
	private Step getStep(boolean backward)
	{
		Step step = getDetailedStep(backward);
		while ((step != null) && 
				(!step.explicitlyInvoked()))
		{
			step = getDetailedStep(backward);
		}
		return step;
	}
}

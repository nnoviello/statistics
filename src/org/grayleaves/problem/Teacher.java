package org.grayleaves.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import com.google.gson.Gson;


public class Teacher implements Observer
{
	private static final String NO_EARLIER_STEPS_TO_EXPLAIN = "No earlier steps to explain.";
	private static final String NO_LATER_STEPS_TO_EXPLAIN = "No later steps to explain.";
	private Stack<Step> previousSteps;
	private Stack<Step> nextSteps;
	private Problem problem;
	private List<Problem> problems;
	public Teacher()
	{
		previousSteps = new Stack<Step>(); 
		nextSteps = new Stack<Step>(); 
		problems = new ArrayList<Problem>(); 
	}
	private void buildHardCodedStatisticsProblem(Problem problem)
	{
		try
		{
			problem.addStepSequence(new StepSequence(StepEnum.ADD_SCORE, "0", problem));
		}
		catch (StepException e)
		{
			e.printStackTrace();
		} 
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
	public Step getPreviousLittleStep()
	{ 
		return getLittleStep(true); 
	}
	public Step getNextStep()
	{
		return getStep(false); 
	}
	public Step getNextLittleStep()
	{
		return getLittleStep(false); 
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
	
	private Step getLittleStep(boolean backward)
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
		Step step = getLittleStep(backward);
		while ((step != null) && 
				(!step.explicitlyInvoked()))
		{
			step = getLittleStep(backward);
		}
		return step;
	}
	public void addProblem(Problem problem)
	{
		problems.add(problem); 
		buildHardCodedStatisticsProblem(problem);
	}
	public List<Problem> getProblems()
	{
		return problems;
	}
	public String updateProblem(String jsonInput)
	{
		Map<String, InterfaceUpdate> map = null; 
		try
		{
			map = problems.get(0).update(jsonInput);
		}
		catch (ProblemException e)
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(map);
	}
}

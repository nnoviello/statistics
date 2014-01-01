package org.grayleaves.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractProblem implements Problem
{
	public static final String DUPLICATE_STEP_SEQUENCE = "AbstractProblem.addStepSequence:  Duplicate step sequence for ";
	public static final String DUPLICATE_STEP_SEQUENCE_CONTINUATION = ".  Use StepSequence(stepEnum, suffix) to create unique step sequences.";

	private List<StepSequence> stepSequences;
	private Map<String, StepSequence> mapStepSequences;
	
	public AbstractProblem()
	{
		stepSequences = new ArrayList<StepSequence>();  
		mapStepSequences = new HashMap<String, StepSequence>();  
	}
	
	@Override
	public void addStepSequence(StepSequence stepSequence) throws StepException
	{
		if (mapStepSequences.containsKey(stepSequence.getId())) 
			throw new StepException(DUPLICATE_STEP_SEQUENCE+stepSequence.getId()+DUPLICATE_STEP_SEQUENCE_CONTINUATION); 
		else
		{
			stepSequences.add(stepSequence);
			mapStepSequences.put(stepSequence.getId(), stepSequence); 
		}
	}

	@Override
	public StepSequence getStepSequence(int i)
	{
		return stepSequences.get(i);
	}
    @Override
    public abstract Step buildStep(String stepSequenceId);
}

package org.grayleaves.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractProblem implements Problem
{
	public static final String DUPLICATE_STEP_SEQUENCE = "AbstractProblem.addStepSequence:  Duplicate step sequence for ";
	public static final String DUPLICATE_STEP_SEQUENCE_CONTINUATION = ".  Use StepSequence(stepEnum, suffix) to create unique step sequences.";
	public static final String MISMATCHED_UPDATE_STEP_FROM_INPUT = "AbstractProblem.buildStepEnum:  No StepEnum found for Update.getUpdateStep():  ";
	public static final String MISMATCHED_UPDATE_POSSIBLE_CAUSES = "\nPossible causes:  Misspelling, or addStepMapEntry(StepEnum) not invoked in problem constructor.";
	public static final String MISMATCHED_STEP_SEQUENCE_ID_POSSIBLE_CAUSES = "\nPossible causes:  Misspelling, or addStepSequence(StepSequence) not previously invoked.";
	public static final String MISMATCHED_STEP_SEQUENCE_ID_FROM_INPUT = "AbstractProblem.buildStepSequence:  No StepSequence found for Update.getStepSequenceId():  ";

	private List<StepSequence> stepSequences;
	private Map<String, StepSequence> mapStepSequences;
	private Map<String, StepEnum> stepMap;
	
	public AbstractProblem()
	{
		stepSequences = new ArrayList<StepSequence>();  
		mapStepSequences = new HashMap<String, StepSequence>();  
		stepMap = new HashMap<String, StepEnum>(); 
	}
	@Override
	public Update buildUpdate(String jsonUpdate) throws ProblemException
	{
		JsonUpdate updater = new JsonUpdate(); 
		Update update = null; 
		try 
		{
			update = updater.getUpdate(jsonUpdate);
		}
		catch (InvalidJsonUpdateException e)
		{
			throw new ProblemException("AbstractProblem.buildUpdate received: "+e.getMessage()); 
		}
		return update; 
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
    public abstract Step buildStep(Update update) throws ProblemException;
    
    @Override
    public StepSequence buildStepSequence(Update update) throws ProblemException
    {
    	StepSequence stepSequence = mapStepSequences.get(update.getStepSequenceId()); 
    	if (stepSequence == null) throw new ProblemException(MISMATCHED_STEP_SEQUENCE_ID_FROM_INPUT+
    			update.getStepSequenceId()+MISMATCHED_STEP_SEQUENCE_ID_POSSIBLE_CAUSES); 
    	else return stepSequence;
    }

    public Map<String, StepEnum> getStepMap()
	{
		return stepMap;
	}
	protected void addStepMapEntry(StepEnum stepEnum)
	{
		getStepMap().put(stepEnum.getName(), stepEnum);
	}
	protected StepEnum buildStepEnum(Update update) throws ProblemException
	{
		StepEnum stepEnum = getStepMap().get(update.getUpdateStep());
		if (stepEnum == null) throw new ProblemException(MISMATCHED_UPDATE_STEP_FROM_INPUT+
				update.getUpdateStep()+MISMATCHED_UPDATE_POSSIBLE_CAUSES); 
		else return stepEnum;
	}
	@Override
	public void update(String jsonInput) throws ProblemException
	{
		Update update = buildUpdate(jsonInput); 
		Step step = buildStep(update); 
		step.execute(); 
		tempPostExecution(step); 
	}
	protected abstract void tempPostExecution(Step step);
}

package org.grayleaves.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StepSequence
{

	public static final String DATA_STEP_SEQUENCE_ID_NOT_FOUND = ", but unable to retrieve data, because step sequence Id not found for ";
	public static final String ATTEMPTED_INTERFACE_UPDATE = "StepSequence.getStepFromStepSequence:  attempted to create interface update for ";
	public static final String DATA_STEP_SEQUENCE = ", but unable to retrieve data, because step sequence Id ";
	public static final String HAS_NO_STEP = " has no step";
	public static final String STEP_HAS_NOT_YET_EXECUTED = " has a step which has not yet executed";
	private String id;
	private StepEnum stepEnum;
	private int index;
	private Step step;
	private Map<String, InterfaceUpdate> interfaceUpdateMap;
	private boolean executed;
	private Problem problem;
	private int order;

	public StepSequence(StepEnum stepEnum, Problem problem)
	{
		this(stepEnum,"", problem );
	}

	public StepSequence(StepEnum stepEnum, String suffix, Problem problem)
	{
		this.stepEnum = stepEnum; 
		id = stepEnum.getName()+suffix; 
		interfaceUpdateMap = new HashMap<String, InterfaceUpdate>(); 
		executed = false; 
		this.problem = problem; 
	}

	public String getId()
	{
		return id;
	}

	public StepEnum getStepEnum()
	{
		return stepEnum;
	}
	public void setIndex(int index)
	{
		this.index = index; 
	}
	public int getIndex()
	{
		return index;
	}
	public void addVisibilityOnlyInterfaceUpdate(String stepSequenceId, VisibilityEnum visibility)
	{
		interfaceUpdateMap.put(stepSequenceId, new VisibilityOnlyInterfaceUpdate(stepSequenceId, visibility.getVisibility())); 
	}
	public void addVisibilityAndDataInterfaceUpdate(String stepSequenceId, VisibilityEnum visibility, String dataStepSequenceId)
	{
		interfaceUpdateMap.put(stepSequenceId, new VisibilityAndDataInterfaceUpdate(stepSequenceId, visibility.getVisibility(), this.id)); 
	}
//	public void addVisibilityAndDataInterfaceUpdate(String stepSequenceId, VisibilityEnum visibility, String dataStepSequenceId)
//	{
//		interfaceUpdateMap.put(stepSequenceId, new VisibilityAndDataInterfaceUpdate(stepSequenceId, visibility.getVisibility(), dataStepSequenceId)); 
//	}
	public Map<String, InterfaceUpdate> execute() throws ProblemException
	{
		updateData();
		
		if (executed)
		{
			for (Entry<String, InterfaceUpdate> entry : interfaceUpdateMap.entrySet())
			{
				if (entry.getValue() instanceof VisibilityOnlyInterfaceUpdate)
				{
					interfaceUpdateMap.put(entry.getKey(), new NullInterfaceUpdate()); 
				}
				else if (entry.getValue() instanceof VisibilityAndDataInterfaceUpdate)
				{
					interfaceUpdateMap.put(entry.getKey(), new DataOnlyInterfaceUpdate(((VisibilityAndDataInterfaceUpdate) entry.getValue()))); 
				}
			}
		}
		else
		{
			executed = true; 
		}
		return interfaceUpdateMap;
	}
	
	protected Step getStepFromStepSequence(String dataStepSequenceId, String requestingStepSequenceId) throws ProblemException
	{
		Step step = null;
		StepSequence dataStepSequence = problem.getMapStepSequences().get(dataStepSequenceId); 
		if (dataStepSequence == null) throw new ProblemException(ATTEMPTED_INTERFACE_UPDATE+requestingStepSequenceId+DATA_STEP_SEQUENCE_ID_NOT_FOUND+dataStepSequenceId); 
		else 
		{
			step = dataStepSequence.getStep();
			if (step == null) throw new ProblemException(ATTEMPTED_INTERFACE_UPDATE+requestingStepSequenceId+DATA_STEP_SEQUENCE+dataStepSequenceId+HAS_NO_STEP);
			else 
			{
				if (!step.hasExecuted()) throw new ProblemException(ATTEMPTED_INTERFACE_UPDATE+requestingStepSequenceId+DATA_STEP_SEQUENCE+dataStepSequenceId+STEP_HAS_NOT_YET_EXECUTED);
			}
		}
		return step; 
	}

	private void updateData() throws ProblemException
	{
		String dataStepSequenceId = null; 
		DataInterfaceUpdate dataInterfaceUpdate = null; 
		for (Entry<String, InterfaceUpdate> entry : interfaceUpdateMap.entrySet())
		{
			if (entry.getValue() instanceof DataInterfaceUpdate)
			{
				dataInterfaceUpdate = (DataInterfaceUpdate) entry.getValue(); 
				dataStepSequenceId = dataInterfaceUpdate.getDataStepSequenceId(); 
				Step dataStep = getStepFromStepSequence(dataStepSequenceId, entry.getKey());
				dataInterfaceUpdate.setData(dataStep.getResult()); 
				dataInterfaceUpdate.setExplanation(dataStep.explain()); 
			}
		}
	}

	public void updateStep(Step step)
	{
		this.step = step; 
	}
	public Step getStep()
	{
		return step;
	}
	public void setOrder(int order)
	{
		this.order = order; 
	}

	public Map<String, InterfaceUpdate> buildAndExecuteSteps(Update update,
			AbstractProblem problem) throws ProblemException
	{
		Step step = problem.buildStep(update, this); 
		step.execute(); 
		updateStep(step); 
		return execute(); 
	}

	public void addStepSequence(StepSequence stepSequence)
	{
	}

	public StepSequence getStepSequence(int i)
	{
		return null;
	}

	public Map<String, InterfaceUpdate> executeSteps()
	{
		return null;
	}



}

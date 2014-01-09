package org.grayleaves.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	private List<StepSequence> stepSequences;
	private boolean list;
	private List<StepSequence> littleStepSequences;
	private String suffix;
	private Map<String, InterfaceUpdate> combinedInterfaceUpdateMap;
	private Update update;

	public StepSequence(StepEnum stepEnum, Problem problem)
	{
		this(stepEnum,"", problem );
	}

	public StepSequence(StepEnum stepEnum, String suffix, Problem problem)
	{
		this.stepEnum = stepEnum; 
		this.suffix = suffix; 
		id = stepEnum.getName()+suffix; 
		interfaceUpdateMap = new HashMap<String, InterfaceUpdate>(); 
		executed = false; 
		this.problem = problem; 
		littleStepSequences = new ArrayList<StepSequence>(); 
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
	public void addVisibilityAndDataInterfaceUpdate(String stepSequenceId, VisibilityEnum visibility)
	{
		interfaceUpdateMap.put(stepSequenceId, new VisibilityAndDataInterfaceUpdate(stepSequenceId, visibility.getVisibility(), this.id)); 
	}
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
	public Map<String, InterfaceUpdate> buildAndExecuteStepsAndStepSequences(Update update,
			AbstractProblem problem) throws ProblemException
	{
		this.update = update; 
		buildAndExecuteStep(update, problem); 
		buildLittleStepSequences(); 
		buildSteps(); 
		return executeStepSequences(); 
	}

	private void buildSteps() throws ProblemException
	{
		for (StepSequence stepSequence : littleStepSequences)
		{
			stepSequence.buildAndExecuteStep(update, problem);
		}
	}

	protected void buildAndExecuteStep(Update update, Problem problem)
			throws ProblemException
	{
		Step step = problem.buildStep(update, this); 
		step.execute(); 
		updateStep(step);
	}
	public void addLittleStepSequence(StepSequence stepSequence)
	{
		littleStepSequences.add(stepSequence);
	}

	public Map<String, InterfaceUpdate> executeStepSequences() throws ProblemException
	{
		interfaceUpdateMap = execute(); 
		combinedInterfaceUpdateMap = new HashMap<String, InterfaceUpdate>();
		addToCombinedMap(interfaceUpdateMap);
		//FIXME when do we do execute? 
		for (StepSequence stepSequence : getLittleStepSequences())
		{
			addToCombinedMap(stepSequence.executeStepSequences());
		}
		return combinedInterfaceUpdateMap;
	}

	private void addToCombinedMap(
			Map<String, InterfaceUpdate> interfaceUpdateMap)
	{
		Set<Entry<String, InterfaceUpdate>> entries = interfaceUpdateMap.entrySet(); 
		for (Entry<String, InterfaceUpdate> entry : entries)
		{
			//TODO test for conflicting keys
			combinedInterfaceUpdateMap.put(entry.getKey(), entry.getValue());
		}
	}

	public void setList(boolean list)
	{
		this.list = list; 
	}

	protected void buildLittleStepSequences() throws ProblemException
	{
		if (list)
		{
			StepSequence stepSequence = null; 
			for (int i = 0; i < problem.getNumberOfSteps(this); i++)
			{
				stepSequence = new StepSequence(getStepEnum(), i+suffix, problem); //TODO revisit prefixing the i; makes nesting awkward
				stepSequence.setIndex(i); 
//				stepSequence.buildStep(update, problem); 
				littleStepSequences.add(stepSequence);  
			}
		}
	}
	@Override
	public String toString()
	{
		return "StepSequence: "+id+", index: "+index+", StepEnum: "+stepEnum.getName()+", step exists: "+((getStep() != null) ? "Y" : "N");
	}
	public List<StepSequence> getLittleStepSequences()
	{
		return littleStepSequences;
	}

	protected void setUpdateForTesting(Update update)
	{
		this.update = update;
	}

	public String printInterfaceUpdates()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("interface updates: \n");
		if (combinedInterfaceUpdateMap != null)
		{
			for (InterfaceUpdate interfaceUpdate : combinedInterfaceUpdateMap.values())
			{
				sb.append(interfaceUpdate.toString()); 
				sb.append("\n"); 
			}
		}
		return sb.toString();
	}

}

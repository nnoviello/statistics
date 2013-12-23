package org.resres.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class AbstractStep extends Observable implements Step
{
	//TODO Move messages to a property file
	private static final String STEP_MUST_BE_INITIALIZED_WITH_A_NON_NULL_VARIABLE = "Command:  must be initialized with a non-null Variable.";
	private String explanation = "";
	private String result ="";

	protected Variable variable;
	protected List<Step> littleSteps;
	protected Teacher teacher;
	private boolean invoked;
	protected boolean executed = false;

	public AbstractStep(Variable variable, String explanation, String result, boolean invoked)
	{
		if (variable == null) throw new IllegalArgumentException(STEP_MUST_BE_INITIALIZED_WITH_A_NON_NULL_VARIABLE); 
		this.variable = variable; 
		this.explanation = explanation; 
		this.result = result; 
		this.invoked = invoked; 
		littleSteps = new ArrayList<Step>();
		buildLittleSteps(); // invoke this with a teacher
	}

	public AbstractStep(Teacher teacher, Variable variable,
			String explanation, String result, boolean invoked)
	{
		this(variable, explanation, result, invoked); 
		if (teacher != null) 
		{
			addObserver(teacher); 
			this.teacher = teacher; 
		}
		littleSteps = new ArrayList<Step>();
		buildLittleSteps(); // invoke this with a teacher
	}
	@Override
	public void execute()
	{
		for (Step step : littleSteps)
		{
			step.execute(); 
		}
		executed = true;
		setChanged(); 
		notifyObservers(this); 
	}
	@Override
	public String explain()
	{
		return explanation+variable.getName()+result;
	}
	protected void buildLittleSteps() {}
	@Override
	public List<Step> getLittleSteps()
	{
		return littleSteps;
	}
	@Override
	public boolean explicitlyInvoked()
	{
		return invoked;
	}

}


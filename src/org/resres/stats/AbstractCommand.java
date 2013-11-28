package org.resres.stats;

import java.util.Observable;

public abstract class AbstractCommand extends Observable implements Command
{

	private static final String SUM_COMMAND_MUST_BE_INITIALIZED_WITH_A_NON_NULL_VARIABLE = "SumCommand:  must be initialized with a non-null Variable.";
	private String explanation = "";
	private String result ="";

	protected Variable variable;

	public AbstractCommand(Variable variable, String explanation, String result)
	{
		if (variable == null) throw new IllegalArgumentException(SUM_COMMAND_MUST_BE_INITIALIZED_WITH_A_NON_NULL_VARIABLE); 
		this.variable = variable; 
		this.explanation = explanation; 
		this.result = result; 
	}
	public AbstractCommand(Teacher teacher, Variable variable,
			String explanation, String result)
	{
		this(variable, explanation, result); 
		if (teacher != null) addObserver(teacher); 
	}
	@Override
	public void execute()
	{
		setChanged(); 
		notifyObservers(this); 
	}
	@Override
	public String explain()
	{
		return explanation+variable.getName()+result;
	}
}


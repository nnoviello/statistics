package org.resres.stats;

public abstract class AbstractCommand implements Command
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

	@Override
	public String explain()
	{
		return explanation+variable.getName()+result;
	}
}


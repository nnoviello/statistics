package org.resres.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class AbstractCommand extends Observable implements Command
{

	private static final String SUM_COMMAND_MUST_BE_INITIALIZED_WITH_A_NON_NULL_VARIABLE = "SumCommand:  must be initialized with a non-null Variable.";
	private String explanation = "";
	private String result ="";

	protected Variable variable;
	protected List<Command> subcommands;
	protected Teacher teacher;
	private boolean invoked;

	public AbstractCommand(Variable variable, String explanation, String result, boolean invoked)
	{
		if (variable == null) throw new IllegalArgumentException(SUM_COMMAND_MUST_BE_INITIALIZED_WITH_A_NON_NULL_VARIABLE); 
		this.variable = variable; 
		this.explanation = explanation; 
		this.result = result; 
		this.invoked = invoked; 
		subcommands = new ArrayList<Command>();
		buildSubcommands(); // invoke this with a teacher
	}

	public AbstractCommand(Teacher teacher, Variable variable,
			String explanation, String result, boolean invoked)
	{
		this(variable, explanation, result, invoked); 
		if (teacher != null) 
		{
			addObserver(teacher); 
			this.teacher = teacher; 
		}
		subcommands = new ArrayList<Command>();
		buildSubcommands(); // invoke this with a teacher
	}
	@Override
	public void execute()
	{
		for (Command command : subcommands)
		{
			command.execute(); 
		}
		setChanged(); 
		notifyObservers(this); 
	}
	@Override
	public String explain()
	{
		return explanation+variable.getName()+result;
	}
	protected void buildSubcommands() {}
	@Override
	public List<Command> getSubcommands()
	{
		return subcommands;
	}
	@Override
	public boolean explicitlyInvoked()
	{
		return invoked;
	}
}


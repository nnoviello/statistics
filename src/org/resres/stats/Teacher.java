package org.resres.stats;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class Teacher implements Observer
{
	private static final String NO_EARLIER_COMMANDS_TO_EXPLAIN = "No earlier commands to explain.";
	private static final String NO_LATER_COMMANDS_TO_EXPLAIN = "No later commands to explain.";
	private Stack<Command> previousCommands;
	private Stack<Command> nextCommands;
	public Teacher()
	{
		previousCommands = new Stack<Command>(); 
		nextCommands = new Stack<Command>(); 
	}
	public String explainPreviousCommand()
	{
		return explainCommand(true); 
	}
	public String explainNextCommand()
	{
		return explainCommand(false); 
	}
	//TODO test update with nextCommands
	@Override
	public void update(Observable objCommand, Object unused)
	{
		previousCommands.push((Command) objCommand);  
	}
	public Command getPreviousCommand()
	{
		return getCommand(true); 
	}
	public Command getPreviousDetailedCommand()
	{
		return getDetailedCommand(true); 
	}
	public Command getNextCommand()
	{
		return getCommand(false); 
	}
	public Command getNextDetailedCommand()
	{
		return getDetailedCommand(false); 
	}
	private String explainCommand(boolean backward)
	{
		String explanation = null;
		Command command = getCommand(backward); 
		if (command == null)
		{
			explanation = (backward) ? NO_EARLIER_COMMANDS_TO_EXPLAIN : NO_LATER_COMMANDS_TO_EXPLAIN; 
		}
		else explanation = command.explain(); 
		return explanation; 
	}
	
	private Command getDetailedCommand(boolean backward)
	{
		Command command = null; 
		if (backward)
		{
			command = shiftOneCommand(previousCommands, nextCommands); 
		}
		else command = shiftOneCommand(nextCommands, previousCommands);
		return command;
	}
	private Command shiftOneCommand(Stack<Command> fromCommands,
			Stack<Command> toCommands)
	{
		if (fromCommands.empty()) return null; 
		Command command = fromCommands.pop(); 
		toCommands.push(command); 
		return command;
	}
	private Command getCommand(boolean backward)
	{
		Command command = getDetailedCommand(backward);
		while ((command != null) && 
				(!command.explicitlyInvoked()))
		{
			command = getDetailedCommand(backward);
		}
		return command;
	}
}

package org.resres.stats;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class Teacher implements Observer
{
	private Stack<Command> commands;

	public Teacher()
	{
		commands = new Stack<Command>(); 
	}
	public String explainLastCommand()
	{
		if (commands.empty()) return "No earlier commands to explain."; 
		else return commands.pop().explain();
	}
	
	@Override
	public void update(Observable objCommand, Object unused)
	{
		commands.push((Command) objCommand);  
	}

	
}

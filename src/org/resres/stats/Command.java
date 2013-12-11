package org.resres.stats;

import java.util.List;

public interface Command
{

	public void execute();

	public String explain();

	/**
	 * Returns null prior to execution of the command.  
	 * Once the command has been executed, value of result is defined by implementing Commands.s
	 * @return
	 */
	public Double getResult();

	public List<Command> getSubcommands();

	public boolean explicitlyInvoked();

}

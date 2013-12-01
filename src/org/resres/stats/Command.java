package org.resres.stats;

import java.util.List;

public interface Command
{

	public void execute();

	public String explain();

	public double getResult();

	public List<Command> getSubcommands();

	public boolean explicitlyInvoked();

}

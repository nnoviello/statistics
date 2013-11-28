package org.resres.stats;

public interface Command
{

	public void execute();

	public String explain();

	public double getResult();

}

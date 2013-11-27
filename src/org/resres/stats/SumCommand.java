package org.resres.stats;

public class SumCommand implements Command
{

	private static final String ADD_ALL_THE_SCORES_OF_VARIABLE = "Add all the scores of variable ";
	private static final String TO_CREATE_THE_SUM = " to create the Sum.";
	private Variable variable;

	public SumCommand(Variable variable)
	{
		this.variable = variable; 
	}

	@Override
	public void execute()
	{
		variable.calculateSum();
	}

	@Override
	public String explain()
	{
		return ADD_ALL_THE_SCORES_OF_VARIABLE+variable.getName()+TO_CREATE_THE_SUM;
	}

}

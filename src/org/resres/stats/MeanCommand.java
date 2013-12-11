package org.resres.stats;

public class MeanCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Divide sum of scores by n of ";
	private static final String RESULT = ", giving the mean.";

	public MeanCommand(Teacher teacher, Variable variable, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
	}
	@Override
	public void execute()
	{
		super.execute(); 
		variable.calculateMean(); 
	}

	@Override
	public Double getResult()
	{
		return (executed) ? variable.getMean() : null;
	}
	@Override
	protected void buildSubcommands()
	{
		subcommands.add(new NCommand(teacher, variable, false)); 
		subcommands.add(new SumOfScoresCommand(teacher, variable, false)); 
	}
}

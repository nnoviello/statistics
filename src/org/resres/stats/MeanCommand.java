package org.resres.stats;

public class MeanCommand extends AbstractCommand implements Command
{

	private static final String EXPLANATION = "Divides sum of scores by n of ";
	private static final String RESULT = ", giving the mean.";

	public MeanCommand(Teacher teacher, Variable variable, boolean invoked)
	{
		super(teacher, variable, EXPLANATION, RESULT, invoked); 
	}
	public MeanCommand(Variable variable, boolean invoked)
	{
		this(null, variable, invoked);
	}

	@Override
	public void execute()
	{
		super.execute(); 
		variable.calculateMean(); 
	}

	@Override
	public double getResult()
	{
		return variable.getMean();
	}
	@Override
	protected void buildSubcommands()
	{
		subcommands.add(new NCommand(teacher, variable, false)); 
		subcommands.add(new SumCommand(teacher, variable, false)); 
	}
}

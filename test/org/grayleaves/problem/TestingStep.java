package org.grayleaves.problem;

import java.util.List;

public class TestingStep implements Step
{

	private boolean executed;

	public TestingStep(Teacher teacher, String field, int index, boolean invoked)
	{
		executed = false; 
	}

	@Override
	public void execute()
	{
		executed = true; 
	}

	@Override
	public String explain()
	{
		return null;
	}

	@Override
	public boolean explicitlyInvoked()
	{
		return false;
	}

	@Override
	public List<Step> getLittleSteps()
	{
		return null;
	}

	@Override
	public Double getResult()
	{
		return null;
	}

	public boolean hasExecuted()
	{
		return executed;
	}
	
}

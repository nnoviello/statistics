package org.grayleaves.problem;

import java.util.List;

public class TestingStep implements Step
{

	private boolean executed;
	private String field;

	public TestingStep(Teacher teacher, String field, int index, boolean invoked)
	{
		this.field = field; 
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
		return "explanation of data step";
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
		return Double.parseDouble(field);
	}

	public boolean hasExecuted()
	{
		return executed;
	}
	
}

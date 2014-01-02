package org.grayleaves.problem;

import java.util.List;

public class TestingStep implements Step
{

	public TestingStep(Teacher teacher, String field, int index, boolean invoked)
	{
	}

	@Override
	public void execute()
	{
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
	
}

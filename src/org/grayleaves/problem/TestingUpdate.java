package org.grayleaves.problem;


public class TestingUpdate implements Update
{
	public int id; 
	public String field; 
	public int[] someArray;
	public String updateStep; 
	public TestingUpdate(int id, String updateStep)
	{
		this.id = id;
		this.updateStep = updateStep;
	}
	@Override
	public String getStepSequenceId()
	{
		return updateStep+id;
	}
	@Override
	public String getUpdateStep()
	{
		return updateStep;
	}
}

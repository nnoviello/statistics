package org.grayleaves.problem;

public class VisibilityOnlyInterfaceUpdate implements InterfaceUpdate
{
	public String stepSequenceId;
	public String visibility;

	public VisibilityOnlyInterfaceUpdate(String stepSequenceId, String visibility)
	{
		this.stepSequenceId = stepSequenceId; 
		this.visibility = visibility; 
	}
	@Override
	public String toString()
	{
		return this.getClass().getName()+":  StepSequenceId: "+stepSequenceId+", visibility: "+visibility;
	}

}

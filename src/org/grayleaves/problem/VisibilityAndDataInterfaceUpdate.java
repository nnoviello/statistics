package org.grayleaves.problem;

public class VisibilityAndDataInterfaceUpdate implements DataInterfaceUpdate
{
	public String stepSequenceId;
	public String visibility;
	public double data;
	public String explanation;
	@JsonSkip protected String dataStepSequenceId; // only for use on server; won't update interface 

	public VisibilityAndDataInterfaceUpdate(String stepSequenceId, String visibility, String dataStepSequenceId)
	{
		this.stepSequenceId = stepSequenceId; 
		this.visibility = visibility; 
		this.dataStepSequenceId = dataStepSequenceId; 
	}

	@Override
	public void setData(double data)
	{
		this.data = data; 
	}
	@Override
	public void setExplanation(String explanation)
	{
		this.explanation = explanation; 
	}

	@Override
	public String getDataStepSequenceId()
	{
		return dataStepSequenceId;
	}
	@Override
	public String toString()
	{
		return this.getClass().getName()+":  StepSequenceId: "+stepSequenceId+", visibility: "+visibility+", dataStepSequenceId: "+dataStepSequenceId;
	}


}

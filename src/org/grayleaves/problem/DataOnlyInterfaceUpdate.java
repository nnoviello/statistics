package org.grayleaves.problem;

public class DataOnlyInterfaceUpdate implements DataInterfaceUpdate
{
	public String stepSequenceId;
	public double data;
	public String explanation; 
	protected String dataStepSequenceId; // only for use on server; won't update interface 

	public DataOnlyInterfaceUpdate(String stepSequenceId, String dataStepSequenceId)
	{
		this.stepSequenceId = stepSequenceId; 
		this.dataStepSequenceId = dataStepSequenceId; 
	}

	public DataOnlyInterfaceUpdate(
			VisibilityAndDataInterfaceUpdate visibilityAndDataInterfaceUpdate)
	{
		this.stepSequenceId = visibilityAndDataInterfaceUpdate.stepSequenceId; 
		this.dataStepSequenceId = visibilityAndDataInterfaceUpdate.dataStepSequenceId; 
		this.data = visibilityAndDataInterfaceUpdate.data; 
		this.explanation = visibilityAndDataInterfaceUpdate.explanation; 
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

}

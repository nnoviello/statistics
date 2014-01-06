package org.grayleaves.problem;

public class StepSequenceKey
{

	@SuppressWarnings("unused")
	private String stepSequenceId;
	@JsonSkip private int order;

	public StepSequenceKey(String stepSequenceId, int order)
	{
		this.stepSequenceId = stepSequenceId; 
		this.order = order; 
	}

}

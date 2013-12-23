package org.resres.stats;

import java.util.List;

public interface Step
{
	//TODO consider whether execute should return result directly
	public void execute();

	public String explain();

	/**
	 * Returns null prior to execution of the step.  
	 * Once the step has been executed, value of result is defined by the implementation Step
	 * @return
	 */
	public Double getResult();

	public List<Step> getLittleSteps();

	public boolean explicitlyInvoked();

}

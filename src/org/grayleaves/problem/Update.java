package org.grayleaves.problem;

/**
 * Interface for all classes to be created by @link{JsonUpdate} from a user interface that submits its updates in JSON format. 
 */
public interface Update
{

	public String getStepSequenceId();

	public String getUpdateStep();

}

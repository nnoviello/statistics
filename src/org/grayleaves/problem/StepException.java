package org.grayleaves.problem;

public class StepException extends Exception
{

	public StepException()
	{
	}

	public StepException(String message)
	{
		super(message);
	}

	public StepException(Throwable cause)
	{
		super(cause);
	}

	public StepException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

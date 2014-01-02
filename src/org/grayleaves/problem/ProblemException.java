package org.grayleaves.problem;

public class ProblemException extends Exception
{

	private static final long serialVersionUID = 1L;

	public ProblemException()
	{
	}

	public ProblemException(String message)
	{
		super(message);
	}

	public ProblemException(Throwable cause)
	{
		super(cause);
	}

	public ProblemException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

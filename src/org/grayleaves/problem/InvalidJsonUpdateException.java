package org.grayleaves.problem;

public class InvalidJsonUpdateException extends Exception
{

	private static final long serialVersionUID = 1L;

	public InvalidJsonUpdateException()
	{
	}

	public InvalidJsonUpdateException(String message)
	{
		super(message);
	}

	public InvalidJsonUpdateException(Throwable cause)
	{
		super(cause);
	}

	public InvalidJsonUpdateException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

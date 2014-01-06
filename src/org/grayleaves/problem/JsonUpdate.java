package org.grayleaves.problem;


import com.google.gson.Gson;


public class JsonUpdate
{
	protected static final String UPDATE_JAVA_CLASS_NOT_IN_INPUT = "JsonUpdate.getUpdate:  no updateJavaClass parameter found in JSON input string:\n";
	protected static final String UPDATE_CLASS = "JsonUpdate.getUpdate:  class ";
	protected static final String DOES_NOT_EXIST = " does not exist or has been renamed.";
	private static final String RESET_STATE_FOR_TESTING = "JsonUpdate.getUpdate:  reset of server state requested from user interface unit test, for testing.  New Teacher will be created."; 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Update getUpdate(String jsonInput) throws InvalidJsonUpdateException
	{
		Class clazz = buildClass(jsonInput); 
		Gson gson = new Gson();
		Update update = gson.fromJson(jsonInput, clazz); 
		return update;
	}
	@SuppressWarnings("rawtypes")
	private Class buildClass(String jsonInput) throws InvalidJsonUpdateException
	{
		Gson gson = new Gson();
		UpdateJavaClass updateJavaClass = null; 
		Class clazz = null; 
		try 
		{
			updateJavaClass = gson.fromJson(jsonInput, UpdateJavaClass.class); 
			if (updateJavaClass.updateJavaClass == null ) throw new InvalidJsonUpdateException(UPDATE_JAVA_CLASS_NOT_IN_INPUT+jsonInput); 
			if (updateJavaClass.resetStateForTesting) 
			{
				throw new ResetStateForTestingException(RESET_STATE_FOR_TESTING); 
			}
			clazz = Class.forName(updateJavaClass.updateJavaClass);
		}
		catch (ClassNotFoundException e)
		{
			throw new InvalidJsonUpdateException(UPDATE_CLASS+updateJavaClass.updateJavaClass+DOES_NOT_EXIST); 
		}
		return clazz;
	}

}

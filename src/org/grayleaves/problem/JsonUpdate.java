package org.grayleaves.problem;


import com.google.gson.Gson;


public class JsonUpdate
{
	protected static final String UPDATE_CLASS_NOT_IN_INPUT = "JsonUpdate.getUpdate:  no updateClass parameter found in JSON input string:\n";
	protected static final String UPDATE_CLASS = "JsonUpdate.getUpdate:  class ";
	protected static final String DOES_NOT_EXIST = " does not exist or has been renamed."; 
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
		UpdateClass updateClass = null; 
		Class clazz = null; 
		try 
		{
			updateClass = gson.fromJson(jsonInput, UpdateClass.class); 
			if (updateClass.updateClass == null ) throw new InvalidJsonUpdateException(UPDATE_CLASS_NOT_IN_INPUT+jsonInput); 
			clazz = Class.forName(updateClass.updateClass);
		}
		catch (ClassNotFoundException e)
		{
			throw new InvalidJsonUpdateException(UPDATE_CLASS+updateClass.updateClass+DOES_NOT_EXIST); 
		}
		return clazz;
	}

}

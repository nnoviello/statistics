package org.grayleaves.problem;


//TODO consider automating the creation of GSON objects
// Errors like the following are due to mismatches in the field definitiions between the Java class and the JavaScript object 
// com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_ARRAY at line 1 column 47
public class StatisticsUpdate implements Update
{
	private String jsonInput = "{\"id\":0,\"htmlId\":\"field1\",\"beforeVisibility\":null,\"afterVisibility\":null,\"beforeValue\":\"value1\",\"afterValue\":\"1.2\",\"updateStep\":\"addScore\",\"index\":0}";
	public int id; 
	public String htmlId; 
	public String[] beforeVisibility; 
	public String afterVisibility; 
	public String beforeValue; 
	public String afterValue; 
	public String updateStep; 
	public int index; 
	
	public StatisticsUpdate()
	{
	}
}

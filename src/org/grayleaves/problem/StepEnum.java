package org.grayleaves.problem;


public enum StepEnum
{
	TESTING_STEP { public String getName() { return "testingStep";}},
	
	ADD_SCORE { public String getName() { return "addScore";}}, 
	DELETE_SCORE { public String getName() { return "deleteScore";}},
	REPLACE_SCORE { public String getName() { return "replaceScore";}};
	
	public abstract String getName(); 
	
}

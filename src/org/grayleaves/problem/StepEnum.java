package org.grayleaves.problem;


public enum StepEnum
{
	TESTING_STEP { public String getName() { return "testingStep";}},
	
	ADD_SCORE { public String getName() { return "addScore";}}, 
	DELETE_SCORE { public String getName() { return "deleteScore";}},
	REPLACE_SCORE { public String getName() { return "replaceScore";}}, 
	DEVIATION { public String getName() { return "deviation";}}, 
	MEAN { public String getName() { return "mean";}},
	N { public String getName() { return "n";}}, 
	SQUARED_DEVIATION { public String getName() { return "squaredDeviation";}}, 
	SUM_OF_SCORES { public String getName() { return "sumOfSquares";}}, 
	SUM_OF_SQUARED_DEVIATIONS { public String getName() { return "sumOfSquaredDeviations";}},
	VARIANCE { public String getName() { return "variance";}}, 
	STANDARD_DEVIATION { public String getName() { return "standardDeviation";}}, 
	VARIABLE_NAME { public String getName() { return "variableName";}};
	
	public  abstract String getName(); 
	
}

package org.grayleaves.problem;

import java.util.Map;


public interface Problem
{

	public void addStepSequence(StepSequence stepSequence) throws StepException;

	public StepSequence getStepSequence(int index);

	public Step buildStep(Update update) throws ProblemException;

	public Update buildUpdate(String jsonUpdate) throws InvalidJsonUpdateException; 
	
    public Map<String, StepEnum> getStepMap();

	public StepSequence buildStepSequence(Update update) throws ProblemException;

}
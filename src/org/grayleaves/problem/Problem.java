package org.grayleaves.problem;

import java.util.Map;


public interface Problem
{

	public void addStepSequence(StepSequence stepSequence) throws StepException;

	public StepSequence getStepSequence(int index);

	public Update buildUpdate(String jsonUpdate) throws ProblemException; 
	
    public Map<String, StepEnum> getStepMap();

	public StepSequence buildStepSequence(Update update) throws ProblemException;

	public void update(String jsonInput) throws ProblemException;

	public Step buildStep(Update update, StepSequence stepSequence) throws ProblemException;

}
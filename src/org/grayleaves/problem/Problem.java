package org.grayleaves.problem;

import java.util.Map;


public interface Problem
{

	public void addStepSequence(StepSequence stepSequence) throws StepException;
	public StepSequence getStepSequence(int index);

	public Map<String, InterfaceUpdate> update(String jsonInput) throws ProblemException;
	public Update buildUpdate(String jsonUpdate) throws ProblemException; 
	public StepSequence buildStepSequence(Update update) throws ProblemException;
	public Step buildStep(Update update, StepSequence stepSequence) throws ProblemException;

	public Map<String, StepEnum> getStepMap();
	public Map<String, StepSequence> getMapStepSequences();

}
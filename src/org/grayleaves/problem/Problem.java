package org.grayleaves.problem;


public interface Problem
{

	public void addStepSequence(StepSequence stepSequence) throws StepException;

	public StepSequence getStepSequence(int index);

	public Step buildStep(String stepSequenceId); 
}
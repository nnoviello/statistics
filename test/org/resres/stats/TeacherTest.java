package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TeacherTest
{

	private Variable variable;
	private Teacher teacher;
	private Step step1;
	private Step step2;
	private Step step3;

	@Before
	public void setUp() throws Exception
	{
		//TODO CreateVariableCommand
		teacher = new Teacher(); 
		variable = new Variable(); 
		variable.setName("X"); 
	}
	@Test
	public void verifyTeacherExplainsStepsInOrderTheyWereIssued() throws Exception
	{
		//TODO consider flows:  "explain last thing you did"; "go back n steps, explain from there"; explain last step in detail" 
		addThreeExplicitSteps(); 
		assertEquals(step3.explain(), teacher.explainPreviousStep()); 
		assertEquals(step2.explain(), teacher.explainPreviousStep()); 
		assertEquals(step1.explain(), teacher.explainPreviousStep()); 
		assertEquals("No earlier steps to explain.", teacher.explainPreviousStep()); 
		assertEquals(step1.explain(), teacher.explainNextStep()); 
	}
	@Test
	public void verifyTeacherOnlyReturnsExplicitlyInvokedStepsByDefault() throws Exception
	{
		addThreeExplicitSteps(); 
		assertTrue(teacher.getPreviousStep() instanceof MeanStep);  
		assertTrue(teacher.getPreviousStep() instanceof AddScoreStep);  
		assertTrue(teacher.getPreviousStep() instanceof AddScoreStep);  
		assertNull(teacher.getPreviousStep()); 
	}
	@Test
	public void verifyTeacherReturnsDetailedStepsOnRequest() throws Exception
	{
		addThreeExplicitSteps(); 
		assertTrue(teacher.getPreviousDetailedStep() instanceof MeanStep);  
		assertTrue(teacher.getPreviousDetailedStep() instanceof SumOfScoresStep);  
		assertTrue(teacher.getPreviousDetailedStep() instanceof NStep);  
		assertTrue(teacher.getPreviousDetailedStep() instanceof AddScoreStep);  
		assertTrue(teacher.getPreviousDetailedStep() instanceof AddScoreStep);  
		assertNull(teacher.getPreviousDetailedStep()); 
	}
	@Test
	public void verifyStepsCanBeReplayedForwardsAndBackwards() throws Exception
	{
		addThreeExplicitSteps(); 
		assertNull(teacher.getNextStep()); 
		assertTrue(teacher.getPreviousStep() instanceof MeanStep);  
		assertTrue(teacher.getNextStep() instanceof MeanStep);  
	}
	protected void addThreeExplicitSteps()
	{
		step1 = new AddScoreStep(teacher, variable, 1.2, 0, true); 
		step1.execute(); 
		step2 = new AddScoreStep(teacher, variable, 1.0, 1, true); 
		step2.execute(); 
		step3 = new MeanStep(teacher, variable, true); 
		step3.execute();
	}
}

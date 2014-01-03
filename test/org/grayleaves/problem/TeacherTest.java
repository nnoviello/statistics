package org.grayleaves.problem;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.grayleaves.problem.Step;
import org.grayleaves.problem.Teacher;
import org.junit.Before;
import org.junit.Test;
import org.resres.stats.AddScoreStep;
import org.resres.stats.MeanStep;
import org.resres.stats.NStep;
import org.resres.stats.SumOfScoresStep;
import org.resres.stats.Variable;

public class TeacherTest
{

	private Variable variable;
	private Teacher teacher;
	private Step step1;
	private Step step2;
	private Step step3;
	private String expectedOutput = "{\"fred\":{\"stepSequenceId\":\"nextStep\",\"visibility\":\"enabled\"}}";

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
		assertTrue(teacher.getPreviousLittleStep() instanceof MeanStep);  
		assertTrue(teacher.getPreviousLittleStep() instanceof SumOfScoresStep);  
		assertTrue(teacher.getPreviousLittleStep() instanceof NStep);  
		assertTrue(teacher.getPreviousLittleStep() instanceof AddScoreStep);  
		assertTrue(teacher.getPreviousLittleStep() instanceof AddScoreStep);  
		assertNull(teacher.getPreviousLittleStep()); 
	}
	@Test
	public void verifyStepsCanBeReplayedForwardsAndBackwards() throws Exception
	{
		addThreeExplicitSteps(); 
		assertNull(teacher.getNextStep()); 
		assertTrue(teacher.getPreviousStep() instanceof MeanStep);  
		assertTrue(teacher.getNextStep() instanceof MeanStep);  
	}
	@Test
	public void verifyTeacherHasProblem() throws Exception
	{
		Problem problem = new TestingProblem(teacher); 
		teacher.addProblem(problem);
		assertEquals(problem, teacher.getProblems().get(0)); 
	}
	@Test
	public void verifyTeacherInvokesFirstProblemAndReturnsJsonOutput() throws Exception
	{
		Problem problem = new TestingProblemForTeacher(); 
		Problem problem2 = new TestingProblemForTeacher(); 
		teacher.addProblem(problem);
		teacher.addProblem(problem2);
		String jsonOutput = teacher.updateProblem("some json input");
		assertEquals(expectedOutput, jsonOutput); 
		assertTrue(((TestingProblemForTeacher) problem).executed); 
		assertFalse(((TestingProblemForTeacher) problem2).executed); 
	}
//	@Test
	public void verifyLogsErrorsFromProblem() throws Exception
	{
		//TODO verifyLogsErrorsFromProblem
	}
//	@Test
	public void verifyChoosesCorrectProblemToUpdateAmongMultipleProblems() throws Exception
	{
		//TODO verifyChoosesCorrectProblemToUpdateAmongMultipleProblems
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
	private class TestingProblemForTeacher extends AbstractProblem
	{
		public boolean executed;
		public TestingProblemForTeacher()
		{
			executed = false;
		}
		@Override
		public Map<String, InterfaceUpdate> update(String jsonInput) throws ProblemException
		{
			Map<String, InterfaceUpdate> update = new HashMap<String, InterfaceUpdate>();
			update.put("fred", new VisibilityOnlyInterfaceUpdate("nextStep", "enabled")); 
			executed = true; 
			return update  ;
		}
		@Override
		public Step buildStep(Update update, StepSequence stepSequence)
				throws ProblemException
		{
			return null;
		}
		
	}
}

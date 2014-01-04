package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProblemBuilderTest
{
	private ProblemBuilder builder;

	@Test
	public void verifyBuildsProblem() throws Exception
	{
		builder = new ProblemBuilder(); 
		builder.setTeacher(new Teacher()); 
		Problem problem = builder.buildProblem(ProblemEnum.BASIC_STATISTICS); 
		assertTrue(problem instanceof StatisticsProblem); 
		assertEquals("variableName", problem.getStepSequence(0).getId());
		assertEquals("score0", problem.getStepSequence(1).getId());
		assertEquals("score9", problem.getStepSequence(10).getId());
		assertEquals("sumOfScores", problem.getStepSequence(11).getId());
		assertEquals("n", problem.getStepSequence(12).getId());
		assertEquals("mean", problem.getStepSequence(13).getId());
		assertEquals("deviation", problem.getStepSequence(14).getId());
		assertEquals("squaredDeviation", problem.getStepSequence(15).getId());
		assertEquals("sumOfSquaredDeviations", problem.getStepSequence(16).getId());
		assertEquals("variance", problem.getStepSequence(17).getId());
		assertEquals("standardDeviation", problem.getStepSequence(18).getId());
	}

}
package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProblemBuilderTest
{
	private ProblemBuilder builder;

//	@Test
	public void verifyBuildsProblem() throws Exception
	{
		builder = new ProblemBuilder(); 
		builder.setTeacher(new Teacher()); 
		Problem problem = builder.buildProblem(ProblemEnum.BASIC_STATISTICS); 
		assertTrue(problem instanceof StatisticsProblem); 
	}

}

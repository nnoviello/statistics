package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.grayleaves.problem.StepEnum;
import org.grayleaves.problem.StepFactory;
import org.junit.Test;
import org.resres.stats.AddScoreStep;
import org.resres.stats.DeleteScoreStep;
import org.resres.stats.ReplaceScoreStep;
import org.resres.stats.Variable;

public class StepFactoryTest
{
	@Test
	public void verifyCommandsCreated() throws Exception
	{
		Teacher teacher = new Teacher(); 
		Variable variable = new Variable(); 
		variable.addScore(1); 
		assertTrue(StepFactory.buildStep(StepEnum.ADD_SCORE, teacher, variable, 1.1, 0, true) 
				instanceof AddScoreStep); 
		assertTrue(StepFactory.buildStep(StepEnum.DELETE_SCORE, teacher, variable, 1.1, 0, true) 
				instanceof DeleteScoreStep); 
		assertTrue(StepFactory.buildStep(StepEnum.REPLACE_SCORE, teacher, variable, 1.1, 0, true) 
				instanceof ReplaceScoreStep); 
	}
}

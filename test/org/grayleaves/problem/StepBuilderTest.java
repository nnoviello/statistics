package org.grayleaves.problem;

import static org.junit.Assert.*;

import java.util.List;

import org.grayleaves.problem.StepBuilder;
import org.grayleaves.problem.StepEnum;
import org.junit.Test;

public class StepBuilderTest
{

	@Test
	public void verifyBuildsStep() throws Exception
	{
		StepBuilder stepBuilder = new TestingStepBuilder();
		Step step = stepBuilder.buildStep(StepEnum.TESTING_STEP); 
		assertTrue(step instanceof TestingStep); 
	}
	private class TestingStepBuilder implements StepBuilder
	{
		@Override
		public Step buildStep(StepEnum stepEnum)
		{
			return new TestingStep();
		}
		
	}
	private class TestingStep implements Step 
	{

		@Override
		public void execute()
		{
		}

		@Override
		public String explain()
		{
			return null;
		}

		@Override
		public boolean explicitlyInvoked()
		{
			return false;
		}

		@Override
		public List<Step> getLittleSteps()
		{
			return null;
		}

		@Override
		public Double getResult()
		{
			return null;
		}
		
	}
}

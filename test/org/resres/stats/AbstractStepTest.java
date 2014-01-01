package org.resres.stats;

import static org.junit.Assert.*;

import java.util.List;

import org.grayleaves.problem.Step;
import org.junit.Before;
import org.junit.Test;

public class AbstractStepTest
{
	private Variable variable;
	private Step step;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 
		variable.setName("X"); 
		step = new TestingStep(variable, true); 
	}
	@Test
	public void verifyStepExplainsItself() throws Exception
	{
		assertEquals("This step does stuff with variable X, with great results.", step.explain()); 
	}
	@Test
	public void verifyStepCanInvokeLittleSteps() throws Exception
	{
		step.execute(); 
		List<Step> littleSteps = step.getLittleSteps(); 
		assertEquals(1, littleSteps.get(0).getResult(), .001); 
		assertEquals(2, littleSteps.get(1).getResult(), .001); 
		assertEquals(2, littleSteps.size() ); 
	}
	@Test
	public void verifyCommandTracksWhetherExplicitlyInvoked() throws Exception
	{
		step = new TestingStep(variable, true); 
		assertTrue(step.explicitlyInvoked()); 
		assertFalse("little steps implicitly invoked",step.getLittleSteps().get(0).explicitlyInvoked()); 
		assertFalse(step.getLittleSteps().get(1).explicitlyInvoked()); 
		
	}
	private class TestingStep extends AbstractStep
	{
		public TestingStep(Variable variable, boolean invoked)
		{
			super(variable, "This step does stuff with variable ", ", with great results.", invoked);
		}
		@Override
		public void execute()
		{
			super.execute(); 
		}
		@Override
		public Double getResult(){return 0d;}
		@Override
		protected void buildLittleSteps()
		{
			littleSteps.add(new TestingLittleStep(variable, 1)); 
			littleSteps.add(new TestingLittleStep(variable, 2)); 
		}
	}
	private class TestingLittleStep extends AbstractStep
	{
		private double order;
		public TestingLittleStep(Variable variable, double order)
		{
			super(null, variable, "This is a little step", ".", false);
			this.order = order; 
		}
		@Override
		public void execute()
		{
			super.execute();
		}
		@Override
		public Double getResult(){return order;}
	}
}

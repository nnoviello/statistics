package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractCommandTest
{
	@Test
	public void verifyCommandExplainsItself() throws Exception
	{
		Variable variable = new Variable(); 
		variable.setName("X"); 
		Command command = new TestingCommand(variable); 
		assertEquals("This command does stuff with variable X, with great results.", command.explain()); 
	}
	private class TestingCommand extends AbstractCommand
	{
		public TestingCommand(Variable variable)
		{
			super(variable, "This command does stuff with variable ", ", with great results.");
		}

		@Override
		public void execute()
		{
		}

		@Override
		public double getResult()
		{
			return 0;
		}
		
	}
}

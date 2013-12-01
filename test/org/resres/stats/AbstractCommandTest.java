package org.resres.stats;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AbstractCommandTest
{
	private Variable variable;
	private Command command;
	private TestingSubCommand subCommand;
	@Before
	public void setUp() throws Exception
	{
		variable = new Variable(); 
		variable.setName("X"); 
		command = new TestingCommand(variable, true); 
	}
	@Test
	public void verifyCommandExplainsItself() throws Exception
	{
		assertEquals("This command does stuff with variable X, with great results.", command.explain()); 
	}
	@Test
	public void verifyCommandCanInvokeSubcommands() throws Exception
	{
		command.execute(); 
		List<Command> subCommands = command.getSubcommands(); 
		assertEquals(1, subCommands.get(0).getResult(), .001); 
		assertEquals(2, subCommands.get(1).getResult(), .001); 
		assertEquals(2, subCommands.size() ); 
	}
	@Test
	public void verifyCommandTracksWhetherExplicitlyInvoked() throws Exception
	{
		command = new TestingCommand(variable, true); 
		assertTrue(command.explicitlyInvoked()); 
		assertFalse("sub commands implicitly invoked",command.getSubcommands().get(0).explicitlyInvoked()); 
		assertFalse(command.getSubcommands().get(1).explicitlyInvoked()); 
		
	}
	private class TestingCommand extends AbstractCommand
	{
		public TestingCommand(Variable variable, boolean invoked)
		{
			super(variable, "This command does stuff with variable ", ", with great results.", invoked);
		}
		@Override
		public void execute()
		{
			super.execute(); 
		}
		@Override
		public double getResult(){return 0;}
		@Override
		protected void buildSubcommands()
		{
			subcommands.add(new TestingSubCommand(variable, 1)); 
			subcommands.add(new TestingSubCommand(variable, 2)); 
		}
	}
	private class TestingSubCommand extends AbstractCommand
	{
		private int order;
		public TestingSubCommand(Variable variable, int order)
		{
			super(variable, "This is a subcommand", ".", false);
			this.order = order; 
		}
		@Override
		public void execute()
		{
			super.execute();
		}
		@Override
		public double getResult(){return order;}
	}
}

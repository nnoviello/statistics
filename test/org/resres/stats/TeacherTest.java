package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TeacherTest
{

	private Variable variable;
	private Teacher teacher;
	private Command command1;
	private Command command2;

	@Before
	public void setUp() throws Exception
	{
		//TODO CreateVariableCommand
		teacher = new Teacher(); 
		variable = new Variable(); 
		variable.setName("X"); 
	}
	protected void addTwoExplicitCommands()
	{
		command1 = new AddScoreCommand(teacher, variable, 1.2, true); 
		command1.execute(); 
		command2 = new NCommand(teacher, variable, true); 
		command2.execute();
	}
	@Test
	public void verifyTeacherRemembersCommandsInOrderTheyWereIssued() throws Exception
	{
		addTwoExplicitCommands(); 
		assertEquals(command2.explain(), teacher.explainLastCommand()); 
		assertEquals(command1.explain(), teacher.explainLastCommand()); 
		assertEquals("No earlier commands to explain.", teacher.explainLastCommand()); 
	}
	@Test
	public void verifyTeacherOnlyReturnsExplicitlyInvokedCommandsByDefault() throws Exception
	{
		addThreeExplicitCommands(); 
		assertTrue(teacher.getLastCommand() instanceof MeanCommand);  
		assertTrue(teacher.getLastCommand() instanceof AddScoreCommand);  
		assertTrue(teacher.getLastCommand() instanceof AddScoreCommand);  
		assertNull(teacher.getLastCommand()); 
	}
	protected void addThreeExplicitCommands()
	{
		Command command = new AddScoreCommand(teacher, variable, 1.2, true); 
		command.execute(); 
		command = new AddScoreCommand(teacher, variable, 1.0, true); 
		command.execute(); 
		command = new MeanCommand(teacher, variable, true); 
		command.execute();
	}
	@Test
	public void verifyTeacherReturnsDetailedCommandsOnRequest() throws Exception
	{
		addThreeExplicitCommands(); 
		assertTrue(teacher.getLastDetailedCommand() instanceof MeanCommand);  
		assertTrue(teacher.getLastDetailedCommand() instanceof SumCommand);  
		assertTrue(teacher.getLastDetailedCommand() instanceof NCommand);  
		assertTrue(teacher.getLastDetailedCommand() instanceof AddScoreCommand);  
		assertTrue(teacher.getLastDetailedCommand() instanceof AddScoreCommand);  
		assertNull(teacher.getLastDetailedCommand()); 
		
	}
}

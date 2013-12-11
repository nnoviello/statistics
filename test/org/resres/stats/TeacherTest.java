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
	private Command command3;

	@Before
	public void setUp() throws Exception
	{
		//TODO CreateVariableCommand
		teacher = new Teacher(); 
		variable = new Variable(); 
		variable.setName("X"); 
	}
	@Test
	public void verifyTeacherExplainsCommandsInOrderTheyWereIssued() throws Exception
	{
		//TODO consider flows:  "explain last thing you did"; "go back n steps, explain from there"; explain last step in detail" 
		addThreeExplicitCommands(); 
		assertEquals(command3.explain(), teacher.explainPreviousCommand()); 
		assertEquals(command2.explain(), teacher.explainPreviousCommand()); 
		assertEquals(command1.explain(), teacher.explainPreviousCommand()); 
		assertEquals("No earlier commands to explain.", teacher.explainPreviousCommand()); 
		assertEquals(command1.explain(), teacher.explainNextCommand()); 
	}
	@Test
	public void verifyTeacherOnlyReturnsExplicitlyInvokedCommandsByDefault() throws Exception
	{
		addThreeExplicitCommands(); 
		assertTrue(teacher.getPreviousCommand() instanceof MeanCommand);  
		assertTrue(teacher.getPreviousCommand() instanceof AddScoreCommand);  
		assertTrue(teacher.getPreviousCommand() instanceof AddScoreCommand);  
		assertNull(teacher.getPreviousCommand()); 
	}
	@Test
	public void verifyTeacherReturnsDetailedCommandsOnRequest() throws Exception
	{
		addThreeExplicitCommands(); 
		assertTrue(teacher.getPreviousDetailedCommand() instanceof MeanCommand);  
		assertTrue(teacher.getPreviousDetailedCommand() instanceof SumOfScoresCommand);  
		assertTrue(teacher.getPreviousDetailedCommand() instanceof NCommand);  
		assertTrue(teacher.getPreviousDetailedCommand() instanceof AddScoreCommand);  
		assertTrue(teacher.getPreviousDetailedCommand() instanceof AddScoreCommand);  
		assertNull(teacher.getPreviousDetailedCommand()); 
	}
	@Test
	public void verifyCommandsCanBeReplayedForwardsAndBackwards() throws Exception
	{
		addThreeExplicitCommands(); 
		assertNull(teacher.getNextCommand()); 
		assertTrue(teacher.getPreviousCommand() instanceof MeanCommand);  
		assertTrue(teacher.getNextCommand() instanceof MeanCommand);  
	}
	protected void addThreeExplicitCommands()
	{
		command1 = new AddScoreCommand(teacher, variable, 1.2, 0, true); 
		command1.execute(); 
		command2 = new AddScoreCommand(teacher, variable, 1.0, 1, true); 
		command2.execute(); 
		command3 = new MeanCommand(teacher, variable, true); 
		command3.execute();
	}
}

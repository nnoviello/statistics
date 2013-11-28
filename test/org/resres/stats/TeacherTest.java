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
		command1 = new AddScoreCommand(teacher, variable, 1.2); 
		command1.execute(); 
		command2 = new NCommand(teacher, variable); 
		command2.execute(); 
	}
	@Test
	public void verifyTeacherRemembersCommandsInOrderTheyWereIssued() throws Exception
	{
		assertEquals(command2.explain(), teacher.explainLastCommand()); 
		assertEquals(command1.explain(), teacher.explainLastCommand()); 
		assertEquals("No earlier commands to explain.", teacher.explainLastCommand()); 
	}
}

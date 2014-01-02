package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UpdateTest
{

	private Update update;
	@Before
	public void setUp() throws Exception
	{
		update = new TestingUpdate(3, "testingStep"); 
		
	}
	@Test
	public void verifyUpdateHasStepInformation() throws Exception
	{
		assertEquals("testingStep3", update.getStepSequenceId());  
		assertEquals("testingStep", update.getUpdateStep());  
		
	}
}

package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.grayleaves.problem.InvalidJsonUpdateException;
import org.grayleaves.problem.JsonUpdate;
import org.grayleaves.problem.TestingUpdate;
import org.grayleaves.problem.Update;
import org.junit.Before;
import org.junit.Test;

public class JsonUpdateTest
{

	private String jsonInput = "{\"updateJavaClass\":\"org.grayleaves.problem.TestingUpdate\",\"id\":5,\"field\":\"value1\",\"someArray\":[7,8,9]}";
	private String jsonInputNoUpdateClass = "{\"id\":5,\"field\":\"value1\",\"someArray\":[7,8,9]}";
	private String jsonInputNonexistentClass = "{\"updateJavaClass\":\"org.resres.stats.controller.NonexistentUpdate\",\"id\":5,\"field\":\"value1\",\"someArray\":[7,8,9]}";
	private JsonUpdate updater;	
	//TODO tests for malformed input
	@Before
	public void setUp() throws Exception
	{
		updater = new JsonUpdate(); 
	}
	@Test
	public void verifyCreatesUpdateClassFromJsonInputGivenFirstFieldIsTargetClass() throws Exception
	{
		Update update = updater.getUpdate(jsonInput);  
		assertTrue(update instanceof TestingUpdate); 
		TestingUpdate testingUpdate = (TestingUpdate) update;  
		assertEquals(5, testingUpdate.id);
		assertEquals("value1", testingUpdate.field);
		assertEquals(9, testingUpdate.someArray[2]);
	}
	@Test
	public void verifyThrowsIfUpdateClassNotInJsonInput() throws Exception
	{
		try
		{
			@SuppressWarnings("unused")
			Update update = updater.getUpdate(jsonInputNoUpdateClass);  
			fail("should throw"); 
		}
		catch (InvalidJsonUpdateException e)
		{
			assertTrue(e.getMessage().startsWith(JsonUpdate.UPDATE_JAVA_CLASS_NOT_IN_INPUT)); 
		}
	}
	@Test
	public void verifyThrowsIfClassFromJsonInputDoesNotExist() throws Exception
	{
		try
		{
			@SuppressWarnings("unused")
			Update update = updater.getUpdate(jsonInputNonexistentClass);  
			fail("should throw"); 
		}
		catch (InvalidJsonUpdateException e)
		{
			assertEquals(JsonUpdate.UPDATE_CLASS+"org.resres.stats.controller.NonexistentUpdate"+JsonUpdate.DOES_NOT_EXIST, e.getMessage()); 
		}
	}
	

}

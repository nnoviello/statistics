package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VariableTest {
	private Variable variable;
	@Before
	public void setUp() throws Exception
	{
		
		variable = new Variable();
	}
	@Test
	public void verifyVariableName() 
	{
		assertEquals("default name","Var1", variable.getName());
		variable.setName("X");
		assertEquals("X", variable.getName());
	}
	@Test
	public void verifyCanAddScoreToVariable() throws Exception {
		assertEquals(0, variable.getScores().size());
		variable.addScore(1.2);
		assertEquals(1, variable.getScores().size());
	}
	@Test
	public void verifyCountOfEnteredScores() throws Exception {
		assertEquals(0, variable.getN()); 
		variable.addScore(1.2);
		assertEquals(1, variable.getN()); 
		variable.addScore(1.2);
		assertEquals(2, variable.getN()); 
	}
	@Test
	public void verifyTracksFrequencyOfEachScore() throws Exception
	{
		assertEquals(0, variable.getFrequency(1.2));
		variable.addScore(1.2); 
		variable.addScore(1.0); 
		variable.addScore(1.2); 
		assertEquals(2, variable.getFrequency(1.2));
		assertEquals(1, variable.getFrequency(1.0));
	}
	@Test
	public void verifyRecalculatesWhenStatisticsRequested() throws Exception
	{
		variable.addScore(1.2); 
		variable.addScore(1.0); 
		variable.addScore(1.2); 
		assertEquals("map not yet updated",null,variable.frequencies.get(1.2)); 
		assertEquals("now updated",2, variable.getFrequency(1.2)); 
	}
	@Test
	public void verifyCalculatesSumOfScores() throws Exception
	{
		assertEquals(0, variable.getSum(), .001); 
		variable.addScore(1.2); 
		assertEquals(1.2, variable.getSum(), .001); 
		variable.addScore(1.0); 
		assertEquals(2.2, variable.getSum(), .001); 
	}
	@Test
	public void verifyCalculatesMean() throws Exception
	{
		assertEquals(0, variable.getMean(), .001); 
		variable.addScore(1.2); 
		assertEquals(1.2, variable.getMean(), .001); 
		variable.addScore(1.0); 
		assertEquals(1.1, variable.getMean(), .001); 
		variable.addScore(1.2); 
		assertEquals(1.133, variable.getMean(), .001); 
	}
}

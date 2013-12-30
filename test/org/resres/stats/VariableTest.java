package org.resres.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VariableTest {
	//TODO tests for index out of bounds
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
		assertEquals(0, variable.getSumOfScores(), .001); 
		variable.addScore(1.2); 
		assertEquals(1.2, variable.getSumOfScores(), .001); 
		variable.addScore(1.0); 
		assertEquals(2.2, variable.getSumOfScores(), .001); 
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
	//TODO calculate deviations from frequencies
	@Test
	public void verifyDeviationsCalculatedForIndividualScores() throws Exception
	{
		variable.addScore(0, 1.2); 
		variable.addScore(1, 1.0); 
		variable.addScore(2, 1.1); 
		assertEquals(0.1, variable.getDeviations().get(0), .001); 
		assertEquals(-0.1, variable.getDeviations().get(1), .001); 
		assertEquals(0.0, variable.getDeviations().get(2), .001); 
	}
	@Test
	public void verifySquaredDeviationsCalculatedForIndividualScores() throws Exception
	{
		variable.addScore(0, 1.2); 
		variable.addScore(1, 1.0); 
		variable.addScore(2, 1.1); 
		assertEquals(0.01, variable.getSquaredDeviations().get(0), .001); 
		assertEquals(0.01, variable.getSquaredDeviations().get(1), .001); 
		assertEquals(0.0, variable.getSquaredDeviations().get(2), .001); 
	}
	@Test
	public void verifyCalculatesSumOfSquaredDeviations() throws Exception
	{
		variable.addScore(0, 1.2); 
		variable.addScore(1, 1.0); 
		variable.addScore(2, 1.1); 
		assertEquals(0.02, variable.getSumOfSquaredDeviations(), .001); 
	}
	@Test
	public void verifyScoreAddedAtPosition() throws Exception
	{
		variable.addScore(0, 1.2); 
		variable.addScore(1, 1.1); 
		variable.addScore(0, 1.0); 
		assertEquals(1.0, variable.getScores().get(0), .001); 
		assertEquals(1.2, variable.getScores().get(1), .001); 
		assertEquals(1.1, variable.getScores().get(2), .001); 
		assertEquals(3,variable.getScores().size()); 
	}
	@Test
	public void verifyScoreDeletedAtPosition() throws Exception
	{
		variable.addScore(0, 1.0); 
		variable.addScore(1, 1.1); 
		variable.addScore(2, 1.2); 
		assertEquals(3,variable.getScores().size()); 
		variable.deleteScore(0); 
		assertEquals(1.1, variable.getScores().get(0), .001); 
		assertEquals(1.2, variable.getScores().get(1), .001); 
		assertEquals(2,variable.getScores().size()); 
	}
	@Test
	public void verifyScoreReplacedAtPosition() throws Exception
	{
		variable.addScore(0, 1.2); 
		variable.addScore(1, 1.2); 
		assertEquals(2,variable.getScores().size()); 
		variable.replaceScore(0, 1.0); 
		assertEquals(1.1, variable.getMean(), .001); 
		assertEquals(2,variable.getScores().size()); 
	}
	@Test
	public void verifyEquals() throws Exception
	{
		variable.setName("X"); 
		variable.addScore(0, 1.0); 
		variable.addScore(1, 1.1); 
		variable.addScore(2, 1.2);
		Variable variable2 = new Variable(); 
		assertTrue(!variable.equals(variable2)); 
		variable2.setName("X"); 
		assertTrue(!variable.equals(variable2)); 
		variable2.addScore(0, 1.0); 
		variable2.addScore(1, 1.1); 
		assertTrue(!variable.equals(variable2)); 
		variable2.addScore(2, 1.3);
		assertTrue(!variable.equals(variable2)); 
		variable2.replaceScore(2, 1.2);
		assertTrue("finally...",variable.equals(variable2)); 
	}
	@Test
	public void verifyCalculationRequiredIffListOfScoresHasChanged() throws Exception
	{	
		assertTrue("without scores, some trivial stats available",variable.hasReadyStatistics()); 
		variable.addScore(1.2); 
		assertFalse("as scores are added, stats not available",variable.hasReadyStatistics()); 
		variable.calculate(); 
		assertTrue("stats now available",variable.hasReadyStatistics()); 
		variable.replaceScore(0, 2.0); 
		assertFalse("replace -> stats not available",variable.hasReadyStatistics()); 
		variable.deleteScore(0); 
		assertFalse("delete -> stats not available",variable.hasReadyStatistics()); 
	}
	@Test
	public void verifyInvocationOfAnyStatsMethodTriggersCalculationAndMakesStatsReady() throws Exception
	{
		variable.addScore(1.2); 
		variable.getN(); 
		assertTrue("stats now available",variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getMean(); 
		assertTrue(variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getDeviations(); 
		assertTrue(variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getFrequency(1.3);
		assertTrue(variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getSquaredDeviations();
		assertTrue(variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getSumOfScores();
		assertTrue(variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getSumOfSquaredDeviations();
		assertTrue(variable.hasReadyStatistics()); 

		variable.addScore(1.3);
		variable.getName();
		assertFalse("but Name is not a stat; stats not calculated",variable.hasReadyStatistics()); 
		variable.addScore(1.3);
		variable.getScores();
		assertFalse("Scores is just a list; stats not calculated ",variable.hasReadyStatistics()); 
	}
}

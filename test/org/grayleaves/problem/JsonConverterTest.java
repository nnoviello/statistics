package org.grayleaves.problem;

import static org.junit.Assert.*;

import org.junit.Test;
import org.resres.stats.Variable;

import com.google.gson.Gson;

public class JsonConverterTest
{

	private Variable variable;
	private Gson gson;

	@Test
	public void verifyVariableConvertsToAndFromJsonCorrectly() throws Exception
	{
		variable = new Variable(); 
		variable.setName("X"); 
		variable.addScore(0, 1.0); 
		variable.addScore(1, 1.1); 
		variable.addScore(2, 1.2); 
		variable.addScore(3, 1.1); 
		gson = new Gson(); 
		String jsonObj = gson.toJson(variable); 
		Variable variable2 = gson.fromJson(jsonObj, Variable.class); 
		assertEquals(variable, variable2); 
		assertEquals(2, variable2.getFrequency(1.1));
	}
}

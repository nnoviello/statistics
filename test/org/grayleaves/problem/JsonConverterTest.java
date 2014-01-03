package org.grayleaves.problem;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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
//	@Test
	public void verifyGsonDealsWithMaps() throws Exception
	{
		Map<String, NullInterfaceUpdate> map = new HashMap<String, NullInterfaceUpdate>(); 
		map.put("fred", new NullInterfaceUpdate()); 
//		Map<String, Variable> map = new HashMap<String, Variable>(); 
//		variable = new Variable(); 
//		variable.setName("fred");
//		variable.addScore(1.2); 
//		Variable variable2 = new Variable(); 
//		variable2.setName("mary");
//		variable2.addScore(1.9); 
//		map.put("any", variable);
//		map.put("benny", variable2);
		gson = new Gson(); 
		System.out.println(gson.toJson(map));
		
	}
}

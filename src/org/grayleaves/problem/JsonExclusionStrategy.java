package org.grayleaves.problem;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class JsonExclusionStrategy implements ExclusionStrategy
{

	@Override
	public boolean shouldSkipClass(Class<?> arg0)
	{
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes fieldAttributes)
	{
		return (fieldAttributes.getAnnotation(JsonSkip.class) != null);
	}

}

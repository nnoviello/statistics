package org.grayleaves.problem;

public enum VisibilityEnum
{

	ENABLED { public String getVisibility() { return "enable";}},
	DISABLED { public String getVisibility() { return "disable";}},
	HIDDEN { public String getVisibility() { return "hide";}};
	
	public abstract String getVisibility(); 

	
}

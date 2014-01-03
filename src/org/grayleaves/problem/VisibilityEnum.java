package org.grayleaves.problem;

public enum VisibilityEnum
{

	ENABLED { public String getVisibility() { return "enabled";}},
	DISABLED { public String getVisibility() { return "disabled";}},
	HIDDEN { public String getVisibility() { return "hidden";}};
	
	public abstract String getVisibility(); 

	
}

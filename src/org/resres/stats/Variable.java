package org.resres.stats;

import java.util.ArrayList;
import java.util.List;

public class Variable {

	private String name;
	private List<Double> scores;
	public Variable() {
		scores = new ArrayList<Double>();
	}
	public void setName(String name) {
		this.name = name;		
	}

	public String getName() {
		return name;
	}

	public void addScore(double d) {
		scores.add(d);
		
	}

	public List getScores() {
		return scores;
	}

}

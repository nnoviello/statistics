package org.resres.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variable {

	private String name = "Var1";
	private List<Double> scores;
	protected Map<Double,Integer> frequencies;
	protected double sum;
	protected double mean;
	protected int n;
	public Variable() {
		scores = new ArrayList<Double>();
		initFrequencyMap();
	}
	public void addScore(double d) {
		scores.add(d);
		
	}

	public List getScores() {
		return scores;
	}
	public int getN()
	{
		calculate(); 
		return n;
	}
	public int getFrequency(double d)
	{
		calculate(); 
		return frequencyForScore(d);
	}
	private void initFrequencyMap()
	{
		frequencies = new HashMap<Double, Integer>();
	}
	protected void calculate()
	{
		buildFrequencyMap(); 
		calculateN(); 
		calculateSum();
		calculateMean(); 
	}
	protected void calculateN()
	{
		n = scores.size(); 
	}
	private void buildFrequencyMap()
	{
		initFrequencyMap();
		for (Double score : scores)
		{
			Integer count = frequencies.get(score);  
			if (count == null) frequencies.put(score, 1);
			else frequencies.put(score, ++count); 
		}
	}
	private int frequencyForScore(double score)
	{
		Integer count = frequencies.get(score);  
		return (count != null) ? count : 0;
	}

	public void setName(String name) {
		this.name = name;		
	}
	public String getName() {
		return name;
	}
	public double getSum()
	{
		calculate(); 
		return sum;
	}
	protected void calculateSum()
	{
		sum = 0; 
		for (double score : scores)
		{
			sum+=score; 
		}
	}
	protected void calculateMean()
	{
		mean = 0; 
		if (n > 0)
		{
			mean = sum / ((double) n); 
		}
	}
	public double getMean()
	{
		calculate(); 
		return mean;
	}
}

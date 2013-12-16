package org.resres.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variable 
{

	private String name = "Var1";
	private List<Double> scores;
	protected Map<Double,Integer> frequencies;
	protected double sumOfScores;
	protected double mean;
	protected int n;
	protected List<Double> deviations;
	protected List<Double> squaredDeviations;
	protected double sumOfSquaredDeviations;
	public Variable() {
		scores = new ArrayList<Double>();
		deviations = new ArrayList<Double>();
		squaredDeviations = new ArrayList<Double>(); 
		initFrequencyMap();
	}
	public void addScore(double score) {
		addScore(scores.size(), score);  
	}
	public void addScore(int index, double score)
	{
		scores.add(index, score);
	}
	public void deleteScore(int index)
	{
		scores.remove(index); 
	}
	public void replaceScore(int index, double score)
	{
		deleteScore(index);
		addScore(index, score); 
	}

	public List<Double> getScores() {
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
	//TODO only calculate if we have changed state since last calculation
	protected void calculate()
	{
		buildFrequencyMap(); 
		calculateN(); 
		calculateSumOfScores();
		calculateMean();
		calculateDeviations(); 
		calculateSquaredDeviations(); 
		calculateSumOfSquaredDeviations();
		
	}
	protected void calculateDeviations()
	{
		for (Double score : scores)
		{
			deviations.add(score - mean); 
		}
	}
	private void calculateSquaredDeviations()
	{
		for (Double deviation: deviations)
		{
			squaredDeviations.add(deviation*deviation); 
		}
	}
	private void calculateSumOfSquaredDeviations()
	{
		sumOfSquaredDeviations = 0; 
		for (double squaredDeviation : squaredDeviations)
		{
			sumOfSquaredDeviations+=squaredDeviation; 
		}
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
	public double getSumOfScores()
	{
		calculate(); 
		return sumOfScores;
	}
	protected void calculateSumOfScores()
	{
		sumOfScores = 0; 
		for (double score : scores)
		{
			sumOfScores+=score; 
		}
	}
	protected void calculateMean()
	{
		mean = 0; 
		if (n > 0)
		{
			mean = sumOfScores / ((double) n); 
		}
	}
	public double getMean()
	{
		calculate(); 
		return mean;
	}
	public List<Double> getDeviations()
	{
		calculate();
		return deviations;
	}
	public List<Double> getSquaredDeviations()
	{
		calculate(); 
		return squaredDeviations;
	}
	public double getSumOfSquaredDeviations()
	{
		calculate(); 
		return sumOfSquaredDeviations;
	}
	@Override
	public boolean equals(Object arg)
	{
		if (arg == null) return false;
		if (this.getClass() != arg.getClass()) return false;
		Variable otherVariable = (Variable) arg; 
		if (!this.name.equals(otherVariable.name)) return false; 
		if (this.scores.size() != otherVariable.scores.size()) return false;
		for (int i = 0; i < this.scores.size(); i++)
		{
			if (!this.scores.get(i).equals(otherVariable.scores.get(i))) return false;
		}
		return true; 
	}
}

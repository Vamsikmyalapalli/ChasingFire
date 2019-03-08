package testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import grading.Grade;
import grading.SizeException;
import grading.WeightedTotalStrategy;

public class WeightedTotalStrategyTest 
{
	private HashMap<String,Double> midterm, originalMidterm;
	private ArrayList<Grade> grades, originalGrades;

	private ArrayList<Grade> copyList(ArrayList<Grade> original)
	{
		ArrayList<Grade> result = new ArrayList<Grade>();
		for (int i=0; i<original.size(); i++)
		{
			result.add(original.get(i));
		}
		return result;
	}

	private HashMap<String, Double> copyMap(HashMap<String, Double> original)
	{
		HashMap<String, Double> result = new HashMap<String, Double>();
		Iterator<String> i = original.keySet().iterator();
		while (i.hasNext())
		{	
			String key = i.next();
			result.put(key,  original.get(key));
		}
		return result;
	}
	
	
	@Before
	public void initList()
	{
		grades = new ArrayList<Grade>();
		grades.add(new Grade("TF", 1.0));
		grades.add(new Grade("TF", 0.0));
		grades.add(new Grade("SA", 0.0));
		grades.add(new Grade("SA", 0.5));
		grades.add(new Grade("SA", 1.0));
		grades.add(new Grade("SA", 0.0));
		
		originalGrades = copyList(grades);
	}

	@Before
	public void initWeights()
	{
		midterm = new HashMap<String, Double>();
		midterm.put("TF", 20.0);
		midterm.put("SA", 15.0);
		
		originalMidterm = copyMap(midterm);
	}

	@Test(expected = SizeException.class)
	public void nullList_nullMap() throws SizeException
	{
		WeightedTotalStrategy wts;

		wts = new WeightedTotalStrategy();
		wts.calculate("Midterm", null);
	}

	@Test(expected = SizeException.class)
	public void nullList_goodMap() throws SizeException
	{
		WeightedTotalStrategy wts;

		wts = new WeightedTotalStrategy(midterm);
		wts.calculate("Midterm", null);
	}

	@Test
	public void goodList_goodMap() throws SizeException
	{
		WeightedTotalStrategy wts;
		Grade                 g;

		wts = new WeightedTotalStrategy(midterm);
		g = wts.calculate("Midterm", grades);
		assertEquals("Weighted: Good List, Good Map", 42.5, g.getValue(), 0.01);


		boolean ok;
		ok = true;
		for (int i=0; i<grades.size(); i++)
		{
			ok &= (grades.get(i).getKey().equals(originalGrades.get(i).getKey()));
			ok &= (grades.get(i).getValue().equals(originalGrades.get(i).getValue()));
		}
		assertTrue("Weighted: Good List, Good Map [List Unchanged]", ok);
		

		ok = true;
		Iterator<String> i = midterm.keySet().iterator();
		while (i.hasNext())
		{
			String key = i.next();
			ok &= midterm.get(key).equals(originalMidterm.get(key));
		}
		assertTrue("Weighted: Good List, Good Map [Map Unchanged]", ok);
	}


	@Test
	public void goodList_partialMap() throws SizeException
	{
		WeightedTotalStrategy wts;
		Grade                 g;

		grades.add(new Grade("MC", 1.0)); // Should use a weight of 1
		wts = new WeightedTotalStrategy(midterm);
		g = wts.calculate("Midterm", grades);
		assertEquals("Weighted: Good List, Partial Map", 43.5, g.getValue(), 0.01);
	}


	@Test
	public void goodList_badMap() throws SizeException
	{
		WeightedTotalStrategy wts;
		Grade                 g;

		midterm.put("TF", -1.5); // Should use a default weight of 0
		wts = new WeightedTotalStrategy(midterm);
		g = wts.calculate("Midterm", grades);
		assertEquals("Weighted: Good List, Map with Bad Weight", 22.5, g.getValue(), 0.01);
	}


	@Test
	public void missingInList_goodMap() throws SizeException
	{
		WeightedTotalStrategy wts;
		Grade                 g;

		grades.add(new Grade("SA", null)); // Should use a default grade of 0
		wts = new WeightedTotalStrategy(midterm);
		g = wts.calculate("Midterm", grades);
		assertEquals("Weighted: Missing in List, Good Map", 42.5, g.getValue(), 0.01);
	}


	@Test
	public void missingInList_partialMap() throws SizeException
	{
		WeightedTotalStrategy wts;
		Grade                  g;

		grades.add(new Grade("MC", null)); // Should use a default grade of 0 and weight of 1
		wts = new WeightedTotalStrategy(midterm);
		g = wts.calculate("Midterm", grades);
		assertEquals("Weighted: Missing In List, Partial Map", 42.5, g.getValue(), 0.01);
	}


	@Test
	public void missingAll() throws SizeException
	{
		WeightedTotalStrategy wts;
		Grade                 g;

		grades = new ArrayList<Grade>();
		grades.add(new Grade("TF", null));
		grades.add(new Grade("SA", null));
		grades.add(new Grade("MC", null));
		wts = new WeightedTotalStrategy(midterm);
		g = wts.calculate("Bart Simpson", grades);
		assertEquals("Weighted: Missing All", 0.0, g.getValue(), 0.01);
	}
}

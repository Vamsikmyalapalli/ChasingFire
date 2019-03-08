package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import grading.DropFilter;
import grading.Filter;
import grading.Grade;
import grading.GradingStrategy;
import grading.SizeException;
import grading.TotalStrategy;
import grading.WeightedTotalStrategy;

public class IntegrationTests 
{
	// Data for Complete 01
	private double[]           complete_01_pa = {20., 18., 5., 15., 20., 20.};
	private double[]           complete_01_hw = {20., 0., 5., 10., 15.};
	private double             complete_01_midterm  = 80.;
	private double             complete_01_final    = 75.;
	private double             complete_01_expected = 80.7;

	// Data for Complete 02
	private double[]           complete_02_pa = {20., 20., 20., 20., 20., 20.};
	private double[]           complete_02_hw = {20., 20., 20., 20., 20.};
	private double             complete_02_midterm  = 100.;
	private double             complete_02_final    = 100.;
	private double             complete_02_expected = 100.0;
	
	// Data for Complete 03
	private double[]           complete_03_pa = {10., 10., 5., 15., 5., 20.};
	private double[]           complete_03_hw = {20., 0., 0., 10., 15.};
	private double             complete_03_midterm  = 60.;
	private double             complete_03_final    = 45.;
	private double             complete_03_expected = 54.0;

	// Data for Missing One in One
	private double[]           missing_oneinone_pa = {20., 18., 5., 15., 20., 20.};
	private double[]           missing_oneinone_hw = {20., -1., 5., 10., 15.};
	private double             missing_oneinone_midterm  = 80.;
	private double             missing_oneinone_final    = 75.;
	private double             missing_oneinone_expected = 80.7;
	
	// Data for Missing One in Each
	private double[]           missing_oneineach_pa = {20., 18., 5., 15., -1., 20.};
	private double[]           missing_oneineach_hw = {20., 5., 0., -1., 15.};
	private double             missing_oneineach_midterm  = -1.;
	private double             missing_oneineach_final    = -1.;
	private double             missing_oneineach_expected = 35.2;
	
	// Data for Missing Multiple in Each
	private double[]           missing_multipleineach_pa = {-1., 20., 5., 15., -1., -1.};
	private double[]           missing_multipleineach_hw = {20., -1., 0., -1., 15.};
	private double             missing_multipleineach_midterm  = -1.;
	private double             missing_multipleineach_final    = -1.;
	private double             missing_multipleineach_expected = 19.5;

	// Data for Missing All
	private double[]           missing_all_pa = {-1., -1., -1., -1., -1., -1.};
	private double[]           missing_all_hw = {-1., -1., -1., -1., -1.};
	private double             missing_all_midterm  = -1.;
	private double             missing_all_final    = -1.;
	private double             missing_all_expected = 0.0;

	// Data for all tests
	private double[]           weights    = {0.40, 0.10, 0.20, 0.30};
	private String[]           categories = {"PA", "HW", "Midterm", "Final"};
	
	// Objects for all tests (that are re-initialized before each test)
	private Filter             dropFilter;
	private GradingStrategy    totalStrategy, weightedTotalStrategy;
	private Map<String,Double> weightMap;
	
	private static final double EPSILON = 0.01;

	
	private Grade createGrade(String key, double value)
	{
		if (value < 0.0) return new Grade(key, null);
		else             return new Grade(key, value);
	}
	
	private List<Grade> createGrades(String prefix, double[] values)
	{
		List<Grade> result = new ArrayList<Grade>();
		for (int i=0; i<values.length; i++)
		{
			Double value;
			if (values[i] < 0.0) value = null;
			else                 value = new Double(values[i]);
			result.add(new Grade(prefix+String.format("%2d", i), value));
		}
		return result;
	}
	
	private Grade calculateGrade(String key, Filter filter, GradingStrategy strategy, List<Grade> grades) throws SizeException
	{
		List<Grade> applicable;
		
		if (filter != null) applicable = filter.apply(grades);
		else                applicable = grades;
		
		return strategy.calculate(key, applicable);
	}
	
	@Before
	public void initialize()
	{
		dropFilter    = new DropFilter(true, false);
		totalStrategy = new TotalStrategy();
		
		weightMap = new HashMap<String,Double>();
		for (int i=0; i<categories.length; i++) weightMap.put(categories[i], new Double(weights[i]));
		weightedTotalStrategy = new WeightedTotalStrategy(weightMap);
	}
	
	@Test
	public void complete_01() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], complete_01_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], complete_01_hw)));
		grades.add(createGrade(categories[2], complete_01_midterm));
		grades.add(createGrade(categories[3], complete_01_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Complete 01", complete_01_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}

	@Test
	public void complete_02() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], complete_02_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], complete_02_hw)));
		grades.add(createGrade(categories[2], complete_02_midterm));
		grades.add(createGrade(categories[3], complete_02_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Complete 02", complete_02_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}
	
	@Test
	public void complete_03() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], complete_03_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], complete_03_hw)));
		grades.add(createGrade(categories[2], complete_03_midterm));
		grades.add(createGrade(categories[3], complete_03_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Complete 03", complete_03_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}
	
	@Test
	public void missing_oneinone() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], missing_oneinone_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], missing_oneinone_hw)));
		grades.add(createGrade(categories[2], missing_oneinone_midterm));
		grades.add(createGrade(categories[3], missing_oneinone_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Missing One in One Catgeory", missing_oneinone_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}
	
	@Test
	public void missing_oneineach() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], missing_oneineach_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], missing_oneineach_hw)));
		grades.add(createGrade(categories[2], missing_oneineach_midterm));
		grades.add(createGrade(categories[3], missing_oneineach_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Missing One in Each Catgeory", missing_oneineach_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}
	
	@Test
	public void missing_multipleineach() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], missing_multipleineach_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], missing_multipleineach_hw)));
		grades.add(createGrade(categories[2], missing_multipleineach_midterm));
		grades.add(createGrade(categories[3], missing_multipleineach_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Missing Multiple in Each Catgeory", missing_multipleineach_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}
	
	@Test
	public void missing_all() throws SizeException
	{   
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(calculateGrade(categories[0], dropFilter, totalStrategy, createGrades(categories[0], missing_all_pa)));
		grades.add(calculateGrade(categories[1], null,       totalStrategy, createGrades(categories[1], missing_all_hw)));
		grades.add(createGrade(categories[2], missing_all_midterm));
		grades.add(createGrade(categories[3], missing_all_final));
		
		Grade courseGrade = weightedTotalStrategy.calculate("Course Grade", grades);
		assertEquals("Missing All", missing_all_expected, courseGrade.getValue().doubleValue(), EPSILON);
	}
	
}

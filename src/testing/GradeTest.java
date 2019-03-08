package testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grading.Grade;

public class GradeTest
{
	private static final double EPSILON = 0.01;

	@Test(expected = IllegalArgumentException.class)
	public void constructorSD_NullKey() throws IllegalArgumentException
	{
		new Grade(null, 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorSD_EmptyKey() throws IllegalArgumentException
	{
		new Grade("", 0.0);
	}

	@Test
	public void constructors() throws IllegalArgumentException
	{
		double valued = 75.0;
		Double valueD = new Double(50.0); 
		Grade  g;
		String key    = "Exam";

		g = new Grade(key, valueD);
		assertEquals("Grade(String,Double); Checking key",   g.getKey(),   key);
		assertEquals("Grade(String,Double); Checking value", g.getValue(), valueD, EPSILON);

		g = new Grade(key, valued);
		assertEquals("Grade(String,double); Checking key",   g.getKey(),   key);
		assertEquals("Grade(String,double); Checking value", g.getValue(), valued, EPSILON);

		g = new Grade(key);
		assertEquals("Grade(String); Checking key",   g.getKey(),   key);
		assertEquals("Grade(String); Checking value", g.getValue(), 0.0, EPSILON);
	}


	@Test
	public void toStringTest() throws IllegalArgumentException
	{
		assertEquals("Grade.toString()", (new Grade("Exam",   0.0)).toString(),   "Exam:   0.0");
		assertEquals("Grade.toString()", (new Grade("Exam", 100.0)).toString(),   "Exam: 100.0");
		assertEquals("Grade.toString()", (new Grade("Exam",  50.9)).toString(),   "Exam:  50.9");
		assertEquals("Grade.toString()", (new Grade("Exam",  null)).toString(),   "Exam:    NA");
	}

	@Test
	public void compareToTest() throws IllegalArgumentException
	{
		Grade a = new Grade("Labs", 65.5);
		Grade b = new Grade("Exam", 65.5);
		Grade c = new Grade("PA1",  84.1);
		Grade h = new Grade("PA2", 100.0);
		Grade z = new Grade("PA3");
		Grade n = new Grade("HW1", null);
		Grade m = new Grade("HW2", null);
		
		assertEquals("a.compareTo(b)", a.compareTo(b),  0);
		assertEquals("b.compareTo(a)", b.compareTo(a),  0);
		assertEquals("a.compareTo(c)", a.compareTo(c), -1);
		assertEquals("c.compareTo(a)", c.compareTo(a),  1);
		assertEquals("a.compareTo(h)", a.compareTo(h), -1);
		assertEquals("h.compareTo(b)", h.compareTo(b),  1);
		assertEquals("a.compareTo(z)", a.compareTo(z),  1);
		assertEquals("z.compareTo(a)", z.compareTo(a), -1);
		assertEquals("z.compareTo(z)", z.compareTo(z),  0);
		assertEquals("h.compareTo(h)", h.compareTo(h),  0);
		assertEquals("a.compareTo(a)", a.compareTo(a),  0);

		assertEquals("a.compareTo(n)", a.compareTo(n),  1);
		assertEquals("n.compareTo(a)", n.compareTo(a), -1);
		assertEquals("n.compareTo(m)", n.compareTo(m),  0);
}
}

package testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grading.Missing;

public class MissingTest {

	@Test
	public void doubleValue2() 
	{
		assertEquals("Missing.doubleValue(Double,double)",  1.0, Missing.doubleValue(null,  1.0), 0.01);
		assertEquals("Missing.doubleValue(Double,double)",   0.0, Missing.doubleValue(null,  0.0), 0.01);
		assertEquals("Missing.doubleValue(Double,double)",  -1.0, Missing.doubleValue(null, -1.0), 0.01);

		assertEquals("Missing.doubleValue(Double,double)",   3.0, Missing.doubleValue(new Double(3.0),  1.0), 0.01);
		assertEquals("Missing.doubleValue(Double,double)",   3.0, Missing.doubleValue(new Double(3.0),  0.0), 0.01);
		assertEquals("Missing.doubleValue(Double,double)",   3.0, Missing.doubleValue(new Double(3.0), -1.0), 0.01);
	}

	@Test
	public void doubleValue1() 
	{
		assertEquals("Missing.doubleValue(Double)", 0.0, Missing.doubleValue(null), 0.01);
		assertEquals("Missing.doubleValue(Double)",  2.0, Missing.doubleValue(new Double(2.0)), 0.01);
		assertEquals("Missing.doubleValue(Double)", -2.0, Missing.doubleValue(new Double(-2.0)), 0.01);
	}
}

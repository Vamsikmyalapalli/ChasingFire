package testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grading.Missing;

public class MissingTest {

//	@Test
//	public void doubleValue3() 
//	{
//		assertEquals("Missing.doubleValue(Double,double,double)", 5.0, Missing.doubleValue(new Double(5.0), 1.0, 1.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", 2.0, Missing.doubleValue(null, 2.0, 1.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", 1.0, Missing.doubleValue(null, 1.0, 1.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", 0.0, Missing.doubleValue(null, 0.0, 1.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", 2.0, Missing.doubleValue(new Double(2.0), 3.0, 1.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", 1.0, Missing.doubleValue(new Double(1.0), 3.0, 1.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", 3.0, Missing.doubleValue(new Double(0.0), 3.0, 1.0), 0.01);
//
//		assertEquals("Missing.doubleValue(Double,double,double)", -5.0, Missing.doubleValue(new Double(-5.0),  -9.0, -10.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", -9.0, Missing.doubleValue(new Double(-15.0), -9.0, -10.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", -9.0, Missing.doubleValue(null, -9.0, -10.0), 0.01);
//		assertEquals("Missing.doubleValue(Double,double,double)", -9.0, Missing.doubleValue(new Double(-15.0), -9.0, -10.0), 0.01);
//	}

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

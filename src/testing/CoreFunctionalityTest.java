package testing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import app.Gradient;

@FixMethodOrder(MethodSorters.JVM)
public class CoreFunctionalityTest {

	@Test
	public void Test1Complete01() {
		String[] args = new String[] {"20.0","18.0","5.0","15.0","20.0","20.0","20.0","5.0","0.0","10.0","15.0","80.0","75.0"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade:  80.7";
	    assertEquals(s1, outContent.toString());
	}
	
	
	@Test
	public void Test2Complete02() {
		String[] args = new String[] {"20","20","20","20","20","20","20","20","20","20","20","100","100"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade: 100.0";
	    assertEquals(s1, outContent.toString());
	}
	
	@Test
	public void Test3Complete03() {
		String[] args = new String[] {"10","10","5","15","5","20","20","0","0","10","15","60","45"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade:  54.0";
	    assertEquals(s1, outContent.toString());
	}
	
	@Test
	public void Test4MissingAll() {
		String[] args = new String[] {"NA","NA","NA","NA","NA","NA","0","0","0","0","0","0","0"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade:   0.0";
	    assertEquals(s1, outContent.toString());
	}	
	
	@Test
	public void Test5MissingOneInOneCategory() {
		String[] args = new String[] {"20","18","5","15","20","20","20","NA","5","10","15","80","75"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade:  80.7";
	    assertEquals(s1, outContent.toString());
	}
	
	@Test
	public void Test6MissingOneinEachCategory() {
		String[] args = new String[] {"20","18","5","15","NA","20","20","5","0","NA","15","NA","NA"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade:  35.2";
	    assertEquals(s1, outContent.toString());
	}
	
	@Test
	public void Test7MissingMultipleInEachCategory() {
		String[] args = new String[] {"NA","20","5","15","NA","NA","20","NA","0","NA","15","NA","NA"};
		
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
	    Gradient.main(args);
	    String s1= "Course Grade:  19.5";
	    assertEquals(s1, outContent.toString());
	}
	
}
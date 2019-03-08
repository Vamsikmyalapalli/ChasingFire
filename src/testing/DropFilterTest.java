package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import grading.DropFilter;
import grading.Grade;
import grading.SizeException;

public class DropFilterTest {
	public List<Grade> duplicates, missing, unique;
	public Grade[] d, m, u;

	private List<Grade> constructFrom(Grade[] elements, int... indexes) {
		List<Grade> result = new ArrayList<Grade>();
		for (int i = 0; i < indexes.length; i++) {
			result.add(elements[indexes[i]]);
		}
		return result;
	}

	@Before
	public void initialize() throws IllegalArgumentException {
		u = new Grade[7];
		u[0] = new Grade("Exam0", 75.0);
		u[1] = new Grade("Exam1", 60.0);
		u[2] = new Grade("Exam2", 69.0);
		u[3] = new Grade("Exam3", 82.0);
		u[4] = new Grade("Exam4", 93.0);
		u[5] = new Grade("Exam5", 0.0);
		u[6] = new Grade("Exam6", 100.0);

		unique = constructFrom(u, 0, 1, 2, 3, 4, 5, 6);

		d = new Grade[9];
		d[0] = new Grade("Quiz0", 90.0);
		d[1] = new Grade("Quiz1", 50.0);
		d[2] = new Grade("Quiz2", 50.0);
		d[3] = new Grade("Quiz3", 70.0);
		d[4] = new Grade("Quiz4", 80.0);
		d[5] = new Grade("Quiz5", 85.0);
		d[6] = new Grade("Quiz6", 60.0);
		d[7] = new Grade("Quiz7", 85.0);
		d[8] = new Grade("Quiz8", 80.0);

		duplicates = constructFrom(d, 0, 1, 2, 3, 4, 5, 6, 7, 8);

		m = new Grade[7];
		m[0] = new Grade("Exam0", 75.0);
		m[1] = new Grade("Exam1", null);
		m[2] = new Grade("Exam2", 69.0);
		m[3] = new Grade("Exam3", 82.0);
		m[4] = new Grade("Exam4", 93.0);
		m[5] = new Grade("Exam5", 0.0);
		m[6] = new Grade("Exam6", 100.0);

		missing = constructFrom(m, 0, 1, 2, 3, 4, 5, 6);
	}

	@Test
	public void sideEffects() throws SizeException {
		DropFilter rule = new DropFilter();
		rule.apply(unique);

		assertEquals("Side effects; Checking size()", u.length, unique.size());

		for (int i = 0; i < unique.size(); i++) {
			assertTrue("Side effects; Checking element " + i, unique.get(i) == u[i]);
		}
	}

	@Test(expected = SizeException.class)
	public void nullList() throws SizeException {
		DropFilter rule = new DropFilter();
		rule.apply(null);
	}

	@Test(expected = SizeException.class)
	public void emptyList() throws SizeException {
		DropFilter rule = new DropFilter();
		rule.apply(new ArrayList<Grade>());
	}

	@Test(expected = SizeException.class)
	public void smallList1() throws SizeException {
		DropFilter rule = new DropFilter(false, true);
		List<Grade> list = new ArrayList<Grade>();
		list.add(new Grade("Test1"));
		rule.apply(list);
	}

	@Test(expected = SizeException.class)
	public void smallList2() throws SizeException {
		DropFilter rule = new DropFilter(true, false);
		List<Grade> list = new ArrayList<Grade>();
		list.add(new Grade("Test1"));
		rule.apply(list);
	}

	@Test(expected = SizeException.class)
	public void smallList3() throws SizeException {
		DropFilter rule = new DropFilter();
		List<Grade> list = new ArrayList<Grade>();
		list.add(new Grade("Test1"));
		list.add(new Grade("Test2"));
		rule.apply(list);
	}

	@Test
	public void dropLowest() throws SizeException {
		DropFilter rule;
		Grade g, h;
		List<Grade> actual, expected;

		rule = new DropFilter(true, false);
		actual = rule.apply(unique);
		expected = constructFrom(u, 0, 1, 2, 3, 4, 6);
		assertEquals("Drop lowest from unique; Checking size()", unique.size() - 1, actual.size());

		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop lowest from unique; Checking " + g, actual.contains(g));
		}

		rule = new DropFilter(true, false);
		actual = rule.apply(duplicates);
		expected = constructFrom(d, 0, 3, 4, 5, 6, 7, 8); // It should also contain 1 or 2, but it's not worth checking
															// if the size is correct
		assertEquals("Drop lowest from duplicates; Checking size()", duplicates.size() - 1, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop lowest from duplicates; Checking " + g, actual.contains(g));
		}
		g = duplicates.get(1);
		h = duplicates.get(2);
		assertTrue("Drop lowest from duplicates; Checking " + g + " or " + h, actual.contains(g) || actual.contains(h));

	}

	@Test
	public void dropLowestWithMissing() throws SizeException {
		DropFilter rule;
		Grade g;
		List<Grade> actual, expected;

		rule = new DropFilter(true, false);
		actual = rule.apply(missing);
		expected = constructFrom(m, 0, 2, 3, 4, 5, 6);
		assertEquals("Drop lowest from missing; Checking size()", unique.size() - 1, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop lowest from missing; Checking " + g, actual.contains(g));
		}
	}

	@Test
	public void dropHighest() throws SizeException {
		DropFilter rule;
		Grade g;
		List<Grade> actual, expected;

		rule = new DropFilter(false, true);
		actual = rule.apply(unique);
		expected = constructFrom(u, 0, 1, 2, 3, 4, 5);
		assertEquals("Drop highest from unique; Checking size()", unique.size() - 1, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop highest from unique; Checking " + g, actual.contains(g));
		}

		rule = new DropFilter(false, true);
		actual = rule.apply(duplicates);
		expected = constructFrom(d, 1, 2, 3, 4, 5, 6, 7, 8);
		assertEquals("Drop highest from duplicates; Checking size()", duplicates.size() - 1, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop highest from duplicates; Checking " + g, actual.contains(g));
		}
	}

	@Test
	public void dropHighestWithMissing() throws SizeException {
		DropFilter rule;
		Grade g;
		List<Grade> actual, expected;

		rule = new DropFilter(false, true);
		actual = rule.apply(missing);
		expected = constructFrom(m, 0, 1, 2, 3, 4, 5);
		assertEquals("Drop highest from missing; Checking size()", unique.size() - 1, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop highest from missing; Checking " + g, actual.contains(g));
		}
	}

	@Test
	public void dropBoth() throws SizeException {
		DropFilter rule;
		Grade g;
		List<Grade> actual, expected;

		rule = new DropFilter();
		actual = rule.apply(unique);
		expected = constructFrom(u, 0, 1, 2, 3, 4);
		assertEquals("Drop highest and lowest from unique; Checking size()", unique.size() - 2, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop highest and lowest from unique; Checking " + g, actual.contains(g));
		}

		rule = new DropFilter();
		actual = rule.apply(duplicates);
		expected = constructFrom(d, 2, 3, 4, 5, 6, 7, 8);
		assertEquals("Drop highest and lowest from duplicates; Checking size()", duplicates.size() - 2, actual.size());
		for (int i = 0; i < expected.size(); i++) {
			g = expected.get(i);
			assertTrue("Drop highest from duplicates; Checking " + g, actual.contains(g));
		}
	}

}
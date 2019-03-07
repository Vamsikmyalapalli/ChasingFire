package grading;

public class Missing {
	public static final double DEFAULT_MISSING_VALUE = 0.0;

	public static double doubleValue(Double number) {
		return doubleValue(number, DEFAULT_MISSING_VALUE);
	}

	public static double doubleValue(Double number, double missingValue) {
		if (number == null)
			return missingValue;
		else
			return number.doubleValue();
	}
}


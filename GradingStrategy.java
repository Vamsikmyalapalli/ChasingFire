package grading;

import java.util.List;

public interface GradingStrategy {
    // Abstract method
	public abstract Grade calculate(String key, List<Grade> grades) throws SizeException;

}

package grading;

import java.util.List;

public interface Filter {

	// Abstract method
	public abstract List<Grade> apply(List<Grade> grades) throws SizeException;

}

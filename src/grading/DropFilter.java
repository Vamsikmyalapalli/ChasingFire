package grading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DropFilter implements Filter {
	private boolean shouldDropHighest, shouldDropLowest;

	// Default constructor
	public DropFilter() {
		this(true, true);
	}
   
	public DropFilter(boolean shouldDropLowest, boolean shouldDropHighest) {
		this.shouldDropLowest = shouldDropLowest;
		this.shouldDropHighest = shouldDropHighest;
	}

	public List<Grade> apply(List<Grade> grades) throws SizeException {
		ArrayList<Grade> result;

		if ((grades == null) || (numberToDrop() >= grades.size()))
			throw new SizeException();

		result = new ArrayList<Grade>();
		for (int i = 0; i < grades.size(); i++)
			result.add(grades.get(i));
		Collections.sort(result);

		// Drop elements as appropriate
		if (shouldDropLowest)
			result.remove(0);
		if (shouldDropHighest)
			result.remove(result.size() - 1);

		return result;
	}

	private int numberToDrop() {
		int result = 0;

		if (shouldDropLowest)
			++result;
		if (shouldDropHighest)
			++result;

		return result;
	}
}

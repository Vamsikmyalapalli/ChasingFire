package grading;

import java.util.List;

public interface Filter {

	public abstract List<Grade> apply(List<Grade> grades) throws SizeException;

}

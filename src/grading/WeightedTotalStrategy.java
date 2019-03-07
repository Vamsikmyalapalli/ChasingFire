package grading;

import java.util.List;
import java.util.Map;

public class WeightedTotalStrategy implements GradingStrategy {
	private Map<String, Double> weights;

	public WeightedTotalStrategy() {
		this(null);
	}

	public WeightedTotalStrategy(Map<String, Double> weights) {
		this.weights = weights;
	}

	public Grade calculate(String key, List<Grade> grades) throws SizeException {
		double total, w;

		// Error checking
		if ((grades == null) || (grades.size() == 0))
			throw new SizeException("No Grades");

		total = 0.0;
		w = 1.0;

		for (Grade g : grades) {
			if (weights == null)
				w = 1.0;
			else
				w = Missing.doubleValue(weights.get(g.getKey()), 1.0);

			if (w < 0.0)
				w = 0.0;

			total += w * Missing.doubleValue(g.getValue());
		}

		return new Grade(key, new Double(total));
	}

}

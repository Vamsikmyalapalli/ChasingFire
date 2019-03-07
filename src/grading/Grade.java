package grading;

public class Grade implements Comparable<Grade> {
	private Double value;
	private String key;

	public Grade(String key) throws IllegalArgumentException {
		this(key, new Double(0.0));
	}

	public Grade(String key, double value) throws IllegalArgumentException {
		this(key, new Double(value));
	}

	public Grade(String key, Double value) throws IllegalArgumentException {
		if ((key == null) || key.equals(""))
			throw new IllegalArgumentException();

		this.key = key;
		this.value = value;
	}

	public int compareTo(Grade other) {
		int result = 0;

		if ((this.getValue() == null) && (other.getValue() == null))
			result = 0;
		else if (this.getValue() == null)
			result = -1;
		else if (other.getValue() == null)
			result = 1;
		else
			result = this.getValue().compareTo(other.getValue());

		return result;
	}

	public String getKey() {
		return key;
	}

	public Double getValue() {
		return value;
	}

	public String toString() {
		if (value == null)
			return String.format("%s: %5s", key, "NA");
		else
			return String.format("%s: %5.1f", key, value);
	}

}

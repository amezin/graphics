package FIT_9202_Mezin.Common;

public abstract class MinMaxProperty<T extends Comparable<T>> extends
		Property<T> {

	private static <T extends Comparable<T>> T clamp(T value, T min, T max) {
		if (min.compareTo(value) > 0) {
			return min;
		}
		if (max.compareTo(value) < 0) {
			return max;
		}
		return value;
	}

	private final T min, max;

	public MinMaxProperty(T value, T min, T max) {
		super(clamp(value, min, max));
		this.min = min;
		this.max = max;
	}

	public T getMax() {
		return max;
	}

	public T getMin() {
		return min;
	}

	@Override
	public void setValue(T value) {
		super.setValue(clamp(value, min, max));
	}

}

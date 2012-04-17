package FIT_9202_Mezin.Common;

public class DoubleProperty extends MinMaxProperty<Double> {

	public DoubleProperty(Double value, Double min, Double max) {
		super(value, min, max);
	}

	@Override
	public void setValue(String value) {
		super.setValue(Double.valueOf(value));
	}

}

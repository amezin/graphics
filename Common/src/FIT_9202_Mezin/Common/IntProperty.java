package FIT_9202_Mezin.Common;

public class IntProperty extends MinMaxProperty<Integer> {

	public IntProperty(Integer value, Integer min, Integer max) {
		super(value, min, max);
	}

	@Override
	public void setValue(String value) {
		super.setValue(Integer.valueOf(value));
	}

}

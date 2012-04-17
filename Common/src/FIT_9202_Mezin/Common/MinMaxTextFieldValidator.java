package FIT_9202_Mezin.Common;

import java.text.ParseException;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class MinMaxTextFieldValidator<T> extends Validator {

	private Comparable<T> minimum, maximum;

	private final Class<? extends T> valueClass;

	public MinMaxTextFieldValidator(Class<? extends T> valueClass) {
		this(null, null, valueClass);
	}

	public MinMaxTextFieldValidator(Comparable<T> minimum,
			Comparable<T> maximum, Class<? extends T> valueClass) {
		if (valueClass == null) {
			throw new NullPointerException();
		}
		this.minimum = minimum;
		this.maximum = maximum;
		this.valueClass = valueClass;
	}

	public Comparable<T> getMaximum() {
		return maximum;
	}

	public Comparable<T> getMinimum() {
		return minimum;
	}

	public Class<? extends T> getValueClass() {
		return valueClass;
	}

	public void setMaximum(Comparable<T> maximum) {
		if (maximum == null) {
			throw new NullPointerException();
		}
		this.maximum = maximum;
	}

	public void setMinimum(Comparable<T> minimum) {
		if (minimum == null) {
			throw new NullPointerException();
		}
		this.minimum = minimum;
	}

	@Override
	protected boolean validate(JComponent input) {
		if (!(input instanceof JFormattedTextField)) {
			return true;
		}

		JFormattedTextField field = JFormattedTextField.class.cast(input);
		AbstractFormatter formatter = field.getFormatter();
		if (formatter == null) {
			return true;
		}

		String text = field.getText();
		try {
			T value = valueClass.cast(formatter.stringToValue(text));
			if (minimum.compareTo(value) > 0 || maximum.compareTo(value) < 0) {
				return false;
			}
			return true;
		} catch (ClassCastException e) {
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}

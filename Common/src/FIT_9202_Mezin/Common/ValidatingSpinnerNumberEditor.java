package FIT_9202_Mezin.Common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.JSpinner.NumberEditor;

public class ValidatingSpinnerNumberEditor<T extends Comparable<T>> extends
		NumberEditor {

	private class Validator extends MinMaxTextFieldValidator<T> {

		public Validator(Class<? extends T> valueClass) {
			super(valueClass);
		}

		@Override
		public void setMaximum(Comparable<T> maximum) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMinimum(Comparable<T> minimum) {
			throw new UnsupportedOperationException();
		}

		@Override
		protected boolean validate(JComponent input) {
			if (input != getTextField()) {
				return true;
			}

			super.setMinimum(getValueClass().cast(getModel().getMinimum()));
			super.setMaximum(getValueClass().cast(getModel().getMaximum()));

			return super.validate(getTextField());
		}
	}

	private final Validator validator;

	public ValidatingSpinnerNumberEditor(JSpinner spinner,
			Class<? extends T> valueClass) {
		super(spinner);

		validator = new Validator(valueClass);
		initialize();
	}

	public ValidatingSpinnerNumberEditor(JSpinner spinner, String numberFormat,
			Class<? extends T> valueClass) {
		super(spinner, numberFormat);

		validator = new Validator(valueClass);
		initialize();
	}

	public String getMessage() {
		return validator.getMessage();
	}

	public void setMessage(String message) {
		validator.setMessage(message);
	}

	private void handleEnter() {
		try {
			if (validator.verify(getTextField())) {
				commitEdit();
			}
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void initialize() {
		setInputVerifier(validator);

		getTextField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleEnter();
				}
			}
		});
	}

}

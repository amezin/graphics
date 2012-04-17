package FIT_9202_Mezin.Common;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.event.*;

public class Control<T extends Number & Comparable<T>> extends JPanel {

	private final JLabel label;
	private final JSlider slider;
	private final JSpinner spinner;

	private final ValidatingSpinnerNumberEditor<T> spinnerEditor;
	private final SpinnerNumberModel spinnerModel;

	private final T min, max;
	private final Class<? extends T> valueClass;

	private final EventListenerList listeners = new EventListenerList();
	private final ChangeEvent changeEvent = new ChangeEvent(this);

	private boolean syncing = false;

	public Control(String title, T min, T max, T value, T step,
			Class<? extends T> valueClass) {
		super(new GridLayout(3, 1));

		if (title == null || min == null || max == null || step == null
				|| valueClass == null) {
			throw new NullPointerException();
		}

		this.min = min;
		this.max = max;
		this.valueClass = valueClass;

		label = new JLabel(title);
		add(label);

		spinnerModel = new SpinnerNumberModel(value, min, max, step);
		spinner = new JSpinner(spinnerModel);
		spinnerEditor = new ValidatingSpinnerNumberEditor<>(spinner, valueClass);
		spinnerEditor.setMessage(String.format(
				Messages.getString("Control.ErrorMessage"), //$NON-NLS-1$
				valueClass.getSimpleName(), min, max));
		spinner.setEditor(spinnerEditor);
		add(spinner);

		int divisions = Math.round((max.floatValue() - min.floatValue())
				/ step.floatValue());
		slider = new JSlider(0, divisions);
		add(slider);

		syncSliderToSpinner();

		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				syncSliderToSpinner();
			}
		});

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				syncSpinnerToSlider();
			}
		});
	}

	public void addChangeListener(ChangeListener l) {
		listeners.add(ChangeListener.class, l);
	}

	public ChangeListener[] getChangeListeners() {
		return listeners.getListeners(ChangeListener.class);
	}

	public T getValue() {
		return valueClass.cast(spinner.getValue());
	}

	public void removeChangeListener(ChangeListener l) {
		listeners.remove(ChangeListener.class, l);
	}

	public void setValue(T value) {
		spinner.setValue(value);
	}

	protected void fireChangeEvent() {
		for (ChangeListener l : getChangeListeners()) {
			l.stateChanged(changeEvent);
		}
	}

	private void syncSliderToSpinner() {
		if (syncing) {
			return;
		}

		syncing = true;
		try {
			float spinnerVal = Number.class.cast(spinner.getValue())
					.floatValue();
			float fraction = (spinnerVal - min.floatValue())
					/ (max.floatValue() - min.floatValue());
			float sliderVal = fraction
					* (slider.getMaximum() - slider.getMinimum())
					+ slider.getMinimum();
			slider.setValue(Math.round(sliderVal));

			fireChangeEvent();
		} finally {
			syncing = false;
		}
	}

	private void syncSpinnerToSlider() {
		if (syncing) {
			return;
		}

		syncing = true;
		try {
			double fraction = (double)(slider.getValue() - slider.getMinimum())
					/ (slider.getMaximum() - slider.getMinimum());
			double spinnerVal = fraction
					* (max.doubleValue() - min.doubleValue())
					+ min.doubleValue();
			spinner.setValue(spinnerVal);
			spinner.commitEdit();

			fireChangeEvent();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} finally {
			syncing = false;
		}
	}
}

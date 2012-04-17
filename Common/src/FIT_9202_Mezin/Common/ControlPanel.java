package FIT_9202_Mezin.Common;

import java.awt.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends GroupBox {

	private static class ControlUpdater<T extends Number & Comparable<T>>
			implements ChangeListener {
		private final Control<T> control;

		public ControlUpdater(Control<T> control) {
			this.control = control;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			control.setValue(((MinMaxProperty<T>)e.getSource()).getValue());
		}
	}

	private static class PropertyUpdater<T extends Number & Comparable<T>>
			implements ChangeListener {
		private final MinMaxProperty<T> property;

		public PropertyUpdater(MinMaxProperty<T> property) {
			this.property = property;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			property.setValue(((Control<T>)e.getSource()).getValue());
		}

	}

	private static final Insets CONTROL_INSETS = new Insets(5, 5, 5, 5);
	private final GridBagConstraints controlConstraints = new GridBagConstraints();

	public ControlPanel() {
		super(Messages.getString("ControlPanel.Title")); //$NON-NLS-1$

		setLayout(new GridBagLayout());

		controlConstraints.fill = GridBagConstraints.HORIZONTAL;
		controlConstraints.insets = CONTROL_INSETS;
		controlConstraints.weightx = 1.0;
	}

	public <T extends Number & Comparable<T>> void addControl(
			MinMaxProperty<T> p, String title, T step,
			Class<? extends T> valueClass) {

		Control<T> c = new Control<>(title, p.getMin(), p.getMax(),
				p.getValue(), step, valueClass);

		c.addChangeListener(new PropertyUpdater<>(p));
		p.addChangeListener(new ControlUpdater<>(c));
		add(c, controlConstraints);
	}

}

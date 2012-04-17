package FIT_9202_Mezin.Common;

import javax.swing.event.*;

public abstract class Property<T> {

	private T value;
	private final EventListenerList listeners = new EventListenerList();
	private final ChangeEvent changeEvent = new ChangeEvent(this);

	public Property(T value) {
		if (value == null) {
			throw new NullPointerException();
		}
		this.value = value;
	}

	public void addChangeListener(ChangeListener l) {
		listeners.add(ChangeListener.class, l);
	}

	public ChangeListener[] getChangeListeners() {
		return listeners.getListeners(ChangeListener.class);
	}

	public T getValue() {
		return value;
	}

	public void removeChangeListener(ChangeListener l) {
		listeners.remove(ChangeListener.class, l);
	}

	public abstract void setValue(String value);

	public void setValue(T value) {
		if (value == null) {
			throw new NullPointerException();
		}
		boolean changed = !this.value.equals(value);
		this.value = value;
		if (changed) {
			fireChangeEvent();
		}
	}

	@Override
	public String toString() {
		return value.toString();
	}

	private void fireChangeEvent() {
		for (ChangeListener l : getChangeListeners()) {
			l.stateChanged(changeEvent);
		}
	}

}

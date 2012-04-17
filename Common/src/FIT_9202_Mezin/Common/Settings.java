package FIT_9202_Mezin.Common;

import java.io.*;
import java.util.*;

import javax.swing.event.*;

public abstract class Settings {
	private final HashMap<String, Property<?>> settings = new HashMap<>();
	private final EventListenerList listeners = new EventListenerList();
	private final ChangeEvent changeEvent = new ChangeEvent(this);
	private final ChangeListener changeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			fireChangeEvent();
		}
	};

	public void addChangeListener(ChangeListener l) {
		listeners.add(ChangeListener.class, l);
	}

	public <T extends Property<?>> T get(String name, Class<T> propertyClass) {
		return propertyClass.cast(settings.get(name));
	}

	public Map<String, Property<?>> getAll() {
		return Collections.unmodifiableMap(settings);
	}

	public ChangeListener[] getChangeListeners() {
		return listeners.getListeners(ChangeListener.class);
	}

	public Map<String, String> load(InputStream is) throws IOException {
		Properties p = new Properties();
		p.loadFromXML(is);
		return setProperties(p);
	}

	public void removeChangeListener(ChangeListener l) {
		listeners.remove(ChangeListener.class, l);
	}

	public void save(OutputStream os) throws IOException {
		toProperties().storeToXML(os, getComment());
	}

	public Map<String, String> setProperties(Properties p) {
		if (p == null) {
			throw new NullPointerException();
		}
		HashMap<String, String> errors = new HashMap<>();
		for (Map.Entry<Object, Object> e : p.entrySet()) {
			Property<?> prop = settings.get(e.getKey().toString());
			if (prop == null) {
				errors.put(e.getKey().toString(),
						Messages.getString("Settings.UnknownProperty")); //$NON-NLS-1$
				continue;
			}
			try {
				prop.setValue(e.getValue().toString());
			} catch (RuntimeException re) {
				errors.put(e.getKey().toString(), re.getLocalizedMessage());
			}
		}
		return errors;
	}

	public Properties toProperties() {
		Properties p = new Properties();
		for (Map.Entry<String, Property<?>> e : settings.entrySet()) {
			p.put(e.getKey(), e.getValue().toString());
		}
		return p;
	}

	protected void add(String name, Property<?> p) {
		if (settings.containsKey(name)) {
			throw new IllegalArgumentException();
		} else {
			settings.put(name, p);
			p.addChangeListener(changeListener);
		}
	}

	protected abstract String getComment();

	private void fireChangeEvent() {
		for (ChangeListener l : getChangeListeners()) {
			l.stateChanged(changeEvent);
		}
	}
}

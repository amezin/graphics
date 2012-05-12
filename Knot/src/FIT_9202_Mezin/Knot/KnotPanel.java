package FIT_9202_Mezin.Knot;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import FIT_9202_Mezin.Common.RenderPanel;

public class KnotPanel extends RenderPanel {

	private final KnotSettings settings;
	private final ChangeListener settingsChangeListener = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			repaint();
		}
	};

	private final MouseMotionListener mouseControl = new MouseMotionListener() {
		private int px, py;

		private final double ANGLE_RANGE = KnotSettings.ANGLE_MAX
				- KnotSettings.ANGLE_MIN;

		private double clampAngle(double newAngle) {
			double d = (newAngle - KnotSettings.ANGLE_MIN) / ANGLE_RANGE;
			double floor = Math.floor(d);
			return newAngle - floor * ANGLE_RANGE;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			double dx = (e.getX() - px) / getClientRect().getWidth();
			double dy = (e.getY() - py) / getClientRect().getHeight();

			double oy = settings.getAngleOY().getValue();
			settings.getAngleOY().setValue(clampAngle(oy + ANGLE_RANGE * dx));

			if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0) {
				double ox = settings.getAngleOX().getValue();
				settings.getAngleOX().setValue(
						clampAngle(ox + ANGLE_RANGE * dy));
			} else {
				double r = settings.getR().getValue();
				settings.getR().setValue(r + Math.max(1.0, r) * dy);
			}

			mouseMoved(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			px = e.getX();
			py = e.getY();
		}
	};

	private Knot knot = null;

	public KnotPanel(KnotSettings settings) {
		if (settings == null) {
			throw new NullPointerException();
		}

		this.settings = settings;

		settings.addChangeListener(settingsChangeListener);
		addMouseMotionListener(mouseControl);
	}

	@Override
	protected void draw() {
		if (knot == null) {
			return;
		}

		KnotDrawer drawer = new KnotDrawer(knot, getBuffer(), settings
				.getAngleOX().getValue(), settings.getAngleOY().getValue(),
				settings.getR().getValue(), getForeground());
		drawer.draw();
	}

	public Knot getKnot() {
		return knot;
	}

	public KnotSettings getSettings() {
		return settings;
	}

	public void setKnot(Knot knot) {
		if (this.knot == knot) {
			return;
		}

		this.knot = knot;
		repaint();
	}

}

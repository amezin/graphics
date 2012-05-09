package FIT_9202_Mezin.Knot;

import FIT_9202_Mezin.Common.RenderPanel;

public class KnotPanel extends RenderPanel {

	private final KnotSettings settings;

	public KnotPanel(KnotSettings settings) {
		if (settings == null) {
			throw new NullPointerException();
		}

		this.settings = settings;
	}

	@Override
	protected void draw() {
		// TODO Auto-generated method stub

	}

	public KnotSettings getSettings() {
		return settings;
	}

}

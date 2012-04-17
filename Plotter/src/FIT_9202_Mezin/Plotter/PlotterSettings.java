package FIT_9202_Mezin.Plotter;

import FIT_9202_Mezin.Common.IntProperty;
import FIT_9202_Mezin.Common.Settings;

public class PlotterSettings extends Settings {

	public static final int MIN_COORD = -1000, MAX_COORD = 1000;

	private final IntProperty x1 = new IntProperty(-10, MIN_COORD, MAX_COORD);
	private final IntProperty y1 = new IntProperty(0, MIN_COORD, MAX_COORD);
	private final IntProperty x2 = new IntProperty(10, MIN_COORD, MAX_COORD);
	private final IntProperty y2 = new IntProperty(0, MIN_COORD, MAX_COORD);
	private final IntProperty r = new IntProperty(10, 0, MAX_COORD);

	public static final String X1_NAME = "x1", //$NON-NLS-1$
			Y1_NAME = "y1", //$NON-NLS-1$
			X2_NAME = "x2", //$NON-NLS-1$
			Y2_NAME = "y2", //$NON-NLS-1$
			R_NAME = "r"; //$NON-NLS-1$
	public static final String COMMENT = "Plotter settings"; //$NON-NLS-1$

	public PlotterSettings() {
		add(X1_NAME, x1);
		add(Y1_NAME, y1);
		add(X2_NAME, x2);
		add(Y2_NAME, y2);
		add(R_NAME, r);
	}

	public IntProperty getR() {
		return r;
	}

	public IntProperty getX1() {
		return x1;
	}

	public IntProperty getX2() {
		return x2;
	}

	public IntProperty getY1() {
		return y1;
	}

	public IntProperty getY2() {
		return y2;
	}

	@Override
	protected String getComment() {
		return COMMENT;
	}

}

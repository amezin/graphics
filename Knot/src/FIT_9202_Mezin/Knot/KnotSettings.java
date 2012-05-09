package FIT_9202_Mezin.Knot;

import FIT_9202_Mezin.Common.DoubleProperty;
import FIT_9202_Mezin.Common.Settings;

public class KnotSettings extends Settings {

	private static final String COMMENT = "Knot Settings"; //$NON-NLS-1$

	public static final double ANGLE_MIN = 0.0, ANGLE_MAX = 360.0;
	public static final double R_MIN = 0.0, R_MAX = 100000.0;
	public static final double R_DEFAULT = 1.0;

	public static final String ANGLE_OX = "AngleOX"; //$NON-NLS-1$
	public static final String ANGLE_OY = "AngleOY"; //$NON-NLS-1$
	public static final String R = "R"; //$NON-NLS-1$

	private final DoubleProperty angleOX, angleOY, r;

	public KnotSettings() {
		angleOX = new DoubleProperty(ANGLE_MIN, ANGLE_MIN, ANGLE_MAX);
		angleOY = new DoubleProperty(ANGLE_MIN, ANGLE_MIN, ANGLE_MAX);
		r = new DoubleProperty(R_DEFAULT, R_MIN, R_MAX);

		add(ANGLE_OX, angleOX);
		add(ANGLE_OY, angleOY);
		add(R, r);
	}

	public DoubleProperty getAngleOX() {
		return angleOX;
	}

	public DoubleProperty getAngleOY() {
		return angleOY;
	}

	@Override
	protected String getComment() {
		return COMMENT;
	}

	public DoubleProperty getR() {
		return r;
	}

}

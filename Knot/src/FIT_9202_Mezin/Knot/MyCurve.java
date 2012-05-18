package FIT_9202_Mezin.Knot;

public class MyCurve extends Curve {

	public MyCurve(double[] x, double[] m) {
		super(x, m);
	}

	@Override
	protected double interpolate(double t, double x1, double m1, double x2,
			double m2) {
		double c0 = x1;
		double c1 = m1;
		double c3 = x2 - x1 - 1.5 * m1 + 0.5 * m2;
		double c4 = 3.0 * (x2 - x1) - m1 - 2.0 * m2;
		double c5 = -3.0 * (x2 - x1) + 1.5 * m1 + 1.5 * m2;
		return (((c5 * t + c4) * t + c3) * t * t + c1) * t + c0;
	}

}

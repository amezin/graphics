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
		double c3 = 10 * (x2 - x1) - 6 * m1 - 4 * m2;
		double c4 = -15 * (x2 - x1) + 8 * m1 + 7 * m2;
		double c5 = 6 * (x2 - x1) - 9 * m1 + 3 * m2;
		return (((c5 * t + c4) * t + c3) * t * t + c1) * t + c0;
	}

}

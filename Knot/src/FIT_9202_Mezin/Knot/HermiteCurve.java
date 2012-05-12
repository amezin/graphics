package FIT_9202_Mezin.Knot;

public class HermiteCurve extends Curve {

	public HermiteCurve(double[] x, double[] m) {
		super(x, m);
	}

	@Override
	protected double interpolate(double t, double p1, double m1, double p2,
			double m2) {
		double t2 = t * t;
		double t3 = t2 * t;
		return (2.0 * t3 - 3.0 * t2 + 1.0) * p1 + (t3 - 2.0 * t2 + t) * m1
				+ (-2.0 * t3 + 3.0 * t2) * p2 + (t3 - t2) * m2;
	}

}

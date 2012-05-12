package FIT_9202_Mezin.Knot;

public abstract class Curve {

	private final double[] x, m;

	public Curve(double[] x, double[] m) {
		if (x.length < 2 || x.length != m.length) {
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.m = m;
	}

	public double get(double t) {
		if (t < 0.0 || t > 1.0) {
			throw new IllegalArgumentException();
		}

		int segmentCount = getPointCount() - 1;
		int segment = Math.min((int) (t * segmentCount), segmentCount - 1);
		double localT = Math
				.max(0.0, Math.min(1.0, t * segmentCount - segment));

		return interpolate(localT, x[segment], m[segment], x[segment + 1],
				m[segment + 1]);
	}

	public int getPointCount() {
		return x.length;
	}

	protected abstract double interpolate(double t, double x1, double m1,
			double x2, double m2);

}

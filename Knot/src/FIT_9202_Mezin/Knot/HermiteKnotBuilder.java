package FIT_9202_Mezin.Knot;

public class HermiteKnotBuilder extends KnotBuilder {

	private static final double R = 100.0;
	private static final int POINTS = 6;

	public static Knot build() {
		HermiteKnotBuilder builder = new HermiteKnotBuilder();

		double t = 0.0;
		double step = Math.PI * 2.0 / POINTS;
		for (int i = 0; i < POINTS + 1; i++, t += step) {
			double y = -(2.0 + Math.cos(3.0 * t)) * Math.cos(2.0 * t) * R;
			double x = (2.0 + Math.cos(3.0 * t)) * Math.sin(2.0 * t) * R;
			double z = Math.sin(3.0 * t) * R;

			double ny = -(2.0 + Math.cos(3.0 * (t + step * 0.5)))
					* Math.cos(2.0 * (t + step * 0.5)) * R;
			double nx = (2.0 + Math.cos(3.0 * (t + step * 0.5)))
					* Math.sin(2.0 * (t + step * 0.5)) * R;
			double nz = Math.sin(3.0 * (t + step * 0.5)) * R;

			double py = -(2.0 + Math.cos(3.0 * (t - step * 0.5)))
					* Math.cos(2.0 * (t - step * 0.5)) * R;
			double px = (2.0 + Math.cos(3.0 * (t - step * 0.5)))
					* Math.sin(2.0 * (t - step * 0.5)) * R;
			double pz = Math.sin(3.0 * (t - step * 0.5)) * R;

			double dx = x - px + (nx - x);
			double dy = y - py + (ny - y);
			double dz = z - pz + (nz - z);

			builder.addSegment(x, y, z, dx, dy, dz);
		}

		return builder.buildKnot();
	}

	private HermiteKnotBuilder() {

	}

	@Override
	protected Curve buildCurve(double[] p, double[] m) {
		return new HermiteCurve(p, m);
	}

}

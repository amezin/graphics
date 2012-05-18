package FIT_9202_Mezin.Knot;

import java.util.ArrayList;
import java.util.Random;

public class MyKnotBuilder extends KnotBuilder {

	private static final double W = 500.0, H = 500.0, D = 500.0;
	private static final double SOFTNESS = 1.0;
	private static final int POINTS = 10;

	public static Knot build() {
		ArrayList<Double> x = new ArrayList<>(), y = new ArrayList<>(), z = new ArrayList<>();

		Random r = new Random();

		for (int i = 0; i < POINTS; i++) {
			x.add((r.nextDouble() - 0.5) * W);
			y.add((r.nextDouble() - 0.5) * H);
			z.add((r.nextDouble() - 0.5) * D);
		}

		MyKnotBuilder builder = new MyKnotBuilder();
		for (int i = 0; i + 2 < x.size(); i++) {
			double dx = (x.get(i + 1) - x.get(i) + x.get(i + 2) - x.get(i + 1))
					* SOFTNESS;
			double dy = (y.get(i + 1) - y.get(i) + y.get(i + 2) - y.get(i + 1))
					* SOFTNESS;
			double dz = (z.get(i + 1) - z.get(i) + z.get(i + 2) - z.get(i + 1))
					* SOFTNESS;
			builder.addSegment(x.get(i), y.get(i), z.get(i), dx, dy, dz);
		}
		return builder.buildKnot();
	}

	private MyKnotBuilder() {

	}

	@Override
	protected Curve buildCurve(double[] p, double[] m) {
		return new MyCurve(p, m);
	}

}

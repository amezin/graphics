package FIT_9202_Mezin.Knot;

import java.util.ArrayList;

public class MyKnotBuilder extends KnotBuilder {

	private static final double SOFTNESS = 0.5;

	public static Knot build() {
		ArrayList<Double> x = new ArrayList<>(), y = new ArrayList<>(), z = new ArrayList<>();

		x.add(98.0);
		y.add(327.0);
		z.add(50.0);

		x.add(94.0);
		y.add(221.0);
		z.add(50.0);

		x.add(74.0);
		y.add(107.0);
		z.add(50.0);

		x.add(31.0);
		y.add(47.0);
		z.add(0.0);

		x.add(-38.0);
		y.add(22.0);
		z.add(-30.0);

		x.add(-59.0);
		y.add(-26.0);
		z.add(0.0);

		x.add(-17.0);
		y.add(-53.0);
		z.add(30.0);

		x.add(65.0);
		y.add(-75.0);
		z.add(-30.0);

		x.add(113.0);
		y.add(-161.0);
		z.add(-20.0);

		x.add(88.0);
		y.add(-265.0);
		z.add(-10.0);

		x.add(-1.0);
		y.add(-290.0);
		z.add(0.0);

		x.add(-94.0);
		y.add(-244.0);
		z.add(10.0);

		x.add(-100.0);
		y.add(-172.0);
		z.add(20.0);

		x.add(-61.0);
		y.add(-108.0);
		z.add(30.0);

		x.add(55.0);
		y.add(20.0);
		z.add(0.0);

		x.add(75.0);
		y.add(71.0);
		z.add(0.0);

		x.add(55.0);
		y.add(97.0);
		z.add(0.0);

		x.add(14.0);
		y.add(72.0);
		z.add(0.0);

		x.add(4.0);
		y.add(23.0);
		z.add(0.0);

		x.add(63.0);
		y.add(-44.0);
		z.add(0.0);

		x.add(64.0);
		y.add(-97.0);
		z.add(0.0);

		x.add(14.0);
		y.add(-118.0);
		z.add(0.0);

		x.add(-33.0);
		y.add(-106.0);
		z.add(0.0);

		x.add(-54.0);
		y.add(-67.0);
		z.add(0.0);

		x.add(-37.0);
		y.add(-19.0);
		z.add(0.0);

		x.add(-18.0);
		y.add(40.0);
		z.add(0.0);

		x.add(-17.0);
		y.add(147.0);
		z.add(0.0);

		x.add(-36.0);
		y.add(237.0);
		z.add(0.0);

		x.add(-47.0);
		y.add(330.0);
		z.add(0.0);

		x.add(-46.0);
		y.add(356.0);
		z.add(0.0);

		MyKnotBuilder builder = new MyKnotBuilder();
		for (int i = 1; i + 1 < x.size(); i++) {
			double dx = (x.get(i) - x.get(i - 1) + x.get(i + 1) - x.get(i))
					* SOFTNESS;
			double dy = (y.get(i) - y.get(i - 1) + y.get(i + 1) - y.get(i))
					* SOFTNESS;
			double dz = (z.get(i) - z.get(i - 1) + z.get(i + 1) - z.get(i))
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

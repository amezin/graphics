package FIT_9202_Mezin.Knot;

public class HermiteKnotBuilder extends KnotBuilder {

	public static Knot build() {
		HermiteKnotBuilder builder = new HermiteKnotBuilder();

		double r = KnotSettings.R_DEFAULT * 0.5;

		builder.addSegment(-r, 0, 0, 0, 0, -r * 2);
		builder.addSegment(r, 0, 0, 0, 0, r * 2);

		return builder.buildKnot();
	}

	private HermiteKnotBuilder() {

	}

	@Override
	protected Curve buildCurve(double[] p, double[] m) {
		return new HermiteCurve(p, m);
	}

}

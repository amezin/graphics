package FIT_9202_Mezin.Knot;

public class MyKnotBuilder extends KnotBuilder {

	public static Knot build() {
		MyKnotBuilder builder = new MyKnotBuilder();

		builder.addSegment(0, 0, 0, 0, 0, 0);
		builder.addSegment(0, 0, 0, 0, 0, 0);
		builder.addSegment(0, 0, 0, 0, 0, 0);

		return builder.buildKnot();
	}

	private MyKnotBuilder() {

	}

	@Override
	protected Curve buildCurve(double[] p, double[] m) {
		return new MyCurve(p, m);
	}

}

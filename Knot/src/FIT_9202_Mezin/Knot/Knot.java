package FIT_9202_Mezin.Knot;

public class Knot {
	private final Curve x, y, z;

	public Knot(Curve x, Curve y, Curve z) {
		if (x.getPointCount() != y.getPointCount()
				|| y.getPointCount() != z.getPointCount()) {
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX(double t) {
		return x.get(t);
	}

	public double getY(double t) {
		return y.get(t);
	}

	public double getZ(double t) {
		return z.get(t);
	}
}

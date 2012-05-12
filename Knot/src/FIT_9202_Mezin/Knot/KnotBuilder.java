package FIT_9202_Mezin.Knot;

import java.util.ArrayList;

public abstract class KnotBuilder {

	private final ArrayList<Double> x, y, z, mx, my, mz;

	public KnotBuilder() {
		x = new ArrayList<>();
		y = new ArrayList<>();
		z = new ArrayList<>();
		mx = new ArrayList<>();
		my = new ArrayList<>();
		mz = new ArrayList<>();
	}

	public void addSegment(double px, double py, double pz, double dx,
			double dy, double dz) {
		x.add(px);
		y.add(py);
		z.add(pz);
		mx.add(dx);
		my.add(dy);
		mz.add(dz);
	}

	private Curve buildCurve(ArrayList<Double> p, ArrayList<Double> m) {
		double[] ap = new double[p.size()];
		double[] am = new double[m.size()];
		for (int i = 0; i < p.size(); i++) {
			ap[i] = p.get(i);
		}
		for (int i = 0; i < m.size(); i++) {
			am[i] = m.get(i);
		}
		return buildCurve(ap, am);
	}

	protected abstract Curve buildCurve(double[] p, double[] m);

	public Knot buildKnot() {
		return new Knot(buildCurve(x, mx), buildCurve(y, my), buildCurve(z, mz));
	}
}

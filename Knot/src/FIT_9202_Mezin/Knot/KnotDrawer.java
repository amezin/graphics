package FIT_9202_Mezin.Knot;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import FIT_9202_Mezin.Common.RenderBuffer;

public class KnotDrawer {
	private static boolean neighbours(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= 1;
	}

	private final Knot knot;
	private final RenderBuffer target;
	private final double rotationMatrix[][], r;
	private final int color;

	private final double screenHalfW, screenHalfH;

	private final Random random = new Random();

	public KnotDrawer(Knot knot, RenderBuffer target, double angleOX,
			double angleOY, double r, Color color) {
		this.knot = knot;
		this.target = target;
		this.r = r;
		this.color = color.getRGB();

		screenHalfW = target.getWidth() * 0.5;
		screenHalfH = target.getHeight() * 0.5;

		rotationMatrix = new double[3][3];
		fillRotationMatrix(-Math.toRadians(angleOY), Math.toRadians(angleOX));
	}

	public void draw() {
		drawPart(0.0, 1.0, new Point(), new Point(), new Point());
	}

	private void drawPart(double t1, double t2, Point p1, Point p2, Point p) {
		project(knot.getX(t1), knot.getY(t1), knot.getZ(t1), p1);
		project(knot.getX(t2), knot.getY(t2), knot.getZ(t2), p2);

		if (neighbours(p1, p2)) {
			double m = t1 + random.nextDouble() * (t2 - t1);
			project(knot.getX(m), knot.getY(m), knot.getZ(m), p);
			if (p1.equals(p) || p2.equals(p)) {
				return;
			}
		}

		double m = (t1 + t2) * 0.5;
		if (m == t1 || m == t2) {
			return;
		}
		drawPart(t1, m, p1, p2, p);
		drawPart(m, t2, p1, p2, p);
	}

	private void fillRotationMatrix(double yaw, double pitch) {
		double ycos = Math.cos(yaw);
		double ysin = Math.sin(yaw);
		double pcos = Math.cos(pitch);
		double psin = Math.sin(pitch);

		rotationMatrix[0][0] = ycos;
		rotationMatrix[0][1] = 0.0;
		rotationMatrix[0][2] = ysin;

		rotationMatrix[1][0] = psin * ysin;
		rotationMatrix[1][1] = pcos;
		rotationMatrix[1][2] = psin * -ycos;

		rotationMatrix[2][0] = pcos * -ysin;
		rotationMatrix[2][1] = psin;
		rotationMatrix[2][2] = pcos * ycos;
	}

	private void project(double x, double y, double z, Point out) {
		double px = rotationMatrix[0][0] * x + rotationMatrix[0][1] * y
				+ rotationMatrix[0][2] * z;
		double py = rotationMatrix[1][0] * x + rotationMatrix[1][1] * y
				+ rotationMatrix[1][2] * z;
		double pz = rotationMatrix[2][0] * x + rotationMatrix[2][1] * y
				+ rotationMatrix[2][2] * z;
		pz += r;
		double k = -r / pz;
		px *= k;
		py *= k;

		out.x = (int) Math.floor(screenHalfW + px);
		out.y = (int) Math.floor(screenHalfH - py);

		if (out.x < 0) {
			out.x = -1;
		}
		if (out.x >= target.getWidth()) {
			out.x = target.getWidth();
		}
		if (out.y < 0) {
			out.y = -1;
		}
		if (out.y >= target.getHeight()) {
			out.y = target.getHeight();
		}
		if (out.x < 0 || out.y < 0 || out.x >= target.getWidth()
				|| out.y >= target.getHeight() || pz < 0.0) {
			return;
		}
		target.setRGB(out.x, out.y, color);
	}
}

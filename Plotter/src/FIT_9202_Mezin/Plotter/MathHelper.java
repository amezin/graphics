package FIT_9202_Mezin.Plotter;

public class MathHelper {

	public static long distanceSq(int dx, int dy) {
		return dot(dx, dy, dx, dy);
	}

	public static long distanceSq(int x1, int y1, int x2, int y2) {
		return distanceSq(x2 - x1, y2 - y1);
	}

	public static long dot(int x1, int y1, int x2, int y2) {
		return x1 * x2 + y1 * y2;
	}

	public static int sgn(int n) {
		if (n > 0) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		return -1;
	}

	public static long sgn(long l) {
		if (l > 0) {
			return 1;
		}
		if (l == 0) {
			return 0;
		}
		return -1;
	}

	private MathHelper() {

	}
}

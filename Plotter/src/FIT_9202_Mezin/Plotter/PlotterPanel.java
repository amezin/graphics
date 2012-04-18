package FIT_9202_Mezin.Plotter;

import static FIT_9202_Mezin.Plotter.MathHelper.sgn;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import FIT_9202_Mezin.Common.IntProperty;
import FIT_9202_Mezin.Common.RenderPanel;

public class PlotterPanel extends RenderPanel {

	private class Focus extends MouseAdapter {
		private final IntProperty x, y;
		private final int color;
		private static final int MARKER_R = 1;
		private int mouseX, mouseY;

		public Focus(IntProperty x, IntProperty y, Color color) {
			this.x = x;
			this.y = y;
			this.color = color.getRGB();
		}

		public long distance(int px, int py) {
			return MathHelper.distanceSq(getX(), getY(), px, py);
		}

		public void draw() {
			int cx = getX();
			int cy = getY();

			for (int dy = -MARKER_R; dy <= MARKER_R; dy++) {
				for (int dx = -MARKER_R; dx <= MARKER_R; dx++) {
					setRGB(cx + dx, cy + dy, color);
				}
			}
		}

		public int getX() {
			return x.getValue();
		}

		public int getY() {
			return y.getValue();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - mouseX;
			int dy = e.getY() - mouseY;

			updateMouseXY(e);

			x.setValue(x.getValue() + dx);
			y.setValue(y.getValue() - dy);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() != MouseEvent.BUTTON1) {
				return;
			}

			removeMouseListener(this);
			removeMouseMotionListener(this);

			addMouseListener(picker);
		}

		public void pick(MouseEvent e) {
			updateMouseXY(e);

			removeMouseListener(picker);

			addMouseListener(this);
			addMouseMotionListener(this);
		}

		private void updateMouseXY(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}

	private final PlotterSettings settings;
	private final Focus focus[] = new Focus[2];

	private static final int X_COLOR = Color.BLUE.getRGB();
	private static final int Y_COLOR = Color.RED.getRGB();

	private static final Color P1_COLOR = Color.GREEN;
	private static final Color P2_COLOR = Color.ORANGE;

	private static final int PLOT_COLOR = Color.BLACK.getRGB();
	private static final int PLOT_SYMMETRIC_COLOR = Color.MAGENTA.getRGB();

	private static final Point[] CLOCKWISE = new Point[] { new Point(-1, 1),
			new Point(0, 1), new Point(1, 1), new Point(1, 0),
			new Point(1, -1), new Point(0, -1), new Point(-1, -1),
			new Point(-1, 0) };

	private static final int[] TURN = new int[] { -1, 1, -2, 2, -3, 3 };

	private final MouseAdapter picker = new MouseAdapter() {
		private static final int PICK_R = 10;

		@Override
		public void mousePressed(MouseEvent e) {
			Rectangle clientRect = getClientRect();
			int x = e.getX() - clientRect.x - clientRect.width / 2;
			int y = clientRect.height / 2 - (e.getY() - clientRect.y);
			long d1 = focus[0].distance(x, y);
			long d2 = focus[1].distance(x, y);
			if (Math.min(d1, d2) > PICK_R * PICK_R) {
				return;
			}

			Focus nearest = d1 < d2 ? focus[0] : focus[1];
			nearest.pick(e);
		}
	};

	public PlotterPanel(PlotterSettings settings) {
		if (settings == null) {
			throw new NullPointerException();
		}

		this.settings = settings;
		settings.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				repaint();
			}
		});

		focus[0] = new Focus(settings.getX1(), settings.getY1(), P1_COLOR);
		focus[1] = new Focus(settings.getX2(), settings.getY2(), P2_COLOR);

		addMouseListener(picker);
	}

	public PlotterSettings getSettings() {
		return settings;
	}

	@Override
	protected void draw() {
		drawAxes();
		for (Focus f : focus) {
			f.draw();
		}
		drawCassiniOval();
	}

	private void drawAxes() {
		int startX = -getBuffer().getWidth() / 2;
		int startY = -(getBuffer().getHeight() - getBuffer().getHeight() / 2);
		for (int i = startX; startX + i < getBuffer().getWidth(); i++) {
			setRGB(i, 0, X_COLOR);
		}
		for (int i = startY; startY + i < getBuffer().getHeight(); i++) {
			setRGB(0, i, Y_COLOR);
		}
	}

	private void drawCassiniOval() {
		int x = focus[0].getX();
		int y = findStartY(x, focus[0].getY(),
				focus[1].getY() > focus[0].getY());

		drawCassiniOvalPart(x, y, 0);
		drawCassiniOvalPart(x, y, CLOCKWISE.length / 2);
	}

	private void drawCassiniOvalPart(int startX, int startY, int startDir) {

		int x = startX, y = startY, dir = startDir;
		do {
			setRGB(x, y, PLOT_COLOR);

			int x2 = focus[0].getX() - x + focus[1].getX();
			int y2 = focus[0].getY() - y + focus[1].getY();
			setRGB(x2, y2, PLOT_SYMMETRIC_COLOR);

			dir = findBestDirection(dir, x, y);
			if (dir == -1) {
				break;
			}

			x += CLOCKWISE[dir].x;
			y += CLOCKWISE[dir].y;

			if (focus[0].distance(x, y) > focus[1].distance(x, y)) {
				break;
			}

		} while (!(x == startX && y == startY));
	}

	private int findBestDirection(int currentDir, int x, int y) {

		for (int turn : TURN) {
			int dir1 = (CLOCKWISE.length + currentDir + turn)
					% CLOCKWISE.length;
			int dir2 = (CLOCKWISE.length + currentDir + turn - sgn(turn))
					% CLOCKWISE.length;

			int nx1 = x + CLOCKWISE[dir1].x;
			int ny1 = y + CLOCKWISE[dir1].y;
			int nx2 = x + CLOCKWISE[dir2].x;
			int ny2 = y + CLOCKWISE[dir2].y;

			long err1 = func(nx1, ny1);
			long err2 = func(nx2, ny2);

			if (sgn(err1) * sgn(err2) <= 0) {
				if (Math.abs(err1) < Math.abs(err2)) {
					return dir1;
				} else {
					return dir2;
				}
			}
		}
		return -1;
	}

	private int findStartY(int x, int y, boolean down) {
		int dir = down ? -1 : 1;

		int last = 1;
		while (func(x, y + last * dir) < 0) {
			last *= 2;
		}

		int first = 0;
		while (first < last) {
			int mid = first + (last - first) / 2;
			if (func(x, y + mid * dir) < 0) {
				first = mid + 1;
			} else {
				last = mid;
			}
		}

		long f = Math.abs(func(x, y + last * dir));
		if (last > 0 && Math.abs(func(x, y + (last - 1) * dir)) < f) {
			return y + (last - 1) * dir;
		} else {
			return y + last * dir;
		}
	}

	private long func(int x, int y) {
		long r = settings.getR().getValue();
		long r2 = r * r;
		long r4 = r2 * r2;
		return focus[0].distance(x, y) * focus[1].distance(x, y) - r4;
	}

	private void setRGB(int x, int y, int rgb) {
		int sx = x + getBuffer().getWidth() / 2;
		int sy = getBuffer().getHeight() / 2 - y;
		if (sx >= 0 && sy >= 0 && sx < getBuffer().getWidth()
				&& sy < getBuffer().getHeight()) {

			getBuffer().setRGB(sx, sy, rgb);
		}
	}
}

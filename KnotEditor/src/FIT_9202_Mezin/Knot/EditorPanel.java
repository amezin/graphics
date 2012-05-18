package FIT_9202_Mezin.Knot;

import java.awt.*;
import java.util.ArrayList;

import FIT_9202_Mezin.Common.RenderPanel;

public class EditorPanel extends RenderPanel {

	private final ArrayList<Point> points = new ArrayList<>();

	@Override
	protected void draw() {
		Rectangle client = getClientRect();

		int cx = (client.width - client.x) / 2;
		int cy = (client.height - client.y) / 2;

		for (int i = 0; i < client.height; i++) {
			getBuffer().setRGB(cx, i, Color.RED.getRGB());
		}
		for (int i = 0; i < client.width; i++) {
			getBuffer().setRGB(i, cy, Color.RED.getRGB());
		}

		for (Point p : points) {
			if (client.contains(cx + p.x + client.x, cy + p.y + client.y)) {
				getBuffer()
						.setRGB(cx + p.x, cy + p.y, getForeground().getRGB());
			}
		}
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

}

package FIT_9202_Mezin.Common;

import java.awt.*;

public abstract class RenderPanel extends GroupBox {

	private RenderBuffer buffer = null;

	private static final Dimension PREFERRED_SIZE = new Dimension(320, 240);

	public RenderPanel() {
		super(Messages.getString("RenderPanel.Title"), false); //$NON-NLS-1$
		setPreferredSize(PREFERRED_SIZE);
	}

	public RenderBuffer getBuffer() {
		return buffer;
	}

	@Override
	public void paint(Graphics g) {
		Rectangle clientRect = getClientRect();
		if (clientRect.width <= 0 || clientRect.height <= 0) {
			super.paint(g);
			return;
		}
		if (buffer == null || buffer.getWidth() != clientRect.width
				|| buffer.getHeight() != clientRect.height) {
			buffer = new RenderBuffer(getGraphicsConfiguration(),
					clientRect.width, clientRect.height);
		}

		buffer.clear(getBackground().getRGB());

		draw();

		buffer.flush();

		g.drawImage(buffer.getImage(), clientRect.x, clientRect.y, null);
		paintBorder(g, clientRect);
	}

	protected abstract void draw();

	private void paintBorder(Graphics g, Rectangle clientRect) {
		int x1 = clientRect.x;
		int y1 = clientRect.y;
		int x2 = x1 + clientRect.width;
		int y2 = y1 + clientRect.height;

		g.clearRect(0, 0, getWidth(), y1);
		g.clearRect(0, y2, getWidth(), getHeight() - y2);
		g.clearRect(0, y1, x1, y2);
		g.clearRect(x2, y1, getWidth() - x2, y2);
		super.paintBorder(g);
	}

}

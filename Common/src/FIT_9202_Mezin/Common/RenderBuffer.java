package FIT_9202_Mezin.Common;

import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class RenderBuffer {
	private final BufferedImage image;
	private final int[] rgb;
	private final int width, height;

	public RenderBuffer(GraphicsConfiguration gc, int width, int height) {
		this.width = width;
		this.height = height;
		image = gc.createCompatibleImage(width, height);
		rgb = new int[width * height];
	}

	public void clear(int color) {
		Arrays.fill(rgb, color);
	}

	public void flush() {
		image.setRGB(0, 0, width, height, rgb, 0, width);
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getWidth() {
		return width;
	}

	public void setRGB(int x, int y, int rgb) {
		this.rgb[y * width + x] = rgb;
	}
}

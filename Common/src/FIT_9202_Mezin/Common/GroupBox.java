package FIT_9202_Mezin.Common;

import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GroupBox extends JPanel {

	private final TitledBorder border;

	public GroupBox(String title) {
		this(title, true);
	}

	public GroupBox(String title, boolean doubleBuffer) {
		super(doubleBuffer);

		border = BorderFactory.createTitledBorder(title);
		super.setBorder(border);
	}

	@Override
	public TitledBorder getBorder() {
		return (TitledBorder)super.getBorder();
	}

	public Rectangle getClientRect() {
		Insets insets = getInsets();
		return new Rectangle(insets.left, insets.top, getWidth() - insets.right
				- insets.left, getHeight() - insets.bottom - insets.top);
	}

	public String getTitle() {
		return border.getTitle();
	}

	public void setTitle(String title) {
		border.setTitle(title);
	}

}

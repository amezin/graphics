package FIT_9202_Mezin.Common;

import java.awt.Dimension;
import java.awt.Insets;

public abstract class MainBase {

	protected static void run(Class<? extends MainWindow> windowClass) {
		MainWindow window;
		try {
			window = windowClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		window.pack();

		Insets frameInsets = window.getInsets();
		Dimension contentMinSize = window.getContentPane().getMinimumSize();
		window.setMinimumSize(new Dimension(contentMinSize.width
				+ frameInsets.left + frameInsets.right, contentMinSize.height
				+ frameInsets.top + frameInsets.bottom));

		window.setVisible(true);
	}

}

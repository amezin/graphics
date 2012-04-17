package FIT_9202_Mezin.Common;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public abstract class MainWindow extends JFrame {

	private final String taskName;

	public MainWindow(String taskName) {
		super(taskName);
		this.taskName = taskName;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setJMenuBar(new JMenuBar());
	}

	public String getTaskName() {
		return taskName;
	}

}

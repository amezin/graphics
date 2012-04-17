package FIT_9202_Mezin.Plotter;

import java.awt.BorderLayout;

import FIT_9202_Mezin.Common.ControlPanel;
import FIT_9202_Mezin.Common.MainWindowWithSettings;

public class PlotterWindow extends MainWindowWithSettings<PlotterSettings> {

	private static final String TASK_NAME = "Plotter"; //$NON-NLS-1$

	private final PlotterPanel renderPanel;

	private final ControlPanel controlPanel;

	public PlotterWindow() {
		super(TASK_NAME, new PlotterSettings());

		setLayout(new BorderLayout());

		renderPanel = new PlotterPanel(
				PlotterSettings.class.cast(getSettings()));
		add(renderPanel, BorderLayout.CENTER);

		controlPanel = new ControlPanel();
		add(controlPanel, BorderLayout.SOUTH);

		PlotterSettings settings = renderPanel.getSettings();
		controlPanel.addControl(settings.getX1(), PlotterSettings.X1_NAME, 1,
				Integer.class);
		controlPanel.addControl(settings.getY1(), PlotterSettings.Y1_NAME, 1,
				Integer.class);
		controlPanel.addControl(settings.getX2(), PlotterSettings.X2_NAME, 1,
				Integer.class);
		controlPanel.addControl(settings.getY2(), PlotterSettings.Y2_NAME, 1,
				Integer.class);
		controlPanel.addControl(settings.getR(), PlotterSettings.R_NAME, 1,
				Integer.class);
	}
}

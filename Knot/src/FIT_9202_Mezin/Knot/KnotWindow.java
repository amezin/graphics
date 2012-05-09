package FIT_9202_Mezin.Knot;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import FIT_9202_Mezin.Common.*;

public class KnotWindow extends MainWindow {

	private static final String TASK_NAME = "Knot"; //$NON-NLS-1$

	private static final double STEP = 1.0;

	private final KnotPanel panel;
	private final ControlPanel controls;
	private final GroupBox knotType;

	private final JRadioButton firstKnot, secondKnot;

	private final KnotSettings settings;

	public KnotWindow() {
		super(TASK_NAME);

		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		settings = new KnotSettings();

		panel = new KnotPanel(settings);
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		getContentPane().add(panel, constraints);

		controls = new ControlPanel();
		controls.addControl(settings.getAngleOX(), KnotSettings.ANGLE_OX, STEP,
				Double.class);
		controls.addControl(settings.getAngleOY(), KnotSettings.ANGLE_OY, STEP,
				Double.class);
		controls.addControl(settings.getR(), KnotSettings.R, STEP, Double.class);
		constraints.weighty = 0.0;
		constraints.gridwidth = 1;
		constraints.gridy = 1;
		getContentPane().add(controls, constraints);

		knotType = new GroupBox(Messages.getString("KnotWindow.KnotType")); //$NON-NLS-1$
		firstKnot = new JRadioButton(Messages.getString("KnotWindow.FirstKnot")); //$NON-NLS-1$
		secondKnot = new JRadioButton(
				Messages.getString("KnotWindow.SecondKnot")); //$NON-NLS-1$

		ButtonGroup knots = new ButtonGroup();
		knots.add(firstKnot);
		knots.add(secondKnot);

		knotType.setLayout(new BoxLayout(knotType, BoxLayout.Y_AXIS));
		knotType.add(firstKnot);
		knotType.add(secondKnot);

		constraints.weightx = 0.0;
		getContentPane().add(knotType, constraints);

		firstKnot.setSelected(true);
	}

}

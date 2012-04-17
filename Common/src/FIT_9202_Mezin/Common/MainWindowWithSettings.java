package FIT_9202_Mezin.Common;

import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class MainWindowWithSettings<TSettings extends Settings> extends
		MainWindow {

	private final TSettings settings;

	private final JFileChooser fileChooser = new JFileChooser();
	private final JMenuItem loadSettingsMenuItem, saveSettingsMenuItem;

	public MainWindowWithSettings(String taskName, TSettings settings) {
		super(taskName);

		if (settings == null) {
			throw new NullPointerException();
		}
		this.settings = settings;

		JMenu settingsMenu = new JMenu(
				Messages.getString("MainWindowWithSettings.SettingsMenu")); //$NON-NLS-1$
		settingsMenu.setMnemonic('S');
		getJMenuBar().add(settingsMenu);

		loadSettingsMenuItem = new JMenuItem(
				Messages.getString("MainWindowWithSettings.LoadSettingsMenuItem")); //$NON-NLS-1$
		loadSettingsMenuItem.setMnemonic('L');
		loadSettingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		loadSettingsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadSettings();
			}
		});
		settingsMenu.add(loadSettingsMenuItem);

		saveSettingsMenuItem = new JMenuItem(
				Messages.getString("MainWindowWithSettings.SaveSettingsMenuItem")); //$NON-NLS-1$
		saveSettingsMenuItem.setMnemonic('S');
		saveSettingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveSettingsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSettings();
			}
		});
		settingsMenu.add(saveSettingsMenuItem);
	}

	public JMenuItem getLoadSettingsMenuItem() {
		return loadSettingsMenuItem;
	}

	public JMenuItem getSaveSettingsMenuItem() {
		return saveSettingsMenuItem;
	}

	public Settings getSettings() {
		return settings;
	}

	private void loadSettings() {
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		Map<String, String> errors = new HashMap<>();
		try (InputStream is = new FileInputStream(fileChooser.getSelectedFile())) {
			errors = settings.load(is);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
		}
		if (!errors.isEmpty()) {
			StringBuilder reportBuilder = new StringBuilder(
					Messages.getString("MainWindowWithSettings.SettingsLoadErrorReportPrefix")); //$NON-NLS-1$

			for (Map.Entry<String, String> e : errors.entrySet()) {
				reportBuilder.append(e.getKey());
				reportBuilder.append(':');
				reportBuilder.append('\t');
				reportBuilder.append(e.getValue());
				reportBuilder.append('\n');
			}
			JOptionPane.showMessageDialog(this, reportBuilder.toString());
		}
	}

	private void saveSettings() {
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		try (OutputStream os = new FileOutputStream(
				fileChooser.getSelectedFile())) {

			settings.save(os);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
		}
	}

}

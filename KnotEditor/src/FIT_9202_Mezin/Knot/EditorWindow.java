package FIT_9202_Mezin.Knot;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class EditorWindow extends JFrame {

	private static final String APP_TITLE = "Knot Editor"; //$NON-NLS-1$

	public static void main(String[] args) {
		new EditorWindow().setVisible(true);
	}

	private final EditorPanel editPanel;
	private final JTextArea textArea;

	public EditorWindow() {
		super(APP_TITLE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());

		editPanel = new EditorPanel();
		getContentPane().add(editPanel, BorderLayout.CENTER);
		editPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Point np = new Point();
				np.x = e.getX() - editPanel.getClientRect().x;
				np.y = e.getY() - editPanel.getClientRect().y;
				np.x -= (editPanel.getClientRect().width - editPanel
						.getClientRect().x) / 2;
				np.y -= (editPanel.getClientRect().height - editPanel
						.getClientRect().y) / 2;
				editPanel.getPoints().add(np);

				textArea.setText(textArea.getText()
						+ String.format(
								"x.add(%d.0); y.add(%d.0); z.add(0.0);\n", //$NON-NLS-1$
								-np.x, np.y));

				editPanel.repaint();
			}

		});

		textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.EAST);
		textArea.setEditable(false);

		pack();

	}
}

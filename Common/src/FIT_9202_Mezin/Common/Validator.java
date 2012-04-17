package FIT_9202_Mezin.Common;

import java.awt.Point;

import javax.swing.*;

public abstract class Validator extends InputVerifier {

	private Popup popup = null;

	private String message = null;

	private boolean allowLeavingInvalid = false;

	public String getMessage() {
		return message;
	}

	public boolean isAllowLeavingInvalid() {
		return allowLeavingInvalid;
	}

	public void setAllowLeavingInvalid(boolean allowLeavingInvalid) {
		this.allowLeavingInvalid = allowLeavingInvalid;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public final boolean shouldYieldFocus(JComponent input) {
		return allowLeavingInvalid || verify(input);
	}

	@Override
	public final boolean verify(JComponent input) {
		if (validate(input)) {
			hideTip();
			return true;
		}

		showTip(input);
		return false;
	}

	protected abstract boolean validate(JComponent input);

	private void hideTip() {
		if (popup != null) {
			popup.hide();
			popup = null;
		}
	}

	private void showTip(JComponent component) {
		JToolTip toolTip = component.createToolTip();
		toolTip.setTipText(message);

		Point location = component.getLocationOnScreen();
		location.y += component.getHeight();
		Popup newPopup = PopupFactory.getSharedInstance().getPopup(component,
				toolTip, location.x, location.y);

		newPopup.show();

		hideTip();
		popup = newPopup;
	}

}

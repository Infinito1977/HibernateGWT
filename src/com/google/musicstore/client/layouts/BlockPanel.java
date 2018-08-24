package com.google.musicstore.client.layouts;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

public class BlockPanel extends PopupPanel {
    public BlockPanel(int offsetHeight) {
	int left = (int) ((Window.getClientWidth()) / 2.5);
	int top = (int) ((Window.getClientHeight()) / 2.5 - offsetHeight);
	setPopupPosition(left, top);
	setGlassEnabled(true);
	show();
    }
}

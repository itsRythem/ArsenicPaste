package com.arsenicclient.settings;

public abstract class Setting {
	public String name = "";
	private boolean visible = true;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
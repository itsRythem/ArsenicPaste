package com.arsenicclient.settings.impl;

import com.arsenicclient.settings.Setting;

public class BooleanSetting extends Setting {

	private boolean toggled;

	public BooleanSetting(String name, boolean toggled) {
		this.name = name;
		this.toggled = toggled;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public void toggle() {
		this.toggled = !toggled;
	}

}
package com.arsenicclient.settings.impl;

import com.arsenicclient.settings.Setting;

public class KeySetting extends Setting {

	private int code;

	public KeySetting(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
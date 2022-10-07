package com.arsenicclient.settings.impl;

import org.lwjgl.util.Color;

import com.arsenicclient.settings.Setting;

public class ColorSetting extends Setting {

	private Color color;

	public ColorSetting(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
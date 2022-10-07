package com.arsenicclient.settings.impl;

import com.arsenicclient.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberSetting extends Setting {

	private float value, min, max, increment;

	public NumberSetting(String name, float value, float min, float max, float increment) {
		this.name = name;
		this.value = value;
		this.min = min;
		this.max = max;
		this.increment = increment;
	}

	public float getValue() {
		return value;
	}

	public int getValueInt() {
		return (int) value;
	}

	public void setValue(float value) {
		this.value = Math.round(value * (increment * 100)) / (increment * 100);
	}

	public float getMin() {
		return min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getIncrement() {
		return increment;
	}

	public void setIncrement(float increment) {
		this.increment = increment;
	}


}
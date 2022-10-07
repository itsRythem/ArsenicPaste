package com.arsenicclient.settings.impl;

import com.arsenicclient.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {

	private String mode;
	private List<String> modes = new ArrayList<String>();
	private boolean expanded;

	public ModeSetting(String name, String mode, String... modes) {
		this.name = name;
		this.mode = mode;
		this.modes = Arrays.asList(modes);
	}

	public String getMode() {
		return mode;
	}

	public void next() {
		int count = 0;
		int tcount = 0;
		for(String mode : modes) {

			if(count > 1) return;

			if(count > 0) {
				this.mode = mode;
				tcount++;
			}
			if(mode == this.mode)
			count++;
		}

		if(tcount == 0)
			mode = modes.get(0);	
	}

}
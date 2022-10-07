package com.arsenicclient.module.impl.render;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.hud.Dropdown;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.arsenicclient.settings.impl.BooleanSetting;
import com.arsenicclient.settings.impl.NumberSetting;
import com.google.common.eventbus.Subscribe;

@ModuleInfo(name = "HUD", description = "Configure the HUD", category = Category.RENDER)
public class HUD extends Module {

	public BooleanSetting sfx = new BooleanSetting("Suffix", true);
	
	public HUD() {
		this.registerSettings(sfx);
	}

	public void onEnable() {
		toggle();
	}

}

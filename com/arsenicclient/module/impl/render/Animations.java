package com.arsenicclient.module.impl.render;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.hud.Dropdown;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.arsenicclient.settings.impl.ModeSetting;
import com.arsenicclient.settings.impl.NumberSetting;
import com.google.common.eventbus.Subscribe;

@ModuleInfo(name = "Animations", description = "Block animations", category = Category.RENDER)
public class Animations extends Module {

	public ModeSetting mode = new ModeSetting("Mode", "1.7", "1.7", "Table", "Exhibobo", "Exhibobo2", "Swong", "Atlas", "Throw");
	public NumberSetting x = new NumberSetting("X", 0, -1, 1, 1);
	public NumberSetting y = new NumberSetting("Y", 0, -1, 1, 1);
	public NumberSetting z = new NumberSetting("Z", 0, -1, 1, 1);
	public NumberSetting by = new NumberSetting("Block Y", 0, -1, 1, 1);
	
	public static Animations INSTANCE = new Animations();
	
	public Animations() {
		this.registerSettings(mode, x, y, z, by);
	}
	
	public void onEnable() {
	}

}

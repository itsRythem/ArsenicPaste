package com.arsenicclient.module.impl.render;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.event.impl.player.EventPreMotion;
import com.arsenicclient.hud.Dropdown;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.google.common.eventbus.Subscribe;

@ModuleInfo(name = "Click GUI", description = "cgui", category = Category.RENDER)
public class ClickGUI extends Module {

	public ClickGUI() {
		this.setKey(Keyboard.KEY_RSHIFT);
	}

	public void onEnable() {
		mc.displayGuiScreen(new Dropdown());
		toggle();
	}

}

package com.arsenicclient.module.impl.movement;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;


@ModuleInfo(name = "Flight", description = "Allows you to fly", category = Category.MOVEMENT)
public class Flight extends Module {

    @Override
    public void onEnable() {
        mc.thePlayer.capabilities.allowFlying = mc.thePlayer.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.allowFlying = mc.thePlayer.capabilities.isFlying = false;
    }
}

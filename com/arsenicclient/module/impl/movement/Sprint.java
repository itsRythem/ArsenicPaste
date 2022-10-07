package com.arsenicclient.module.impl.movement;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.ClientTickEvent;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;

@ModuleInfo(name = "Sprint", description = "Automatically sprints forward when you move", category = Category.MOVEMENT)

public class Sprint extends Module {

    @Subscribe
    public void onUpdateEvent(ClientTickEvent event) {
        if(this.isEnabled()) {
            if(mc.thePlayer.moveForward > 0 && (!mc.thePlayer.isUsingItem() || Arsenic.getArsenic().getModuleManager().getModule("No Slow").isEnabled()) && !mc.thePlayer.isCollidedHorizontally) mc.thePlayer.setSprinting(true);
        }
    }
}
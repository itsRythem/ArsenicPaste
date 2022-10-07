package com.arsenicclient.module.impl.player;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.event.impl.ClientTickEvent;
import com.arsenicclient.event.impl.packet.EventReceivePacket;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

@ModuleInfo(name = "Velocity", description = "Cancels hit knockback", category = Category.PLAYER)

public class Velocity extends Module {

    @Subscribe
    public void onPacket(EventReceivePacket event) {
       if(this.isEnabled()) return;
       
       if(event.getPacket() instanceof S12PacketEntityVelocity || event.getPacket() instanceof S27PacketExplosion)
		event.setCancelled();
    }
}
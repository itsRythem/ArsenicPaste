package com.arsenicclient.module.impl.movement;

import org.lwjgl.input.Keyboard;

import com.arsenicclient.event.impl.ClientTickEvent;
import com.arsenicclient.module.Category;
import com.arsenicclient.module.Module;
import com.arsenicclient.module.ModuleInfo;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;

@ModuleInfo(name = "No Slow", description = "Removes block slowdown", category = Category.MOVEMENT)

public class NoSlow extends Module {
}
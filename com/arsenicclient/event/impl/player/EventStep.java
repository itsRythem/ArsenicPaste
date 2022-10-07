package com.arsenicclient.event.impl.player;

import com.arsenicclient.event.Event;

import net.minecraft.client.entity.EntityPlayerSP;

public class EventStep extends Event {

	private final double height;
	
	public EventStep(final double height) {
		
		this.height = height;
		
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getDifference(final EntityPlayerSP self) {
		
		return height - self.posY;
		
	}
	
}

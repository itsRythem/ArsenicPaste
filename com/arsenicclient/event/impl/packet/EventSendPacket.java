package com.arsenicclient.event.impl.packet;

import com.arsenicclient.event.Event;

import net.minecraft.network.Packet;

public class EventSendPacket extends Event {
	
	private final Packet outgoing;
	
	public EventSendPacket(final Packet outgoing) {
		
		this.outgoing = outgoing;
		
	}

	public Packet getPacket() {
		return outgoing;
	}

	
}

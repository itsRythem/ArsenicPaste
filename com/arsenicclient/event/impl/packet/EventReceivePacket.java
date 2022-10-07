package com.arsenicclient.event.impl.packet;

import com.arsenicclient.event.Event;

import net.minecraft.network.Packet;

public class EventReceivePacket extends Event {
	
	private final Packet incoming;
	
	public EventReceivePacket(final Packet incoming) {
		
		this.incoming = incoming;
		
	}

	public Packet getPacket() {
		return incoming;
	}

}

package com.arsenicclient.event.impl.player;

import com.arsenicclient.event.Event;

public class EventChatMessage extends Event {

	private String message;
	
	public EventChatMessage(final String message) {
	
		this.message = message;
		
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(final String set) {
		this.message = set;
	}
	
}

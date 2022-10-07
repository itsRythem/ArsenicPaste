package com.arsenicclient.event;

public abstract class Event {
	
	private boolean cancelled;
	
	public void setCancelled() {
		this.cancelled = true;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	
}

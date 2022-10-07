package com.arsenicclient.command.client;

import com.arsenicclient.Arsenic;
import com.arsenicclient.event.impl.player.EventChatMessage;
import com.google.common.eventbus.Subscribe;

public class ChatManager {

	
	public ChatManager() {
		Arsenic.getArsenic().getShuttle().register(this);
	}
	
	@Subscribe
	public void handle(final EventChatMessage outgoing) {
		
		final String message = outgoing.getMessage();
		
		//If the message doesn't start with our prefix we allow it to be sent normally
		if(!message.startsWith(Arsenic.getArsenic().getPrefix())) return;
		
		
		outgoing.setCancelled();
		
		
	}
	
}

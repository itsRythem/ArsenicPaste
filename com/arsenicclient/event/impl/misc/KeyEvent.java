package com.arsenicclient.event.impl.misc;

import com.arsenicclient.event.Event;

public class KeyEvent extends Event {
	
	private int key;

    public KeyEvent(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
    
}

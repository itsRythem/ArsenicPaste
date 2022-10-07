package com.arsenicclient.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.lwjgl.input.Keyboard;

@Retention(RetentionPolicy.RUNTIME)

public @interface ModuleInfo {
	
    abstract String name();
    
    abstract Category category();

    abstract String description();
}

package com.arsenicclient.command.client;

public abstract class Command {
	
	private final String name, description;
	
	
	public Command(final String name, final String description) {
		
		this.name = name;
		this.description = description;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public abstract void onCommand(final String in);

}

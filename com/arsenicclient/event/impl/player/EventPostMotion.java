package com.arsenicclient.event.impl.player;

import com.arsenicclient.event.Event;

public class EventPostMotion extends Event {

	private double x, y, z;
	private float yaw, pitch;
	private boolean ground;
	
	public EventPostMotion(final double x, final double y, final double z, final float yaw, final float pitch, final boolean ground, final boolean post) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.ground = ground;
		
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public boolean isGround() {
		return ground;
	}
}

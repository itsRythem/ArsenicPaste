package com.arsenicclient.event.impl.player;

import com.arsenicclient.event.Event;

public class EventPreMotion extends Event {

	private double x, y, z;
	private float yaw, pitch;
	private boolean ground;
	
	public EventPreMotion(final double x, final double y, final double z, final float yaw, final float pitch, final boolean ground, final boolean post) {
		
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

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public void setZ(final double z) {
		this.z = z;
	}

	public void setYaw(final float yaw) {
		this.yaw = yaw;
	}

	public void setPitch(final float pitch) {
		this.pitch = pitch;
	}

	public void setGround(final boolean ground) {
		this.ground = ground;
	}
	
	
	
}

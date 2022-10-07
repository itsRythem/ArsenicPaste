package com.arsenicclient.hud.notifications;

import com.arsenicclient.utils.impl.system.Clock;

public class Notification {

	double x, y;
	private Type type;
	private String info;
	private Clock clock = new Clock();;
	private long time;

	public Notification(String info, long time, Type type) {
		this.info = info;
		this.time = time;
		this.type = type;
	}

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public enum Type {
		ENABLE, DISABLE, INFO
	}
}

package com.arsenicclient.utils.impl.system;

public class Clock {

	private double stopwatch;
	
	public Clock() {
		
		stopwatch = System.currentTimeMillis();
		
	}
	
	public double getTime() {
		
		return System.currentTimeMillis()-stopwatch;
		
	}
	
	/**
	 * <p>
	 *	Sets time to current System time
	 * 
	 */
	public void reset() {
		stopwatch = System.currentTimeMillis();
		
	}
	
	public double getSecond() {
		
		return ((Math.round((System.currentTimeMillis()-stopwatch)/1000))*1000)*1000;
		
	}
	
}

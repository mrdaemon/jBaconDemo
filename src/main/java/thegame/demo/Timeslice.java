package thegame.demo;

import org.lwjgl.Sys;

public class Timeslice {
	
	private long tickstart = 0l;
	private long tickspaused = 0l;
	
	private boolean paused = false;
	private boolean started = false;
	
	private static Timeslice instance;
	
	protected Timeslice() {
		
	}
	
	public static Timeslice getInstance() {
		if (instance == null) {
			instance = new Timeslice();
		}
		
		return instance;
	}
	
	/**
	 * Start timer, record initial tick count
	 */
	public synchronized void start() {
		paused = false;
		started = true;
		tickstart = Timeslice.getTicks();
	}
	
	
	/**
	 * Stop timer, clear all counters.
	 */
	public synchronized void stop() {
		if(this.isStarted()) {
			paused = false;
			started = false;
			
			tickstart = 0l;
			tickspaused = 0l;
		}
	}
	
	/**
	 * Pause timer, effectively putting all motion
	 * affected by it on hold.
	 */
	public synchronized void pause() {
		if(this.isStarted() && !this.isPaused()) {
			paused = true;
			tickspaused = Timeslice.getTicks() - tickstart;
		}
	}
	
	/**
	 * Resume a paused timer. 
	 */
	public synchronized void resume() {
		if(this.isPaused()) {
			paused = false;
			tickstart = Timeslice.getTicks() - tickspaused;
			tickspaused = 0l; // clear paused ticks counter
		}
	}
	
	/**
	 * Get a delta representing the elapsed ticks since start.
	 * @return
	 */
	public long getDelta() {
		if(this.isStarted()) {
			if(this.isPaused()) {
				return this.tickspaused;
			} else {
				return Timeslice.getTicks() - this.tickstart;
			}
		} else {
			return 0l;
		}
	}
	

	/**
	 * @return true on paused
	 */
	public boolean isPaused() {
		return this.paused;
	}

	/**
	 * @return true on started
	 */
	public boolean isStarted() {
		return this.started;
	}	
	
	
	/**
	 * Get the current tick count from platform/implementation
	 * @return tick count (milliseconds)
	 */
	public static long getTicks() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		
	}
}

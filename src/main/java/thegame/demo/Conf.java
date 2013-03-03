package thegame.demo;

/*
 * Configuration class. Contains the various values that can be tweaked for the program.
 * This is genuinely horrendous and I'm sorry.
 */
public class Conf {
	// TODO: Make this mess something infinitely saner like, properties.
	
	// Let's pretend we have settings or something
	public static final boolean VSYNC = true;
	public static final int FPS = 60;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final boolean FULLSCREEN = false;
	
	// Star field values
	public static final int STARS_MAX = 1024; // Amount of stars
	public static final int STARS_MAX_DEPTH = 16;
	public static final float STARS_DEPTH_POW = 1.4f;
	public static final float STARS_SIZE = 4.0f;
	public static final float STARS_SPEED = 400.0f;
	
	// Sin table lookup size
	public static final int LOOKUP_SIZE = 512;
	
}

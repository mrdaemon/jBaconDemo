package thegame.demo;

import org.lwjgl.opengl.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


/**
 * LET'S MAKE PROGRAM
 * aaaah the numbers
 * @author Alexandre Gauthier
 *
 */
public class Game {
	
	
	private StarField starfield;
	
	// Magical game loop state
	private boolean isRunning = false;
	
	
	/**
	 * Game entry point
	 */
	public void run() throws LWJGLException {
		init();
		glInit();
		resize(); // Setup initial size

		Timeslice ticks = Timeslice.getInstance();
		ticks.start();
		
		this.isRunning = true;
		
		// Main Loop: This is where the magic happens
		while(this.isRunning && !Display.isCloseRequested()) {
			if(Display.wasResized())
				resize();
			
			update(ticks.getDelta());
			
			ticks.start(); // Reset counter
			
			render();
			
			
			// Flip buffers, sync
			Display.update();
			Display.sync(Conf.FPS);
		}
		
		destroy();
		Display.destroy();	
	}
	
	/**
	 * Exit game
	 */
	public void exit() {
		this.isRunning = false;
	}
	
	/*
	 * Initialize Subsystems
	 */
	private void init() throws LWJGLException {
		// Setup display
		Display.setTitle("Bacon Demo");
		Display.setResizable(true);
		Display.setDisplayMode(new DisplayMode(Conf.WIDTH, Conf.HEIGHT));
		Display.setVSyncEnabled(Conf.VSYNC);
		Display.setFullscreen(Conf.FULLSCREEN);
		
		Display.create();
		
		this.starfield = new StarField();
	}
	
	/*
	 * Initialize OpenGL Context
	 */
	private void glInit() {
		//GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, Conf.WIDTH, Conf.HEIGHT, 0, -1, 1);
	}
	
	/*
	 * Draw to screen
	 */
	private void render() {
		// Clear screen
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		starfield.draw();
	}
	
	private void update(long tdelta) {
		starfield.update(tdelta);
	}
	
	/*
	 * Resize render surface
	 */
	private void resize() {
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// TODO: stub - update matrices
		
	}
	
	/*
	 * Cleanup resources
	 */
	private void destroy() {
		// TODO: stub - get rid textures and resources
	}
}

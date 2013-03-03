package thegame.demo;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

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
	
	
	// Magical game loop state
	private boolean isRunning = false;
	
	/**
	 * Game entry point
	 */
	public void run() throws LWJGLException {
		init();
		glInit();
		resize(); // Setup initial size
		
		this.isRunning = true;
		
		// Main Loop: This is where the magic happens
		while(this.isRunning && !Display.isCloseRequested()) {
			if(Display.wasResized())
				resize();
			
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
		Display.setTitle("The Game");
		Display.setResizable(true);
		Display.setDisplayMode(new DisplayMode(Conf.WIDTH, Conf.HEIGHT));
		Display.setVSyncEnabled(Conf.VSYNC);
		Display.setFullscreen(Conf.FULLSCREEN);
		
		Display.create();
	}
	
	/*
	 * Initialize OpenGL Context
	 */
	private void glInit() {
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black
	}
	
	/*
	 * Draw to screen
	 */
	private void render() {
		// Clear screen
		glClear(GL_COLOR_BUFFER_BIT);
		
		// TODO: stub - render game
	}
	
	/*
	 * Resize render surface
	 */
	private void resize() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// TODO: stub - update matrices
		
	}
	
	/*
	 * Cleanup resources
	 */
	private void destroy() {
		// TODO: stub - get rid textures and resources
	}
}

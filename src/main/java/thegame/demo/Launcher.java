package thegame.demo;

import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 * Launcher extraordinaire 
 * 
 * @author Alexandre Gauthier
 *
 */
public class Launcher {

	/**
	 * Program entry point
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			new Game().run();
		} catch (LWJGLException e){
			Toolkit.getDefaultToolkit();
			Sys.alert("Initialization Error", 
					"There was an error initializing the graphic subsystem: " 
							+ e.getLocalizedMessage());
			System.exit(1);
		}
		
		System.exit(0);
		
	}
	
}

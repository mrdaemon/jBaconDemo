package thegame.demo;

import java.util.Random;

public class Utils {
	
	private static Random rng;

	// WHAT AM I DOING OH GOD
	static {
		rng = new Random();
	}

	public static int snap(float f, float nearest) {
		return (int) (Math.round(f / nearest) * nearest);
	}

	public static float getRandomFloat(float min, float max) {
		return min + (rng.nextFloat() * ((max - min) + 1));
	}
}

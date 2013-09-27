package thegame.demo;

import org.lwjgl.opengl.*;

/**
 * Star Field implementation
 * Controls the magical field of wonderful stars
 */
public class StarField {
	
	/**
	 * Single star coordinates
	 */
	private static class Star {
		public float x;
		public float y;
		public float z;
		
		/**
		 * Initialize new star with random position
		 */
		public Star() {
			this.x = Utils.getRandomFloat(0, Conf.WIDTH);
			this.y = Utils.getRandomFloat(0, Conf.HEIGHT);
			this.z = Utils.getRandomFloat(1, Conf.STARS_MAX_DEPTH);
		}
	}
	
	private StarField.Star[] stars; 
	private float velocity = 0.0f;
	private float angle = 0.0f;

	public StarField() {
		stars = new StarField.Star[Conf.STARS_MAX];
		for (int i=0; i < Conf.STARS_MAX; i++) {
			stars[i] = new Star();
		}
	}
	
	
	public void draw() {
		for (int i = 0; i < Conf.STARS_MAX; i++) {
			Star s = this.stars[i];
			
			
			float color = (float) (1.0f / Math.pow(s.z, Conf.STARS_DEPTH_POW));
			float size = (float) (Conf.STARS_SIZE / Math.pow(s.z, Conf.STARS_DEPTH_POW));
			if (size < 1)
				size = 1f;
			
			/* TODO: Maybe? 8-bit effect, snap to nearest 4 */
			int x = Utils.snap(s.x, 4);
			int y = Utils.snap(s.y, 4);
			
			// Change color based on z distance
			GL11.glColor3f(color, color, color);
			
			// Draw 'em Quads
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(x - size, y - size);
				GL11.glVertex2d(x + size, y - size);
				GL11.glVertex2d(x + size, y + size);
				GL11.glVertex2d(x - size, y + size);
			GL11.glEnd();
		}
		
	}
	
	public void update(long tdelta) {
		SinTable sintable = SinTable.getInstance();
		
		// Update velocity and angle
		this.angle = (float) ((this.angle + 0.5f * (tdelta / 1000f)) % (2 * Math.PI));
		this.velocity = (sintable.sin(this.angle) * Conf.STARS_SPEED);

		for(int i=0; i < Conf.STARS_MAX; i++) {
			Star s = this.stars[i];
			
			// Move stars based on z value
			s.x -= (this.velocity / s.z) * (tdelta / 1000f);
			s.y -= (Conf.STARS_SPEED / s.z) * (tdelta / 1000f);
			
			// Wrap around screen
			if (s.x > Conf.WIDTH) {
				s.x = 0;
			} else if(s.x < 0) {
				s.x = Conf.WIDTH;
			}
			
			if (s.y > Conf.HEIGHT) {
				s.y = 0;
			} else if (s.y < 0) {
				s.y = Conf.HEIGHT;
			}
		}

	}
	
}	

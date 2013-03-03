package thegame.demo;

public class SinTable {
	
	private float[] sin_table = new float[Conf.LOOKUP_SIZE];
	
	private static SinTable instance;
	
	protected SinTable() {
		/* Fuck off my constructor, I ARE SINGLETON */
		for (int i=0; i < Conf.LOOKUP_SIZE; i++) {
			sin_table[i] = (float) Math.sin((i / (float) Conf.LOOKUP_SIZE) * Math.PI * 2);
		}
	}
	
	public static SinTable getInstance() {
		if(instance == null) {
			instance = new SinTable();
		}
		return instance;
	}
	
	public float sin(float theta) {
		int index = Math.abs((int) (Conf.LOOKUP_SIZE * (theta % Math.PI) / Math.PI));
		return this.sin_table[index];
	}

}

package game;

import edu.monash.fit2099.engine.Capabilities;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private Capabilities capabilities = new Capabilities();
	
	public Dirt() {
		super('.');
	}
}

package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Capabilities;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents a crop.
 * 
 * @author glio0001, braj0006
 *
 */
public class Crop extends Ground{
	
	/**
	 * Age of crop
	 */
	private int age = 0;
	
	/**
	 * Age of crop to ripen
	 */
	private int ripeAge = 20;
	
	/**
	 * Age sped up when fertilized
	 */
	private int fertilizeEffect = 10;
	private Capabilities capabilities = new Capabilities();
	private Actions allowableActions = new Actions();

	public Crop() {
		super('$');
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		allowableActions.add(new HarvestAction(this, location));
		return allowableActions;
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);
		age += 1;
	}
	
	/**
	 * Speeds up the aging of crop
	 */
	public void fertilize() {
		age += fertilizeEffect;
	}
	
	/**
	 * @return True is crop's age is past ripeAge
	 */
	public boolean isRipe() {
		if (age >= ripeAge) {
			return true;
		}
		return false;
	}
}

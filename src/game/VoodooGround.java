package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents a ground object that is responsible for
 * spawning the voodoo zombie Mambo Marie.
 * 
 * @author glio0001, braj0006
 *
 */
public class VoodooGround extends Ground{

	private Random rand = new Random();
	private double spawnChance = 1;
	private Display disp = new Display();
	
	public VoodooGround() {
		super('m');
	}

	/**
	 * When the conditional statement is true, it will replace the ground with dirt and add mambo marie 
	 * onto the map
	 * @param location The location to remove and add
	 */
	@Override
	public void tick(Location location) {
		if(rand.nextDouble() <= spawnChance) {
			location.setGround(new Dirt());
			location.addActor(new VoodooZombie("Mambo Marie"));
			disp.println("Mambo Marie has spawned!");
		}
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
}

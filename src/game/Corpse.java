package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * 
 * A class that represents a Corpse which is a portable item.
 * 
 * @author glio0001, braj0006
 */
public class Corpse extends PortableItem{

	/**
	 * minimum number of turns taken to respawn
	 */
	private int minRespawnTurns = 5;
	
	/**
	 * maximum number of turns taken to respawn
	 */
	private int maxRespawnTurns = 10;
	
	/** 
	 * number of turns passed
	 */
	private int turns = 0;
	private Random rand = new Random();

	
	public Corpse(String name, char displayChar) {
		super(name, displayChar);
	}

    @Override
	public void tick(Location currentLocation, Actor actor) {
		turns += 1;
	}
	
    @Override
	public void tick(Location currentLocation) {
		turns += 1;
		if(canRespawn()) {
			respawn(currentLocation);
		}
	}

    /**
     * If corpse is within the range between minimum respawn turns and
     * maximum respawn turns, it has a chance to respawn. If it is equals
     * to maximum repsawn turns, it guarantees a respawn. 
     * 
     * @return true if the corpse is allowed to respawn
     */
    public boolean canRespawn() {
    	if(turns > minRespawnTurns) {
    		if(rand.nextBoolean()) {
    			return true;
    		}
    	}
    	else if(turns == maxRespawnTurns) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Removes the corpse from the map and adds a new zombie in it.
     * 
     * @param currentLocation location to remove corpse and add zombie
     */
	public void respawn(Location currentLocation) {
		currentLocation.removeItem(this);
		currentLocation.addActor(new Zombie(name));
	}
	
}

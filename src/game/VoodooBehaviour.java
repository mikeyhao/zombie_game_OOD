package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents the main behaviour
 * for the VoodooZombie.
 * It keeps track of the chanting durations and spell casting cooldown
 * and will return the appropriate actions.
 * 
 * @author glio0001, braj0006
 *
 */
public class VoodooBehaviour implements Behaviour{

	private Random rand = new Random();
	private int createTicker = 0;
	private int chantTicker = 0;
	private int createCooldown;
	private int chantDuration;
	private int howManyZombies;
	
	public VoodooBehaviour(int createCooldown, int chantDuration, int howManyZombies) {
		this.createCooldown = createCooldown;
		this.chantDuration = chantDuration;
		this.howManyZombies = howManyZombies;
	}
	
	/**
	 * It will check if it is ready to create zombies, if not, it will increment the ticker and
	 * return null and the behaviour doesn't perform an action. 
	 * When it is time to cast the spell, it will start the chanting ticker, and it will call 
	 * the ChantAction. Once chanting is done, CreateZombieActon is called to create zombies.
	 * 
	 * 
	 * @param actor The actor performing the behaviour
	 * @param map The map where the actor exists in
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		// can start chanting
		if(createTicker >= createCooldown) {
			
			// can start creating
			if(chantTicker >= chantDuration) {
				ArrayList<Location> locations = new ArrayList<Location>();
				int maxX = map.getXRange().max();
				int maxY = map.getYRange().max();
				
				int x, y;
				int i = 0;
				while (i<howManyZombies) {
					x = rand.nextInt(maxX);
					y = rand.nextInt(maxY);
					Location here = map.at(x, y);
					if (!here.containsAnActor()) {
						locations.add(here);
						i++;
					}
				}
				createTicker = 0;
				chantTicker = 0;
				return new CreateZombieAction(locations);
			}
			
			else {
				chantTicker ++;
				return new ChantAction(chantTicker, chantDuration);
			}
		}
		
		createTicker ++;
		return null;
	}

	@Override
	public boolean hasCapability(Enum<?> capability) {
		// TODO Auto-generated method stub
		return false;
	}

}

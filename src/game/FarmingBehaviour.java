package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Allows an Actor to perform a farming action, which includes
 * either a SowAction, HarvestAction or FertilizeAction.
 * 
 * @author glio0001, braj0006
 *
 */
public class FarmingBehaviour implements Behaviour{

	private double sowChance = 0.33;
	private Random rand = new Random();
	

	/**
	 * Returns a SowAction depending on the probability. If not, it will
	 * attempt to either Harvest or Fertilize a nearby crop. If no crop is found
	 * it will return null, meaning this behaviour does not return any action
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no Action is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		boolean willSow = rand.nextDouble()<=sowChance;
		
		Location here = map.locationOf(actor);		
		for(Exit exits : here.getExits()) {
			Location destination = exits.getDestination();
			boolean NoItems = destination.getItems().size()<1;
			
			if(destination.getGround().getDisplayChar() == '.' && NoItems && willSow) {
				return new SowAction(destination);
			}
			
			else if(destination.getGround().getDisplayChar() == '$' && NoItems) {
				Crop crop = (Crop) destination.getGround();
				if (crop.isRipe()){
					return new HarvestAction(crop, destination);
				}
				else {
					return new FertilizeAction(crop);
				}
			}
		}
		return null;
	}

	@Override
	public boolean hasCapability(Enum<?> capability) {
		return false;
	}

}

package game;

import java.util.List;

import edu.monash.fit2099.engine.*;


/**
 * A class that generates a PickUpAction if the actor 
 * is standing on an item that can be picked up
 * 
 * @author glio0001, braj0006
 *
 */
public class PickUpBehaviour implements Behaviour{

	/**
	 * Determines if this behaviour requires movement
	 */
	Capabilities isMovement = new Capabilities();
	
	public PickUpBehaviour() {
		isMovement.addCapability(BehaviourCapability.NON_MOVEMENT);
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		Location here = map.locationOf(actor);
		List<Item> items = here.getItems();
		for (Item item: items) {
			return item.getPickUpAction();
		}
		return null;
		}

	@Override
	public boolean hasCapability(Enum<?> capability) {
		// TODO Auto-generated method stub
		return isMovement.hasCapability(capability);
	}
}

	

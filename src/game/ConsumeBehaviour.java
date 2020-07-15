package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * A behaviour that returns a consume action if a food is nearby
 * 
 * @author glio0001, braj0006
 */
public class ConsumeBehaviour implements Behaviour{

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);		
		for(Exit exits : here.getExits()) {
			Location destination = exits.getDestination();
			for (Item item: destination.getItems()){
				if(item.hasCapability(ItemCapability.IS_FOOD)) {
					Food food = (Food)item;
					actor.addItemToInventory(food);
					return new ConsumeAction(food);
				}
			}
		}
		return null;
	}

	@Override
	public boolean hasCapability(Enum<?> capability) {
		// TODO Auto-generated method stub
		return false;
	}

}

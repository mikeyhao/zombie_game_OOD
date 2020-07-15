package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action to harvest a crop into food.
 * 
 * @author glio0001, braj0006
 *
 */
public class HarvestAction extends Action{

	/**
	 * Location of crop to be harvested.
	 */
	protected Location here;
	
	/**
	 * Reference to crop object
	 */
	protected Crop crop;
	
	public HarvestAction(Crop crop, Location here) {
		this.crop = crop;
		this.here = here;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		here.setGround(new Dirt());
		Food food = new Food();
		
		if (actor.hasCapability(HumanCapability.FARMER)) {
			here.addItem(food);
		}
		
		else if(actor.hasCapability(HumanCapability.PLAYER)) {
			actor.addItemToInventory(food);
		}
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" harvests crop into food.";
	}

}

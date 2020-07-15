package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action to upgrade an item with upgrade capability of club or mace
 * in the inventory
 * 
 * @author glio0001, braj0006
 *
 */
public class UpgradeItemAction extends Action{

	protected Item item;
	
	public UpgradeItemAction(Item item) {
		this.item = item;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		if (item.hasCapability(ItemUpgradeCapability.CLUB)){
			actor.addItemToInventory(new ZombieClub());
		}
		else if (item.hasCapability(ItemUpgradeCapability.MACE)){
			actor.addItemToInventory(new ZombieMace());
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " upgrades " + this.item;
	}

}

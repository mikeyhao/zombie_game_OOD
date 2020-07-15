package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * A class that represents the action
 * when a player uses the Shotgun to
 * choose aim direction.
 * 
 * @author glio0001, braj0006
 *
 */
public class ShotgunAction extends Action{

	private Menu subMenu = new Menu();
	private int damage;
	private Display disp = new Display();
	
	public ShotgunAction(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Presents the player with a submenu to choose the direction
	 * to aim the shotgun at
	 * 
	 * @param actor Actor performing action
	 * @param map Map where actor currently exists in
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		boolean noAmmo = true;
		
		// checks for ammo
		for (Item item: actor.getInventory()) {
			if (item.hasCapability(ItemCapability.IS_SHOTGUN_AMMO)) {
				actor.removeItemFromInventory(item);
				noAmmo = false;
				break;
			}
		}

		if (noAmmo) {
			return "no ammo. ";
			}
		
		Actions actions = new Actions();
		actions.add(new ShotgunSelectionAction(Directions.NORTH, damage));
		actions.add(new ShotgunSelectionAction(Directions.SOUTH, damage));
		actions.add(new ShotgunSelectionAction(Directions.WEST, damage));
		actions.add(new ShotgunSelectionAction(Directions.EAST, damage));
		
		subMenu.showMenu(actor, actions, disp).execute(actor, map);
		
		return "Shotgun action complete.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Use shotgun.";
	}

}

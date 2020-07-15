package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents the action when
 * VoodooZombie decides to disappear from 
 * the map
 * 
 * @author glio0001, braj0006
 *
 */
public class VoodooVanishAction extends Action{

	public VoodooVanishAction() {
		
	}
	
	/**
	 * Removes the actor, sets a corner ground object to VoodooGround
	 * for the VoodooZombie to spawn in later turns.
	 * @param actor Actor to be removed
	 * @param map The map of the current actor
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		int x = 1, y = 1;
		while (map.at(x,y).containsAnActor()) {
			x ++;
			y ++;
		}
		map.at(x, y).setGround(new VoodooGround());
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " has vanished from the map!";
	}

}

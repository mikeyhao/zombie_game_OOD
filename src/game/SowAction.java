package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Action to sow crop onto the dirt.
 * 
 * @author glio0001, braj0006
 *
 */
public class SowAction extends Action{

	/**
	 * Location to sow crop.
	 */
	protected Location here;
	
	public SowAction(Location dirt) {
		this.here = dirt;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		here.setGround(new Crop());
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " sows crops";
	}

}

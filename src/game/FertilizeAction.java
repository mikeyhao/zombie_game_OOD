package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An Action to fertilize a crop.
 * 
 * @author glio0001, braj0006
 *
 */
public class FertilizeAction extends Action{

	/**
	 * Reference to the crop object to be fertilized
	 */
	protected Crop here;
	
	public FertilizeAction(Crop crop) {
		this.here = crop;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		here.fertilize();
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " fertilizes crop.";
	}

}

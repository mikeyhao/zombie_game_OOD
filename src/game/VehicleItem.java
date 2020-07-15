package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * A class that generates a vehicle object+ addAc
 * 
 * @author glio0001, braj0006
 *
 */
public class VehicleItem extends Item{

	public VehicleItem(String name, char displayChar) {
		super(name, displayChar, false);
	}

	/**
	 * An action to be added into the allowableActions
	 * @param action Action to be added
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

}

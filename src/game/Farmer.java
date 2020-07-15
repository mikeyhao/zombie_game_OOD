package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * A Farmer
 * 
 * @author glio0001, braj0006
 *
 */
public class Farmer extends Human{

	/**
	 * A farmer can farm, consume or wander.
	 */
	private Behaviour[] behaviours = {
			new FarmingBehaviour(),
			new ConsumeBehaviour(),
			new WanderBehaviour()
	};
	
	public Farmer(String name) {
		super(name, 'F', 50);
		this.addCapability(HumanCapability.FARMER);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
}

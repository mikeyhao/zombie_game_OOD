package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


/**
 * Special Action for consuming food.
 */
public class ConsumeAction extends Action{

	/**
	 * Food to be consumed.
	 */
	protected Food food;

	public ConsumeAction(Food food) {
		this.food = food;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(food.getHeal());
		actor.removeItemFromInventory(food);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" eats food, provide healing for "+ Integer.toString(food.getHeal());
	}

}

package game;

import edu.monash.fit2099.engine.Item;

/**
 * A food object that can be consumed by a human.
 * 
 * @author glio0001, braj0006
 *
 */
public class Food extends Item{


	/**
	 * The amount of health the food heals
	 */
	protected int healPoints;
	
	public Food() {
		super("Food", '*', true);
		this.addCapability(ItemCapability.IS_FOOD);
		this.healPoints = 10;
		
		this.allowableActions.add(new ConsumeAction(this));
	}

	/**
	 * @return healPoints of food.
	 */
	public int getHeal() {
		return healPoints;
	}
	
}

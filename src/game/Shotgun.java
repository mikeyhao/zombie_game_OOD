package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A class that represents a Shotgun weapon, 
 * it extends from WeaponItem
 * 
 * @author glio0001, braj0006
 *
 */
public class Shotgun extends WeaponItem{
	
	public Shotgun() {
		super("shotgun", 's', 1000, "shot");
		
		this.allowableActions.add(new ShotgunAction(this.damage()));
	}

}

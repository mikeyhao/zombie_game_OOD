package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * An arm weapon, dropped by a zombie.
 * 
 * @author glio0001, braj0006
 *
 */
public class ArmWeapon extends WeaponItem{

	public ArmWeapon() {
		super("zombie arm", 'A', 15, "ARM ATTACK");
		this.addCapability(ItemUpgradeCapability.CLUB);
		
		this.allowableActions.add(new UpgradeItemAction(this));
	}

}

package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A leg weapon, dropped by a zombie.
 * 
 * @author glio0001, braj0006
 *
 */
public class LegWeapon extends WeaponItem{

	public LegWeapon() {
		super("zombie leg", 'L', 15, "LEG ATTACK");
		this.addCapability(ItemUpgradeCapability.MACE);

		this.allowableActions.add(new UpgradeItemAction(this));
	}

}

package game;

import edu.monash.fit2099.engine.Item;

public class ShotgunAmmo extends Item{

	public ShotgunAmmo() {
		super("Shotgun ammo", 'o', true);
		addCapability(ItemCapability.IS_SHOTGUN_AMMO);
	}

}

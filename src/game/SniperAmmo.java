package game;

import edu.monash.fit2099.engine.Item;

public class SniperAmmo extends Item{

	public SniperAmmo() {
		super("Sniper ammo", 'i', true);
		addCapability(ItemCapability.IS_SNIPER_AMMO);
	}

}

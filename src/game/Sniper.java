package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;

public class Sniper extends WeaponItem{
	public Sniper() {
		super("sniper", 'S', 40, "shot");
		
		Action action = new SniperAction(this.damage());
		this.allowableActions.add(action);
	}


}

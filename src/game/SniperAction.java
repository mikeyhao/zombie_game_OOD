package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.WeaponItem;

public class SniperAction extends Action{
	
	private Action nextTurn;
	private Actor target;
	private Menu subMenu = new Menu();
	private int damage;
	private Display disp = new Display();
	
	public SniperAction(int damage) {
		this.damage = damage;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		boolean noAmmo = true;
		// checks for ammo
		for (Item item: actor.getInventory()) {
			if (item.hasCapability(ItemCapability.IS_SNIPER_AMMO)) {
				actor.removeItemFromInventory(item);
				noAmmo = false;
				break;
			}
		}
		// no ammo condition
		if (noAmmo) {
			return "No sniper ammo.";
		}
		
		ArrayList<Actor> targets = new ArrayList<Actor>();
		int minX = map.getXRange().min();
		int maxX = map.getXRange().max();
		int minY = map.getYRange().min();
		int maxY = map.getYRange().max();
		
		for (int x=minX; x <= maxX; x++) {
			for (int y=minY; y <= maxY; y++) {
				Location here = new Location(map, x, y);
				if (map.isAnActorAt(here)) {
					Actor target = map.getActorAt(here);
					if (target.hasCapability(ZombieCapability.UNDEAD)) {
						targets.add(target);
					}
				}
			}
		}

		Actions actions = new Actions();
		for (Actor target: targets) {
			actions.add(new SniperTurnAction(1, target, damage));
		}
		
		nextTurn = subMenu.showMenu(actor, actions, disp);
		
		
		return "target chosen.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Use sniper.";
	}

	@Override
	public Action getNextAction() {
		return nextTurn;
	}
	
}

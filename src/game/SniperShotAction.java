package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SniperShotAction extends Action{

	private int turns;
	private Actor target;
	private int damage;
	private double snipeHitChance;
	private Random rand = new Random();
	private Display disp = new Display();
	
	public SniperShotAction(int turns, Actor target, int damage) {
		this.turns = turns;
		this.target = target;
		this.damage = damage;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		
		if (turns == 1) {
			this.damage = damage;
			snipeHitChance = 0.75;
		}
		else if (turns == 2) {
			this.damage = damage*2;
			snipeHitChance = 0.90;
		}
		else {
			this.damage = 999999999;
			snipeHitChance = 1;
		}
		
		if (rand.nextDouble() <= snipeHitChance) {
			target.hurt(damage);
		}
		else {
			return "Missed " + target;
		}

		if (!target.isConscious()) {	
			if(target.hasCapability(ZombieCapability.ALIVE)) {
				Item corpse = new Corpse("dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}
		
		disp.println(result);
		
		return "Snipes " + target + " for " + damage;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Shoot now!";
	}

}

package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	protected double biteMissChance = 0.5; // remind change TODO
	protected int biteHeal = 5;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();
		String result = "";
		result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		String punchMiss = actor + " misses " + target + ".";
		
		if(actor.hasCapability(ZombieCapability.ALIVE)) { // represents when a human attacks a zombie
			target.hurt(damage);
			if(target.hasCapability(LostLimbCapability.ARM) || target.hasCapability(LostLimbCapability.LEG)) {
				Location currentLocation = map.locationOf(target);
				WeaponItem simpleClub;
				
				if (target.hasCapability(LostLimbCapability.ARM)) { 
					simpleClub = new ArmWeapon(); 
				}
				else {
					simpleClub = new LegWeapon(); }
				
				for(Exit exits : currentLocation.getExits()) {
					Location destination = exits.getDestination();
					if (!destination.containsAnActor() && destination.getItems().size()<1) {
						destination.addItem(simpleClub);
						break;
					}
				}
			}
		}
		
		else if(actor.hasCapability(ZombieCapability.UNDEAD)) { // represents when a zombie attacks a human
			if (weapon.verb() == "bites") {
				if(rand.nextDouble() <= biteMissChance) {
					return actor + " misses " + target + ".";
				}
				target.hurt(damage);
				actor.heal(biteHeal);
				String healText = "\n" + actor + " heals for " + Integer.toString(biteHeal) + " points.";
				result += healText;
					
			}
			else {
				if(rand.nextBoolean()) {
					return punchMiss;
				}
				target.hurt(damage);
			}
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
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}

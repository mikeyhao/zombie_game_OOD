package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.interfaces.ActorInterface;


/**
 * A class that represents the Shotgun event where
 * a target is damaged. This action is called
 * after the direction has been passed
 * through the constructor.
 * 
 * @author glio0001, braj0006
 *
 */
public class ShotgunSelectionAction extends Action{

	private Directions direction;
	private ArrayList<Location> hitSpots;
	private int damage;
	private double shotgunMiss = 1; // currently at 100% for visualization purpose
	private Random rand = new Random();
	private Display disp = new Display();
	
	public ShotgunSelectionAction(Directions direction, int damage) {
		this.direction = direction;
		this.hitSpots = new ArrayList<Location>();
		this.damage = damage;
	}
	
	/**
	 * It calls filterEligible which replaces hitSpots
	 * with the appropriate directional x,y values. It will then
	 * compile all the target within the range and then perform
	 * hurt() on all of them individually.
	 * 
	 * @param actor The actor performing the action
	 * @param map The map where th eactor is currently at
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		filterEligible(map, actor, this.direction);
		ArrayList<Actor> targets = new ArrayList<Actor>();
		String result = "";
		
		
		for(Location loc: hitSpots) {
			if (loc.containsAnActor()) {
				if (loc.getActor().hasCapability(ZombieCapability.UNDEAD)) {
					targets.add(loc.getActor());
				}
			}
		}
		
		for(Actor target: targets) {
			if (rand.nextDouble() <= shotgunMiss) {
				target.hurt(damage);
				String temp = target + " hurt by shotgun for " + damage;
				result += "\n";
				result += temp;

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
			}
			else {
				String temp = "shotgun missed " + target;
				result += "\n";
				result += temp;
			}
		}

		disp.println(result);
		
		
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		String direct = "";
		if (direction == Directions.NORTH) {
			direct = "north. ";
		}
		else if (direction == Directions.SOUTH) {
			direct = "south. ";
		}
		else if (direction == Directions.EAST) {
			direct = "east. ";
		}
		else if (direction == Directions.WEST) {
			direct = "west. ";
		}
		return actor + " use shotgun at direction " + direct;
	}
	
	/**
	 * Determines which direction the shotgun is going to shoot and
	 * replaces the class level variable hitSpots with the
	 * appropriate locations.
	 * 
	 * @param map The map of the actor
	 * @param actor The actor performing action
	 * @param direction The direction to shoot, an ENUM value
	 */
	private void filterEligible(GameMap map, Actor actor, Directions direction){
		int x = map.locationOf(actor).x();
		int y = map.locationOf(actor).y();

		ArrayList<Location> southLocs = new ArrayList<Location>(Arrays.asList(new Location(map,x-3,y+3), new Location(map,x-2,y+3), 
				new Location(map,x-1,y+3), new Location(map,x,y+3), new Location(map,x+1,y+3), new Location(map,x+2,y+3), new Location(map,x+3,y+3), 
				new Location(map,x-2,y+2), new Location(map,x-1,y+2), new Location(map,x,y+2), new Location(map,x+1,y+2), new Location(map,x+2,y+2), 
				new Location(map,x-1,y+1), new Location(map,x,y+1), new Location(map,x+1,y+1)));
				
		ArrayList<Location> northLocs = new ArrayList<Location>(Arrays.asList(new Location(map,x-3,y-3), new Location(map,x-2,y-3), 
				new Location(map,x-1,y-3), new Location(map,x,y-3), new Location(map,x+1,y-3), new Location(map,x+2,y-3), new Location(map,x+3,y-3), 
				new Location(map,x-2,y-2), new Location(map,x-1,y-2), new Location(map,x,y-2), new Location(map,x+1,y-2), new Location(map,x+2,y-2), 
				new Location(map,x-1,y-1), new Location(map,x,y-1), new Location(map,x+1,y-1)));

		ArrayList<Location> westLocs = new ArrayList<Location>(Arrays.asList(new Location(map,x-3,y+3), new Location(map,x-3,y+2), new Location(map,x-3,y+1), 
				new Location(map,x-3,y), new Location(map,x-3,y-1), new Location(map,x-3,y-2), new Location(map,x-3,y-3), new Location(map,x-2,y+2), 
				new Location(map,x-2,y+1), new Location(map,x-2,y), new Location(map,x-2,y-1), new Location(map,x-2,y-2), new Location(map,x-1,y+1), 
				new Location(map,x-1,y), new Location(map,x-1,y-1)));

		ArrayList<Location> eastLocs = new ArrayList<Location>(Arrays.asList(new Location(map,x+3,y+3), new Location(map,x+3,y+2), new Location(map,x+3,y+1), 
				new Location(map,x+3,y), new Location(map,x+3,y-1), new Location(map,x+3,y-2), new Location(map,x+3,y-3), new Location(map,x+2,y+2), 
				new Location(map,x+2,y+1), new Location(map,x+2,y), new Location(map,x+2,y-1), new Location(map,x+2,y-2), new Location(map,x+1,y+1), 
				new Location(map,x+1,y), new Location(map,x+1,y-1)));
		
		if (direction == Directions.NORTH) {
			for (Location loc: northLocs) {
				int xhere = loc.x();
				int yhere = loc.y();
				try {
					map.at(xhere, yhere);
					hitSpots.add(loc);
				} catch (Exception e) {
					continue;
				}
			}
		}
		
		else if (direction == Directions.SOUTH) {
			for (Location loc: southLocs) {
				int xhere = loc.x();
				int yhere = loc.y();
				try {
					map.at(xhere, yhere);
					hitSpots.add(loc);
				} catch (Exception e) {
					continue;
				}
			}
		}
		
		else if (direction == Directions.EAST) {
			for (Location loc: eastLocs) {
				int xhere = loc.x();
				int yhere = loc.y();
				try {
					map.at(xhere, yhere);
					hitSpots.add(loc);
				} catch (Exception e) {
					continue;
				}
			}
		}
		
		else if (direction == Directions.WEST) {
			for (Location loc: westLocs) {
				int xhere = loc.x();
				int yhere = loc.y();
				try {
					map.at(xhere, yhere);
					hitSpots.add(loc);
				} catch (Exception e) {
					continue;
				}
			}
		}
		
	}

}

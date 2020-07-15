package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that creates zombies onto the given map,
 * the locations to spawn zombies are given when
 * through the class constructor
 * 
 * @author glio0001, braj0006
 *
 */
public class CreateZombieAction extends Action{

	private ArrayList<Location> createLocations;
	private String tempMenuDescription = "";
	
	public CreateZombieAction(ArrayList<Location> here) {
		createLocations = here;
	}

	/**
	 * Loops through the locations and creates zombies at the locations
	 * 
	 * @param actor The actor performing action
	 * @param map The map to spawn zombies in
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		for(Location here : createLocations) {
			here.addActor(new Zombie("mambo kids"));
			String temp = "" + here.x() +","+ here.y()+ ". ";
			tempMenuDescription += temp;
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor +"created a zombie at " + tempMenuDescription;
	}
	
	

}

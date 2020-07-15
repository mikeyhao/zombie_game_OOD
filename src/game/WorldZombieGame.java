package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * A class that represents the world used for
 * the specific zombie game
 * 
 * @author glio0001, braj0006
 */
public class WorldZombieGame extends World{

	public WorldZombieGame(Display display) {
		super(display);
	}
	
	/**
	 * Runs like the engine World class, but it will now
	 * check if there are any zombies or humans left and end
	 * the game accordingly. It also contains the condition
	 * when the player quits the game.
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (super.stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			int zombiesAlive = 0;
			int humansAlive = 0;

			// Process all the actors.
			for (Actor actor : actorLocations) {
				
				if(actor.hasCapability(ZombieCapability.UNDEAD)) {
					zombiesAlive ++;
				}
				else {
					humansAlive ++;
				}
				
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}
			
			// when all zombies are defeated
			if (zombiesAlive == 0) {
				display.println(playerWin());
				playersMap.removeActor(player);
			}
			// when all humans/farmers are defeated
			else if (humansAlive == 1) {
				display.println(playerLost());
				playersMap.removeActor(player);
			}
			
		}
		display.println(endGameMessage());
	}
	
	protected String playerLost() {
		return "Player loses! All humans are dead!";
	}
	
	protected String playerWin() {
		return "Player wins! All zombies are dead!";
	}
	
}

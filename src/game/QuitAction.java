package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that player quitting the game.
 * 
 * @author glio0001, braj0006
 */
public class QuitAction extends Action{

	private Player player;
	private Display disp = new Display();
	
	public QuitAction(Player player) {
		this.player = player;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(player);
		return player + " has quit the game.";
	}

	@Override
	public String hotkey() {
		return "q";
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return "Do you want to quit?";
	}

}

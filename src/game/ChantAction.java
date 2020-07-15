package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantAction extends Action{

	private int progress;
	private int duration;
	
	public ChantAction(int progress, int duration) {
		this.progress = progress;
		this.duration = duration;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " chanting zombie spawning spell, progress "+progress+"/"+duration;
	}

}

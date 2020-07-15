package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class DummyZombie extends ZombieActor{

	public DummyZombie() {
		super("dummy zombie", 'z', 1, ZombieCapability.UNDEAD);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		return new DoNothingAction();	
	}

}

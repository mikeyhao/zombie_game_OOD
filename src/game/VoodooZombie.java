package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class VoodooZombie extends ZombieActor{
	
	private int zombieCreateAmount = 5;
	private int createCooldown = 2;
	private int chantDuration = 1;
	private int vanishTicker = 0;
	private int vanishDuration = 30;
	
	private GameMap vanishMap;
	private GameMap mainMap;
	private Behaviour[] behaviours = {
			new VoodooBehaviour(createCooldown, chantDuration, zombieCreateAmount),
			new WanderBehaviour()
	};

	public VoodooZombie(String name) {
		super(name, 'M', 250, ZombieCapability.UNDEAD);
	}
	

	/**
	 * It will either perform VoodooBehaviour or WanderBehaviour, VoodooBehaviour fails if it is still currently on cooldown as it
	 * will return null, and WanderBehaviour is called instead.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current VoodooZombie is
	 * @param display the Display where the VoodooZombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		vanishTicker++;
		
		if(vanishTicker >= vanishDuration) {
			return new VoodooVanishAction();
		}
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}	
}

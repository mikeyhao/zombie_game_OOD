package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;

public class SniperTurnAction extends Action {

	private int aimRound;
	private int totalAimRound = 3;
	private Action nextTurn;
	private Display disp = new Display();
	private Menu subMenu = new Menu();
	private Actor target;
	private int damage;
	
	public SniperTurnAction(int aimRound, Actor target, int damage) {
		this.aimRound = aimRound;
		this.target = target;
		this.damage = damage;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		disp.println("SHOOT OR AIM?");
		
		if (aimRound == 1) {
			nextTurn = new SniperTurnAction(2, target, damage);
		}
		else if (aimRound == 2) {
			nextTurn = new SniperTurnAction(3, target, damage);
		}
		else if (aimRound == 3) {
			nextTurn = null;
		}
		
		// Adds sub-option to shoot or aim
		Actions actions = new Actions();
		actions.add(new SniperShotAction(aimRound, target, damage));
		// No option to aim again if at round 3
		if (aimRound < 3) {
			actions.add(new SniperTurnAction(aimRound+1, target, damage));
		}
		Action shotChoice = subMenu.showMenu(actor, actions, disp);
		if (shotChoice instanceof SniperShotAction) {
			disp.println(shotChoice.execute(actor, map));
			nextTurn = null;
		}
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Aim sniper at... " + target + " | Round(" + aimRound + "/" + totalAimRound + ")";
	}

	@Override
	public Action getNextAction() {
		return nextTurn;
	}
	
}

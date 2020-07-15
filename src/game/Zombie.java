package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.ActorLocations;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	
	private Random rand = new Random();
	
	/**
	 * Chance that a zombie uses a punch when getting intrinsic weapon
	 */
	private double punchChance = 0; 
	
	/**
	 * Chance that a zombie speaks.
	 */
	private double speechChance = 0.1; 
	
	/**
	 * Chance that a zombie loses their limbs
	 */
	private double loseLimbChance = 1; 
	
	/**
	 * Determines whether a zombie is allowed to move
	 */
	private boolean canMove = true;
	
	/**
	 * The limbs of zombie
	 */
	private Limbs arms, legs;
	
	/**
	 * Zombie's speech
	 */
	private String zombieSpeech = "Braaains";
	
	protected Display display = new Display();
	
	private Behaviour[] behaviours = {
			new PickUpBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		arms = new Limbs(2);
		legs = new Limbs(2);
	}
	

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon bite = new IntrinsicWeapon(15, "bites");
		IntrinsicWeapon punch = new IntrinsicWeapon(10, "punches");
		if(rand.nextDouble() <= punchChance) {
			return punch;
		}
		else {
			return bite;}
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		// Zombie speech
		if(rand.nextDouble() <= speechChance) {
			display.println(name+":"+zombieSpeech); 
		}
		
		if(this.getArms() <= 1 && rand.nextBoolean()) {
			for (Item item: this.getInventory()) {
				this.removeItemFromInventory(item);
				map.locationOf(this).addItem(item);
				display.println(name+" dropped "+item);
				break;
			}
		}
		
		
		movementTracker();
		for (Behaviour behaviour : behaviours) {
			if (behaviour.hasCapability(BehaviourCapability.MOVEMENT) && canMove == false){
				System.out.println(behaviour.getClass().toString());
				continue;
			}
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
	@Override
	public void hurt(int points) {
		super.hurt(points);

		if (this.hasCapability(LostLimbCapability.ARM)) {
			this.removeCapability(LostLimbCapability.ARM);
		}
		else if (this.hasCapability(LostLimbCapability.LEG)) {
			this.removeCapability(LostLimbCapability.LEG);
		}
		
		if (rand.nextDouble() <= loseLimbChance) {
			if(rand.nextBoolean()) {
				loseAnArm();
			}
			else {
				loseALeg();
			}
		}
	}
	
	/**
	 * Losing an arm will reduce it's punch chance, becomes 0 if
	 * the zombie has no arms.
	 * 
	 * It also adds a capability which acts as a tracker whether the zombie has lost an arm
	 */
	public void loseAnArm() {
		if(armsExists()) {
			display.println(name+" lost an arm.");
			arms.breakALimb();
			this.addCapability(LostLimbCapability.ARM);
			if(!armsExists()) {
				Behaviour[] newBehaviours = {new AttackBehaviour(ZombieCapability.ALIVE),new HuntBehaviour(Human.class, 10),new WanderBehaviour()};
				this.behaviours = newBehaviours;
				punchChance = 0;
			}
			punchChance = punchChance/2;
		}
	}
	
	/**
	 * Losing a leg
	 * 
	 * It also adds a capability which acts as a tracker whether the zombie has lost a leg
	 */
	public void loseALeg() {
		if(legsExists()) {
			display.println(name+" lost a leg.");
			legs.breakALimb();
			this.addCapability(LostLimbCapability.LEG);
		}
	}
	
	/**
	 * @return True is arms still exists
	 */
	public boolean armsExists() {
		return arms.hasLimbs();
	}
	
	/**
	 * @return True is legs still exists
	 */
	public boolean legsExists() {
		return legs.hasLimbs();
	}
	
	/**
	 * @return Returns number of legs
	 */
	public int getLegs() {
		return legs.getTotal();
	}

	/**
	 * @return Returns number of legs
	 */
	public int getArms() {
		return arms.getTotal();
	}
	
	/**
	 * A ticker to determine if a zombie is allowed to move.
	 * 
	 * If 2, the zombie can move.
	 * If 1, the zombie can move if it couldn't the previous turn, vice versa.
	 * If 0, the zombie cannot move.
	 */
	public void movementTracker() {
		if(getLegs() >= 2) {
			canMove = true;
		}
		else if(getLegs() == 1) {
			canMove = !canMove;
		}
		else {
			canMove = false;
		}
	}
}

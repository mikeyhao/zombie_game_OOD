package game;

/**
 * A class representing the limbs of an actor.
 * Usage: private Limbs arms/legs.
 * 
 * @author glio0001, braj0006
 *
 */
public class Limbs {

	/**
	 * Total limbs
	 */
	private int total;
	
	public Limbs(int total) {
		this.total = total;
	}
	
	/**
	 * Decrements the total limbs
	 */
	public void breakALimb() {
		if(hasLimbs()) {
			total -= 1;
		}
	}
	
	/**
	 * @return True if there are still limbs left
	 */
	public boolean hasLimbs() {
		if(total>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return Returns number of limbs
	 */
	public int getTotal() {
		return total;
	}
	
}

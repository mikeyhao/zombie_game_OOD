package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.demo.mars.MartianItem;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * A class that handles map creation
 * 
 * @author glio0001, braj0006
 *
 */
public class MapCreator {
	
	private GameMap startMap;
	private GameMap townMap;
	private GameMap testMap;
	
	public MapCreator() {
		
	}
	
	/**
	 * Takes a MapVariant and creates the map.
	 * @param mapType ENUM value of MapVariants
	 */
	public void createMap(MapVariants mapType) {
		if (mapType == MapVariants.START) {
			createStartMap();
		}
		else if (mapType == MapVariants.TOWN){
			createTownMap();
		}
		else if (mapType == MapVariants.TESTER) {
			createTesterMap();
		}
	}
	
	/**
	 * Getter for the map
	 * @param mapType ENUM value of MapVariants
	 * @return GameMap object of the map
	 */
	public GameMap getMap(MapVariants mapType) {
		if (mapType == MapVariants.START) {
			return startMap;
		}
		else if (mapType == MapVariants.TOWN){
			return townMap;
		}
		else if (mapType == MapVariants.TESTER) {
			return testMap;
		}
		return null;
	}
	
	/**
	 * Populates the map with items and actors
	 * @param mapType ENUM value of MapVariants
	 */
	public void fillMap(MapVariants mapType) {
		if (mapType == MapVariants.START) {
			GameMap gameMap = this.getMap(MapVariants.START);
			String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy"};
			String[] farmers = {"Farmer John", "Farmer Mikey", "Farmer Ben"};
			
			int x, y;
			for (String name : humans) {
				do {
					x = (int) Math.floor(Math.random() * 20.0 + 30.0);
					y = (int) Math.floor(Math.random() * 7.0 + 5.0);
				} 
				while (gameMap.at(x, y).containsAnActor());
				gameMap.at(x,  y).addActor(new Human(name));
			}
			for (String name : farmers) {
				do {
					x = (int) Math.floor(Math.random() * 20.0 + 30.0);
					y = (int) Math.floor(Math.random() * 7.0 + 5.0);
				} 
				while (gameMap.at(x, y).containsAnActor());
				gameMap.at(x,  y).addActor(new Farmer(name));	
			}

//			gameMap.at(50, 18).addItem(new Plank());

			gameMap.at(30, 20).addActor(new Zombie("Groan"));
			gameMap.at(30,  18).addActor(new Zombie("Boo"));
			gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
			gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
			gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
			gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	

	        VehicleItem vehicle = new VehicleItem("Vehicle", '^');
	        vehicle.addAction(new MoveActorAction(townMap.at(42, 15), "to the town!"));
	        gameMap.at(42, 14).addItem(vehicle);
//
//			gameMap.at(42, 15).addItem(new Shotgun());
	        
			gameMap.at(1, 1).setGround(new VoodooGround());
		}
		else if (mapType == MapVariants.TOWN){
			GameMap gameMap = this.getMap(MapVariants.TOWN);
			
	        VehicleItem vehicle = new VehicleItem("Vehicle", '^');
	        vehicle.addAction(new MoveActorAction(startMap.at(42, 13), "to the main map!"));
	        gameMap.at(42, 14).addItem(vehicle);
			
			gameMap.at(43, 15).addItem(new Shotgun());
			gameMap.at(41, 15).addItem(new Sniper());
			gameMap.at(44, 15).addItem(new ShotgunAmmo());
			gameMap.at(41, 16).addItem(new SniperAmmo());
		}
		else if (mapType == MapVariants.TESTER) {
			GameMap gameMap = this.getMap(MapVariants.TESTER);
			
			// SHOTGUN TEST SECTION
			gameMap.at(42, 15).addItem(new Shotgun());
			gameMap.at(42, 15).addItem(new ShotgunAmmo());
			for(int i=16; i < 20; i++) {
				for(int j=38; j < 47; j++) {
					gameMap.at(j, i).addActor(new DummyZombie());
				}
			}
			
			// SNIPER TEST SECTION
//			gameMap.at(42, 15).addItem(new Sniper());
//			gameMap.at(42, 15).addItem(new SniperAmmo());
//			gameMap.at(30, 20).addActor(new DummyZombie());
//			gameMap.at(30,  18).addActor(new DummyZombie());
//			gameMap.at(10,  4).addActor(new DummyZombie());
//			gameMap.at(50, 18).addActor(new DummyZombie());
//			gameMap.at(1, 10).addActor(new DummyZombie());
//			gameMap.at(62, 12).addActor(new DummyZombie());	
		}
	}
	
	/**
	 * Initializes startMap
	 */
	private void createStartMap() {

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		
		GameMap gameMap = new GameMap(groundFactory, map );
		startMap = gameMap;
	}
	
	/**
	 * Initializes townMap
	 */
	private void createTownMap() {

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................");
		
		GameMap gameMap = new GameMap(groundFactory, map );
		townMap = gameMap;
	}
	

	/**
	 * Initializes testMap
	 */
	private void createTesterMap() {
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................");
		
		GameMap gameMap = new GameMap(groundFactory, map );
		testMap = gameMap;
	}
}

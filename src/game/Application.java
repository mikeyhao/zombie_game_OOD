package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.interfaces.ActorInterface;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
//		World world = new World(new Display());
		WorldZombieGame world = new WorldZombieGame(new Display());
		
		// DONT TOUCH ME
		MapCreator mapCreator = new MapCreator();
		
		mapCreator.createMap(MapVariants.START);
		GameMap gameMap = mapCreator.getMap(MapVariants.START);
		
		mapCreator.createMap(MapVariants.TOWN);
		GameMap townMap = mapCreator.getMap(MapVariants.TOWN);
		
		mapCreator.createMap(MapVariants.TESTER);
		GameMap testMap = mapCreator.getMap(MapVariants.TESTER);
		
		world.addGameMap(gameMap);
		world.addGameMap(townMap);
		world.addGameMap(testMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(42, 15));
//		world.addPlayer(player, testMap.at(42, 15));
		
		mapCreator.fillMap(MapVariants.START);
		mapCreator.fillMap(MapVariants.TOWN);
		mapCreator.fillMap(MapVariants.TESTER);

		
		world.run();
	}
}

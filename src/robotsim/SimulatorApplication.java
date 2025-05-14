package robotsim;
import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import robotsim.model.ChargingStation;
import robotsim.model.Door;
import robotsim.model.Factory;
import robotsim.model.ProductionArea;
import robotsim.model.ProductionMachine;
import robotsim.model.Robot;
import robotsim.model.Room;
import robotsim.model.SimulatorController;
import robotsim.model.Position;
import robotsim.model.DijkstraPathFinder;
import java.util.logging.*;
import java.util.Arrays;

public class SimulatorApplication {
	
	private static final Logger LOGGER = Logger.getLogger(SimulatorApplication.class.getName());
	public static final int robotRadius = 8;
	
	public static void main(String[] args) {
		
		LOGGER.info("Starting the robot simulator...");
		LOGGER.config("With parameters " + Arrays.toString(args) + ".");
		
		final Factory factory = new Factory("Factory", new Position(0, 0), 500, 500);
		
		final Room room1 = new Room(new Position(25, 50), 150, 150, "Production Room 1", factory);
		final ProductionArea area1 = new ProductionArea(new Position(50, 75), 100, 100, "Production Area 1", factory);
		final ProductionMachine machine1 = new ProductionMachine(new Position(75, 100), 50, 50, "Machine 1", factory);
		area1.addMachine(machine1);
		room1.addArea(area1);
		final Door door1 = new Door(new Position(50, 200), 50, 5, "Entrance", factory, room1, null);
		room1.addDoor(door1);
		
		final Room room2 = new Room(new Position(300, 50), 150, 150, "Production Room 2", factory);
		final ProductionArea area2 = new ProductionArea(new Position(325, 75), 100, 100, "Production Area 2", factory);
		final ProductionMachine machine2 = new ProductionMachine(new Position(350, 100), 50, 50, "Machine 2", factory);
		area2.addMachine(machine2);
		room2.addArea(area2);
		final Door door2 = new Door(new Position(300, 75), 5, 50, "Entrance", factory, room2, null);
		room2.addDoor(door2);
		
		final Room room3 = new Room(new Position(300, 300), 150, 150, "Charging Room", factory);
		final ChargingStation station = new ChargingStation(new Position(350, 350), 50,50, "Charging Station", factory, false);
		room3.addStation(station);
		final Door door3 = new Door(new Position(450, 325), 5, 50, "Entrance", factory, room3, null);
		room3.addDoor(door3);
		
		final Robot robot1 = new Robot(new Position(10, 10), robotRadius, "Robot 2", factory, 0, null, new DijkstraPathFinder(factory));
		final Robot robot2 = new Robot(new Position(40, 10), robotRadius, "Robot 1", factory, 0, null, new DijkstraPathFinder(factory));
		
		factory.addRoom(room1);
		factory.addRoom(room2);
		factory.addRoom(room3);
		factory.addRobot(robot1);
		factory.addRobot(robot2);
	
		final SimulatorController controller = new SimulatorController(factory);
		
		final CanvasViewer viewer = new CanvasViewer(controller);
		
	}
}

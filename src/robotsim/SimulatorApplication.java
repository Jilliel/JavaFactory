package robotsim;
import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import robotsim.model.ChargingStation;
import robotsim.model.Conveyor;
import robotsim.model.Door;
import robotsim.model.Factory;
import robotsim.model.ProductionArea;
import robotsim.model.ProductionMachine;
import robotsim.model.Robot;
import robotsim.model.Room;
import robotsim.model.SimulatorController;
import robotsim.model.VendingMachine;
import robotsim.model.Washer;
import robotsim.model.Position;
import robotsim.model.DijkstraPathFinder;
import java.util.logging.*;
import java.io.IOException;
import java.util.Arrays;

public class SimulatorApplication {
	
	private static final Logger LOGGER = Logger.getLogger(SimulatorApplication.class.getName());
	
	public static void main(String[] args) throws IOException {
		
		LOGGER.info("Starting the robot simulator...");
		LOGGER.config("With parameters " + Arrays.toString(args) + ".");
		
		final Factory factory = new Factory("Factory", new Position(0, 0), 500, 500);
		
		final Room room1 = new Room(new Position(50, 50), 150, 150, "Production Room 1", factory);
		final ProductionArea area1 = new ProductionArea(new Position(75, 75), 100, 100, "Production Area 1", factory);
		final Washer washer = new Washer(new Position(80, 160), 5, 0, "Washer", factory);
		area1.addMaterial(washer);
		final ProductionMachine machine1 = new ProductionMachine(new Position(100, 100), 50, 50, "Machine 1", factory);
		area1.addMachine(machine1);
		room1.addArea(area1);
		final Door door1 = new Door(new Position(75, 200), 50, 5, "Entrance", factory, room1, null);
		room1.addDoor(door1);
		
		final Room room2 = new Room(new Position(300, 50), 150, 150, "Production Room 2", factory);
		final ProductionArea area2 = new ProductionArea(new Position(325, 75), 100, 100, "Production Area 2", factory);
		final ProductionMachine machine2 = new ProductionMachine(new Position(350, 100), 50, 50, "Machine 2", factory);
		area2.addMachine(machine2);
		room2.addArea(area2);
		final Door door2 = new Door(new Position(300, 75), 5, 50, "Entrance", factory, room2, null);
		room2.addDoor(door2);
		
		final Room room3 = new Room(new Position(300, 300), 150, 150, "Charging Room", factory);
		final ChargingStation station1 = new ChargingStation(new Position(315, 315), 40, 40, "Charging Station 1", factory);
		final ChargingStation station2 = new ChargingStation(new Position(395, 315), 40, 40, "Charging Station 2", factory);
		final ChargingStation station3 = new ChargingStation(new Position(315, 395), 40, 40, "Charging Station 3", factory);
		final ChargingStation station4 = new ChargingStation(new Position(395, 395), 40, 40, "Charging Station 4", factory);
		room3.addStation(station1);
		room3.addStation(station2);
		room3.addStation(station3);
		room3.addStation(station4);
		final Door door3 = new Door(new Position(450, 325), 5, 50, "Entrance", factory, room3, null);
		room3.addDoor(door3);
		
		final Robot robot1 = new Robot(new Position(10, 10), Robot.defaultRobotRadius, "Robot 1", factory, 0, null, new DijkstraPathFinder(factory));
		final Robot robot2 = new Robot(new Position(40, 10), Robot.defaultRobotRadius, "Robot 2", factory, 0, null, new DijkstraPathFinder(factory));
		final Robot robot3 = new Robot(new Position(70, 10), Robot.defaultRobotRadius, "Robot 3", factory, 0, null, new DijkstraPathFinder(factory));
		
		final VendingMachine Vmachine = new VendingMachine(new Position(20, 350), 40, 80, "Vending Machine", factory);
		final Conveyor conveyor = new Conveyor(new Position(50, 370), 150, 40, "Conveyor", factory, -1, true);
		
		factory.addRoom(room1);
		factory.addRoom(room2);
		factory.addRoom(room3);
		factory.addRobot(robot1);
		factory.addRobot(robot2);
		factory.addRobot(robot3);
		factory.addVendingMacchine(Vmachine);
		factory.addConveyor(conveyor);
	
		final SimulatorController controller = new SimulatorController(factory);
		
		final CanvasViewer viewer = new CanvasViewer(controller);
		
	}
}

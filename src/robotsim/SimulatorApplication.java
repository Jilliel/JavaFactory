package robotsim;
import robotsim.model.ChargingStation;
import robotsim.model.Door;
import robotsim.model.Factory;
import robotsim.model.ProductionArea;
import robotsim.model.Robot;
import robotsim.model.Room;
import robotsim.model.ProductionMachine;
import java.util.ArrayList;
import fr.tp.inf112.projects.canvas.view.CanvasViewer;


public class SimulatorApplication {
	
	public static void main(String[] args) {
		final Room room1 = new Room(25, 50, 150, 150, "Production Room 1");
		final ProductionArea area1 = new ProductionArea(50,75, 100, 100, "Production Area 1");
		final ProductionMachine machine1 = new ProductionMachine(75, 100, 50, 50, "Machine 1");
		area1.addMachine(machine1);
		room1.addArea(area1);
		final Door door1 = new Door(50, 200, 50, 5, "Entrance", true, room1, null);
		room1.addDoor(door1);
		
		final Room room2 = new Room(300, 50, 150, 150, "Production Room 2");
		final ProductionArea area2 = new ProductionArea(325,75, 100, 100, "Production Area 2");
		final ProductionMachine machine2 = new ProductionMachine(350, 100, 50, 50, "Machine 2");
		area2.addMachine(machine2);
		room2.addArea(area2);
		final Door door2 = new Door(300, 75, 5, 50, "Entrance", true, room2, null);
		room2.addDoor(door2);
		
		final Room room3 = new Room(300, 300, 150, 150, "Charging Room");
		final ChargingStation station = new ChargingStation(350, 350, 50,50, "Charging Station", false);
		room3.addStation(station);
		final Door door3 = new Door(450, 325, 5, 50, "Entrance", false, room3, null);
		room3.addDoor(door3);
		
		final Robot robot1 = new Robot(10, 10, 5, "Robot 1", 0, 100, null, false, null);
		final Robot robot2 = new Robot(40, 10, 5, "Robot 2", 0, 100, null, false, null);
		
		final Factory factory = new Factory("Factory", 0, 0, 500, 500);
		factory.addRoom(room1);
		factory.addRoom(room2);
		factory.addRoom(room3);
		factory.addRobot(robot1);
		factory.addRobot(robot2);
		
		final CanvasViewer viewer = new CanvasViewer(factory);
		
	}
}

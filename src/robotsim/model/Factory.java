package robotsim.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.lang.Math;

import shapes.Rectangle;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import robotsim.SimulatorApplication;
import fr.tp.inf112.projects.canvas.controller.Observable;
import fr.tp.inf112.projects.canvas.controller.Observer;

import java.io.Serializable;

public class Factory extends Component implements Canvas, Serializable, Observable {
	
	private static final long serialVersionUID = 202505090919L;
	
	private String id;
	
	private int width;
	private int height;
	
	private List<Room> rooms;
	private List<Robot> robots;
	
	private transient List<Observer> observers;
	private List<VendingMachine> vendingMachines;
	private List<Conveyor> conveyors;
	private boolean simulationRunning;	
	
	private int counter;
	
	public Factory(String name, Position position, int width, int height) {
		super(position, name, null);
		this.width = width;
		this.height = height;
		this.rooms = new ArrayList<Room>();
		this.robots = new ArrayList<Robot>();
		this.observers = new ArrayList<Observer>();
		this.vendingMachines = new ArrayList<VendingMachine>();
		this.conveyors = new ArrayList<Conveyor>();
		this.simulationRunning = false;
	}
	
	public boolean canProduce(ProductionMachine machine) {
		int targetX = machine.getxCoordinate() + (machine.getWidth()/2) - ProductionMachine.getWasherRadius();
		int targetY = machine.getyCoordinate() + (machine.getHeight()/2) - ProductionMachine.getWasherRadius();
		for (Room room : this.rooms) {
			for (ProductionArea area : room.getAreas()) {
				for (Washer washer : area.getMaterials()) {
					if (washer.getxCoordinate() == targetX && washer.getyCoordinate()==targetY) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override 
	public String getId() {
		return this.id;
	}
	
	private boolean checkRobotName(String name) {
		for (Robot robot : this.robots) {
			if (name == robot.getName()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addRobot(Robot robot) {
		if (this.checkRobotName(robot.getName())) {
			this.robots.add(robot);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkRoomName(String name) {
		for (Room room : this.rooms) {
			if (name == room.getName()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addRoom(Room room) {
		if (this.checkRoomName(room.getName())) {
			this.rooms.add(room);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getValidity(Position position) {
		
		int x = position.getX() + Robot.defaultRobotRadius;
		int y = position.getY() + Robot.defaultRobotRadius;
		if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
			return false;
		}
		
		for (Door door : this.getDoors()) {
			//if (!door.isOpen()) { continue; }
			int w = door.getWidth();
			int h = door.getHeight();
			int x0 = door.getxCoordinate();
			int y0 = door.getyCoordinate();
			/* The door has 2 conditions for collision*/
			boolean xnear = true;
			boolean ynear = true;
			if (w > h) {
				xnear = Math.abs(x0 + w / 2 - x) <= w / 2 - Robot.defaultRobotRadius;
				ynear = Math.abs(y0 + h / 2 - y) <= h / 2 + Robot.defaultRobotRadius;
			} else {
				xnear = Math.abs(x0 + w / 2 - x) <= w / 2 + Robot.defaultRobotRadius;
				ynear = Math.abs(y0 + h / 2 - y) <= h / 2 - Robot.defaultRobotRadius;
			}
			if (xnear && ynear) {
				return true;
			}
		}
				
		for (Room room : this.getRooms()) {
			int w = room.getWidth();
			int h = room.getHeight();
			int x0 = room.getxCoordinate();
			int y0 = room.getyCoordinate();
			boolean left = Math.abs(x0 - x) < Robot.defaultRobotRadius;
			boolean right = Math.abs(x0 + w - x) < Robot.defaultRobotRadius;
			boolean up = Math.abs(y0 - y) < Robot.defaultRobotRadius;
			boolean down = Math.abs(y0 + h - y) < Robot.defaultRobotRadius;
			/* X and Y positions */
			boolean xcontain = x0 < x + Robot.defaultRobotRadius && x - Robot.defaultRobotRadius < x0 + w;
			boolean ycontain = y0 < y + Robot.defaultRobotRadius && y - Robot.defaultRobotRadius < y0 + h;
			
			
			/* Left wall */
			if (right && ycontain) {
				return false;
			}
			/* Left wall */
			else if (left && ycontain) {
				return false;
			}
			/* Upper wall */
			else if (up && xcontain) {
				return false;
			}
			/* Down wall */
			else if (down && xcontain) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public List<Door> getDoors() {
		ArrayList<Door> doors = new ArrayList<Door>();
		for (Room room : this.rooms) {
			doors.addAll(room.getDoors());
		}
		return doors;
	}
	
	public List<Washer> getWashers() {
		ArrayList<Washer> result = new ArrayList<Washer>();
		for (Room room: this.rooms) {
			result.addAll(room.getMaterials());
		}
		return result;
	}
	
	@Override
	public Collection<Figure> getFigures() {
		ArrayList<Figure> elements = new ArrayList<Figure>();
		elements.addAll(conveyors);
		/* Add the robots */
		elements.addAll(this.robots);
		/* Add rooms' components */
		for (Room room : this.rooms) {
			elements.addAll(room.getComponents());
		}
		elements.addAll(this.vendingMachines);
		return (Collection<Figure>) elements;
	}
	
	private void assignRobots() {
		for (Washer washer : this.getWashers()) {
			if (washer.isOwned()) {
				continue;
			}
			for (Robot robot: this.robots) {
				if (!robot.isBusy()) {
					robot.assign(washer);
					break;
				}
			}
		}
	}
	
	private void handleDoors() {
		for (Door door : this.getDoors()) {
			int w = door.getWidth();
			int h = door.getHeight();
			int x0 = door.getxCoordinate();
			int y0 = door.getyCoordinate();
			
			for (Robot robot : this.robots) {
				int x = robot.getPosition().getX() + Robot.defaultRobotRadius;
				int y = robot.getPosition().getY() + Robot.defaultRobotRadius;
				int dx = Math.abs(x0+w/2-x);
				int dy = Math.abs(y0+h/2-y);
				/* The door has 2 conditions for collision*/
				boolean xnear = false; 
				boolean ynear = false;
				
				if (w > h) {
					xnear = dx < w - 2 * Robot.defaultRobotRadius;
					ynear = dy < h + 2 * Robot.defaultRobotRadius;
				} else {
					xnear = dx < w + 2 * Robot.defaultRobotRadius;
					ynear = dy < h - 2 * Robot.defaultRobotRadius;
				}
				
				boolean near = xnear && ynear;
				door.setOpen(near);
				if (near) {
					break;
				}
			}
		}
	}
	
	public boolean collide(Robot source, Position next) {
		for (Robot robot : this.robots) {
			if (robot == source) {continue;}
			Position pos = robot.getPosition();
			double dx2 = Math.pow(pos.getX() - next.getX(), 2);
			double dy2 = Math.pow(pos.getY() - next.getY(), 2);
			if (dx2 + dy2 <= Math.pow(2 * Robot.defaultRobotRadius, 2)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void behave() {
		this.assignRobots();
		this.handleDoors();
		for (Robot robot : this.robots) {
			robot.behave();
		}
		for (Room room : this.rooms) {
			room.behave();
		}
		for (VendingMachine machine : this.vendingMachines) {
			machine.behave();
		}
		for (Conveyor conveyor : this.conveyors) {
			conveyor.behave();
		}
	}
	
	@Override
	public boolean addObserver(Observer observer) {
		if (this.observers == null) {
			this.observers = new ArrayList<Observer>(); 
		}
		if (this.observers.contains(observer)) {
			return false;
		}
		this.observers.add(observer);
		return true;
	}

	@Override
	public boolean removeObserver(Observer observer) {
		if (this.observers == null) {
			this.observers = new ArrayList<Observer>(); 
		}
		return this.observers.remove(observer);
	}
	
	public void notifyObservers() {
		if (this.observers == null) {
			this.observers = new ArrayList<Observer>(); 
		}
		for (Observer observer : this.observers) {
			observer.modelChanged();
		}
	}
	
	public void startSimulation() {
		this.simulationRunning = true;
		while (isSimulationRunning()) {
			behave();
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void stopSimulation() {
		this.simulationRunning = false;
	}
	
	public boolean isSimulationRunning() {
		return this.simulationRunning;
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(this.width, this.height);
	}
	
	public Position getDeliveryPoint() {
		if (this.getConveyors().size() > 0) {
			if (counter >= this.getConveyors().size() - 1) {
				counter = -1;
			}
			counter = counter + 1;
			return this.getConveyors().get(counter).getDeliveryPoint();
		}
		else if (this.getVendingMachines().size() > 0) {
			if (counter >= this.getVendingMachines().size() - 1) {
				counter = -1;
			}
			counter = counter + 1;
			return this.getVendingMachines().get(counter).getDeliveryPoint();
		}
		else {
			return new Position(10,10);
		}
		
	}

	public List<VendingMachine> getVendingMachines() {
		return this.vendingMachines;
	}
	
	public void addVendingMacchine(VendingMachine machine) {
		this.vendingMachines.add(machine);
	}
	
	public List<Conveyor> getConveyors() {
		return this.conveyors;
	}
	
	public void addConveyor(Conveyor conveyor) {
		this.conveyors.add(conveyor);
	}
	
	
}

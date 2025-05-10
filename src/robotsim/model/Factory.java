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
	public static final int spatialStep = 3;
	
	private String id;
	
	private int width;
	private int height;
	
	private List<Room> rooms;
	private List<Robot> robots;
	
	private transient List<Observer> observers;
	private boolean simulationRunning;	
	
	public Factory(String name, Position position, int width, int height) {
		super(position, name, null);
		this.width = width;
		this.height = height;
		this.rooms = new ArrayList<Room>();
		this.robots = new ArrayList<Robot>();
		this.observers = new ArrayList<Observer>();
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
		List<Component> obstacles = this.getObstacles();
		int x = position.getX();
		int y = position.getY();
		if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
			return false;
		}

		for (Component obstacle : obstacles) {
			if (obstacle instanceof Door) {
				Door door = (Door) obstacle;
				if (door.isOpen()) {
					continue;
				} else if (Math.abs(door.getxCoordinate() + door.getWidth() / 2 - x) > SimulatorApplication.robotRadius + door.getWidth() / 2) {
					continue;
				} else if (Math.abs(door.getyCoordinate() + door.getHeight() / 2 - y) > SimulatorApplication.robotRadius + door.getHeight() / 2) {
					continue;
				} else {
					return false;
				}
			} else if (obstacle instanceof Room) {
				Room room = (Room) obstacle;
				int leftwall = room.getxCoordinate();
				int rightwall = room.getxCoordinate() + room.getHeight();
				if (Math.abs(x - leftwall) < SimulatorApplication.robotRadius || Math.abs(x - rightwall) < SimulatorApplication.robotRadius) {
					return false;
				}
				int upperwall = room.getyCoordinate();
				int downwall = room.getyCoordinate() + room.getHeight();
				if (Math.abs(y - upperwall) < SimulatorApplication.robotRadius || Math.abs(y - downwall) < SimulatorApplication.robotRadius) {
					return false;
				}
			}
		}
		return true;
	}
	
	public List<Component> getObstacles() {
		ArrayList<Component> obstacles = new ArrayList<Component>();
		for (Room room : this.rooms) {
			obstacles.addAll(room.getObstacles());
		}
		return obstacles;
	}
	
	@Override
	public Collection<Figure> getFigures() {
		ArrayList<Figure> elements = new ArrayList<Figure>();
		/* Add the robots */
		elements.addAll(this.robots);
		/* Add rooms' components */
		for (Room room : this.rooms) {
			elements.addAll(room.getComponents());
		}
		return (Collection<Figure>) elements;
	}
	
	@Override
	public void behave() {
		for (Robot robot : this.robots) {
			robot.behave();
		}
		for (Room room : this.rooms) {
			room.behave();
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
				Thread.sleep(200);
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
}

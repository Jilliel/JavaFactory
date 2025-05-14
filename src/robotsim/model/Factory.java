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
		
		int x = position.getX() + SimulatorApplication.robotRadius;
		int y = position.getY() + SimulatorApplication.robotRadius;
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
				xnear = Math.abs(x0 + w / 2 - x) <= w / 2 - SimulatorApplication.robotRadius;
				ynear = Math.abs(y0 + h / 2 - y) <= h / 2 + SimulatorApplication.robotRadius;
			} else {
				xnear = Math.abs(x0 + w / 2 - x) <= w / 2 + SimulatorApplication.robotRadius;
				ynear = Math.abs(y0 + h / 2 - y) <= h / 2 - SimulatorApplication.robotRadius;
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
			boolean left = Math.abs(x0 - x) < SimulatorApplication.robotRadius;
			boolean right = Math.abs(x0 + w - x) < SimulatorApplication.robotRadius;
			boolean up = Math.abs(y0 - y) < SimulatorApplication.robotRadius;
			boolean down = Math.abs(y0 + h - y) < SimulatorApplication.robotRadius;
			/* X and Y positions */
			boolean xcontain = x0 < x + SimulatorApplication.robotRadius && x - SimulatorApplication.robotRadius < x0 + w;
			boolean ycontain = y0 < y + SimulatorApplication.robotRadius && y - SimulatorApplication.robotRadius < y0 + h;
			
			
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
		/* Add the robots */
		elements.addAll(this.robots);
		/* Add rooms' components */
		for (Room room : this.rooms) {
			elements.addAll(room.getComponents());
		}
		return (Collection<Figure>) elements;
	}
	
	private void assignRobos() {
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
				int x = robot.getPosition().getX() + SimulatorApplication.robotRadius;
				int y = robot.getPosition().getY() + SimulatorApplication.robotRadius;
				int dx = Math.abs(x0+w/2-x);
				int dy = Math.abs(y0+h/2-y);
				/* The door has 2 conditions for collision*/
				boolean xnear = false; 
				boolean ynear = false;
				
				if (w > h) {
					xnear = dx < w - SimulatorApplication.robotRadius;
					ynear = dy < h + SimulatorApplication.robotRadius;
				} else {
					xnear = dx < w + SimulatorApplication.robotRadius;
					ynear = dy < h - SimulatorApplication.robotRadius;
				}
				
				boolean near = xnear && ynear;
				door.setOpen(near);
				if (near) {
					break;
				}
			}
		}
	}
	@Override
	public void behave() {
		this.assignRobos();
		this.handleDoors();
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
				Thread.sleep(50);
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

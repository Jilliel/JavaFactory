package robotsim.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import shapes.Rectangle;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.Figure;

import fr.tp.inf112.projects.canvas.controller.Observable;
import fr.tp.inf112.projects.canvas.controller.Observer;

import java.io.Serializable;

public class Factory extends Component implements Canvas, Serializable, Observable {
	
	private static final long serialVersionUID = 202505090919L;
	
	private int width;
	private int height;
	private List<Room> rooms;
	private List<Robot> robots;
	
	private transient List<Observer> observers;
	private boolean simulationRunning;

	private String id;

	
	public Factory(String name, int x, int y, int width, int height) {
		super(x, y, new Rectangle(width, height), name, null);
		this.robots = new ArrayList<Robot>();
		this.rooms = new ArrayList<Room>();
		this.width = width;
		this.height = height;
		this.observers = new ArrayList<Observer>();
		this.simulationRunning = false;
		
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
		for (int i=0; i < this.robots.size(); i++) {
			if (name == this.robots.get(i).getName()) {
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
		for (int i=0; i < this.rooms.size(); i++) {
			if (name == this.rooms.get(i).getName()) {
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
	
	@Override
	public Collection<Figure> getFigures() {
		ArrayList<Figure> elements = new ArrayList<Figure>();
		/* Add the robots */
		elements.addAll(this.robots);
		/* Add rooms' components */
		for (int i=0; i < this.rooms.size(); i++) {
			elements.addAll(this.rooms.get(i).getComponents());
		}
		return (Collection<Figure>) elements;
	}
	
	@Override
	public void behave() {
		for (int i=0; i < this.robots.size(); i++) {
			this.robots.get(i).behave();
		}
		for (int i=0; i < this.rooms.size(); i++) {
			this.rooms.get(i).behave();
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
}

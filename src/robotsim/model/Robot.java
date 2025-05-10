package robotsim.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStyle;
import shapes.Oval;

public class Robot extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090930L;
	
	private double speed;
	private Room room;
	private int radius;
	private List<Position> targets;
	private List<Position> path;
	private FactoryPathFinder pathFinder;
	
	private static final ComponentStyle style = new ComponentStyle(new ComponentColor(0, 255, 0), null);
	
	public Robot(Position position, int radius, String name, Factory factory, double speed, Room room, FactoryPathFinder pathFinder) {
		super(position, name, factory);
		this.speed = speed;
		this.radius = radius;
		this.room = room;
		this.pathFinder = pathFinder;
		this.targets = new ArrayList<Position>();
		this.path = new ArrayList<Position>();
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	@Override
	public String toString() {
		return "Nom: " + this.getName() + " / Vitesse: " + this.getSpeed() + "km/h.";
	}
	
	@Override
	public Style getStyle() {
		return Robot.style;
	}
	
	public void addTarget(Position target) {
		this.targets.add(target);
	}
	
	private Position getTarget() {
		return this.targets.remove(0);	
	}
	
	private void setPath(List<Position> path) {
		this.path = path;
	}
	
	private Position getNextOnPath() {
		return this.path.remove(0);	
	}
	
	private void move() {
		Position next = this.getNextOnPath();
		//TODO: Make the robot move here
	}
	
	public void behave() {
		if (this.path.size() == 0) {
			List<Position> path = this.pathFinder.findPath(this.getPosition(), this.getTarget());
			// Handle edge-cases here.
			this.setPath(path);
		}
		this.move();
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Oval(radius, radius);
	}
}

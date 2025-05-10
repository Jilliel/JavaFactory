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
	private Position current;
	private boolean awaitingPath;
	private transient FactoryPathFinder pathFinder = null;
	
	private static final ComponentStyle style = new ComponentStyle(new ComponentColor(0, 255, 0), null);
	
	public Robot(Position position, int radius, String name, Factory factory, double speed, Room room, FactoryPathFinder pathFinder) {
		super(position, name, factory);
		this.speed = speed;
		this.radius = radius;
		this.room = room;
		this.targets = new ArrayList<Position>();
		this.current = null;
		this.path = new ArrayList<Position>();
		this.pathFinder = pathFinder;
		this.awaitingPath = false;
	}
	
	public Position getCenter() {
		return new Position(this.getxCoordinate() + this.radius / 2, this.getyCoordinate() + this.radius / 2);
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
	
	private void renewTarget() {
		if (this.targets.size() > 0) {
			this.current = this.targets.remove(0);	
			this.awaitingPath = true;
		} else {
			this.current = null;
		}
	}
	
	private void renewPath() {
		if (this.pathFinder == null) {
			this.pathFinder = new DijkstraPathFinder(this.getFactory());
		}
		List<Position> newpath = this.pathFinder.findPath(this.getCenter(), this.current);
		if (newpath != null) {
			this.path = newpath;
			this.awaitingPath = false;
		}
	}
	
	private Position getNextOnPath() {
		return this.path.remove(0);	
	}
	
	private void move() {
		this.setPosition(this.getNextOnPath());
	}
	
	public void behave() {
		if (this.current == null) {
			this.renewTarget();
			return;
		}
		
		if (this.path.size() == 0) {
			if (!(this.awaitingPath))  {
				this.renewTarget();
			}
			if (this.current == null) {
				return;
			}
			this.renewPath();
		} else {
			this.move();
		}
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Oval(radius, radius);
	}
}

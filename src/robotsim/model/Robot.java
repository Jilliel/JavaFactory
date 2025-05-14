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
	private List<Position> path;
	private Washer washer;
	private Position target;
	private transient FactoryPathFinder pathFinder = null;
	private boolean busy;
	private boolean delivering;
	private boolean awaitingpath;
	
	private static final ComponentStyle style = new ComponentStyle(new ComponentColor(0, 255, 0), null);
	
	public Robot(Position position, int radius, String name, Factory factory, double speed, Room room, FactoryPathFinder pathFinder) {
		super(position, name, factory);
		this.speed = speed;
		this.radius = radius;
		this.room = room;
		this.busy = false;
		this.target = null;
		this.washer = null;
		this.delivering = false;
		this.pathFinder = pathFinder;
		this.awaitingpath = false;
		this.path = new ArrayList<Position>();
	}
	
	public boolean isBusy() {
		return this.busy;
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
	
	public void assign(Washer washer) {
		this.busy = true;
		this.washer = washer;
		this.target = washer.getPosition();
		this.awaitingpath = true;
		this.washer.setOwner(this);
	}
		
	private void findPath() {
		if (this.pathFinder == null) {
			this.pathFinder = new DijkstraPathFinder(this.getFactory());
		}
		List<Position> newpath = this.pathFinder.findPath(this.getPosition(), this.target);
		if (newpath != null) {
			this.path = newpath;
			this.awaitingpath = false;
		} else {
			this.awaitingpath = true;
		}
	}
	
	private Position getNextOnPath() {
		return this.path.remove(0);	
	}
	
	private void move() {
		if (this.path.size() > 0) {
			Position next = this.getNextOnPath();
			this.setPosition(next);
		}
	}
	
	public void behave() {
		if (this.washer == null) {
			return;
		}
		
		if (this.path.size() == 0) {
			if (this.awaitingpath) {
				this.findPath();
			} else if (!this.delivering) {
				// Find a delivering zone  (Experimental)
				this.target = new Position(10, 10);
				// Pick the washer
				this.washer.pick();
				// Setup path
				this.delivering = true;
				this.findPath();
			} else { 
				this.busy = false;
				this.target = null;
				this.washer.drop();
				this.delivering = false;
			}
		}
		this.move();
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Oval(radius, radius);
	}
}

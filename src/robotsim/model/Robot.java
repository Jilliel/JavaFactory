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
	
	private String defaultName;
	private double speed;
	private Room room;
	private int radius;
	private List<Position> path;
	private Washer washer;
	private Position target;
	private transient FactoryPathFinder pathFinder = null;
	public static final int defaultRobotRadius = 8;
	private boolean busy;
	private boolean delivering;
	private boolean awaitingpath;
	public static final int maxNumberOfSteps = 1100;
	private int stepsLeft = Robot.maxNumberOfSteps;
	public static final int backupBattery = 400;
	private int backupLeft = Robot.backupBattery;
	private boolean charging = false;
	
	public Robot(Position position, int radius, String name, Factory factory, double speed, Room room, FactoryPathFinder pathFinder) {
		super(position, name, factory);
		this.defaultName = name;
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

	public void setCharging(boolean charging) {
		this.charging = charging;
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
		int number1 = (255 * this.stepsLeft) / Robot.maxNumberOfSteps;
		int number2 = (255 * this.backupLeft) / Robot.backupBattery;
		ComponentStyle style = new ComponentStyle(new ComponentColor(number2 - number1 , number1, 0), null);
		return style;
	}
	
	public void assign(Washer washer) {
		this.busy = true;
		this.washer = washer;
		this.target = washer.getPosition();
		this.awaitingpath = true;
		this.washer.setOwner(this);
	}
	
	public void assign(ChargingStation station) {
		this.busy = true;
		this.target = (new Position((station.getWidth()/2) - Robot.defaultRobotRadius, (station.getHeight()/2)- Robot.defaultRobotRadius)).add(station.getPosition());
		this.awaitingpath = true;
		this.delivering = true;
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
	
	private boolean checkOhersOnPath() {
		return this.getFactory().collide(this, this.path.get(0));
	}
	
	private void move() {
		if (this.path.size() > 0) {
			if (this.checkOhersOnPath()) {
				return;
			} else {
				this.setPosition(this.getNextOnPath());
				if (this.stepsLeft > 0) {
					this.stepsLeft = this.stepsLeft - 1;
					this.setName(defaultName + " - " +(100 * (this.stepsLeft + this.backupLeft)/(Robot.maxNumberOfSteps + Robot.backupBattery)) + "%");
				}
				else {
					this.backupLeft = this.backupLeft - 1;
					this.setName(defaultName + " - CRITICAL" );
				}
			}
		}
	}
	
	public void behave() {
		if (this.backupLeft == 0) {
			this.setName(defaultName + " - DEAD" );
			return;
		}
		if (this.washer == null || !this.busy || this.charging) {
			return;
		} 
		
		if (this.path.size() == 0) {
			if (this.awaitingpath) {
				this.findPath();
			} else if (!this.delivering) {
				// Find a delivering zone  (Experimental)
				this.target = this.getFactory().getDeliveryPoint();
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
		return new Oval(radius, radius);
	}

	public boolean canGoToWasher(Washer washer) {
		List<Position> newpath = this.pathFinder.findPath(this.getPosition(), washer.getPosition());
		return newpath.size() <= this.stepsLeft;
	}
	
	public void charge() {
		if (this.path.size() == 0) {
			this.charging = true;
			if (this.backupLeft < Robot.backupBattery) {
				this.backupLeft = this.backupLeft + 1;
				this.setName(defaultName + " - CRITICAL" );
			}
			else if (this.stepsLeft < Robot.maxNumberOfSteps) {
				this.stepsLeft = this.stepsLeft + 1;
				this.setName(defaultName + " - " +(100 * (this.stepsLeft + this.backupLeft)/(Robot.maxNumberOfSteps + Robot.backupBattery)) + "%" );
			}
			else {
				this.charging = false;
			}
		}
	}
}

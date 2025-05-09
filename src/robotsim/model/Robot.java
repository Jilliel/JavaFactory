package robotsim.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStyle;
import shapes.Oval;

public class Robot extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090930L;
	private double speed;
	private double battery;
	private Room room;
	private boolean busy;
	private List<Washer> washers;
	private int radius;
	private static final ComponentStyle style = new ComponentStyle(new ComponentColor(0, 255, 0), null);
	
	public Robot(int x, int y, int radius, String name, Factory factory, double speed, double battery, Room room,
			boolean busy) {
		super(x, y, new Oval(radius, radius), name, factory);
		this.speed = speed;
		this.radius = radius;
		this.battery = battery;
		this.room = room;
		this.busy = busy;
		this.washers = new ArrayList<Washer>();
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
		this.setShape(new Oval(radius, radius));
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getBattery() {
		return battery;
	}

	public void setBattery(double battery) {
		this.battery = battery;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public List<Washer> getWashers() {
		return washers;
	}

	public void setWashers(List<Washer> washers) {
		this.washers = washers;
	}
	
	public void addWasher(Washer washer) {
		this.washers.add(washer);
	}
	
	public Washer popWasher(int index) {
		return this.washers.remove(index);	
	}
	
	public Washer getWasher(int index) {
		return this.washers.get(index);
	}

	@Override
	public String toString() {
		return "Nom: " + this.getName() + " / Vitesse: " + this.getSpeed() + "km/h.";
	}
	
	@Override
	public Style getStyle() {
		return Robot.style;
	}
	
	public boolean near(Washer other) {
		boolean xcdt = (other.getX() == this.getX());
		boolean ycdt = (other.getY() == this.getY());
		/* Washers and robots are shaped like circles*/
		return (xcdt && ycdt);
	}	
	
	private Washer getTarget() {
		if (this.washers.size() == 0) {	return null; }
		
		Washer target = this.getWasher(0);
		if (this.near(target)) {
			this.popWasher(0);
			return this.getTarget();
		} else {
			return target;
		}
	}
	
	private void move(Washer target) {
		
	}
	
	public void behave() {
		Washer target = this.getTarget();
		if (target == null) {
			return;
		}
		this.move(target);
	}
}

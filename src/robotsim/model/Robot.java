package robotsim.model;
import shapes.Oval;
import java.util.ArrayList;


public class Robot extends Component{
	
	private double speed;
	private double battery;
	private Room room;
	private boolean busy;
	private ArrayList<Washer> washers;
	private int radius;
	
	public Robot(int x, int y, int radius, String name, double speed, double battery, Room room,
			boolean busy, ArrayList<Washer> washers) {
		super(x, y, new Oval(radius, radius), name);
		this.speed = speed;
		this.radius = radius;
		this.battery = battery;
		this.room = room;
		this.busy = busy;
		this.washers = washers;
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

	public ArrayList<Washer> getWashers() {
		return washers;
	}

	public void setWashers(ArrayList<Washer> washers) {
		this.washers = washers;
	}
	
	public void addWasher(Washer washer) {
		this.washers.add(washer);
	}
	
	public Washer popWasher(int index) {
		return this.washers.remove(index);	
	}

	
	@Override
	public String toString() {
		return "Nom: " + this.getName() + " / Vitesse: " + speed + "km/h.";
	}
	
}

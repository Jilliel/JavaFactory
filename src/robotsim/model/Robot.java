package robotsim.model;
import shapes.Oval;
import java.util.ArrayList;


public class Robot extends Component{
	
	private String name;
	private double speed;
	private double battery;
	private Room room;
	private boolean busy;
	private ArrayList<Washer> washers;
	private int radius;
	
	public Robot(double x, double y, int radius, String name, double speed, double battery, Room room,
			boolean busy, ArrayList<Washer> washers) {
		super(x, y, new Oval(radius, radius));
		this.name = name;
		this.speed = speed;
		this.radius = radius;
		this.battery = battery;
		this.room = room;
		this.busy = busy;
		this.washers = washers;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Nom: " + name + " / Vitesse: " + speed + "km/h.";
	}
	
}

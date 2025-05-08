package robotsim.model;
import java.util.ArrayList;
import shapes.Rectangle;

public class Room extends Component{
	private ArrayList<Door> doors;
	private ArrayList<Washer> washers;
	private ArrayList<ProductionArea> areas;
	private ArrayList<ChargingStation> stations;
	
	public Room(int x, int y, int width, int height, ArrayList<Door> doors, ArrayList<Washer> washers,
			ArrayList<ProductionArea> areas, ArrayList<ChargingStation> stations) {
		super(x, y, new Rectangle(width, height));
		this.doors = doors;
		this.washers = washers;
		this.areas = areas;
		this.stations = stations;
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}
	
	public void addDoor(Door door) {
		this.doors.add(door);
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

	
	public ArrayList<ProductionArea> getAreas() {
		return areas;
	}

	public void setAreas(ArrayList<ProductionArea> areas) {
		this.areas = areas;
	}
	
	public void addArea(ProductionArea area) {
		this.areas.add(area);
	}
	
	
	public ArrayList<ChargingStation> getStations() {
		return stations;
	}

	public void setStations(ArrayList<ChargingStation> stations) {
		this.stations = stations;
	}
	
	public void addStation(ChargingStation station) {
		this.stations.add(station);
	}
	
}

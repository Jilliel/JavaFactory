package robotsim.model;
import java.util.List;
import java.util.ArrayList;
import shapes.Rectangle;
import robotsim.model.Factory;
import java.io.Serializable;

public class Room extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090931L;
	private List<Door> doors;
	private List<ProductionArea> areas;
	private List<ChargingStation> stations;
	

	public Room(int x, int y, int width, int height, String name, Factory factory) {
		super(x, y, new Rectangle(width, height), name, factory);
		this.doors = new ArrayList<Door>();
		this.areas = new ArrayList<ProductionArea>();
		this.stations = new ArrayList<ChargingStation>();
	}
	
	public List<Door> getDoors() {
		return doors;
	}

	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}
	
	public void addDoor(Door door) {
		this.doors.add(door);
	}
	
	public List<ProductionArea> getAreas() {
		return areas;
	}

	public void setAreas(List<ProductionArea> areas) {
		this.areas = areas;
	}
	
	public void addArea(ProductionArea area) {
		this.areas.add(area);
	}
	
	
	public List<ChargingStation> getStations() {
		return stations;
	}

	public void setStations(List<ChargingStation> stations) {
		this.stations = stations;
	}
	
	public void addStation(ChargingStation station) {
		this.stations.add(station);
	}
	
	public List<Component> getComponents() {
		ArrayList <Component> result = new ArrayList<Component>();
		/* Add itself */
		result.add(this);
		/* Add the stations */
		result.addAll(this.stations);
		/* Add the doors */
		result.addAll(this.doors);
		/* Add areas' components figures */
		for (int i=0; i<this.areas.size(); i++) {
			result.addAll(this.areas.get(i).getComponents());
		}
		return result;
	}
	
	public void behave() {
		for (int i=0; i < this.doors.size(); i++) {
			this.doors.get(i).behave();
		}
		for (int i=0; i < this.areas.size(); i++) {
			this.areas.get(i).behave();
		}
		for (int i=0; i < this.stations.size(); i++) {
			this.stations.get(i).behave();
		}
	}
}

package robotsim.model;
import java.util.ArrayList;

import robotsim.shape.Shape;

public class Room extends Component{
	private ArrayList<Door> doors;
	private ArrayList<Washer> washers;
	private ArrayList<ProductionArea> areas;
	private ArrayList<ChargingStation> stations;
	
	public Room(double x, double y, Shape shape, ArrayList<Door> doors, ArrayList<Washer> washers,
			ArrayList<ProductionArea> areas, ArrayList<ChargingStation> stations) {
		super(x, y, shape);
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

	public ArrayList<Washer> getWashers() {
		return washers;
	}

	public void setWashers(ArrayList<Washer> washers) {
		this.washers = washers;
	}

	public ArrayList<ProductionArea> getAreas() {
		return areas;
	}

	public void setAreas(ArrayList<ProductionArea> areas) {
		this.areas = areas;
	}

	public ArrayList<ChargingStation> getStations() {
		return stations;
	}

	public void setStations(ArrayList<ChargingStation> stations) {
		this.stations = stations;
	}
	
}

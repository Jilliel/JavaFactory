package robotsim.model;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

import java.util.ArrayList;
import shapes.Rectangle;
import java.io.Serializable;

public class Room extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090931L;
	private List<Door> doors;
	private List<ProductionArea> areas;
	private List<ChargingStation> stations;
	private int width;
	private int height;
	

	public Room(Position position, int width, int height, String name, Factory factory) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
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
	
	public List<Component> getObstacles() {
		ArrayList<Component> obstacles = new ArrayList<Component>();
		/* Add itself */
		obstacles.add(this);
		/* Add the doors */
		obstacles.addAll(this.doors);
		return obstacles;
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
		for (ProductionArea area : this.areas) {
			result.addAll(area.getComponents());
		}
		return result;
	}
	
	public void behave() {
		for (Door door : this.doors) {
			door.behave();
		}
		for (ProductionArea area : this.areas) {
			area.behave();
		}
		for (ChargingStation station : this.stations) {
			station.behave();
		}
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(this.width, this.height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return null;
	}
}

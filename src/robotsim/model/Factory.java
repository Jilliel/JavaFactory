package robotsim.model;
import java.util.ArrayList;
import java.util.Collection;
import shapes.Rectangle;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.Figure;

public class Factory extends Component implements Canvas{
	
	private ArrayList<Robot> robots;
	private ArrayList<Room> rooms;
	private int width;
	private int height;
	
	public Factory(String name, int x, int y, int width, int height) {
		super(x, y, new Rectangle(width, height), name);
		this.robots = new ArrayList<Robot>();
		this.rooms = new ArrayList<Room>();
		this.width = width;
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public void setId(String id) {
		this.setName(id);
	}
	
	@Override 
	public String getId() {
		return this.getName();
	}
	
	private boolean checkRobotName(String name) {
		for (int i=0; i < this.robots.size(); i++) {
			if (name == this.robots.get(i).getName()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addRobot(Robot robot) {
		if (this.checkRobotName(robot.getName())) {
			this.robots.add(robot);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkRoomName(String name) {
		for (int i=0; i < this.rooms.size(); i++) {
			if (name == this.rooms.get(i).getName()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addRoom(Room room) {
		if (this.checkRoomName(room.getName())) {
			this.rooms.add(room);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Collection<Figure> getFigures() {
		ArrayList<Figure> elements = new ArrayList<Figure>();
		for (int i=0; i < this.robots.size(); i++) {
			elements.add(this.robots.get(i));
		}
		for (int i=0; i < this.rooms.size(); i++) {
			elements.addAll(this.rooms.get(i).getComponents());
		}
		return (Collection<Figure>) elements;
	}
	
	@Override
	public void behave() {
		for (int i=0; i < this.robots.size(); i++) {
			this.robots.get(i).behave();
		}
		for (int i=0; i < this.rooms.size(); i++) {
			this.rooms.get(i).behave();
		}
	}
}

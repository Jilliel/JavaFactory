package robotsim.model;
import robotsim.shape.Shape;

public class Door extends Component {
	
	private double width;
	private boolean open;
	private Room room1;
	private Room room2;
	
	
	public Door(double x, double y, Shape shape, double width, boolean open, Room room1, Room room2) {
		super(x, y, shape);
		this.width = width;
		this.open = open;
		this.room1 = room1;
		this.room2 = room2;
	}

	public Room getRoom1() {
		return room1;
	}

	public void setRoom1(Room room1) {
		this.room1 = room1;
	}

	public Room getRoom2() {
		return room2;
	}

	public void setRoom2(Room room2) {
		this.room2 = room2;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
}

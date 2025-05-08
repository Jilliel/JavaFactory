package robotsim.model;
import shapes.Rectangle;
import robotsim.model.Factory;

public class Door extends Component {
	private int width;
	private boolean open;
	private Room room1;
	private Room room2;
	
	
	public Door(int x, int y, int width, int height, String name, Factory factory, boolean open, Room room1, Room room2) {
		super(x, y, new Rectangle(width, height), name, factory);
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
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
}

package robotsim.model;
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStroke;
import shapes.ComponentStyle;
import shapes.Rectangle;

public class Door extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090921L;
	private int width;
	private boolean open;
	private Room room1;
	private Room room2;
	private int height;
	private static final ComponentStyle openedState = new ComponentStyle(null, new ComponentStroke(null, 3, null));
	private static final ComponentStyle closedState = new ComponentStyle(new ComponentColor(0, 0, 0), new ComponentStroke(null, 3, null));
	
	
	public Door(Position position, int width, int height, String name, Factory factory, boolean open, Room room1, Room room2) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
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
	
	@Override
	public Style getStyle() {
		if (this.isOpen()){
			return Door.openedState;
		}
		else {
			return Door.closedState;
		}
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(this.width, this.height);
	}
}

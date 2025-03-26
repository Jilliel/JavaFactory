package robotsim.model;
import robotsim.shape.Rectangle;

public class Door extends Component {
	
	private double width;
	private boolean open;

	
	public Door(double x, double y, double width, boolean open) {
		super(x, y, new Rectangle(width, 0));
		this.width = width;
		this.open = open;
		
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

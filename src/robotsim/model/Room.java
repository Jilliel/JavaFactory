package robotsim.model;
import robotsim.shape.Rectangle;

public class Room extends Component{
	
	public Room(double x, double y, double width, double height) {
		super(x, y, new Rectangle(width, height));
	}
	
}

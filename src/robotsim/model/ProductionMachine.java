package robotsim.model;
import shapes.Rectangle;

public class ProductionMachine extends Component{
	
	public ProductionMachine(int x, int y, int width, int height, String name) {
		super(x, y, new Rectangle(width, height), name);
	}
	
}

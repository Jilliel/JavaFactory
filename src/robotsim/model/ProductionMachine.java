package robotsim.model;
import shapes.Rectangle;
import robotsim.model.Factory;

public class ProductionMachine extends Component{
	
	public ProductionMachine(int x, int y, int width, int height, String name, Factory factory) {
		super(x, y, new Rectangle(width, height), name, factory);
	}
	
}

package robotsim.model;
import shapes.Rectangle;
import robotsim.model.Factory;
import java.io.Serializable;

public class ProductionMachine extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090922L;
	
	public ProductionMachine(int x, int y, int width, int height, String name, Factory factory) {
		super(x, y, new Rectangle(width, height), name, factory);
	}
	
}

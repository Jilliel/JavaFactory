package robotsim.model;
import robotsim.shape.Circle;

public class Washer extends Component{
	
	private double weight;
	
	public Washer(double x, double y, double radius, double weight) {
		super(x, y, new Circle(radius));
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

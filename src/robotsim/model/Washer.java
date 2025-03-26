package robotsim.model;
import robotsim.shape.Shape;

public class Washer extends Component{
	
	private double weight;

	public Washer(double x, double y, Shape shape, double weight) {
		super(x, y, shape);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

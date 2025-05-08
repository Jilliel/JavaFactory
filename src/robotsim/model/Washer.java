package robotsim.model;
import shapes.Oval;

public class Washer extends Component{
	
	private double weight;

	public Washer(double x, double y, double radius, double weight) {
		super(x, y, new Oval((int)radius, (int)radius));
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

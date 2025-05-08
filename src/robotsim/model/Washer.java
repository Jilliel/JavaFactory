package robotsim.model;
import shapes.Oval;

public class Washer extends Component{
	
	private double weight;

	public Washer(int x, int y, int radius, double weight) {
		super(x, y, new Oval(radius, radius));
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

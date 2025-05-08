package robotsim.model;
import shapes.Oval;
import robotsim.model.Factory;

public class Washer extends Component{
	
	private double weight;

	public Washer(int x, int y, int radius, double weight, String name, Factory factory) {
		super(x, y, new Oval(radius, radius), name, factory);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

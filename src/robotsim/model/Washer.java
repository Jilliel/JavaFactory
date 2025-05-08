package robotsim.model;
import shapes.Oval;

public class Washer extends Component{
	
	private double weight;
	private int radius;
	
	public Washer(int x, int y, int radius, double weight, String name) {
		super(x, y, new Oval(radius, radius), name);
		this.weight = weight;
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

package robotsim.model;
import shapes.Oval;
import robotsim.model.Factory;
import java.io.Serializable;

public class Washer extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090923L;
	private double weight;
	private int radius;
	
	public Washer(int x, int y, int radius, double weight, String name, Factory factory) {
		super(x, y, new Oval(radius, radius), name, factory);
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

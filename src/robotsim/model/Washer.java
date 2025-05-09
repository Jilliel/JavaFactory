package robotsim.model;
import java.io.Serializable;

import shapes.ComponentColor;
import shapes.ComponentStyle;
import shapes.Oval;

public class Washer extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090923L;
	private double weight;
	private int radius;
	
	public Washer(int x, int y, int radius, double weight, String name, Factory factory) {
		super(x, y, new Oval(radius, radius), name, factory);
		this.weight = weight;
		this.radius = radius;
		this.setStyle(new ComponentStyle(new ComponentColor(0, 0, 255), null));
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

package robotsim.model;
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStyle;
import shapes.Oval;

public class Washer extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090923L;
	private double weight;
	private int radius;
	private static final ComponentStyle style = new ComponentStyle(new ComponentColor(0, 0, 255), null);
	
	public Washer(int x, int y, int radius, double weight, String name, Factory factory) {
		super(x, y, name, factory);
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
	
	@Override
	public Style getStyle() {
		return Washer.style;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Oval(radius, radius);
	}
	
}

package robotsim.model;
import robotsim.shape.Shape;

public class Component {
	private double x;
	private double y;
	private Shape shape;
	
	public Component(double x, double y, Shape shape) {
		super();
		this.x = x;
		this.y = y;
		this.shape = shape;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}

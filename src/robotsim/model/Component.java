package robotsim.model;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.Figure;

public class Component implements Figure {
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Name not specified";
	}

	@Override
	public int getxCoordinate() {
		// TODO Auto-generated method stub
		return (int)this.x;
	}

	@Override
	public int getyCoordinate() {
		// TODO Auto-generated method stub
		return (int)this.y;
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

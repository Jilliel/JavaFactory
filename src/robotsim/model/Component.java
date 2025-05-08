package robotsim.model;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.Figure;

public class Component implements Figure {
	private int x;
	private int y;
	private Shape shape;
	
	public Component(int x, int y, Shape shape) {
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
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
		return this.x;
	}

	@Override
	public int getyCoordinate() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

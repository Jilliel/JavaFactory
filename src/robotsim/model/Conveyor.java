package robotsim.model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStroke;
import shapes.ComponentStyle;
import shapes.Rectangle;

public class Conveyor extends Component implements Serializable{

	private static final long serialVersionUID = 202505151712L;
	private int width;
	private int height;
	private Position deliveryPoint;
	
	public Conveyor(Position position, int width, int height, String name, Factory factory, Position deliveryPoint) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
		this.deliveryPoint = deliveryPoint;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(width, height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return new ComponentStyle(null, new ComponentStroke(new ComponentColor(100, 100, 100), 5, null));
	}
	
	public Position getDeliveryPoint() {
		return this.deliveryPoint;
	}
}

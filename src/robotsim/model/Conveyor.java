package robotsim.model;

import java.io.IOException;
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
	private Position deliveryPoint = null;
	private int delta;
	private boolean horizontal;
	private Position deltaPos;
	
	
	
	public Conveyor(Position position, int width, int height, String name, Factory factory, int delta, boolean horizontal) throws IOException {
		super(position, name, factory);
		this.width = width;
		this.height = height;
		if (delta == 0) {
			throw new IOException("delta should not be zero");
		}
		this.delta = delta;
		this.horizontal = horizontal;
		if (horizontal) {
			this.deltaPos = new Position(delta, 0);
		}
		else {
			this.deltaPos = new Position(0, delta);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(width, height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return new ComponentStyle(new ComponentColor(100, 100, 100), null);
	}
	
	public void setDeliveryPoint(Position pos) {
		this.deliveryPoint = pos;
	}
	
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	public Position getDeliveryPoint() {
		Position point;
		if (this.deliveryPoint == null) {
			if (this.isHorizontal()) {
				if (this.delta<0) {
					point = new Position(this.getxCoordinate()+this.getWidth(),
							this.getyCoordinate()+(this.getHeight()/2));
				}
				else {
					point = new Position(this.getxCoordinate(),
							this.getyCoordinate()+(this.getHeight()/2));
				}
			}
			else {
				if (this.delta<0) {	
					point = new Position(this.getxCoordinate()+(this.getWidth()/2),
							this.getyCoordinate()+this.getHeight());
				}
				else {
					point = new Position(this.getxCoordinate()+(this.getWidth()/2),
							this.getyCoordinate());
				}
			}
		}
		else {
			point = this.deliveryPoint;
		}
		return point.add(new Position(-Robot.defaultRobotRadius, -Robot.defaultRobotRadius));
	}
	
	@Override
	public void behave() {
		for (Room room : this.getFactory().getRooms()) {
			for (ProductionArea area : room.getAreas()) {
				for (Washer washer : area.getMaterials()) {
					int x = washer.getxCoordinate();
					int y = washer.getyCoordinate();
					if ( (x >= this.getxCoordinate() && x <= this.getxCoordinate() + this.getWidth()) 
							&& (y >= this.getyCoordinate() && y <= this.getyCoordinate() + this.getHeight())
							&& (!washer.isPicked())) {
						washer.setPosition(washer.getPosition().add(this.deltaPos));
					}
				}
			}
		}
	}
	
	
}

package robotsim.model;
import java.io.Serializable;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import robotsim.SimulatorApplication;
import shapes.ComponentColor;
import shapes.ComponentStyle;
import shapes.Oval;

public class Washer extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090923L;
	private double weight;
	private int radius;
	private Robot owner;
	private boolean picked;
	private static final ComponentStyle style = new ComponentStyle(new ComponentColor(0, 0, 255), null);
	
	public Washer(Position position, int radius, double weight, String name, Factory factory) {
		super(position, name, factory);
		this.weight = weight;
		this.radius = radius;
		this.picked = false;
		this.owner = null;
	}
	
	public void pick() {
		this.picked = true;
	}
	
	public void drop() {
		this.picked = false;
	}
	
	public boolean isPicked() {
		return this.picked;
	}
	
	public void setOwner(Robot owner) {
		this.owner = owner;
	}
	
	public Robot getOwner() {
		return this.owner;
	}
	
	public void releaseOwner() {
		this.owner = null;
	}
	
	public boolean isOwned() {
		return this.owner != null;
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
	
	@Override
	public void behave() {
		if (this.picked && this.owner != null) {
			Position topleft = this.owner.getPosition();
			int xcenter = topleft.getX() + Robot.defaultRobotRadius - this.radius;
			int ycenter = topleft.getY() + Robot.defaultRobotRadius - this.radius;
			this.setPosition(new Position(xcenter, ycenter));
		}
	}
}

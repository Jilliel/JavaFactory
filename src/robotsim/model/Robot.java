package robotsim.model;
import robotsim.shape.Shape;

public class Robot extends Component{
	
	private String name;
	private double speed;
	
	public Robot(double x, double y, Shape shape, String name, double speed) {
		super(x, y, shape);
		this.name = name;
		this.speed = speed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpeed() {
		return speed;
	}
	
	public String getName() {
		return name;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Nom: " + name + " / Vitesse: " + speed + "km/h.";
	}
	
}

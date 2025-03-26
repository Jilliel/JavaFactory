package robotsim.model;
import robotsim.shape.Circle;

public class Robot extends Component{
	
	private String name;
	private double speed;
	
	public double getSpeed() {
		return speed;
	}
	
	public String getName() {
		return name;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Robot(String name, double x, double y, double radius) {
		super(x, y, new Circle(radius));
		this.name = name;
		this.speed = 0;
	}

	@Override
	public String toString() {
		return "Nom: " + name + " / Vitesse: " + speed + "km/h.";
	}
	
}

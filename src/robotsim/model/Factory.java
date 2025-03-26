package robotsim.model;
import java.util.ArrayList;
import robotsim.shape.Rectangle;

public class Factory extends Component{
	
	private String name;
	private ArrayList<Robot> robots;
	
	public Factory(String name, double x, double y, double width, double height) {
		super(x, y, new Rectangle(width, height));
		this.name = name;
		this.robots = new ArrayList<Robot>();
	}
	
	private boolean checkRobotName(String name) {
		for (int i=0; i < this.robots.size(); i++) {
			if (name == this.robots.get(i).getName()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addRobot(String name, double x, double y) {
		if (this.checkRobotName(name)) {
			Robot robot = new Robot(name, x, y, );
			this.robots.add(robot);
			return true;
		} else {
			return false;
		}
	}
	
	public void printToConsole() {
		System.out.println("Factory Name: " + this.name);
		for (int i=0; i < this.robots.size(); i++) {
			System.out.println(this.robots.get(i));
		}
	}

}

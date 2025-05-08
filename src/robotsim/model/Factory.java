package robotsim.model;
import java.util.ArrayList;
import shapes.Rectangle;

public class Factory extends Component{
	
	private String name;
	private ArrayList<Robot> robots;
	
	public Factory(String name, double x, double y, int width, int height) {
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
	
	public boolean addRobot(Robot robot) {
		if (this.checkRobotName(robot.getName())) {
			this.robots.add(robot);
			return true;
		} else {
			return false;
		}
	}

}

package robotsim.model;
import shapes.Rectangle;
import robotsim.model.Factory;
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

public class ChargingStation extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090929L;
	private boolean busy;
	private int height;
	private int width;
	
	public ChargingStation(Position position, int width, int height, String name, Factory factory) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
		this.busy = false;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(this.width, this.height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void behave() {
		boolean busy = false;
		for (Robot robot : this.getFactory().getRobots()) {
			int x = robot.getxCoordinate();
			int y = robot.getyCoordinate();
			if ( (x >= this.getxCoordinate() && x < this.getxCoordinate() + this.getWidth()) 
					&& (y >= this.getyCoordinate() && y < this.getyCoordinate() + this.getHeight())) {
				robot.charge();
				busy = true;
			}
		}
		this.busy = busy;
	}

}

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
	
	public ChargingStation(int x, int y, int width, int height, String name, Factory factory, boolean busy) {
		super(x, y, name, factory);
		this.width = width;
		this.height = height;
		this.busy = busy;
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

}

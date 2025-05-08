package robotsim.model;
import shapes.Rectangle;

public class ChargingStation extends Component{
	private boolean busy;
	
	public ChargingStation(int x, int y, int width, int height, String name, boolean busy) {
		super(x, y, new Rectangle(width, height), name);
		this.busy = busy;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

}

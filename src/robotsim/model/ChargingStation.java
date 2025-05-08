package robotsim.model;
import shapes.Rectangle;

public class ChargingStation extends Component{
	private boolean busy;
	
	public ChargingStation(double x, double y, int width, int height, boolean busy) {
		super(x, y, new Rectangle(width, height));
		this.busy = busy;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

}

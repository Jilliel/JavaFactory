package robotsim.model;

import robotsim.shape.Shape;

public class ChargingStation extends Component{
	private boolean busy;
	
	public ChargingStation(double x, double y, Shape shape, boolean busy) {
		super(x, y, shape);
		this.busy = busy;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

}

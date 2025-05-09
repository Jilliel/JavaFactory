package robotsim.model;
import shapes.Rectangle;
import robotsim.model.Factory;
import java.io.Serializable;

public class ChargingStation extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090929L;
	private boolean busy;
	
	public ChargingStation(int x, int y, int width, int height, String name, Factory factory, boolean busy) {
		super(x, y, new Rectangle(width, height), name, factory);
		this.busy = busy;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

}

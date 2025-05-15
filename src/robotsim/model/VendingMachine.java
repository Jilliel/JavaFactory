package robotsim.model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStroke;
import shapes.ComponentStyle;
import shapes.Rectangle;

public class VendingMachine extends Component implements Serializable{

	private static final long serialVersionUID = 202505151711L;
	private int width;
	private int height;
	private static final int waitTime = 10;
	private int timeLeft;
	
	public VendingMachine(Position position, int width, int height, String name, Factory factory) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
	}
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(width, height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		int number = (int) (255 * ((float) this.timeLeft/(float) VendingMachine.waitTime));
		return new ComponentStyle(null, new ComponentStroke(new ComponentColor(number, number, 0), 5, null));
	}
	
	private boolean checkAndRemoveWasher() {
		// Will check if a Washer is in the zone of the VendingMachine, and remove it if so
		for (Room room : this.getFactory().getRooms()) {
			for (ProductionArea area : room.getAreas()) {
				for (Washer washer : area.getMaterials()) {
					int x = washer.getxCoordinate();
					int y = washer.getyCoordinate();
					if ( (x >= this.getxCoordinate() && x < this.getxCoordinate() + this.getWidth()) 
							&& (y >= this.getyCoordinate() && y < this.getyCoordinate() + this.getHeight())
							&& (!washer.isPicked())) {
						area.removeWasher(washer);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void behave() {
		if (this.timeLeft == 0) {
			if (checkAndRemoveWasher()) {
				this.timeLeft = VendingMachine.waitTime;
			}
		}
		else {
			this.timeLeft = this.timeLeft - 1;
		}
	}
	
	public Position getDeliveryPoint() {
		return new Position(this.getxCoordinate() + (this.getWidth()/2), this.getyCoordinate() + (this.getHeight())/2);
	}

}

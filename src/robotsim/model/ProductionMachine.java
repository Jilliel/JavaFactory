package robotsim.model;
import shapes.ComponentColor;
import shapes.ComponentStroke;
import shapes.ComponentStyle;
import shapes.Rectangle;
import robotsim.model.Factory;
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

public class ProductionMachine extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090922L;
	private int width;
	private int height;
	private static final int washerRadius = 5;
	private static final float washerWeight = 1;
	private static final int timeToProduce = 200;
	private int timeLeft;
	
	public ProductionMachine(Position position, int width, int height, String name, Factory factory) {
		super(position, name, factory);
		this.width = width;
		this.height = height;
		this.timeLeft = ProductionMachine.timeToProduce;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(width, height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		int number = (int) (255 * ((float) this.timeLeft/(float) ProductionMachine.timeToProduce));
		return new ComponentStyle(null, new ComponentStroke(new ComponentColor(0, 255-number, 255-number), 5, null));
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
		this.getFactory().notifyObservers();
	}

	public static int getWasherRadius() {
		return washerRadius;
	}

	public static int getTimeToProduce() {
		return timeToProduce;
	}

	public static float getWasherWeight() {
		return washerWeight;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
	
}

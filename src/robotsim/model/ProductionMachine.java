package robotsim.model;
import shapes.Rectangle;
import robotsim.model.Factory;
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

public class ProductionMachine extends Component implements Serializable{
	
	private static final long serialVersionUID = 202505090922L;
	private int width;
	private int height;
	
	public ProductionMachine(int x, int y, int width, int height, String name, Factory factory) {
		super(x, y, name, factory);
		this.width = width;
		this.height = height;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(width, height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

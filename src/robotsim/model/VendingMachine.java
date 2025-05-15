package robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import shapes.ComponentColor;
import shapes.ComponentStroke;
import shapes.ComponentStyle;
import shapes.Rectangle;

public class VendingMachine extends Component {

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

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle(width, height);
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return new ComponentStyle(null, new ComponentStroke(new ComponentColor(100, 100, 100), 5, null));
	}

}

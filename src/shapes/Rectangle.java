package shapes;
import fr.tp.inf112.projects.canvas.model.RectangleShape;

public class Rectangle implements RectangleShape {
	private int width;
	private int height;
	
	public Rectangle(int width, int height) {
		this.height = height;
		this.width = width;
	}
	
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
}

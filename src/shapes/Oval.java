package shapes;
import fr.tp.inf112.projects.canvas.model.OvalShape;

public class Oval implements OvalShape {
	private int horizontal_radius;
	private int vertical_radius;
	
	public Oval(int horizontal_radius, int vertical_radius) {
		this.horizontal_radius = horizontal_radius;
		this.vertical_radius = vertical_radius;
	}
	
	
	public int getHorizontal_radius() {
		return horizontal_radius;
	}


	public void setHorizontal_radius(int horizontal_radius) {
		this.horizontal_radius = horizontal_radius;
	}


	public int getVertical_radius() {
		return vertical_radius;
	}


	public void setVertical_radius(int vertical_radius) {
		this.vertical_radius = vertical_radius;
	}


	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 2*this.horizontal_radius;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 2*this.vertical_radius;
	}

}

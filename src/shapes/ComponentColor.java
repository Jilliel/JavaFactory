package shapes;

import fr.tp.inf112.projects.canvas.model.Color;

public class ComponentColor implements Color {

	private int red;
	private int green;
	private int blue;
	
	
	public ComponentColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	@Override
	public int getRedComponent() {
		// TODO Auto-generated method stub
		return this.red;
	}

	@Override
	public int getGreenComponent() {
		// TODO Auto-generated method stub
		return this.green;
	}

	@Override
	public int getBlueComponent() {
		// TODO Auto-generated method stub
		return this.blue;
	}

}

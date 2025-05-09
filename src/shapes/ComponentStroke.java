package shapes;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

public class ComponentStroke implements Stroke{

	private Color color;
	private float thickness;
	private float[] dashPattern;

	public ComponentStroke(Color color, float thickness, float[] dashPattern) {
		this.color = color;
		this.thickness = thickness;
		this.dashPattern = dashPattern;
	}
	
	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public float getThickness() {
		// TODO Auto-generated method stub
		return this.thickness;
	}

	@Override
	public float[] getDashPattern() {
		// TODO Auto-generated method stub
		return this.dashPattern;
	}

}

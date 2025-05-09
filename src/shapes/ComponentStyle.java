package shapes;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;
import fr.tp.inf112.projects.canvas.model.Style;

public class ComponentStyle implements Style{
	
	private Color color;
	private Stroke stroke;
	
	public ComponentStyle(Color color, Stroke stroke) {
		this.color = color;
		this.stroke = stroke;
	}
	
	@Override
	public Color getBackgroundColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public Stroke getStroke() {
		// TODO Auto-generated method stub
		return this.stroke;
	}

}

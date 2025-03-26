package robotsim.model;
import java.util.ArrayList;

import robotsim.shape.Shape;

public class ProductionArea extends Component {
	
	private ArrayList<Washer> materials;

	public ProductionArea(double x, double y, Shape shape, ArrayList<Washer> materials) {
		super(x, y, shape);
		this.materials = materials;
	}

	public ArrayList<Washer> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Washer> materials) {
		this.materials = materials;
	}
	
}

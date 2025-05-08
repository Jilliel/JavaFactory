package robotsim.model;
import java.util.ArrayList;
import shapes.Rectangle;

public class ProductionArea extends Component {
	
	private ArrayList<Washer> materials;

	public ProductionArea(double x, double y, int width, int height, ArrayList<Washer> materials) {
		super(x, y, new Rectangle(width, height));
		this.materials = materials;
	}

	public ArrayList<Washer> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Washer> materials) {
		this.materials = materials;
	}
	
	public void addMetrial(Washer washer) {
		this.materials.add(washer);
	}
}
